package edenia.dragon.shout.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.flowpowered.math.imaginary.Quaterniond;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.animal.Wolf;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.rotation.Rotations;
import org.spongepowered.api.world.Location;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.weather.Weathers;


public enum Shout {
	
	Allegeance_Animale("Raan", "Mir", "Tah", 50, 60, 70),
	Allie_Draconique("Od", "Ah", "Ving", 5, 5, 300),
	Allie_Heroique("Hun", "Kaal", "Zoor", 5, 5, 180),
	Aspect_Draconique("Mul", "Qah", "Diiv", 50, 100, 150),
	Asservissement("Gol", "Hah", "Dov", 10, 90, 120),
	Aura_de_Perception("Laas", "Yah", "Nir", 30, 40, 50),
	Ciel_Degage("Lok", "Vah", "Koor", 5, 10, 15),
	Corps_Ethere("Feim", "Zii", "Gron", 20, 30, 40),
	Cri_de_Glace("Iiz", "Slen", "Nus", 60, 90, 120),
	Cyclone("Ven", "Gaar", "Nos", 30, 45, 60),
	Deferlement("Fus", "Ro", "Dah", 15, 20, 45),
	Desarmement("Zun", "Haal", "Viik", 30, 35, 45),
	Fendragon("Joor", "Zah", "Frul", 10, 12, 15),
	Furie_Combative("Mid", "Vur", "Shaan", 20, 30, 40),
	Furie_Elemental("Su", "Grah", "Dun", 30, 40, 50),
	Impultion("Wuld", "Nah", "Kest", 20, 25, 35),
	Intimidation("Faas", "Ru", "Maar", 40, 45, 50),
	Invocation_de_Durnehviir("Dur", "Neh", "Viir", 5, 5, 300),
	Laceration_d_Ame("Rii", "Vaaz", "Zol", 5, 5, 90),
	Marque_Mortelle("Krii", "Lun", "Aus", 20, 30, 40),
	Paix_de_Kyne("Kaan", "Drem", "Ov", 40, 50, 60),
	Ponction_de_Vitalite("Gaan", "Lah", "Haas", 30, 60, 90),
	Ralenti("Tiid", "Klo", "Ul", 30, 45, 60),
	Souffle_Ardent("Yol", "Toor", "Shul", 30, 50, 100),
	Souffle_Glacee("Fo", "Krah", "Diin", 30, 50, 100),
	Telekinesie("Zul", "Mey", "Gut", 30, 15, 5),
	Tourmente("Strun", "Bah", "Qo", 300, 480, 600);
	
	public String name, m1, m2, m3;
	public int c1, c2, c3;
	
	@SuppressWarnings("rawtypes")
	public static Vector3d getVelocity(Vector3d c, Location f) {
		return new Vector3d(f.getX()-c.getX(), 0.6, f.getZ()-c.getZ()).mul(5/f.getPosition().distance(c));
	}
	
	Shout(String w1, String w2, String w3, int cool1, int cool2, int cool3){
		this.name = name().replace("_", " ");
		
		this.m1 = w1;
		this.m2 = w2;
		this.m3 = w3;
		
		this.c1 = cool1;
		this.c2 = cool2;
		this.c3 = cool3;
	}
	
	public void shout(final Player p, final int num){

		if (this == Allie_Heroique){
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			for (int i = 0; i < num; i++){
				Entity w = p.getWorld().createEntity(EntityTypes.WOLF, p.getLocation().getPosition());
				w.offer(Keys.DISPLAY_NAME, Text.of("Invocation de ", p.getName()));
				w.offer(Keys.CUSTOM_NAME_VISIBLE, true);
				w.offer(Keys.HEALTH, 30.0);
				w.setCreator(p.getUniqueId());
				p.getLocation().spawnEntity(w);

			}
		}

		if (this == Aspect_Draconique){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_GROWL, p.getLocation().getPosition(), 1);
			int dur = 300 * num;
			Potions.Absorbtion.effect(p, dur, 1, potion);
			Potions.Force.effect(p, dur, 2, potion);
			Potions.Resistance.effect(p, dur, 3, potion);
			Particules.Lave.effet(p, 100);
		}

		if (this == Aura_de_Perception){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_HURT, p.getLocation().getPosition(), 1);
			for (Player p2 : Sponge.getServer().getOnlinePlayers()){
				if ((p2.getWorld() == p.getWorld())
						&& (p2.getLocation().getPosition().distance(p.getPosition()) < 10*num)){
					if (p2 != p){
						Potions.Surbrillance.effect(p2, 100*num, num-1, potion);
					}
				}else{p.sendMessage(Text.of(TextColors.LIGHT_PURPLE, "[Perception] Aucun joueur a proximite."));}
			}
		}

		if (this == Ciel_Degage){
			p.getWorld().playSound(SoundTypes.ENTITY_LIGHTNING_THUNDER, p.getLocation().getPosition(), 1);
			if (num == 3){
				p.getWorld().setWeather(Weathers.CLEAR);
			}else{
				p.getWorld().setWeather(Weathers.CLEAR, 1200*num);
			}
		}

		if (this == Corps_Ethere){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.BLOCK_FIRE_EXTINGUISH, p.getLocation().getPosition(), 1);
			Potions.Resistance.effect(p, 150*num, 50, potion);
			Potions.Faiblesse.effect(p, 150*num, 50, potion);
		}

		if (this == Cri_de_Glace){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.BLOCK_GLASS_BREAK, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				Particules.Spell_Instant.effet(p, 35*num);
				Living le = (Living) e;
				if ((e.getLocation().getPosition().distance(p.getPosition()) < 4) && (le!=p)){
					Potions.Lent.effect(le, 60*num, 25, potion);
				}
			}
		}

		if (this == Cyclone){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_FIREWORK_LARGE_BLAST, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				if ((e.getLocation().getPosition().distance(p.getPosition()) < 4) && (e!=p)){
					Particules.Explosion.effet(p, num*10);
					if (e instanceof Living){
						e.damage(num*3, DamageSources.GENERIC);
					}
					e.setVelocity(new Vector3d(0,num,0));
				}
			}
		}

		if (this == Deferlement){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_GENERIC_EXPLODE, p.getLocation().getPosition(), 1);
			Particules.Nuage.effet(p, 200*num);
			for(Entity e : near){
				if((e.getLocation().getPosition().distance(p.getLocation().getPosition()) < 4) && e!=p){
					e.setVelocity(getVelocity(p.getLocation().getPosition(), e.getLocation()).mul(num/ 2 + 1)
							.mul(1).add(new Vector3d(0, 0.1, 0)));
				}
			}
		}

		//New
		if (this == Desarmement){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.BLOCK_ANVIL_LAND, p.getLocation().getPosition(), 1);
			Particules.Crit.effet(p, 25*num);
			for (Entity e : near){
				Player p2 = (Player) e;
				if ((e.getLocation().getPosition().distance(p.getLocation().getPosition()) < 7) && (e!=p)){
					Location<World> loc = p2.getLocation()
							.add(Quaterniond.fromAxesAnglesDeg(p.getHeadRotation().getX(),
							-p.getHeadRotation().getY(), p.getHeadRotation().getZ()).getDirection().mul(num*2));
					ItemStack stack = p2.getItemInHand(HandTypes.MAIN_HAND).get();
					Entity optItem = loc.createEntity(EntityTypes.ITEM);
					Item item = (Item) optItem;
					item.offer(Keys.REPRESENTED_ITEM, stack.createSnapshot());
					loc.spawnEntity(item);

					p2.setItemInHand(HandTypes.MAIN_HAND, null);
				}
			}
		}

		if (this == Fendragon){
			Collection<Entity> near = p.getNearbyEntities(35);
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SHOOT, p.getLocation().getPosition(), 1);
			Particules.Magic_Crit.effet(p, 200*num);
			for (Entity e : near){
				if ((e != p) && (e instanceof Player)){
					Player p2 = (Player) e;
					if ((p2.get(Keys.IS_FLYING).get()) || (p2.get(Keys.IS_ELYTRA_FLYING).get())){
						if(p2.getLocation().getPosition().distance(p.getLocation().getPosition()) < ((7*num) - 2)){
							Particules.Nuage.effet(p2, 100);
							p2.getWorld().playSound(SoundTypes.ENTITY_GENERIC_EXPLODE, p2.getLocation().getPosition()
									,1);
							p2.offer(Keys.IS_FLYING, false);
							p2.offer(Keys.IS_ELYTRA_FLYING, false);
							p2.offer(Keys.CAN_FLY, false);
						}
					}
				}
			}
		}

		if (this == Furie_Combative){
			Collection<Entity> near = p.getNearbyEntities(25);
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				Player p2 = (Player) e;
				if ((p2.isOnline()) && (p2.getLocation().getPosition()
						.distance(p.getLocation().getPosition()) < num*8) && (p2 != p)){
					Potions.Minage.effect(p2, num*100, num-1, potion);
					Potions.Force.effect(p2, num*100, num-1, potion);
				}
			}
		}

		if (this == Furie_Elemental){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			Potions.Minage.effect(p, num*100, num-1, potion);
			Potions.Force.effect(p, num*100, num-1, potion);
		}

		if (this == Impultion){
			final Vector3d direction = Quaterniond.fromAxesAnglesDeg(p.getHeadRotation().getX(),
					-p.getHeadRotation().getY(), p.getHeadRotation().getZ()).getDirection();
			p.setVelocity(direction.mul((num*2)+3));
			p.getWorld().playSound(SoundTypes.ENTITY_BAT_TAKEOFF, p.getLocation().getPosition(), 1);
		}

		if (this == Laceration_d_Ame){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_IRONGOLEM_ATTACK, p.getLocation().getPosition(),1);
			if (num > 2){
				Particules.Mob_spell.effet(p, num*15);
				for (Entity e : near){
					if (e instanceof Player){
						Player p2 = (Player) e;
						if ((e.getLocation().getPosition().distance(p.getPosition()) < 7) && (e!=p)){
							p2.damage(10, DamageSources.GENERIC);
						}
					}
				}
			}
		}

		if (this == Marque_Mortelle){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_HURT, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				Particules.Fumer_large.effet(p, num*15);
				if (e instanceof Player){
					Player p2 = (Player) e;
					if ((e.getLocation().getPosition().distance(p.getPosition()) < 7) && (e!=p)){
						Potions.Resistance.effect(p2, 120*num, num*2, potion);
						Potions.Wither.effect(p2, 20*num, num-1, potion);
					}
				}
			}
		}

		if (this == Ponction_de_Vitalite){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			List<PotionEffect> potion1 = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				if (e instanceof Player){
					Player p2 = (Player) e;
					if ((e.getLocation().getPosition().distance(p.getPosition()) < 7) && (e!=p)){
						Potions.Faim.effect(p2, 20*num, num*4, potion);
						if (num > 1){
							Potions.Wither.effect(p2, 20*num, num*3, potion);
							Potions.Regeneration.effect(p, 20*num, num, potion1);
						}
					}
				}
			}
		}

		if (this == Ralenti){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_GROWL, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				e.setVelocity(e.getVelocity().mul(1/num*3));
				if ((e instanceof Living) && (e!=p)){
					Potions.Lent.effect(e, 600 * num, num - 1, potion);
				}
			}
		}

		if (this == Souffle_Ardent){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_GHAST_SHOOT, p.getLocation().getPosition(), 1);
			Particules.Flamme.effet(p, 100*num);
			for (Entity e : near){
				if((e.getLocation().getPosition().distance(p.getPosition()) < 4) && (e!=p)){
					e.offer(Keys.FIRE_TICKS, 100*num);
				}
			}
		}

		if (this == Souffle_Glacee){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.BLOCK_GLASS_BREAK, p.getLocation().getPosition(), 1);
			Particules.Spell_Instant.effet(p, 100*num);
			for (Entity e : near){
				if (e instanceof Living){
					Living le = (Living) e;
					if ((e.getLocation().getPosition().distance(p.getPosition()) <= 4) && (e != p)){
						Potions.Lent.effect(le, 50*num, num-1, potion);
						Potions.Wither.effect(le, 20*num, num-1, potion);
					}
				}
			}
		}

		if (this == Telekinesie){
			for (Player p2 : Sponge.getServer().getOnlinePlayers()){
				if ((p2.getWorld() == p.getWorld())
						&& (p2.getLocation().getPosition().distance(p.getPosition()) < num*30) && (p2!=p)){
					p2.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_GROWL,
							p2.getLocation().getPosition(), num, 10);
				}
			}
		}

		if (this == Tourmente){
			if (num == 3){p.getWorld().setWeather(Weathers.THUNDER_STORM);}
			else {p.getWorld().setWeather(Weathers.THUNDER_STORM, 600*num);}

			for (Player p2 : Sponge.getServer().getOnlinePlayers()){
				if ((p2!=p) && (p2.getLocation().getPosition().distance(p.getPosition()) <= num*25)){
					for (int i = 0; i < num; i++){
						Entity ligthning = p2.getWorld().createEntity(EntityTypes.LIGHTNING, p2.getLocation().getPosition());
						p2.getLocation().spawnEntity(ligthning);
					}

					if (p2.getLocation().getPosition().distance(p.getPosition()) <= 10){
						p2.offer(Keys.FIRE_TICKS, 100*num);
					}
				}
			}
		}
	}
}
