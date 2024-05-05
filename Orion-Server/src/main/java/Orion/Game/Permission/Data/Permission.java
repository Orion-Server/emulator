package Orion.Game.Permission.Data;

import Orion.Api.Server.Game.Permission.Data.IPermission;
import Orion.Api.Server.Game.Permission.Enums.PermissionType;
import Orion.Api.Storage.Result.IConnectionResult;
import gnu.trove.map.hash.THashMap;

import java.sql.ResultSetMetaData;

public class Permission implements IPermission {
    private int id;
    private String name;
    private String badgeCode;
    private int level;
    private int roomEffect;
    private boolean shouldLogCommands;
    private String prefix;
    private String prefixColor;

    private int autoCreditsAmount;
    private int autoDucketsAmount;
    private int autoGotwAmount;
    private int autoPointsAmount;

    private final THashMap<String, PermissionType> commandPermissions;

    private final THashMap<String, PermissionType> accountPermissions;

    public Permission(IConnectionResult result) {
        this.commandPermissions = new THashMap<>();
        this.accountPermissions = new THashMap<>();

        try {
            this.fill(result);
        } catch (Exception e) {
            System.out.println(STR."Error while filling permission: \{e.getMessage()}");
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBadgeCode() {
        return this.badgeCode;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getRoomEffect() {
        return this.roomEffect;
    }

    @Override
    public boolean isShouldLogCommands() {
        return this.shouldLogCommands;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getPrefixColor() {
        return this.prefixColor;
    }

    @Override
    public int getAutoCreditsAmount() {
        return this.autoCreditsAmount;
    }

    @Override
    public int getAutoDucketsAmount() {
        return this.autoDucketsAmount;
    }

    @Override
    public int getAutoGotwAmount() {
        return this.autoGotwAmount;
    }

    @Override
    public int getAutoPointsAmount() {
        return this.autoPointsAmount;
    }

    @Override
    public THashMap<String, PermissionType> getCommandPermissions() {
        return this.commandPermissions;
    }

    @Override
    public THashMap<String, PermissionType> getAccountPermissions() {
        return this.accountPermissions;
    }

    @Override
    public PermissionType getCommandPermission(String command) {
        return this.commandPermissions.get(command);
    }

    @Override
    public PermissionType getAccountPermission(String permission) {
        return this.accountPermissions.get(permission);
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.id = result.getInt("id");
        this.name = result.getString("rank_name");
        this.badgeCode = result.getString("badge");
        this.level = result.getInt("level");
        this.roomEffect = result.getInt("room_effect");
        this.shouldLogCommands = result.getString("log_commands").equals("1");
        this.prefix = result.getString("prefix");
        this.prefixColor = result.getString("prefix_color");

        this.autoCreditsAmount = result.getInt("auto_credits_amount");
        this.autoDucketsAmount = result.getInt("auto_pixels_amount");
        this.autoGotwAmount = result.getInt("auto_gotw_amount");
        this.autoPointsAmount = result.getInt("auto_points_amount");

        final ResultSetMetaData metaData = result.getMetaData();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);

            if(columnName.matches("^(cmd_)|(_cmd)$")) {
                this.commandPermissions.putIfAbsent(
                        columnName.replaceAll("^(cmd_)|(_cmd)$", ""),
                        PermissionType.valueOf(result.getString(columnName))
                );
            }

            if(columnName.matches("^(acc_)|(_acc)$")) {
                this.accountPermissions.putIfAbsent(
                        columnName.replaceAll("^(acc_)|(_acc)$", ""),
                        PermissionType.valueOf(result.getString(columnName))
                );
            }
        }
    }
}
