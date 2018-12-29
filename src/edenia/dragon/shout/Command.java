package edenia.dragon.shout;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

public class Command implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		Player p = (Player) src;
		String args1 = args.getOne("mot1").get().toString();
		String args2 = args.getOne("mot2").get().toString();
		String args3 = args.getOne("mot3").get().toString();
		
		//Bloc gérant le /shout <mot1> <mot2> <mot3> :
		if((args1 != "©") && (args2 != "©") && (args3 != "©")){
			for(Shout s : Shout.values()){
				if((args1.equalsIgnoreCase(s.m1)) && (args2.equalsIgnoreCase(s.m2)) && (args3.equalsIgnoreCase(s.m3))){
					s.shout(p, 3);
					break;
				}
			}
		}else if((args1 != "©") && (args2 != "©") && (args3 == "©")){
			for(Shout s : Shout.values()){
				if((args1.equalsIgnoreCase(s.m1)) && (args2.equalsIgnoreCase(s.m2))){
					s.shout(p, 2);
					break;
				}
			}
		}else if((args1 != "©") && (args2 == "©") && (args3 == "©")){
			for(Shout s : Shout.values()){
				if(args1.equalsIgnoreCase(s.m1)){
					s.shout(p, 1);
					break;
				}
			}
		}
		
		return CommandResult.success();
	}

}
