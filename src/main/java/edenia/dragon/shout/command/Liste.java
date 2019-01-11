package edenia.dragon.shout.command;

import edenia.dragon.shout.utils.Items;
import edenia.dragon.shout.utils.Shout;
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
			String arg1 = args.getOne("arg1").get().toString();
			String arg2 = args.getOne("arg2").get().toString();

			if(!arg1.equalsIgnoreCase("�") && (arg1.equalsIgnoreCase("give"))
					&& (player.hasPermission("eden.op"))){
				for (Items i : Items.values()){
					if(arg2.equalsIgnoreCase(i.name)){
						i.giveItem(player);
						break;
					}
				}
			}else{
				player.sendMessage(Text.of("  Liste des Cris connu"));
				player.sendMessage(Text.of("----------------------"));
			
				if(player.hasPermission("eden.op")){
					for(Shout s : Shout.values()){
						player.sendMessage(Text.of(TextColors.DARK_AQUA, s.name, " - ", s.m1, " ", s.m2, " ", s.m3));
					}
				}
				player.sendMessage(Text.of("----------------------"));
			}
			
		return CommandResult.success();
	}

}
