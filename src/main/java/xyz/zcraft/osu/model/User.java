package xyz.zcraft.osu.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class User {
    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("default_group")
    private String defaultGroup;

    private long id;

    @SerializedName("is_active")
    private boolean isActive;

    @SerializedName("is_bot")
    private boolean isBot;

    @SerializedName("is_deleted")
    private boolean isDeleted;

    @SerializedName("is_online")
    private boolean isOnline;

    @SerializedName("is_supporter")
    private boolean isSupporter;

    @SerializedName("last_visit")
    private String lastVisit;

    @SerializedName("pm_friends_only")
    private boolean pmFriendsOnly;

    @SerializedName("profile_colour")
    private String profileColour;

    private String username;

    @SerializedName("statistics_rulesets")
    private StatisticsRuleset statisticsRulesets;

    @Data
    public static class StatisticsRuleset {
        private Statistics osu;
        private Statistics taiko;
        private Statistics fruits;
        private Statistics mania;
    }

    @Data
    public static class Statistics {
        public UserExtended.Level level;
        public Double pp;

        @SerializedName("global_rank")
        public Long globalRank;

        @SerializedName("ranked_score")
        public Long rankedScore;

        @SerializedName("hit_accuracy")
        public Double hitAccuracy;

        public Double accuracy;

        @SerializedName("play_count")
        public Long playCount;

        @SerializedName("play_time")
        public Long playTime;

        @SerializedName("total_score")
        public Long totalScore;

        @SerializedName("total_hits")
        public Long totalHits;

        @SerializedName("maximum_combo")
        public Long maximumCombo;

        @SerializedName("replays_watched_by_others")
        public Long replaysWatchedByOthers;

        @SerializedName("is_ranked")
        public Boolean isRanked;

        @SerializedName("grade_counts")
        public UserExtended.GradeCounts gradeCounts;

        public UserExtended.Rank rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User score = (User) o;
        return Objects.equals(id, score.id);
    }
}
