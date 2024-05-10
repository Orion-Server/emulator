package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Component.IRoomVotesComponent;

import java.util.ArrayList;
import java.util.List;

public class RoomVotesComponent implements IRoomVotesComponent {
    private final List<Integer> votes;

    public RoomVotesComponent() {
        this.votes = new ArrayList<>();
    }

    @Override
    public boolean habboHasVote(IHabbo habbo) {
        return this.votes.contains(habbo.getData().getId());
    }
}
