package net.wavemc.pvp.kit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.wavemc.kit2.Anchor;
import net.wavemc.kit2.AntiStomperReal;
import net.wavemc.kit2.Archer;
import net.wavemc.kit2.Boxer;
import net.wavemc.kit2.Fireman;
import net.wavemc.kit2.Fisherman;
import net.wavemc.kit2.GladiatorListener;
import net.wavemc.kit2.Kangaroo;
import net.wavemc.kit2.Monk;
import net.wavemc.kit2.Nenhum;
import net.wavemc.kit2.Ninja;
import net.wavemc.kit2.Phantom;
import net.wavemc.kit2.Poseidon;
import net.wavemc.kit2.PvP;
import net.wavemc.kit2.QuickDropper;
import net.wavemc.kit2.Sight;
import net.wavemc.kit2.Snail;
import net.wavemc.kit2.Stomper;
import net.wavemc.kit2.Switcher;
import net.wavemc.kit2.Thor;
import net.wavemc.kit2.Viper;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.provider.EnderMageReal;
import net.wavemc.kit2.Reaper;
import net.wavemc.pvp.listener.PlayerJoin;


public enum WaveKit2  {
	PVP("Nenhum", 0, 0 , new PvP() , new ItemStack(Material.BARRIER), "Sem habilidades" , 1 , 0),
	ANCHOR("Anchor", 10000, 0, new Anchor() , new ItemStack(Material.ANVIL), "Não receba ou de knockback" , 1, 1),
	ANTISTOMPER("AntiStomper", 8000, 0, new AntiStomperReal() , new ItemStack(Material.DIAMOND_HELMET), "Imune a stompers" , 1, 2),
	NINJA("Ninja", 10000, 0, new Ninja() , new ItemStack(Material.EMERALD), "Teleporte ao ultimo inimigo hitado", 1,3),
	POSEIDON("Poseidon", 4000, 0, new Poseidon() , new ItemStack(Material.WATER_BUCKET), "Forte na água", 1,4),
	ARCHER("Archer", 0, 0, new Archer() , new ItemStack(Material.BOW), "Receba arco e flecha!", 1,5),
	BOXER("Boxer", 15000, 0, new Boxer() , new ItemStack(Material.QUARTZ), "De mais dano e leve menos.", 1,6),
    VIPER("Viper", 10000, 0, new Viper() , new ItemStack(Material.SPIDER_EYE), "De veneno ao hitar", 1,7),
    SIGHT("Sight", 10000, 0, new Sight(), new ItemStack(Material.RED_DYE), "De cegueira ao hitar!", 1,8),
    NEO("Neo", 8000, 0, new net.wavemc.kit2.NEO() , new ItemStack(Material.BARRIER), "Imune a varios kits", 1,9),
	AUTOBOWL("AutoBowl", 2500, 2500, new QuickDropper() , new ItemStack(Material.BOWL), "Estaque potes automaticamente", 1,10),
	KANGAROO("Kangaroo", 0, 0, new Kangaroo() , new ItemStack(Material.FIREWORK_ROCKET), "De doublejumps", 1,11),
	STOMPER("Stomper", 15000, 0, new Stomper() , new ItemStack(Material.IRON_BOOTS), "Esmague seus inimigos.", 1,12),
	THOR("Thor", 2500, 2500, new Thor() , new ItemStack(Material.GOLDEN_AXE), "Invoque raios.", 1,13),
	GLADIATOR("Gladiator", 15000, 0, new GladiatorListener() , new ItemStack(Material.IRON_BARS), "Desafie seu inimigo para 1v1.", 1,14),
	SNAIL("Snail", 10000, 0, new Snail() , new ItemStack(Material.FERMENTED_SPIDER_EYE), "De lentidão a cada hit", 1,15),
    MAGMA("Magma", 10000, 0, new Fireman() , new ItemStack(Material.LAVA_BUCKET), "De fogo a cada hit.", 1,16),
	MONK("Monk", 9000, 0, new Monk() , new ItemStack(Material.BLAZE_ROD), "Bagunçe o inventario do inimigo.", 1,17),
	PHANTOM("Phantom", 9000, 0, new Phantom() , new ItemStack(Material.FEATHER), "Voe pela arena.", 1,18),
	TURTLE("Turtle", 4000, 0, new net.wavemc.kit2.Turtle() , new ItemStack(Material.GOLDEN_HELMET), "Leve menos dano em shift.", 1,19),
	FISHERMAN("Fisherman", 9000, 0, new Fisherman() , new ItemStack(Material.FISHING_ROD), "Puxe jogadores com sua vara de pesca.", 1,20),
	SWITCHER("Switcher", 8000, 0, new Switcher() , new ItemStack(Material.SNOWBALL), "Troque de lugar com o inimigo.",  1,21),
	REAPER("Reaper", 13000, 500, new Reaper(), new ItemStack(Material.WOODEN_HOE), "De wither com sua foice!", 1,22);
	
	private final String name;
	private final String description;
	private final int price;
	private final int pricecash;
	private final KitHandler2 handler2;
	private final ItemStack icon;
	private final int page;

	private final int id;
	private static String nl = System.getProperty("line.separator");
	static {
		getKits().forEach(kit -> 
			Bukkit.getPluginManager().registerEvents(kit.getHandler(), WavePvP.getInstance())
		);
	}
	
	public static List<WaveKit2> getKits() {
		return Arrays.asList(values());
	}
	
	public static Optional<WaveKit2> findKit(String name) {
		return getKits().stream().filter(
				kit -> kit.toString().equalsIgnoreCase(name) 
					|| kit.getName().equalsIgnoreCase(name)
		).findFirst();
	}
	
	WaveKit2(String name, int price, int pricecash, KitHandler2 handler2, ItemStack icon, String description , int page, int id) {
		this.name = name;
		this.price = price;
		this.pricecash = pricecash;
		this.handler2 = handler2;
		this.icon = icon;
		this.description = description;
		this.page = page;
		this.id = id;
	}
	
	public void send(Player player) {
		if (!(player.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(player.getLocation()))) {
			player.sendMessage("§cVocê deve estar na área de spawn para escolher kits");
	      	player.closeInventory();
	  		return;
	  	 }
		
		KitManager2.getPlayer(player.getName()).setkit2(this);	
		player.sendMessage("§b" + name + " selecionado!");
		
		}
		
	public static Optional<WaveKit2> findKit(int ID) {
		return getKits().stream().filter(
				kit -> kit.getID() == ID
				
		).findFirst();
	}
	
	public KitHandler2 getHandler() {
		return handler2;
	}
	 public static WaveKit2 getKitTypeByName(String kitname) {
		 WaveKit2[] values;
	        for (int length = (values = values()).length, i = 0; i < length; ++i) {
	            final WaveKit2 kitType = values[i];
	            if (kitType.name().equalsIgnoreCase(kitname)) {
	                return kitType;
	            }
	        }
	        return null;
	    }

	public boolean isFree() {
		return price == 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}
	public int getPage() {
		return page;
	}
	public int getPriceCash() {
		return pricecash;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
	public String getDescription() {
		return description.replace("&", "§");
	}
}
