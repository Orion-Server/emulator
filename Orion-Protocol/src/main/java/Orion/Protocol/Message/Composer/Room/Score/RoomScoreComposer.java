package Orion.Protocol.Message.Composer.Room.Score;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomScoreComposer extends MessageComposer {
    public RoomScoreComposer(final int score, final boolean habboCanVote) {
        super(ComposerHeaders.RoomScoreComposer);

        appendInt(score);
        appendBoolean(habboCanVote);
    }
}
