package net.wavemc.pvp;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum PlayerGroup {

    DONO("DONO", 0, "Dono", "cornotop.vermelhoescuro", ChatColor.DARK_RED, 1),
    ADMIN("ADMIN", 1, "ADMIN", "cornotop.vermelhoclaro", ChatColor.RED, 2),
    MOD("MOD", 2, "MOD", "cornotop.roxo", ChatColor.DARK_PURPLE, 3),
    TRIAL("TRIAL", 3, "TRIAL", "cornotop.rosa", ChatColor.LIGHT_PURPLE, 4),
    HELPER("HELPER", 4, "HELPER", "cornotop.verde", ChatColor.DARK_GREEN, 5),
   ESTAGIARIO("ESTAGIARIO", 5, "ESTAGIARIO", "cornotop.amarelo", ChatColor.YELLOW, 6),
   YOUTUBER("YOUTUBER", 6, "YT", "cornotop.azulclaro", ChatColor.AQUA, 7),
    BETA("BETA", 7, "BETA", "cornotop.azulescuro", ChatColor.DARK_BLUE, 8),
    BOOSTER("BOOSTER", 8, "Booster", "cornotop.preto", ChatColor.BLACK, 9),
    PRO("PRO", 9, "PRO", "cornotop.dourado", ChatColor.GOLD, 10),
    MVP("MVP", 10, "MVP", "cornotop.azul", ChatColor.BLUE, 11),
    VIP("VIP", 11, "VIP", "cornotop.verdeclaro", ChatColor.GREEN, 12),
    MEMBRO("MEMBRO", 12, "YT", "cornotop.cinza", ChatColor.GRAY, 13);

    private final String name;
    private final String permission;
    private final ChatColor color;
    private final int priority;

    PlayerGroup(final String s, final int n, final String name, final String permission, final ChatColor color, final int priority) {
        this.name = name;
        this.permission = permission;
        this.color = color;
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public String getPermission() {
        return this.permission;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public String getColoredName() {
        return this.getColor() + this.getName();
    }

    public int getPriority() {
        return this.priority;
    }

    public String getBoldColoredName() {
        return this.getColor() + "§l" + this.getName();
    }

    public static PlayerGroup getByName(final String name) {
        for (PlayerGroup group : PlayerGroup.values()) {
            if (group.name().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    public static PlayerGroup getGroup(final Player player) {
        for (PlayerGroup group : PlayerGroup.values()) {
            if (player.hasPermission(group.getPermission())) {
                return group;
            }
        }
        return PlayerGroup.MEMBRO;
    }

    public static String getPlayerNameWithGroup(Player player) {
        PlayerGroup group = getGroup(player);
        String prefix = group.getBoldColoredName().toUpperCase();
        return prefix + group.getColor() + " " + player.getName();
    }

}
