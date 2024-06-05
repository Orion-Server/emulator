package Orion.Protocol.Message.Composer.Room.Score;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomScoreComposer extends Composer {
    private final int score;
    private final boolean habboCanVote;

    public RoomScoreComposer(final int score, final boolean habboCanVote) {
        this.score = score;
        this.habboCanVote = habboCanVote;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomScoreComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.score);
        msg.appendBoolean(this.habboCanVote);
    }
}
