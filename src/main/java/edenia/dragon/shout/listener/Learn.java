package edenia.dragon.shout.listener;

import edenia.dragon.shout.Edenia;
import edenia.dragon.shout.configuration.ConfigurationManager;
import edenia.dragon.shout.utils.Items;
import edenia.dragon.shout.utils.Shout;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.type.Exclude;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.*;
import org.spongepowered.api.item.inventory.property.*;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Learn {

	private Edenia plugin;
	private HashMap<Integer, String> select = new HashMap<>();
	private HashMap<Player, BlockSnapshot> loca = new HashMap<>();
	private HashMap<String, String> locCri = new HashMap<>();

	public Learn(Edenia plugin) {
		this.plugin = plugin;
	}

	@Listener
	public  void onServerLoad(GameStartedServerEvent event){
		Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.GREEN, "Chargement des Config"));
		for (Shout s : Shout.values()){
			for(int nb = 1; nb <= 3; nb++){
				String loc = Integer.toString(nb);
				if(ConfigurationManager.getInstance().getConfig().getNode("murs", s.name, "Location", loc)
						.getValue() != null){
					String value = ConfigurationManager.getInstance().getConfig()
							.getNode("murs", s.name, "Location", loc).getValue().toString();
					locCri.put(value, s.name);
				}
			}
		}
		Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.GREEN
				, "Chargement des Config TERMINER"));
	}

	@Listener
	public void onPlayerJoin(ClientConnectionEvent.Join event){
		Player p = event.getTargetEntity();
		String uuid = String.valueOf(p.getProfile().getUniqueId());
		ConfigurationManager.getInstance().editConfigP(p.getName(),uuid);
	}

	@Listener
	public void onPlayerInteract(InteractBlockEvent.Secondary event){
		Player p = (Player) event.getSource();
		String uuid = p.getUniqueId().toString();
		BlockSnapshot b = event.getTargetBlock();
		String bLoca = b.getLocation().toString();

		List<Text> lore = new ArrayList<Text>();
		lore.add(Text.of(TextColors.RED, "Derniere flamme d'un dragon"));
		ItemStack soul = ItemStack.builder()
				.itemType(ItemTypes.NETHER_STAR)
				.add(Keys.DISPLAY_NAME, Text.of(TextColors.AQUA, "Ame de Dragon"))
				.add(Keys.ITEM_LORE, lore)
				.build();

		//Apprentissage des Cris
		if((b.getState().getType() == BlockTypes.BOOKSHELF) && (locCri.containsKey(bLoca))
				&& (p.getItemInHand(HandTypes.MAIN_HAND).get().equalTo(soul))){

			String criMur = locCri.get(bLoca);
			if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, criMur)
					.getValue() == null){
				ConfigurationManager.getInstance().editConfigL(uuid, criMur, "1");
				ConfigurationManager.getInstance().editConfigLPos(uuid, bLoca);
				removeItem(p, soul);
				p.sendMessage(Text.of(TextColors.GREEN, "Vous venez d'apprendre ", TextColors.DARK_GREEN,criMur));
			}else{
				if(ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, criMur)
						.getString().equalsIgnoreCase("1")) {
					if (!ConfigurationManager.getInstance().getConfig().getNode("learn", uuid, bLoca)
							.getBoolean()) {
						ConfigurationManager.getInstance().editConfigL(uuid, criMur, "2");
						ConfigurationManager.getInstance().editConfigLPos(uuid, bLoca);
						p.sendMessage(Text.of(TextColors.GREEN, "Vous venez d'apprendre ", TextColors.DARK_GREEN, criMur));
					}
				}else {
					if (ConfigurationManager.getInstance().getConfig().getNode("shouts", "Learn", uuid, criMur)
							.getString().equalsIgnoreCase("2")){
						if (!ConfigurationManager.getInstance().getConfig().getNode("learn", uuid, bLoca)
								.getBoolean()) {
							ConfigurationManager.getInstance().editConfigL(uuid, criMur, "3");
							ConfigurationManager.getInstance().editConfigLPos(uuid, bLoca);
							p.sendMessage(Text.of(TextColors.GREEN, "Vous venez d'apprendre ", TextColors.DARK_GREEN
									, criMur));
						}
					}
				}
			}
		}

		//Gestion de la pose des Cris
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
	@Exclude({ClickInventoryEvent.Drop.Outside.class, ClickInventoryEvent.Creative.class})
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

			if(ConfigurationManager.getInstance().getConfig().getNode("murs", cri, "Location", "1")
					.getValue() == null){
				if(!locCri.containsKey(loc)){
					locCri.put(loc, cri);
					ConfigurationManager.getInstance().editConfigM(cri, "1", loc);
					p.sendMessage(Text.of(TextColors.GREEN,"Le cri ", TextColors.DARK_GREEN,cri
							, TextColors.GREEN, " a ete ajoute a ce mur."));
				}else{p.sendMessage(Text.of(TextColors.RED,"Un cri est deja pose sur ce mur !"));}
			}else{
				if(ConfigurationManager.getInstance().getConfig().getNode("murs", cri, "Location", "2")
						.getValue() == null){
					if(!locCri.containsKey(loc)){
						locCri.put(loc, cri);
						ConfigurationManager.getInstance().editConfigM(cri, "2", loc);
						p.sendMessage(Text.of(TextColors.GREEN,"Le cri ", TextColors.DARK_GREEN,cri
								, TextColors.GREEN, " a ete ajoute a ce mur."));
					}else{p.sendMessage(Text.of(TextColors.RED,"Un cri est deja pose sur ce mur !"));}
				}else if(ConfigurationManager.getInstance().getConfig().getNode("murs", cri, "Location", "3")
						.getValue() == null){
					if(!locCri.containsKey(loc)){
						locCri.put(loc, cri);
						ConfigurationManager.getInstance().editConfigM(cri, "3", loc);
						p.sendMessage(Text.of(TextColors.GREEN,"Le cri ", TextColors.DARK_GREEN,cri
								, TextColors.GREEN, " a ete ajoute a ce mur."));
					}else{p.sendMessage(Text.of(TextColors.RED,"Un cri est deja pose sur ce mur !"));}
				}else{p.sendMessage(Text.of(TextColors.RED, "Ce cri est deja entierement pose !"));}
			}
			event.setCancelled(true);
			p.closeInventory();
		}
	}

	private void removeItem(final Player p, final ItemStack item){
		for (Inventory inv : p.getInventory().slots()){
			if (inv.contains(item)){
				p.sendMessage(Text.of("OK"));
			}
		}
	}

}
