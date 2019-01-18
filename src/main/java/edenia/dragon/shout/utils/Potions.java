package edenia.dragon.shout.utils;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;


public enum Potions {

    Lent;

    public String name;
    Potions(){this.name = name();}

    public void effect(Entity e, int dure, int puissance){
        List<PotionEffect> potion = new ArrayList<PotionEffect>();

        if(this == Lent){
            PotionEffect lent = PotionEffect.builder()
                    .potionType(PotionEffectTypes.SLOWNESS)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(lent);
            e.offer(Keys.POTION_EFFECTS, potion);
        }
    }
}
