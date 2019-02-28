package edenia.dragon.shout.utils;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.Entity;

import java.util.List;


public enum Potions {

    Absorbtion,
    Faiblesse,
    Faim,
    Force,
    Lent,
    Minage,
    Regeneration,
    Resistance,
    Surbrillance,
    Wither;

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

        if (this == Faim){
            PotionEffect faim = PotionEffect.builder()
                    .potionType(PotionEffectTypes.HUNGER)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(faim);
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

        if (this == Regeneration){
            PotionEffect regen = PotionEffect.builder()
                    .potionType(PotionEffectTypes.REGENERATION)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(regen);
        }

        if (this == Resistance){
            PotionEffect resist = PotionEffect.builder()
                    .potionType(PotionEffectTypes.RESISTANCE)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(resist);
        }

        if (this == Surbrillance){
            PotionEffect surbri = PotionEffect.builder()
                    .potionType(PotionEffectTypes.GLOWING)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(surbri);
        }

        if (this == Wither){
            PotionEffect wither = PotionEffect.builder()
                    .potionType(PotionEffectTypes.WITHER)
                    .duration(dure)
                    .amplifier(puissance)
                    .build();
            potion.add(wither);
        }

        e.offer(Keys.POTION_EFFECTS, potion);
    }
}
