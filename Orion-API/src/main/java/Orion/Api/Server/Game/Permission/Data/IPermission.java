package Orion.Api.Server.Game.Permission.Data;

import Orion.Api.Server.Game.Permission.Enums.PermissionType;
import Orion.Api.Util.IFillable;
import gnu.trove.map.hash.THashMap;

public interface IPermission extends IFillable {
    int getId();

    String getName();

    String getBadgeCode();

    int getLevel();

    int getRoomEffect();

    boolean isShouldLogCommands();

    String getPrefix();

    String getPrefixColor();

    int getAutoCreditsAmount();

    int getAutoDucketsAmount();

    int getAutoGotwAmount();

    int getAutoPointsAmount();

    THashMap<String, PermissionType> getCommandPermissions();

    THashMap<String, PermissionType> getAccountPermissions();

    PermissionType getCommandPermission(String command);

    PermissionType getAccountPermission(String permission);
}
