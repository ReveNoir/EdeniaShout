package edenia.dragon.shout;

import java.io.File;
import java.util.logging.Logger;

import edenia.dragon.shout.command.Command;
import edenia.dragon.shout.configuration.ConfigurationManager;
import edenia.dragon.shout.command.Liste;
import edenia.dragon.shout.listener.Learn;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "edenshout", name = "Dragon Shout", version = "1.0")
public class Edenia{

    @Inject
    @DefaultConfig(sharedRoot = true)
    private File configFile;

    @Inject
    @DefaultConfig(sharedRoot = true)
    ConfigurationLoader<CommentedConfigurationNode> configManager;

    @Inject
    Logger logger;

    public Logger getLogger(){return logger;}

    @Listener
    public void onServersStarting(GameStartingServerEvent event){
        logger.info("Edenia Dragon Shout Start !");

        ConfigurationManager.getInstance().setup(configFile, configManager);
        Sponge.getEventManager().registerListeners(this, new Learn(this));

        CommandSpec ds = CommandSpec.builder()
                .description(Text.of("- /ds : Affiche la liste des Cris connu"))
                .executor(new Liste())
                .build();
        CommandSpec cmd = CommandSpec.builder()
                .description(Text.of(": Vous permet de lancer un Cri"))
                .arguments(GenericArguments.optionalWeak(GenericArguments.string(Text.of("mot1")), "�"),
                        GenericArguments.optionalWeak(GenericArguments.string(Text.of("mot2")), "�"),
                        GenericArguments.optionalWeak(GenericArguments.string(Text.of("mot3")), "�"))
                .executor(new Command())
                .build();

        Sponge.getCommandManager().register(this, ds, "dragonshout", "ds");
        Sponge.getCommandManager().register(this, cmd, "shout");
    }

    @Listener
    public void onServerStop(GameStoppedServerEvent event) {logger.info("Edenia Dragon Shout Stop !");}

    public Edenia getPlugin() { return this; }
}
