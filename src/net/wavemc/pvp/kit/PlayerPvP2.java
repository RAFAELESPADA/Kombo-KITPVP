package net.wavemc.pvp.kit;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

public class PlayerPvP2 {
	
	private final String name;
	private WaveKit2 kit2;
	
	public PlayerPvP2(String name, WaveKit2 kit2) {
		this.name = name;
		this.kit2 = kit2;
	}
	
	public List<WaveKit2> getAvailablekit2s() {
		return WaveKit2.getKits().stream().filter(
				kit22 -> kit22.isFree() && kit22.getPrice() != -1 || Bukkit.getPlayer(name) != null 
				&& (Bukkit.getPlayer(name).hasPermission("wave.kit2." + kit22.toString().toLowerCase())
						|| Bukkit.getPlayer(name).hasPermission("wave.kit2.*"))
		).collect(Collectors.toList());
	}
	
	public void removekit2() {
		this.kit2 = null;
	}
	public String getName() {
		return name;
	}
	public boolean haskit2() {
		return kit2 != null && kit2 != WaveKit2.PVP;
	}
	
	public boolean haskit2(WaveKit2 kit2) {
		return haskit2() && this.kit2.equals(kit2) && kit2 != WaveKit2.PVP;
	}
	
	public boolean haskit2(KitHandler2 handler2) {
		return haskit2() && this.kit2.getHandler().equals(handler2);
	}
	
	public WaveKit2 getkit2() {
		if (kit2 != null) {
		return kit2;
		}
		return WaveKit2.PVP;
	}
	
	public void setkit2(WaveKit2 kit2) {
		this.kit2 = kit2;
	}
}
