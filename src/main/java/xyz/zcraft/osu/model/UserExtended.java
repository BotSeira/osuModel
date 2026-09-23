package xyz.zcraft.osu.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserExtended extends User {
    @SerializedName("cover_url")
    public String coverUrl;

    public String discord;

    @SerializedName("has_supported")
    public Boolean hasSupported;

    public String interests;

    @SerializedName("join_date")
    public String joinDate;

    public String location;

    @SerializedName("max_blocks")
    public Integer maxBlocks;

    @SerializedName("max_friends")
    public Integer maxFriends;

    public String occupation;

    public String playmode;

    public List<String> playstyle;

    @SerializedName("post_count")
    public Integer postCount;

    @SerializedName("profile_hue")
    public Integer profileHue;

    @SerializedName("profile_order")
    public List<String> profileOrder;

    public String title;

    @SerializedName("title_url")
    public String titleUrl;

    public String twitter;

    public String website;

    public Country country;

    public Cover cover;

    public Kudosu kudosu;

    @SerializedName("account_history")
    public List<JsonObject> accountHistory;

    @SerializedName("active_tournament_banner")
    public Object activeTournamentBanner;

    @SerializedName("active_tournament_banners")
    public List<Object> activeTournamentBanners;

    public List<Badge> badges;

    @SerializedName("beatmap_playcounts_count")
    public Integer beatmapPlaycountsCount;

    @SerializedName("comments_count")
    public Integer commentsCount;

    @SerializedName("current_season_stats")
    public SeasonStats currentSeasonStats;

    @SerializedName("daily_challenge_user_stats")
    public DailyChallengeUserStats dailyChallengeUserStats;

    @SerializedName("favourite_beatmapset_count")
    public Integer favouriteBeatmapsetCount;

    @SerializedName("follower_count")
    public Long followerCount;

    @SerializedName("graveyard_beatmapset_count")
    public Integer graveyardBeatmapsetCount;

    public List<Group> groups;

    @SerializedName("guest_beatmapset_count")
    public Integer guestBeatmapsetCount;

    @SerializedName("loved_beatmapset_count")
    public Integer lovedBeatmapsetCount;

    @SerializedName("mapping_follower_count")
    public Integer mappingFollowerCount;

    @SerializedName("matchmaking_stats")
    public List<MatchmakingStat> matchmakingStats;

    @SerializedName("monthly_playcounts")
    public List<MonthlyPlaycount> monthlyPlaycounts;

    @SerializedName("nominated_beatmapset_count")
    public Integer nominatedBeatmapsetCount;

    public Page page;

    @SerializedName("pending_beatmapset_count")
    public Long pendingBeatmapsetCount;

    @SerializedName("previous_usernames")
    public List<String> previousUsernames;

    @SerializedName("rank_highest")
    public RankHighest rankHighest;

    @SerializedName("ranked_beatmapset_count")
    public Integer rankedBeatmapsetCount;

    @SerializedName("replays_watched_counts")
    public List<MonthlyPlaycount> replaysWatchedCounts;

    @SerializedName("scores_best_count")
    public Integer scoresBestCount;

    @SerializedName("scores_first_count")
    public Integer scoresFirstCount;

    @SerializedName("scores_pinned_count")
    public Integer scoresPinnedCount;

    @SerializedName("scores_recent_count")
    public Integer scoresRecentCount;

    public Statistics statistics;

    @SerializedName("support_level")
    public Integer supportLevel;

    public Team team;

    @SerializedName("user_achievements")
    public List<UserAchievement> userAchievements;

    @SerializedName("rank_history")
    public RankHistory rankHistory;

    @SerializedName("ranked_and_approved_beatmapset_count")
    public Integer rankedAndApprovedBeatmapsetCount;

    @SerializedName("unranked_beatmapset_count")
    public Integer unrankedBeatmapsetCount;

    @Deprecated
    @SerializedName("is_restricted")
    public Boolean isRestricted;

    @Data
    public static class Kudosu {
        public Integer total;
        public Integer available;
    }

    @Data
    public static class Country {
        public String code;
        public String name;
    }

    @Data
    public static class RankHighest {
        public Long rank;

        @SerializedName("updated_at")
        public String updatedAt;
    }

    @Data
    public static class Cover {
        @SerializedName("custom_url")
        public String customUrl;

        public String url;

        public Long id;
    }

    @Data
    public static class Badge {
        @SerializedName("awarded_at")
        public String awardedAt;

        public String description;

        @SerializedName("image@2x_url")
        public String image2xUrl;

        @SerializedName("image_url")
        public String imageUrl;

        public String url;
    }

    @Data
    public static class Group {
        public Long id;
        public String identifier;
        public String name;

        @SerializedName("short_name")
        public String shortName;

        public String description;
        public String colour;
    }

    @Data
    public static class MonthlyPlaycount {
        @SerializedName("start_date")
        public String startDate;

        public Long count;
    }

    @Data
    public static class Page {
        public String html;
        public String raw;
    }

    @Data
    public static class Team {
        public Integer id;
        public String name;
        @SerializedName("short_name")
        public String shortName;
        @SerializedName("flag_url")
        public String flagUrl;
    }

    @Data
    public static class Level {
        public Integer current;
        public Integer progress;
    }

    @Data
    public static class GradeCounts {
        public Long ss;
        public Long ssh;
        public Long s;
        public Long sh;
        public Long a;
    }

    @Data
    public static class Rank {
        public Long global;
        public Long country;
    }

    @Data
    public static class UserAchievement {
        @SerializedName("achieved_at")
        public String achievedAt;

        @SerializedName("achievement_id")
        public Long achievementId;
    }

    @Data
    public static class RankHistory {
        public String mode;
        public List<Long> data;
    }

    @Data
    public static class SeasonStats {
        public Division division;

        public Long rank;

        public Season season;

        @SerializedName("total_score")
        public Double totalScore;

        @Data
        public static class Division {
            @SerializedName("colour_tier")
            public String colourTier;

            public Long id;

            @SerializedName("image_url")
            public String imageUrl;

            public String name;

            public Double threshold;
        }

        @Data
        public static class Season {
            @SerializedName("end_date")
            public String endDate;

            public Long id;

            public String name;

            @SerializedName("room_count")
            public Integer roomCount;

            @SerializedName("start_date")
            public String startDate;
        }
    }

    @Data
    public static class DailyChallengeUserStats {
        @SerializedName("daily_streak_best")
        public Integer dailyStreakBest;

        @SerializedName("daily_streak_current")
        public Integer dailyStreakCurrent;

        @SerializedName("last_update")
        public String lastUpdate;

        @SerializedName("last_weekly_streak")
        public String lastWeeklyStreak;

        public Integer playcount;

        @SerializedName("top_10p_placements")
        public Integer top10pPlacements;

        @SerializedName("top_50p_placements")
        public Integer top50pPlacements;

        @SerializedName("user_id")
        public Long userId;

        @SerializedName("weekly_streak_best")
        public Integer weeklyStreakBest;

        @SerializedName("weekly_streak_current")
        public Integer weeklyStreakCurrent;
    }

    @Data
    public static class MatchmakingStat {
        @SerializedName("first_placements")
        public Integer firstPlacements;

        @SerializedName("is_rating_provisional")
        public Boolean isRatingProvisional;

        public Integer plays;

        @SerializedName("pool_id")
        public Long poolId;

        public Long rank;

        public Integer rating;

        @SerializedName("total_points")
        public Integer totalPoints;

        @SerializedName("user_id")
        public Long userId;

        public Pool pool;

        @Data
        public static class Pool {
            public Boolean active;
            public Long id;
            public String name;

            @SerializedName("ruleset_id")
            public Long rulesetId;

            public String type;

            @SerializedName("variant_id")
            public Long variantId;
        }
    }
}
