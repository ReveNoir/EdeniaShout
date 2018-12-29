package edenia.dragon.shout.utils;

import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.flowpowered.math.vector.Vector3d;

public enum Particules {

	Nuage;
	
	public String name;
	Particules(){
		this.name = name();
	}
	public void effet(Player p, int quantite){
		
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
