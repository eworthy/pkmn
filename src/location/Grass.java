package location;
//
//import java.util.Random;
//
//import pokemon.Pokemon;
//
///**
// * @author ellen
// *
// */
//public class Grass extends Location {
//	private static final int DEFAULT_GRASS_DENSITY = 95;
//
//	private static final String NAME = "GRASSY FIELD";
//	private static final String DESCRIPTION = "It's a grassy field.";
//	private static final String[] CHOICES = {"Forward",
//											 "Back",
//											 "Left",
//											 "Right"};
//
//	private static final int ROW = 0;
//	private static final int COL = 1;
//	
//	private static Random generator = new Random();
////	private boolean[][] grassFeatures;
//	private int[] location;
//	/** Types of Pokemon that spawn here */
//	private Pokemon[] pkmn;
//	/** Spawn likelihood for each Pkmn (0 - 100) */
//	private int[] spawnChance; 
//	//TODO encounter rate (eg higher in certain conditions/repel and sweet scent scenarios); pkmn based on time of day
//	private int encounterRate;
//	
//	
//	private int currentCol;
//	private int currentRow;
//	
//	
//	/**
//	 * 
//	 */
//	public Grass(int rows, int cols, int grassDensity) {
//		super(NAME, DESCRIPTION, CHOICES);
////		grassFeatures = new boolean[rows][cols];
////		generateGrassFeatures(grassDensity);
//		location = new int[2];
//		//TODO fix to make it vary based on where player actually starts
//		location[ROW] = 0;
//		location[COL] = 0;
//	}
//	
//	public Grass(int rows, int cols) {
//		this(rows, cols, DEFAULT_GRASS_DENSITY);
//	}

//	private void generateGrassFeatures(int grassDensity) {
//		for(int col = 0; col < grassFeatures[0].length; col++) {
//			for (int row = 0; row < grassFeatures.length; row++) {
//				grassFeatures[row][col] = false;
//				if (generator.nextInt(101) <= grassDensity + 1) {
//					grassFeatures[row][col] = true;
//				}
//			}
//		}
//		
//	}
	
//	public boolean[][] getGrassFeatures() {
//		return grassFeatures;
//	}

//	public int[] getLocation() {
//		return location;
//	}
//	
//	private void updateLocation() {
//		location[ROW] = currentRow;
//		location[COL] = currentCol;
//	}

//	public void forward() {
//		if (currentRow < grassFeatures[0].length)
//			currentRow++;
//		updateLocation();
//	}
//	
//	public void back() {
//		if (currentRow > 0)
//			currentRow--;
//		updateLocation();
//	}
//	
//	public void left() {
//		if (currentCol > 0)
//			currentCol--;
//		updateLocation();
//	}
//	
//	public void right() {
//		if (currentCol < grassFeatures.length)
//			currentCol++;
//		updateLocation();
//	}

//	private void encounter() {
//		if(grassFeatures[currentRow][currentCol] == true) {
//			if(generator.nextInt(101) < encounterRate) {
//				encounterPokemon();
//			}
//		}
//	}
//
//	private Pokemon encounterPokemon() {
//		int chance = generator.nextInt(101);
//		for(int i = 0; i < spawnChance.length; i++) {
//			if (chance < spawnChance[i]) {
//				return pkmn[i];
//			}
//		}
//	}
//}
