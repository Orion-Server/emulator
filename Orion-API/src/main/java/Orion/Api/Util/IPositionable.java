package Orion.Api.Util;

import Orion.Api.Server.Game.Util.Position;

public interface IPositionable {
    Position getPosition();

    void setPosition(Position position);
}
