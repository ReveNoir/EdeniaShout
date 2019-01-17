package edenia.dragon.shout.command;

import edenia.dragon.shout.configuration.ConfigurationManager;
import edenia.dragon.shout.utils.Shout;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Command implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		Player p = (Player) src;
		String uuid = p.getUniqueId().toString();
		String args1 = args.getOne("mot1").get().toString();
		String args2 = args.getOne("mot2").get().toString();
		String args3 = args.getOne("mot3").get().toString();
		
		//Bloc g�rant le /shout <mot1> <mot2> <mot3> :
		if((!args1.equalsIgnoreCase("�")) && (!args2.equalsIgnoreCase("�"))
				&& (!args3.equalsIgnoreCase("�"))){
			for(Shout s : Shout.values()){
				if((args1.equalsIgnoreCase(s.m1)) && (args2.equalsIgnoreCase(s.m2)) && (args3.equalsIgnoreCase(s.m3))){
					if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, s.name)
							.getValue() != null){
						int power = Integer.parseInt(ConfigurationManager.getInstance().getConfig()
								.getNode("shouts", "Learn", uuid, s.name).getString());
						if(power == 3){
							s.shout(p, 3);
							break;
						}else{p.sendMessage(Text.of(TextColors.RED,"Vous ne connaissez pas ce cri !"));}
					}else{p.sendMessage(Text.of(TextColors.RED,"Vous ne connaissez pas ce cri !"));}
				}
			}
		}else if((!args1.equalsIgnoreCase("�")) && (!args2.equalsIgnoreCase("�"))
				&& (args3.equalsIgnoreCase("�"))){
			for(Shout s : Shout.values()){
				if((args1.equalsIgnoreCase(s.m1)) && (args2.equalsIgnoreCase(s.m2))){
					if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, s.name)
							.getValue() != null){
						int power = Integer.parseInt(ConfigurationManager.getInstance().getConfig()
								.getNode("shouts", "Learn", uuid, s.name).getString());
						if(power >= 2){
							s.shout(p, 2);
							break;
						}else{p.sendMessage(Text.of(TextColors.RED,"Vous ne connaissez pas ce cri !"));}
					}else{p.sendMessage(Text.of(TextColors.RED,"Vous ne connaissez pas ce cri !"));}
				}
			}
		}else if((!args1.equalsIgnoreCase("�")) && (args2.equalsIgnoreCase("�"))
				&& (args3.equalsIgnoreCase("�"))){
			for(Shout s : Shout.values()){
				if(args1.equalsIgnoreCase(s.m1)){
					if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, s.name)
							.getValue() != null){
						int power = Integer.parseInt(ConfigurationManager.getInstance().getConfig()
								.getNode("shouts", "Learn", uuid, s.name).getString());
						if(power >= 1){
							s.shout(p, 1);
							break;
						}else{p.sendMessage(Text.of(TextColors.RED,"Vous ne connaissez pas ce cri !"));}
					}else{p.sendMessage(Text.of(TextColors.RED,"Vous ne connaissez pas ce cri !"));}
				}
			}
		}
		
		return CommandResult.success();
	}

}
