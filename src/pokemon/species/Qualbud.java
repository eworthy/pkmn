/**
 * 
 */
package pokemon.species;

import move.Move;
import pokemon.Nature;
import pokemon.PkmnType;
import pokemon.Pokemon;
import pokemon.Stat;

/**
 * @author ellen
 *
 */
public class Qualbud extends Pokemon {
	
	private static final int[] SPECIES_BASE_STATS = {10, 8, 8, 8, 4, 5, 8, 2};
	private static final int[] SPECIES_MODIFIER = {2, 2, 2, 2, 2, 2, 2, 2};
	private static final int EGG_GROUP = 0;
	private static final String DESCRIPTION = "Description of fire pokemon";
	private static final String NAME = "QUALBUD";
	private static final PkmnType[] TYPES = {PkmnType.GRASS,
			 PkmnType.DRAGON};
	private static final Class<? extends Pokemon> BOTTOM_EVOLUTION = Qualbud.class;
	private static final Class<? extends Pokemon> NEXT_EVOLUTION = Qualgon.class;
	private static final int POKEDEX = 9;
	private static final int PERCENT_MALE = 50;
	private static final int CATCH_CHANCE = 0;
	private static final int EV_REWARD_VALUE = 1;
	private static final Stat EV_TYPE = Stat.DEFENSE;

	
	/**
	 * @param name
	 * @param gender
	 * @param level
	 * @param shiny
	 */
	public Qualbud(int level) {
		super(NAME, DESCRIPTION, POKEDEX, EGG_GROUP, Pokemon.generateGender(PERCENT_MALE), 
				  level,  TYPES, SPECIES_BASE_STATS, SPECIES_MODIFIER, CATCH_CHANCE,
				  BOTTOM_EVOLUTION, NEXT_EVOLUTION, EV_REWARD_VALUE, EV_TYPE);
	}
	
	/**
	 * @param name
	 * @param gender
	 * @param level
	 * @param shiny
	 */
	public Qualbud(int gender, int level, boolean shiny) {
		super(NAME, DESCRIPTION, POKEDEX, EGG_GROUP, gender, level, shiny, 
				  TYPES, SPECIES_BASE_STATS, SPECIES_MODIFIER, CATCH_CHANCE,
				  BOTTOM_EVOLUTION, NEXT_EVOLUTION, EV_REWARD_VALUE, EV_TYPE);
	}
	
	/**
	 * @param level
	 * @param maxHP
	 * @param gender
	 * @param personality
	 * @param description
	 * @param name
	 * @param nickname
	 * @param type
	 * @param moves
	 * @param eggGroup
	 * @param shiny
	 */
	public Qualbud(int level, int maxHP, int gender, Nature personality,
			String nickname, Move[] moves, boolean shiny) {
		super(NAME, nickname, DESCRIPTION, POKEDEX, EGG_GROUP, gender, level, shiny, 
				  TYPES, SPECIES_BASE_STATS, SPECIES_MODIFIER, CATCH_CHANCE,
				  BOTTOM_EVOLUTION, NEXT_EVOLUTION, EV_REWARD_VALUE, EV_TYPE,
				  personality, moves);
	}

	/**
	 * @param p
	 */
	public Qualbud(Pokemon p) {
		super(p);
	}


	/* (non-Javadoc)
	 * @see pokemon.Pokemon#canEvolve()
	 */
	@Override
	public boolean canEvolve() {
		return evolveCondition() && (getLevel() >= 16);
	}

	/* (non-Javadoc)
	 * @see pokemon.Pokemon#evolveCondition()
	 */
	@Override
	public boolean evolveCondition() {
		// no special conditions
		return true;
	}

	public int getPokedex() {
		return POKEDEX;
	}
}
