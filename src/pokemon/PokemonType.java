package pokemon;

/**
 * 
 * @author ellen
 * 
 *
 */
public abstract class PokemonType {
	
	//TODO fighting type
	private static final double[][] TYPES = {
	/* TYPES: 	 F    W    G    N    Fl   E    Gnd  I    Drk  Gh   Dr   Psy  B    Psn  R    S    Fa   Fi*/
	/*FIRE*/	{.50, .25, 4.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, .50, 1.0, 2.0, 1.0, .50, 2.0, 1.0, 1.0},
	/*WATER*/	{4.0, .50, .25, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, .50, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0},
	/*GRASS*/	{.25, 4.0, .50, 1.0, .50, 1.0, 2.0, 1.0, 1.0, 1.0, .50, 1.0, .50, .50, 2.0, .50, 1.0, 1.0},
	/*NORMAL*/	{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, .50, .50, 2.0, .25},
	/*FLYING*/	{1.0, 1.0, 2.0, 1.0, 1.0, .50, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 4.0, 1.0, .25, 1.0, 1.0, 1.0},
	/*ELECTRIC*/    {1.0, 4.0, .50, 1.0, 2.0, .50, 0.0, 1.0, 1.0, 1.0, .50, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
	/*GROUND*/	{4.0, 1.0, .50, 1.0, 0.0, 4.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, .50, 2.0, 2.0, 2.0, 1.0, 1.0},
	/*ICE*/		{.50, 4.0, 1.0, 1.0, 2.0, 1.0, 2.0, .50, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, .50, 1.0, 1.0},
	/*DARK*/	{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, .50, 2.0, 1.0, 4.0, 1.0, 1.0, 1.0, 1.0, .50, 1.0},
	/*GHOST*/	{1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, .50, 2.0, 1.0, 4.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
	/*DRAGON*/	{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 4.0, 1.0, 1.0, 1.0, 1.0, .50, 0.0, 1.0},
	/*PSYCHIC*/	{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, .50, 1.0, 1.0, 1.0, .50, 1.0, 1.0},
	/*BUG*/		{.50, 1.0, 4.0, 1.0, .25, 1.0, 1.0, 1.0, 2.0, .50, 1.0, 2.0, 1.0, .25, 1.0, .50, .50, 1.0},
	/*POISON*/	{1.0, 1.0, 2.0, 1.0, 1.0, 1.0, .50, 1.0, 1.0, .50, 1.0, 1.0, 1.0, .50, .50, 0.0, 2.0, 1.0},
	/*ROCK*/	{2.0, 1.0, 1.0, 1.0, 2.0, 1.0, .50, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, .50, 1.0, 1.0},
	/*STEEL*/	{.50, .50, 1.0, 1.0, 1.0, .50, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, .50, 2.0, 1.0},
	/*FAIRY*/	{.50, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, .50, 1.0, .50, 1.0, 1.0},
	/*FIGHTING*/    {1.0, 1.0, 1.0, 4.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
	};
	private static final String[] TYPE_NAMES = {
			"FIRE",
			"WATER",
			"GRASS",
			"NORMAL",
			"FLYING",
			"ELECTRIC",
			"GROUND",
			"ICE",
			"DARK",
			"GHOST",
			"DRAGON",
			"PSYCHIC",
			"BUG",
			"POISON",
			"ROCK",
			"STEEL",
			"FAIRY",
			"FIGHTING",
	};
	private static final int NUM_TYPES = TYPE_NAMES.length;	

	public String getName(PkmnType t) {
		return TYPE_NAMES[t.ordinal()];
	}

	public static double[] getResistanceValues(PkmnType t) {
		return TYPES[t.ordinal()];
	}
	
	public static double getResistanceValue(PkmnType attacking, PkmnType defending) {
		return TYPES[attacking.ordinal()][defending.ordinal()];
	}
}
