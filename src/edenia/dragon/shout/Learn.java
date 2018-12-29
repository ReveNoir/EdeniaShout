package edenia.dragon.shout;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

public class Learn {

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
			Inventory inv = Inventory.builder()
					.of(InventoryArchetypes.DOUBLE_CHEST)
					.property(InventoryDimension.of(9, 5))
					.property(InventoryTitle.of(Text.of("Test")))
					.build(); //Le pb est ici je croi
			p.openInventory(inv);
					
		}else{p.sendMessage(Text.of("Bad"));}
	}
	
}
