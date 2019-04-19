package edenia.dragon.shout.configuration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.util.TypeTokens;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class ConfigurationManager {
	private static ConfigurationManager instance = new ConfigurationManager();
	
	public static ConfigurationManager getInstance() {return instance;}
	
	private ConfigurationLoader<CommentedConfigurationNode> configLoader;
	private CommentedConfigurationNode config;
	
	public void setup(File configFile, ConfigurationLoader<CommentedConfigurationNode> configLoader) {
		this.configLoader = configLoader;
		
		if (!configFile.exists()){
			
			try{
				configFile.createNewFile();
				loadConfig();
				saveConfig();
			}catch (Exception e) { e.printStackTrace(); }
			
		}else { loadConfig(); }
	}
	
	public CommentedConfigurationNode getConfig() {return config;}
	
	public void saveConfig() {
		try {configLoader.save(config);}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void loadConfig() {
		try {config = configLoader.load();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void editConfigP(String p, String arg){
		loadConfig();
		if(config.getNode("shouts", "Player", p.toUpperCase()).getValue() != arg){
			config.getNode("shouts", "Player", p.toUpperCase()).setValue(arg);
		}
		saveConfig();
		loadConfig();
	}

	public void editConfigL(String uuid, String cri, String arg){
		loadConfig();
		if (config.getNode("shouts", "Learn", uuid, cri).getValue() != arg){
			config.getNode("shouts", "Learn", uuid, cri).setValue(arg);
		}
		saveConfig();
		loadConfig();
	}

	public void editConfigLPos(String uuid, String poscri){
		loadConfig();
		config.getNode("learn", uuid, poscri).setValue(true);
		saveConfig();
		loadConfig();
	}

	public void editConfigM(String cri, String nb, String arg){
		loadConfig();
		if(config.getNode("murs", cri, "Location", nb).getValue() != arg){
			config.getNode("murs", cri, "Location", nb).setValue(arg);
		}
		saveConfig();
		loadConfig();
	}

	public void removeConfigM(String cri, String nb){
		loadConfig();
		config.getNode("murs", cri, "Location", nb).setValue(null);
		saveConfig();
		loadConfig();
	}
}
