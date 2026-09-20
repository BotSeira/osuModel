package xyz.zcraft.osu.model.multiplayer;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import xyz.zcraft.osu.model.BeatmapExtended;
import xyz.zcraft.osu.model.User;

import java.util.List;

public class Match {
    private MatchInfo match;
    private List<MatchEvent> events;
    private List<User> users;

    @SerializedName("latest_event_id")
    private Long latestEventId;

    @SerializedName("current_game_id")
    private Long currentGameId;

    @Data
    public static class MatchInfo {
        private long id;
        private String name;

        @SerializedName("start_time")
        private String startTime;

        @SerializedName("end_time")
        private String endTime;
    }

    @Data
    public static class MatchEvent {
        private long id;
        private String timestamp;
        private Detail detail;
        @SerializedName("user_id")
        private Long userId;
        private MatchGame game;

        public record Detail(String type, String text) {
        }
    }

    @Data
    public static class MatchGame {
        private long id;

        @SerializedName("beatmap_id")
        private long beatmapId;

        @SerializedName("start_time")
        private String startTime;

        @SerializedName("end_time")
        private String endTime;

        private String mode;

        @SerializedName("mode_int")
        private int modeInt;

        private List<String> mods;

        @SerializedName("scoring_type")
        private String scoringType;

        @SerializedName("team_type")
        private String teamType;

        private BeatmapExtended beatmap;
        private List<MatchScore> scores;
    }
}
