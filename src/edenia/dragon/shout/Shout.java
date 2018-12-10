package edenia.dragon.shout;

public enum Shout {
	
	Allegeance_Animale("Raan", "Mir", "Tah", 50, 60, 70),
	Allie_Draconique("OD", "Ah", "Ving", 5, 5, 300),
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
	
	Shout(String w1, String w2, String w3, int cool1, int cool2, int cool3){
		this.name = name().replace("_", " ");
		this.m1 = w1;
		this.m2 = w2;
		this.m3 = w3;
		this.c1 = cool1;
		this.c2 = cool2;
		this.c3 = cool3;
	}
	
	
}
