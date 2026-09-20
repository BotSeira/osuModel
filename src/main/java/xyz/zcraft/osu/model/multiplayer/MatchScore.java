package xyz.zcraft.osu.model.multiplayer;

import lombok.Getter;
import lombok.Setter;
import xyz.zcraft.osu.model.Score;

@Getter
@Setter
public class MatchScore extends Score {
    private Info match;
    private String team;

    public record Info(int slot, String team, boolean pass) {
    }
}
