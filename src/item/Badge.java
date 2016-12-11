/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

/**
 *
 * @author ellen
 */
public class Badge {
    
    private final String name;
    /** eg "The badge from Castelia City's gym */
    private final String description;
    
    private final int levelToObeyWild;
    private final int levelToObeyTrade;
    
    public Badge(String name, String description, int wild, int trade) {
        this.name = name;
        this.description = description;
        levelToObeyWild = wild;
        levelToObeyTrade = trade;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLevelToObeyWild() {
        return levelToObeyWild;
    }

    public int getLevelToObeyTrade() {
        return levelToObeyTrade;
    }
}
