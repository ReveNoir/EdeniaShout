package edenia.dragon.shout;


import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Color;


public class Liste implements CommandExecutor{
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
			Player player = (Player) src;
			
			for(Shout s : Shout.values()){
				player.sendMessage(Text.of(Color.BLUE + s.name));
			}
			
			
		return CommandResult.success();
	}

}
