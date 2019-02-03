package edenia.dragon.shout.utils;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.Entity;

import java.util.List;


public enum Potions {

    Absorbtion,
    Faiblesse,
    Force,
    Lent,
    Minage,
    Resistance;

    public String name;
    Potions(){this.name = name();}

    public void effect(Entity e, int dure, int puissance, List<PotionEffect> potion){

        if (this == Absorbtion){
            PotionEffect absor = PotionEffect.builder()
                    .potionType(PotionEffectTypes.ABSORPTION)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(absor);
        }

        if (this == Faiblesse){
            PotionEffect faible = PotionEffect.builder()
                    .potionType(PotionEffectTypes.WEAKNESS)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(faible);
        }

        if (this == Force){
            PotionEffect force = PotionEffect.builder()
                    .potionType(PotionEffectTypes.STRENGTH)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(force);
        }

        if(this == Lent){
            PotionEffect lent = PotionEffect.builder()
                    .potionType(PotionEffectTypes.SLOWNESS)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(lent);
        }

        if (this == Minage){
            PotionEffect minage = PotionEffect.builder()
                    .potionType(PotionEffectTypes.HASTE)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(minage);
        }

        if (this == Resistance){
            PotionEffect resist = PotionEffect.builder()
                    .potionType(PotionEffectTypes.RESISTANCE)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(resist);
        }

        e.offer(Keys.POTION_EFFECTS, potion);
    }
}
