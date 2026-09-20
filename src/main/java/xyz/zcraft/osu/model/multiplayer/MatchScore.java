package xyz.zcraft.osu.model.multiplayer;

import xyz.zcraft.osu.model.Score;

public class MatchScore extends Score {
    private Info match;

    public record Info(
            int slot, String team, boolean pass
    ){}
}
