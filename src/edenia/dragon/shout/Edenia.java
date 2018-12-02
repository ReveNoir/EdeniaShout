package edenia.dragon.shout;


import java.util.logging.Logger;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

@Plugin(id = "edenshout", name = "Dragon Shout", version = "1.0")
public class Edenia{
	
	@Inject
	Logger logger;
	
	public Logger getLogger(){
		return logger;
	}
	
	@Listener
	public void onServersStarting(GameStartingServerEvent event){
		logger.info("Edenia Dragon Shout Start !");
		CommandSpec ds = CommandSpec.builder()
				.description(Text.of("Affiche la liste des Cris connu"))
				.executor(new Liste())
				.build();
		Sponge.getCommandManager().register(this, ds, "dragonshout", "ds");
	}
	
	@Listener
	public void onServerStop(GameStoppedServerEvent event) {
		logger.info("Edenia Dragon Shout Stop !");
	}
}
