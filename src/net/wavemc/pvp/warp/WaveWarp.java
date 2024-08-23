package net.wavemc.pvp.warp;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.file.WaveFile;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.Habilidade2;
import net.wavemc.warp.provider.ArenaBuild;
import net.wavemc.warp.provider.Duels;
import net.wavemc.warp.provider.Gladiator;
import net.wavemc.warp.provider.Lobby2;
import net.wavemc.warp.provider.OneVsOne;
import net.wavemc.warp.provider.Spawn;
import net.wavemc.warp.provider.Sumo;

public enum WaveWarp {

	SPAWN("Spawn", new Spawn(),new ItemStack( Material.AIR)),

	LOBBY("Lobby", new Lobby2(),new ItemStack( Material.AIR)),
	GLADIATOR("Gladiator", new Gladwa(),new ItemStack( Material.AIR)),
	
	ARENABUILD("ArenaBuild", new ArenaBuild(),new ItemStack( Material.AIR)),
	FPS("FPS", new net.wavemc.warp.provider.FPS(),new  ItemStack( Material.AIR)),
    LAVACHALLENGE("LavaChallenge", new LavaChallenge(),  new ItemStack( Material.AIR)),
	DUELS("Duels", new Duels(), new ItemStack(Material.BLAZE_ROD));
	
	static {
		getWarps().forEach(warp -> 
			Bukkit.getPluginManager().registerEvents(warp.getHandler(), WavePvP.getInstance())
		);
	}
	
	Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
	File file = new File(path1.toAbsolutePath().toString());
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
	private final String name;
	private final WarpHandle handler;
	private final ItemStack icon;
	private final Set<String> players;
	
	public static List<WaveWarp> getWarps() {
		return Arrays.asList(values());
	}
	
	public static Optional<WaveWarp> findWarp(String warpName) {
		return getWarps().stream().filter(
				warp -> warp.getName().equalsIgnoreCase(warpName)
		).findFirst();
	}

	public static void removeHandle(String username) {
        getWarps().stream().forEach(warp -> warp.players.remove(username));
        Bukkit.getConsoleSender().sendMessage("[DEBUG] " + "Removendo " + username + " das warps");
        }
	
	WaveWarp(String name, WarpHandle handle, ItemStack icon) {
		this.name = name;
		this.handler = handle;
		this.icon = icon;
		this.players = new LinkedHashSet<>();
	}

	public void send(Player player) {
		send(player, false);
	}
	
	public void send(Player player, boolean silent) {
		
	if (yaml.getString("Mundo-" + name.toLowerCase()) == null) {
		Bukkit.getConsoleSender().sendMessage("Mundo-" + name.toLowerCase());
		player.sendMessage(ChatColor.RED + "O Local " + name + " não está setado.");
		return;
	}

		getWarps().stream().filter(
				warp -> warp != this && warp.players.contains(player.getName())
		).forEach(warp -> warp.players.remove(player.getName()));

		players.add(player.getName());
		handler.execute(player);
		if (Habilidade.ContainsAbility(player)) {
			Habilidade.removeAbility(player);
			}
		if (Habilidade2.ContainsAbility(player)) {
			Habilidade2.removeAbility(player);
			}
		
        
		Location l = new Location(Bukkit.getWorld(yaml.getString("Mundo-" + name.toLowerCase())), yaml.getInt("X-" +name.toLowerCase()) , yaml.getInt("Y-" +name.toLowerCase()), yaml.getInt("Z-" +name.toLowerCase()));
		l.setPitch(yaml.getInt("PITCH-" +name.toLowerCase()));
		l.setYaw(yaml.getInt("YAW-" +name.toLowerCase()));	
		player.teleport(l);
		Bukkit.getConsoleSender().sendMessage(player.getName()+ " se teleportou para a warp " + name.toLowerCase());
		Bukkit.getConsoleSender().sendMessage("Mundo: " + yaml.getString("Mundo-" + name.toLowerCase()));
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		WavePvP.getInstance().getScoreboardBuilder().build(player);
	}
	
	public String getName() {
		return name;
	}
	
	public int getPlayerCount() {
		return players.size();
	}
	
	
    

	
	
	public WarpHandle getHandler() {
		return handler;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
	
	public boolean hasPlayer(String username) {
		return players.contains(username);
	}
}
	
