package xyz.zcraft.osu.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Represents a score returned by the osu! API.
 * This model is compatible with X-Api-Version 20250410
 */
@Data
public class Score {
    @SerializedName("classic_total_score")
    public Long classicTotalScore;

    public Boolean preserve;

    public Boolean processed;

    public Boolean ranked;

    @SerializedName("maximum_statistics")
    public ScoreStatistics maximumStatistics;

    public List<Mod> mods;

    public ScoreStatistics statistics;

    @SerializedName("total_score_without_mods")
    public Long totalScoreWithoutMods;

    @SerializedName("beatmap_id")
    public Long beatmapId;

    @SerializedName("best_id")
    public Long bestId;

    public Long id;

    public String rank;

    public String type;

    @SerializedName("user_id")
    public Long userId;

    public Double accuracy;

    @SerializedName("build_id")
    public Long buildId;

    @SerializedName("ended_at")
    public String endedAt;

    @SerializedName("has_replay")
    public Boolean hasReplay;

    @SerializedName("is_perfect_combo")
    public Boolean isPerfectCombo;

    @SerializedName("legacy_perfect")
    public Boolean legacyPerfect;

    @SerializedName("legacy_score_id")
    public Long legacyScoreId;

    @SerializedName("legacy_total_score")
    public Long legacyTotalScore;

    @SerializedName("max_combo")
    public Long maxCombo;

    public Boolean passed;

    public Double pp;

    @SerializedName("ruleset_id")
    public Long rulesetId;

    @SerializedName("started_at")
    public String startedAt;

    @SerializedName("total_score")
    public Long totalScore;

    @SerializedName("replay")
    public Boolean replay;

    @SerializedName("current_user_attributes")
    public Object currentUserAttributes;

    public BeatmapExtended beatmap;

    public Beatmapset beatmapset;

    @SerializedName("rank_global")
    public Long rankGlobal;

    @SerializedName("user")
    public User user;

    @Deprecated
    @SerializedName("created_at")
    public String createdAt;

    @Deprecated
    @SerializedName("playlist_item_id")
    public Long playlistItemId;

    @Deprecated
    @SerializedName("room_id")
    public Long roomId;

    @Deprecated
    public Long score;

    /**
     * Only provided in the get user best scores endpoint
     */
    public Weight weight;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(id, score.id);
    }

    public static class ScoreStatistics extends HashMap<String, Long> {
    }

    @Data
    public static class Weight {
        private Double percentage;
        private Double pp;
    }
}
