package edenia.dragon.shout.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.flowpowered.math.imaginary.Quaterniond;
import edenia.dragon.shout.Edenia;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.monster.Monster;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.weather.Weathers;

public enum Shout {
	
	Allegeance_Animale("Raan", "Mir", "Tah", 60, 80, 100), //A reprendre
	Allie_Draconique("Od", "Ah", "Ving", 100, 300, 500),
	Allie_Heroique("Hun", "Kaal", "Zoor", 100, 300, 500), //A reprendre
	Aspect_Draconique("Mul", "Qah", "Diiv", 50, 100, 150),
	Asservissement("Gol", "Hah", "Dov", 10, 90, 120), //A faire
	Aura_de_Perception("Laas", "Yah", "Nir", 30, 40, 50),
	Ciel_Degage("Lok", "Vah", "Koor", 20, 20, 20),
	Corps_Ethere("Feim", "Zii", "Gron", 30, 50, 60),
	Cyclone("Ven", "Gaar", "Nos", 30, 45, 60),
	Deferlement("Fus", "Ro", "Dah", 20, 40, 60),
	Desarmement("Zun", "Haal", "Viik", 5, 5, 5),
	Fendragon("Joor", "Zah", "Frul", 10, 20, 30),
	Furie_Combative("Mid", "Vur", "Shaan", 20, 30, 40),
	Furie_Elemental("Su", "Grah", "Dun", 30, 40, 50),
	Impulsion("Wuld", "Nah", "Kest", 15, 20, 30),
	Intimidation("Faas", "Ru", "Maar", 40, 60, 80),
	Laceration_d_Ame("Rii", "Vaaz", "Zol", 20, 40, 60),
	Marque_Mortelle("Krii", "Lun", "Aus", 20, 30, 40),
	Ponction_de_Vitalite("Gaan", "Lah", "Haas", 20, 30, 60),
	Ralenti("Tiid", "Klo", "Ul", 30, 45, 60),
	Souffle_Ardent("Yol", "Toor", "Shul", 20, 40, 60),
	Souffle_Glacee("Fo", "Krah", "Diin", 20, 40, 60),
	Telekinesie("Zul", "Mey", "Gut", 10, 10, 10),
	Tourmente("Strun", "Bah", "Qo", 300, 480, 600);
	
	public String name, m1, m2, m3;
	public int c1, c2, c3;
	private ArrayList<Monster> kps = new ArrayList<>();
	public static Edenia plugins;
	public static HashMap<Player, Long> cooldown = new HashMap<>();
	
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

	public int getCooldown(int num){
		if (num == 1){ return c1; }
		if (num == 2){ return c2; }
		if (num == 3){ return c3; }
		return 0;
	}

	public boolean cooldowns(final Player p, final int num){
		if (cooldown.containsKey(p) && cooldown.get(p) > TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())){
			p.sendMessage(Text.of(TextColors.GREEN,"Vous pourrez reutiliser votre Thu'um dans "
					+(cooldown.get(p) - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))+" secondes."));
			return false;
		} else {
			cooldown.remove(p);
			cooldown.put(p, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + getCooldown(num));
			Task.builder().async().delay(getCooldown(num), TimeUnit.SECONDS).execute(new Runnable() {
				@Override
				public void run() {
					p.sendMessage(Text.of(TextColors.GREEN,"Vous pouvez de nouveau utiliser votre Thu'um."));
				}
			}).submit(plugins);
		}

		for (Player p2 : Sponge.getServer().getOnlinePlayers()){
			if (p.getWorld() == p2.getWorld() && p2.getLocation().getPosition().distance(p.getPosition()) <= 10){
				p2.sendMessage(Text.of(TextColors.WHITE,"<",p.getName()+" (crie)> ",TextColors.RED,m1.toUpperCase()+
						(num > 1 ? " "+m2.toUpperCase()+(num > 2 ? " "+m3.toUpperCase():""):"")+" !"));
			}
		}
		return true;
	}

	public void shout(final Player p, final int num){
		if (!cooldowns(p, num)){ return; }

		//A reprendre
		if (this == Allegeance_Animale){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (int i = 0; i < num; i++){
				Particules.Mob_spell.effet(p, num*35);
				Entity w = p.getWorld().createEntity(EntityTypes.WOLF, p.getLocation().getPosition());

				w.offer(Keys.DISPLAY_NAME, Text.of("Invocation de ", p.getName()));
				w.offer(Keys.CUSTOM_NAME_VISIBLE, true);
				w.offer(Keys.HEALTH, 30.0);
				w.offer(Keys.ANGRY, true);
				w.setCreator(p.getUniqueId());
				p.getLocation().spawnEntity(w);
				Potions.Force.effect(w, 100000, 1, potion);
			}
		}

		if (this == Allie_Draconique){
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			if (num == 1){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole()
						, "noppes clone spawn dragon1 1 "+p.getPosition().getX()+","
								+(p.getPosition().getY()+20)+","+p.getPosition().getZ());
			}
			if (num == 2){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole()
						, "noppes clone spawn dragon2 1 "+p.getPosition().getX()+","
								+(p.getPosition().getY()+20)+","+p.getPosition().getZ());
			}
			if (num == 3){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole()
						, "noppes clone spawn dragon3 1 "+p.getPosition().getX()+","
								+(p.getPosition().getY()+20)+","+p.getPosition().getZ());
			}
		}

		//A reprendre aussi
		if (this == Allie_Heroique){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (int i = 0; i < num+1; i++){
				Particules.Lave.effet(p, num*35);
				Entity s = p.getWorld().createEntity(EntityTypes.SKELETON, p.getLocation().getPosition());

				s.offer(Keys.DISPLAY_NAME, Text.of("Guerrier de ", p.getName()));
				s.offer(Keys.CUSTOM_NAME_VISIBLE, true);
				s.offer(Keys.HEALTH, 50.0);
				s.offer(Keys.ANGRY, true);
				s.setCreator(p.getUniqueId());
				p.getLocation().spawnEntity(s);
				Potions.Force.effect(s, 100000, 1, potion);
				Potions.Resistance.effect(s, 100000, 1, potion);
			}
		}

		if (this == Aspect_Draconique){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_GROWL, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			int dur = 400;
			if (num == 2){
				dur = 600;
			}else {
				if (num == 3){
					dur = 1200;
				}
			}
			Potions.Absorbtion.effect(p, dur, 1, potion);
			Potions.Force.effect(p, dur, 2, potion);
			Potions.Resistance.effect(p, dur, 3, potion);
			Particules.Lave.effet(p, 100);
		}

		//Possiblement à revoir.
		if (this == Aura_de_Perception){
			String name = p.getName();

			p.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_HURT, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);

			if (num == 1){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect "+name+" ebwizardry:sixth_sense 60 3");
			}
			if (num == 2){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect "+name+" ebwizardry:sixth_sense 180 5");
			}
			if (num == 3){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect "+name+" ebwizardry:sixth_sense 300 10");
			}
		}

		if (this == Ciel_Degage){
			p.getWorld().playSound(SoundTypes.ENTITY_LIGHTNING_THUNDER, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			if (num == 3){
				p.getWorld().setWeather(Weathers.CLEAR);
			}else{
				p.getWorld().setWeather(Weathers.CLEAR, 1200*num);
			}
		}

		if (this == Corps_Ethere){
			String name = p.getName();
			p.getWorld().playSound(SoundTypes.BLOCK_FIRE_EXTINGUISH, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
					"effect "+name+" ebwizardry:transience "+20*num+" 0");
		}

		if (this == Cyclone){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_FIREWORK_LARGE_BLAST, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
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
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Particules.Nuage.effet(p, 200*num);
			for(Entity e : near){
				if((e.getLocation().getPosition().distance(p.getLocation().getPosition()) < 4) && e!=p){
					e.setVelocity(getVelocity(p.getLocation().getPosition(), e.getLocation()).mul(num/ 4 + 1)
							.mul(1).add(new Vector3d(0, 0.1, 0)));
				}
			}
		}

		if (this == Desarmement){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.BLOCK_ANVIL_LAND, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Particules.Crit.effet(p, 25*num);
			for (Entity e : near){
				if (e instanceof Player){
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
		}

		if (this == Fendragon){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(35);
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SHOOT, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
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
							if (num == 1){
								Potions.Faim.effect(p2, 3, 100, potion);
							}
							if (num == 2){
								Potions.Faim.effect(p2, 5, 100, potion);
							}
							if (num == 3){
								Potions.Faim.effect(p2, 10, 100, potion);
							}

						}
					}
				}
			}
		}

		if (this == Furie_Combative){
			Collection<Entity> near = p.getNearbyEntities(25);
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				if (e instanceof Player){
					Player p2 = (Player) e;
					if ((p2.isOnline()) && (p2.getLocation().getPosition()
							.distance(p.getLocation().getPosition()) < num*8) && (p2 != p)){
						Potions.Minage.effect(p2, num*200, num, potion);
						Potions.Force.effect(p2, num*200, num, potion);
					}
				}
			}
		}

		if (this == Furie_Elemental){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Potions.Minage.effect(p, num*200, num, potion);
			Potions.Force.effect(p, num*200, num, potion);
		}

		if (this == Impulsion){
			final Vector3d direction = Quaterniond.fromAxesAnglesDeg(p.getHeadRotation().getX(),
					-p.getHeadRotation().getY(), p.getHeadRotation().getZ()).getDirection();
			p.setVelocity(direction.mul((num*2)+3));
			p.getWorld().playSound(SoundTypes.ENTITY_BAT_TAKEOFF, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
		}

		if (this == Intimidation){
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			int x = (int) p.getPosition().getX();
			int y = (int) p.getPosition().getY();
			int z = (int) p.getPosition().getZ();
			String name = p.getName();

			if (num == 1){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect @e[r=10,x="+x+",y="+y+",z="+z+",name=!"+name+"] ebwizardry:fear 60");
			}

			if (num == 2){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect @e[r=30,x="+x+",y="+y+",z="+z+",name=!"+name+"] ebwizardry:fear 120");
			}

			if (num == 3){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect @e[r=50,x="+x+",y="+y+",z="+z+",name=!"+name+"] ebwizardry:fear 1200");
			}

		}

		if (this == Laceration_d_Ame){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_IRONGOLEM_ATTACK, p.getLocation().getPosition(),1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Particules.Mob_spell.effet(p, num*15);
			for (Entity e : near){
				if (e instanceof Player){
					Player p2 = (Player) e;
					if ((e.getLocation().getPosition().distance(p.getPosition()) < 7) && (e!=p)){
						if (num == 1){
							Potions.Wither.effect(p2, 100, 0, potion);
						}
						if (num == 2){
							Potions.Wither.effect(p2, 160, 0, potion);
						}
						if (num == 3){
							Potions.Wither.effect(p2, 240, 0, potion);
						}
					}
				}
			}
		}

		if (this == Marque_Mortelle){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_HURT, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				Particules.Fumer_large.effet(p, num*15);
				if (e instanceof Player){
					Player p2 = (Player) e;
					if ((e.getLocation().getPosition().distance(p.getPosition()) < 7)){
						if (num == 1){
							Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
									"effect "+p2.getName()+" potioncore:vulnerable 20 0");
						}
						if (num == 2){
							Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
									"effect "+p2.getName()+" potioncore:vulnerable 30 1");
						}
						if (num == 3){
							Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
									"effect "+p2.getName()+" potioncore:vulnerable 60 2");
						}
					}
				}
			}
		}

		if (this == Ponction_de_Vitalite){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			List<PotionEffect> potion1 = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_WITHER_SPAWN, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				if (e instanceof Player){
					Player p2 = (Player) e;
					if ((e.getLocation().getPosition().distance(p.getPosition()) < 7) && (e!=p)){
						if (num > 1){
							if (num == 2){
								Potions.Regeneration.effect(p, 400, 2, potion1);
								Potions.Wither.effect(p2, 100, 0, potion);
							}
							if (num == 3){
								Potions.Regeneration.effect(p, 1200, 2, potion1);
								Potions.Wither.effect(p2, 400, 0, potion);
							}
						}else {
							Potions.Regeneration.effect(p, 100, 2, potion1);
							Potions.Faim.effect(p2, 200, 1, potion);
						}
					}
				}
			}
		}

		if (this == Ralenti){
			List<PotionEffect> potion = new ArrayList<PotionEffect>();
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_GROWL, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (Entity e : near){
				e.setVelocity(e.getVelocity().mul(1/num*3));
				if ((e instanceof Living) && (e!=p)){
					Potions.Lent.effect(e, 600 * num, num, potion);
				}
			}
		}

		if (this == Souffle_Ardent){
			Collection<Entity> near = p.getNearbyEntities(15);
			p.getWorld().playSound(SoundTypes.ENTITY_GHAST_SHOOT, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Particules.Flamme.effet(p, 100*num);
			for (Entity e : near){
				if((e.getLocation().getPosition().distance(p.getPosition()) < 4) && (e!=p)){
					if (num == 3){
						e.offer(Keys.FIRE_TICKS, 500);
					}else {
						e.offer(Keys.FIRE_TICKS, 100*num);
					}
				}
			}
		}

		if (this == Souffle_Glacee){
			int x = (int) p.getPosition().getX();
			int y = (int) p.getPosition().getY();
			int z = (int) p.getPosition().getZ();
			String name = p.getName();

			p.getWorld().playSound(SoundTypes.BLOCK_GLASS_BREAK, p.getLocation().getPosition(), 1);
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			Particules.Spell_Instant.effet(p, 100*num);
			if (num < 3){
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect @e[r=7,x="+x+",y="+y+",z="+z+",name=!"+name+"] ebwizardry:frost "+10*num+" 0");
			}else {
				Sponge.getCommandManager().process(Sponge.getServer().getConsole(),
						"effect @e[r=7,x="+x+",y="+y+",z="+z+",name=!"+name+"] mowziesmobs:frozen 20");
			}

		}

		if (this == Telekinesie){
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
			for (Player p2 : Sponge.getServer().getOnlinePlayers()){
				if ((p2.getWorld() == p.getWorld())
						&& (p2.getLocation().getPosition().distance(p.getPosition()) < num*30) && (p2!=p)){
					if (num == 1){
						p2.getWorld().playSound(SoundTypes.ENTITY_WOLF_AMBIENT,
								p2.getLocation().getPosition(), 3);
					}
					if (num == 2){
						p2.getWorld().playSound(SoundTypes.ENTITY_ZOMBIE_AMBIENT,
								p2.getLocation().getPosition(), 3);
					}
					if (num == 3){
						p2.getWorld().playSound(SoundTypes.ENTITY_ENDERDRAGON_GROWL,
								p2.getLocation().getPosition(), 3);
					}
				}
			}
		}

		if (this == Tourmente){
			p.getWorld().playSound(SoundTypes.ENTITY_SHULKER_HURT_CLOSED, p.getLocation().getPosition(), 1);
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
