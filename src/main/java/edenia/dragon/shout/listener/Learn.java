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
import org.spongepowered.api.item.inventory.property.*;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.HashMap;

public class Learn {

	private Edenia plugin;
	public Learn(Edenia plugin) {
		this.plugin = plugin;
	}
	public String player = "player", mur = "mur";
	private HashMap<Integer, String> select = new HashMap<>();
	private HashMap<Player, BlockSnapshot> loca = new HashMap<>();

	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join event){
		Player p = event.getTargetEntity();
		String uuid = String.valueOf(p.getProfile().getUniqueId());
		
		ConfigurationManager.getInstance().editConfig(player, p.getName(), uuid);
	}

	@Listener
	public void onPlayerInteract(InteractBlockEvent.Secondary event){
		Player p = (Player) event.getSource();
		BlockSnapshot b = event.getTargetBlock();
		
		if((p.hasPermission("eden.op")) && (b.getState().getType() == BlockTypes.BOOKSHELF) 
				&& (p.getItemInHand(HandTypes.MAIN_HAND).get().getType() == ItemTypes.BOOK)){

			Inventory inventory = Inventory.builder()
					.of(InventoryArchetypes.CHEST)
					.property("inventorytitle", new InventoryTitle(Text.of("Cris")))
					.property("inventorydimension", new InventoryDimension(9, 3))
					.build(this.plugin);
			p.openInventory(inventory);
			loca.put(p, b);

			int l = 0, c = 0, sl = 0;
			for (Shout s : Shout.values()){
				ItemStack cri = ItemStack.builder()
						.itemType(ItemTypes.WOOL)
						.add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, s.name))
						.build();

				if(c == 9){l++; c = 0;}
				inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(c, l))).set(cri);
				c++;

				select.put(sl, s.name);
				sl++;
			}
		}
	}

	@Listener
	public void onClickInventory(ClickInventoryEvent event){

		Player p = (Player) event.getSource();
		Inventory inv = event.getTargetInventory();
		SlotIndex slot = event.getTransactions().get(0).getSlot()
				.getInventoryProperty(SlotIndex.class).get();
		if(inv.getName().get().equalsIgnoreCase("Cris")){
			int slot1 = slot.getValue().intValue();
			String cri = select.get(slot1);
			BlockSnapshot b = loca.get(p);
			String loc = b.getLocation().toString();

			ConfigurationManager.getInstance().editConfig(mur, loc, cri);

			event.setCancelled(true);
			p.closeInventory();
		}
	}
}
