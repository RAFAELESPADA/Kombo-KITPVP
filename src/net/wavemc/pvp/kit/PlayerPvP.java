package net.wavemc.pvp.kit;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

public class PlayerPvP {
	
	private final String name;
	private WaveKit kit;
	
	public PlayerPvP(String name, WaveKit kit) {
		this.name = name;
		this.kit = kit;
	}
	
	public List<WaveKit> getAvailableKits() {
		return WaveKit.getKits().stream().filter(
				kit -> kit.isFree() && kit.getPrice() != -1 || Bukkit.getPlayer(name) != null 
				&& (Bukkit.getPlayer(name).hasPermission("wave.kit." + kit.toString().toLowerCase())
						|| Bukkit.getPlayer(name).hasPermission("wave.kit.*"))
		).collect(Collectors.toList());
	}
	public List<WaveKit> getAvailableKits2() {
		return WaveKit.getKits();
	}
	
	public void removeKit() {
		this.kit = null;
	}

	public boolean hasKit() {
		return kit != null && kit != WaveKit.NENHUM2;
	}
	
	public boolean hasKit(WaveKit kit) {
		return hasKit() && this.kit.equals(kit) && kit != WaveKit.NENHUM2;
	}
	
	public boolean hasKit(KitHandler handler) {
		return hasKit() && this.kit.getHandler().equals(handler);
	}
	
	public String getName() {
		return name;
	}
	
	public WaveKit getKit() {
		if (kit != null) {
		return kit;
		}
		return WaveKit.NENHUM2;
	}
	
	public void setKit(WaveKit kit) {
		this.kit = kit;
	}
}
