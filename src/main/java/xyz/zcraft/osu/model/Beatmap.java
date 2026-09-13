package xyz.zcraft.osu.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Beatmap {
    @SerializedName("beatmapset_id")
    public Long beatmapsetId;

    @SerializedName("difficulty_rating")
    public Double difficultyRating;

    public Long id;

    public String mode;

    public String status;

    @SerializedName("total_length")
    public Long totalLength;

    @SerializedName("user_id")
    public Long userId;

    public String version;

    @SerializedName("top_tag_ids")
    public List<UserTagId> topUserTagIds;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Beatmap score = (Beatmap) o;
        return Objects.equals(id, score.id);
    }

    @Data
    public static class Covers {
        public String cover;

        @SerializedName("cover@2x")
        public String cover2x;

        public String card;

        @SerializedName("card@2x")
        public String card2x;

        public String list;

        @SerializedName("list@2x")
        public String list2x;

        public String slimcover;

        @SerializedName("slimcover@2x")
        public String slimcover2x;
    }

    @Data
    public static class UserTagId {
        @SerializedName("tag_id")
        public int tagId;
        public int count;
    }
}
