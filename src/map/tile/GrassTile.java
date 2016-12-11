package map.tile;

import pokemon.Species;

/**
 *
 * @author ellen
 */
public class GrassTile extends Tile implements AttackRisk {

	/** Types of Pokemon that spawn here */
	private Species[] pkmn;
	/** Spawn likelihood for each Pkmn (0 - 100) */
	private int[] spawnChance; 
	//TODO encounter rate (eg higher in certain conditions/repel and sweet scent scenarios); pkmn based on time of day
	private int encounterRate;
        /** Species, spawn chance*/
        private Object[][] newpkmn;

    public GrassTile(Object occupant, Object[][] pkmn) {
        super(occupant);
        this.newpkmn = pkmn;
    }

    public GrassTile(Object occupant, String roomName, int row, int col, String direction) {
        super(occupant, roomName, row, col, direction);
    }

    @Override
    public void interact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] getSpawnChance() {
        return spawnChance;
    }

    public void setSpawnChance(int[] spawnChance) {
        this.spawnChance = spawnChance;
    }

    public int getEncounterRate() {
        return encounterRate;
    }

    public void setEncounterRate(int encounterRate) {
        this.encounterRate = encounterRate;
    }

    public Object[][] getNewpkmn() {
        return newpkmn;
    }

    public void setNewpkmn(Object[][] newpkmn) {
        this.newpkmn = newpkmn;
    }
    
}
