package edenia.dragon.shout.command;

import edenia.dragon.shout.configuration.ConfigurationManager;
import edenia.dragon.shout.utils.Items;
import edenia.dragon.shout.utils.Shout;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class Liste implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
			Player player = (Player) src;
			String uuid = player.getUniqueId().toString();
			String arg1 = args.getOne("arg1").get().toString();
			String arg2 = args.getOne("arg2").get().toString();

			if (!arg1.equalsIgnoreCase("�")){
				if((arg1.equalsIgnoreCase("give")) && (player.hasPermission("eden.op"))){
					for (Items i : Items.values()){
						if(arg2.equalsIgnoreCase(i.name)){
							i.giveItem(player);
							break;
						}
					}
				}else {
					if ((arg1.equalsIgnoreCase("all")) && (player.hasPermission("eden.op"))){
						if (!arg2.equalsIgnoreCase("�")){
							boolean name = false;
							Player player2 = null;
							for (Player p2 : Sponge.getServer().getOnlinePlayers()){
								if (arg2.equalsIgnoreCase(p2.getName())){
									player2 = p2;
									name = true;
								}
							}

							if (name){
								String uuid1 = ConfigurationManager.getInstance().getConfig().getNode("shouts", "Player",
										arg2.toUpperCase()).getString();
								for (Shout s : Shout.values()){
									ConfigurationManager.getInstance().editConfigL(uuid1, s.name, "3");
									player2.sendMessage(Text.of(TextColors.AQUA,"Vous venez d'apprendre",
											TextColors.DARK_AQUA," "+s.name));
								}
							}else {player.sendMessage(Text.of(TextColors.RED,"Le joueur n'existe pas !"));}
						}else {player.sendMessage(Text.of(TextColors.RED,"Vous n'avez pas renseigne le nom du joueur."));}
					}
				}
			}else{
				player.sendMessage(Text.of("  Liste des Cris connu"));
				player.sendMessage(Text.of("----------------------"));
			
				if(player.hasPermission("eden.op")){
					for(Shout s : Shout.values()){
						player.sendMessage(Text.of(TextColors.DARK_AQUA, s.name, " - ", s.m1, " ", s.m2, " ", s.m3));
					}
				}else{
					for (Shout s : Shout.values()){
						if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, s.name)
								.getValue() != null){
							if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, s.name)
									.getString().equalsIgnoreCase("1")){
								player.sendMessage(Text.of(TextColors.DARK_AQUA, s.name, " - ", s.m1));
							}else if(ConfigurationManager.getInstance().getConfig()
									.getNode("shouts", "Learn", uuid, s.name)
									.getString().equalsIgnoreCase("2")){
								player.sendMessage(Text.of(TextColors.DARK_AQUA, s.name, " - ", s.m1, " ", s.m2));
							}else if (ConfigurationManager.getInstance().getConfig()
									.getNode("shouts", "Learn", uuid, s.name)
									.getString().equalsIgnoreCase("3")){
								player.sendMessage(Text.of(TextColors.DARK_AQUA, s.name, " - ", s.m1, " ", s.m2
										, " ", s.m3));
							}
						}
					}
				}
				player.sendMessage(Text.of("----------------------"));
			}
			
		return CommandResult.success();
	}

}
