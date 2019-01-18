package edenia.dragon.shout.utils;

import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.flowpowered.math.vector.Vector3d;

public enum Particules {

	Flamme,
	Magic_Crit,
	Nuage;
	
	public String name;
	Particules(){
		this.name = name();
	}
	public void effet(Player p, int quantite){

		if(this == Flamme){
			ParticleEffect flamme = ParticleEffect.builder()
					.type(ParticleTypes.FLAME)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(flamme, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if (this == Magic_Crit){
			ParticleEffect magic_crit = ParticleEffect.builder()
					.type(ParticleTypes.MAGIC_CRITICAL_HIT)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(magic_crit, p.getLocation().getPosition().add(new Vector3d(0,1,0)), 10);
		}

		if(this == Nuage){
			ParticleEffect nuage = ParticleEffect.builder()
					.type(ParticleTypes.CLOUD)
					.quantity(quantite)
					.offset(Vector3d.ONE.negate())
					.build();

			p.spawnParticles(nuage, p.getLocation().getPosition().add(new Vector3d(0, 1, 0)), 10);
		}
	}
}
