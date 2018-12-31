package edenia.dragon.shout.listener;

import edenia.dragon.shout.Edenia;
import edenia.dragon.shout.configuration.ConfigurationManager;
import edenia.dragon.shout.utils.Shout;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.*;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

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
		ItemStack cri = ItemStack.builder()
				.itemType(ItemTypes.WOOL)
				.build();
		
		if((p.hasPermission("eden.op")) && (b.getState().getType() == BlockTypes.BOOKSHELF) 
				&& (p.getItemInHand(HandTypes.MAIN_HAND).get().getType() == ItemTypes.BOOK)){

			Inventory inventory = Inventory.builder()
					.of(InventoryArchetypes.CHEST)
					.property("inventorytitle", new InventoryTitle(Text.of("Cris")))
					.property("inventorydimension", new InventoryDimension(9, 3))
					.build(this.plugin);
			p.openInventory(inventory);

			int l = 0, c = 0;
			for (Shout s : Shout.values()){
				cri.offer(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, s.name));

				if(c == 9){l++; c = 0;}
				inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(c, l))).set(cri);
				c++;
			}
		}
	}

	@Listener
	public void onClickInventory(ClickInventoryEvent event){
		Player p = (Player) event.getSource();
		Inventory inv = event.getTargetInventory();
		if(inv.getName().get().equalsIgnoreCase("Cris")){
			event.setCancelled(true);
			p.closeInventory();
		}
	}
}
