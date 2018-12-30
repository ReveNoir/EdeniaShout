package edenia.dragon.shout.listener;

import edenia.dragon.shout.Edenia;
import edenia.dragon.shout.configuration.ConfigurationManager;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

public class Learn {

	private Edenia plugin;

	public Learn(Edenia plugin) {
		this.plugin = plugin;
	}

	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join event){
		Player p = event.getTargetEntity();
		String uuid = String.valueOf(p.getProfile().getUniqueId());
		
		ConfigurationManager.getInstance().editConfig(p.getName(), uuid);
	}
	
	@Listener
	public void onPlayerInteract(InteractBlockEvent.Secondary event){
		Player p = (Player) event.getSource();
		BlockSnapshot b = event.getTargetBlock();
		
		if((p.hasPermission("eden.op")) && (b.getState().getType() == BlockTypes.BOOKSHELF) 
				&& (p.getItemInHand(HandTypes.MAIN_HAND).get().getType() == ItemTypes.BOOK)){
			p.sendMessage(Text.of("Good"));

			Inventory inventory = Inventory.builder()
					.property("title", new InventoryTitle(Text.of("title")))
					.property("inventorydimension", new InventoryDimension(9, 5))
					.build(this.plugin);

			p.openInventory(inventory);
					
		}else{p.sendMessage(Text.of("Bad"));}
	}
	
}
