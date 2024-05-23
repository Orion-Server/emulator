package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Util.IFillable;

public interface IHabboData extends IFillable {
    int getId();

    int getRank();

    String getUsername();

    String getEmail();

    String getMotto();

    String getLook();

    String getGender();

    int getCredits();

    int getAccountCreated();

    String getAccountCreatedFormatted();

    String getLastLogin();

    int getLastOnline();

    boolean isOnline();

    String getAuthTicket();

    String getRegisterIp();

    String getCurrentIp();

    String getMachineId();

    int getHomeRoom();
}
