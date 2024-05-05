package Orion.Api.Server.Game.Habbo.Provider;

import Orion.Api.Networking.Session.ISession;

public interface IHabboLoginProvider {
    boolean canLogin(ISession session, String authTicket);

    void attemptLogin(ISession session, String authTicket);
}
