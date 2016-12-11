package ui;

import data.JSONConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import map.GridMap;

import ui.enums.Season;

/**
 * 
 * @author ellen
 *
 */
public class GameEnvironment {

    private static GregorianCalendar localCalendar;
    private final static int DAY = 8;
    private final static int NIGHT = 20;

    private static GridMap[] worldMap;

    
    public GameEnvironment(String environmentDataFile) {
        worldMap = JSONConnection.readEnvironmentData(environmentDataFile);
        localCalendar = new GregorianCalendar();
    }
    

    public static Season getSeason() {
        switch (localCalendar.get(Calendar.MONTH)) {
            case Calendar.DECEMBER:
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
                return Season.WINTER;
            case Calendar.MARCH:
            case Calendar.APRIL:
            case Calendar.MAY:
                return Season.SPRING;
            case Calendar.JUNE:
            case Calendar.JULY:
            case Calendar.AUGUST:
                return Season.SUMMER;
            case Calendar.SEPTEMBER:
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
                return Season.AUTUMN;
            default:
                return null;
        }
    }

    public static boolean isNight() {
        int time = localCalendar.get(Calendar.HOUR_OF_DAY);
        return time < DAY || time >= NIGHT;
    }
    
    public static GridMap getRoom(String roomName) {
        for (GridMap g : worldMap) {
            if (g.getName().equals(roomName)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Room not found");
    }

    public static GridMap[] getWorldMap() {
        return worldMap;
    }
    
    
}



    
//	
//	private final String GRASS_LEFT = "\\\\\\";
//	private final String GRASS_RIGHT = "///";
//	
//	public void makeGrass(boolean[][] grassPattern) {
//		for (int r = 0; r < grassPattern[0].length; r++) {
//			for(int c = 0; c < grassPattern.length; c++) {
//				if (grassPattern[r][c] == true) {
//					switch(c%2) {
//					case 0:
//						System.out.print(GRASS_LEFT);
//						break;
//					case 1:
//						System.out.print(GRASS_RIGHT);
//						break;
//					}
//				} else {
//					System.out.print("   ");
//				}
//			}
//			System.out.println();
//		}
//	}
//	
