package xyz.zcraft.osu.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Beatmapset {
    @SerializedName("anime_cover")
    public Boolean animeCover;

    public String artist;

    @SerializedName("artist_unicode")
    public String artistUnicode;

    public Beatmap.Covers covers;

    public String creator;

    @SerializedName("favourite_count")
    public Long favouriteCount;

    @SerializedName("genre_id")
    public Integer genreId;

    public Object hype;

    public Long id;

    @SerializedName("language_id")
    public Integer languageId;

    public Boolean nsfw;

    public Integer offset;

    @SerializedName("play_count")
    public Long playCount;

    @SerializedName("preview_url")
    public String previewUrl;

    public String source;

    public Boolean spotlight;

    public String status;

    public String title;

    @SerializedName("title_unicode")
    public String titleUnicode;

    @SerializedName("track_id")
    public Long trackId;

    @SerializedName("user_id")
    public Long userId;

    public Boolean video = false;

    public Double bpm;

    @SerializedName("can_be_hyped")
    public Boolean canBeHyped;

    @SerializedName("deleted_at")
    public String deletedAt;

    @SerializedName("discussion_enabled")
    public Boolean discussionEnabled;

    @SerializedName("discussion_locked")
    public Boolean discussionLocked;

    @SerializedName("is_scoreable")
    public Boolean isScoreable;

    @SerializedName("last_updated")
    public String lastUpdated;

    @SerializedName("legacy_thread_url")
    public String legacyThreadUrl;

    @SerializedName("nominations_summary")
    public NominationsSummary nominationsSummary;

    public int ranked;

    @SerializedName("ranked_date")
    public String rankedDate;

    public Double rating;

    public Boolean storyboard = false;

    @SerializedName("submitted_date")
    public String submittedDate;

    public String tags;

    public Availability availability;

    public List<BeatmapExtended> beatmaps;

    public List<BeatmapExtended> converts;

    @SerializedName("current_nominations")
    public List<CurrentNomination> currentNominations;

    public Description description;

    public Label genre;

    public Label language;

    @SerializedName("pack_tags")
    public List<String> packTags;

    public List<Integer> ratings;

    @SerializedName("recent_favourites")
    public List<User> recentFavourites;

    @SerializedName("related_users")
    public List<User> relatedUsers;

    @SerializedName("related_tags")
    public List<UserTag> relatedTags;

    public User user;

    @SerializedName("has_favourited")
    public Boolean hasFavourited;

    @Deprecated
    @SerializedName("current_user_attributes")
    public JsonObject currentUserAttributes;

    @Deprecated
    public List<JsonObject> discussions;

    @Deprecated
    public List<JsonObject> events;

    @Data
    public static class Description {
        public String description;
        public String bbcode;
    }

    @Data
    public static class Label {
        public Integer id;
        public String name;
    }

    @Data
    public static class UserTag {
        public int id;
        public String name;
        @SerializedName("ruleset_id")
        public int rulesetId;
        public String description;
    }

    @Data
    public static class NominationsSummary {
        public int current;

        @SerializedName("eligible_main_rulesets")
        public List<String> eligibleMainRulesets;

        @SerializedName("required_meta")
        public RequiredMeta requiredMeta;

        @Data
        public static class RequiredMeta {
            @SerializedName("main_ruleset")
            public int mainRuleset;

            @SerializedName("non_main_ruleset")
            public int nonMainRuleset;
        }
    }

    @Data
    public static class Availability {
        @SerializedName("download_disabled")
        public Boolean downloadDisabled;

        @SerializedName("more_information")
        public Object moreInformation;
    }

    @Data
    public static class CurrentNomination {
        @SerializedName("beatmapset_id")
        public Long beatmapsetId;

        public List<String> rulesets;

        public boolean reset;

        @SerializedName("user_id")
        public Long userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Beatmapset score = (Beatmapset) o;
        return Objects.equals(id, score.id);
    }
}
