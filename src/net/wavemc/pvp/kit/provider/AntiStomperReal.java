package net.wavemc.pvp.kit.provider;




	import org.bukkit.entity.Player;

import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.KitHandler;

	public class AntiStomperReal extends KitHandler {

	@Override
	public void execute(Player player) {
		super.execute(player);
		
		Habilidade.setAbility(player, "AntiStomper");
	}
	}

