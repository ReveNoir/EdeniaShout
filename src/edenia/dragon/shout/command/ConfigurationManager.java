package edenia.dragon.shout.command;

import java.io.File;
import java.io.IOException;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

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
	
	public void editConfig(String p, String id){
		loadConfig();
		if(config.getNode("shouts", "Player", p).getValue() != id){
			config.getNode("shouts", "Player", p).setValue(id);
		}
		saveConfig();
		loadConfig();
	}
}
