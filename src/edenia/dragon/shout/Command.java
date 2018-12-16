package edenia.dragon.shout;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class Command implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		Player p = (Player) src;
		String args1 = args.getOne("mot1").get().toString();
		
		
		for(Shout s : Shout.values()){
			if(args1.equalsIgnoreCase(s.m1)){
				s.shout(p);
				break;
			}else{p.sendMessage(Text.of("BAD"));}
		}
		
		return CommandResult.success();
	}

}
