package xyz.zcraft.osu.model;

import lombok.Data;

import java.util.Map;

@Data
public class Mod {
    private String acronym;
    private Map<String, Object> settings;

    public int getBit() {
        return switch (getAcronym()) {
            case "NF" -> 1;
            case "EZ" -> 2;
            case "TD" -> 4;
            case "HD" -> 8;
            case "HR" -> 16;
            case "SD" -> 32;
            case "DT" -> 64;
            case "RX" -> 128;
            case "HT" -> 256;
            case "NC" -> 512 | 64;   // NC implies DT
            case "FL" -> 1024;
            case "SO" -> 4096;
            case "AP" -> 8192;
            case "PF" -> 16384 | 32; // PF implies SD
            case "V2" -> 536870912;
            default -> 0;
        };
    }
}
