package edenia.dragon.shout.utils;

import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.world.Location;

public enum Particules {

	Crit,
	Explosion,
	Flamme,
	Fumer_large,
	Lave,
	Magic_Crit,
	Mob_spell,
	Nuage,
	Portail,
	Spell,
	Spell_Instant;
	
	public String name;
	Particules(){
		this.name = name();
	}
	public void effet(Player p, int quantite){

		if (this == Crit){
			ParticleEffect crit = ParticleEffect.builder()
					.type(ParticleTypes.CRITICAL_HIT)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();
			p.spawnParticles(crit, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if (this == Explosion){
			ParticleEffect explo = ParticleEffect.builder()
					.type(ParticleTypes.LARGE_EXPLOSION)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(explo, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if(this == Flamme){
			ParticleEffect flamme = ParticleEffect.builder()
					.type(ParticleTypes.FLAME)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(flamme, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if (this == Fumer_large){
			ParticleEffect fl = ParticleEffect.builder()
					.type(ParticleTypes.LARGE_SMOKE)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(fl, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if (this == Lave){
			ParticleEffect lava = ParticleEffect.builder()
					.type(ParticleTypes.LAVA)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();
			p.spawnParticles(lava, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 5);
		}

		if (this == Magic_Crit){
			ParticleEffect magic_crit = ParticleEffect.builder()
					.type(ParticleTypes.MAGIC_CRITICAL_HIT)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(magic_crit, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if (this == Mob_spell){
			ParticleEffect ms = ParticleEffect.builder()
					.type(ParticleTypes.MOB_SPELL)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(ms, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if(this == Nuage){
			ParticleEffect nuage = ParticleEffect.builder()
					.type(ParticleTypes.CLOUD)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(nuage, p.getLocation().getPosition().add(new Vector3d(0, 1, 0)), 10);
		}

		if (this == Portail){
			ParticleEffect port = ParticleEffect.builder()
					.type(ParticleTypes.PORTAL)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(port, p.getLocation().getPosition().add(new Vector3d(0, 1, 0)), 10);
		}

		if (this == Spell){
			ParticleEffect port = ParticleEffect.builder()
					.type(ParticleTypes.SPELL)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(port, p.getLocation().getPosition().add(new Vector3d(0, 1, 0)), 10);
		}

		if(this == Spell_Instant){
			ParticleEffect si = ParticleEffect.builder()
					.type(ParticleTypes.INSTANT_SPELL)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(si, p.getLocation().getPosition().add(new Vector3d(0, 1, 0)), 10);
		}
	}
}
