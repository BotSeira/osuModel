package xyz.zcraft.osu.model;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** Shared lossless command representation of lazer mods. */
public final class ModSettings {
    private static final Set<String> ACRONYMS = Set.of(
            "NF", "EZ", "TD", "HD", "HR", "SD", "DT", "RX", "HT", "NC", "FL", "AT", "SO", "AP", "PF",
            "4K", "5K", "6K", "7K", "8K", "FI", "RD", "CN", "TP", "9K", "CO", "1K", "3K", "2K", "V2",
            "MR", "DC", "DA", "CL", "TC", "BL", "ST", "AC", "AL", "SG", "TR", "WG", "SI", "GR", "DF",
            "WU", "WD", "BR", "AD", "MU", "NS", "MG", "RP", "AS", "FR", "BU", "SY", "DP", "BM", "SV", "SW", "CS", "HO", "NR", "DS", "IN");
    private static final Pattern ATTRIBUTE = Pattern.compile("(?i)(AR|OD|CS|HP)\\s*(?:->|=|:)?\\s*([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+))");

    private ModSettings() {}

    public static List<Mod> parse(String input) {
        if (input == null || input.isBlank() || input.trim().equalsIgnoreCase("NM")) return List.of();
        String text = input.trim();
        if (text.startsWith("+")) text = text.substring(1).trim();
        if (text.equalsIgnoreCase("NM")) return List.of();
        if (text.isEmpty()) throw invalid(input);
        List<Mod> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        int index = 0;
        while (index < text.length()) {
            char c = text.charAt(index);
            if (Character.isWhitespace(c) || c == '+' || c == ',') { index++; continue; }
            if (index + 2 > text.length()) throw invalid(input);
            String acronym = text.substring(index, index + 2).toUpperCase(Locale.ROOT);
            if (!ACRONYMS.contains(acronym) || !seen.add(acronym)) throw invalid(input);
            index += 2;
            Mod mod = new Mod();
            mod.setAcronym(acronym);
            if (index < text.length() && text.charAt(index) == '(') {
                int end = text.indexOf(')', index);
                if (end < 0) throw invalid(input);
                mod.setSettings(parseSettings(acronym, text.substring(index + 1, end)));
                index = end + 1;
            }
            result.add(mod);
        }
        if (result.isEmpty()) throw invalid(input);
        for (Set<String> group : List.of(Set.of("HR", "EZ"), Set.of("DT", "NC", "HT", "DC"), Set.of("NF", "SD", "PF"))) {
            if (group.stream().filter(seen::contains).count() > 1) throw new IllegalArgumentException("Mod 冲突: " + input);
        }
        return result;
    }

    private static Map<String, Object> parseSettings(String acronym, String body) {
        Map<String, Object> values = new LinkedHashMap<>();
        String text = body.trim();
        if (text.isEmpty()) throw invalid(body);
        if (Set.of("DT", "NC", "HT", "DC").contains(acronym) && text.matches("(?i)[0-9]+(?:\\.[0-9]+)?x")) {
            values.put("speed_change", number(text.substring(0, text.length() - 1)));
        } else if (acronym.equals("DA") && ATTRIBUTE.matcher(text).lookingAt()) {
            var matcher = ATTRIBUTE.matcher(text);
            int end = 0;
            while (matcher.find()) {
                if (!text.substring(end, matcher.start()).matches("[\\s,]*")) throw invalid(body);
                put(values, attributeKey(matcher.group(1)), number(matcher.group(2)));
                end = matcher.end();
            }
            if (!text.substring(end).isBlank()) throw invalid(body);
        } else {
            for (String entry : text.split("[,;]", -1)) {
                String[] pair = entry.trim().split("=", 2);
                if (pair.length != 2 || !pair[0].matches("[a-zA-Z_][a-zA-Z_0-9]*") || pair[1].isBlank()) throw invalid(body);
                String key = attributeKey(pair[0]);
                String value = pair[1].trim();
                Object parsed = value.equalsIgnoreCase("true") ? true : value.equalsIgnoreCase("false") ? false
                        : value.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)") ? number(value) : value;
                put(values, key, parsed);
            }
        }
        for (var entry : values.entrySet()) {
            if (Set.of("speed_change", "initial_rate", "final_rate").contains(entry.getKey())
                    && (!(entry.getValue() instanceof Number n) || n.doubleValue() <= 0)) throw invalid(body);
            if (Set.of("approach_rate", "overall_difficulty", "circle_size", "drain_rate").contains(entry.getKey())
                    && (!(entry.getValue() instanceof Number n)
                    || n.doubleValue() < (entry.getKey().equals("approach_rate") ? -10 : 0)
                    || n.doubleValue() > 11)) throw invalid(body);
        }
        return values;
    }

    private static void put(Map<String, Object> values, String key, Object value) {
        if (values.putIfAbsent(key, value) != null) throw new IllegalArgumentException("重复 Mod 参数: " + key);
    }

    private static String attributeKey(String key) {
        return switch (key.toUpperCase(Locale.ROOT)) {
            case "AR" -> "approach_rate";
            case "OD" -> "overall_difficulty";
            case "CS" -> "circle_size";
            case "HP" -> "drain_rate";
            default -> key.toLowerCase(Locale.ROOT);
        };
    }

    private static double number(String value) {
        double result = Double.parseDouble(value);
        if (!Double.isFinite(result)) throw invalid(value);
        return result;
    }

    private static IllegalArgumentException invalid(String value) {
        return new IllegalArgumentException("无效 Mod 或参数: " + value + "，例如 HDDT(1.1x)DA(AR9.5HP5)FL");
    }

    public static String format(List<Mod> mods) {
        return mods == null ? "" : mods.stream().map(ModSettings::format).collect(Collectors.joining());
    }

    public static String format(Mod mod) {
        if (mod.getSettings() == null || mod.getSettings().isEmpty()) return mod.getAcronym();
        if ((Set.of("DT", "NC", "HT", "DC").contains(mod.getAcronym())
                && mod.getSettings().keySet().equals(Set.of("speed_change")))
                || ("DA".equals(mod.getAcronym()) && Set.of("approach_rate", "overall_difficulty", "circle_size", "drain_rate")
                .containsAll(mod.getSettings().keySet()))) {
            String shortSettings = settingsText(mod).replace(" ", "");
            return mod.getAcronym() + (shortSettings.isEmpty() ? "" : "(" + shortSettings + ")");
        }
        String settings = mod.getSettings().entrySet().stream().filter(e -> e.getValue() != null)
                .sorted(Map.Entry.comparingByKey()).map(e -> e.getKey() + "=" + scalar(e.getValue()))
                .collect(Collectors.joining(","));
        return mod.getAcronym() + (settings.isEmpty() ? "" : "(" + settings + ")");
    }

    public static String settingsText(Mod mod) {
        if (mod == null || mod.getSettings() == null) return "";
        return mod.getSettings().entrySet().stream().filter(e -> e.getValue() != null).sorted(Map.Entry.comparingByKey())
                .map(e -> switch (e.getKey()) {
                    case "speed_change" -> scalar(e.getValue()) + "x";
                    case "approach_rate" -> "AR" + scalar(e.getValue());
                    case "overall_difficulty" -> "OD" + scalar(e.getValue());
                    case "circle_size" -> "CS" + scalar(e.getValue());
                    case "drain_rate" -> "HP" + scalar(e.getValue());
                    default -> e.getKey() + "=" + scalar(e.getValue());
                }).collect(Collectors.joining(" "));
    }

    private static String scalar(Object value) {
        return value instanceof Number ? new BigDecimal(value.toString()).stripTrailingZeros().toPlainString() : value.toString();
    }

    public static double clockRate(List<Mod> mods) {
        if (mods == null) return 1;
        for (Mod mod : mods) {
            double fallback = switch (mod.getAcronym()) { case "DT", "NC" -> 1.5; case "HT", "DC" -> .75; default -> 1; };
            if (fallback != 1) return setting(mod, "speed_change", fallback);
        }
        return 1;
    }

    public static double setting(Mod mod, String key, double fallback) {
        Object value = mod.getSettings() == null ? null : mod.getSettings().get(key);
        return value instanceof Number n ? n.doubleValue() : fallback;
    }
}
