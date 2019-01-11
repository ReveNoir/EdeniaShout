package edenia.dragon.shout.utils;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.List;

public enum Items {

    Soul;

    public String name;
    Items(){this.name = name();}

    public void giveItem(final Player p){
        List<Text> lore = new ArrayList<Text>();

        if(this == Soul){
            lore.add(Text.of(TextColors.RED, "Derniere flamme d'un dragon"));
            ItemStack soul = ItemStack.builder()
                    .itemType(ItemTypes.NETHER_STAR)
                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.AQUA, "Ame de Dragon"))
                    .add(Keys.ITEM_LORE, lore)
                    .build();
            for (Inventory inv : p.getInventory().slots()){
                if(inv.size() == 0){
                    inv.offer(soul);
                    break;
                }
            }
        }

    }

}
