/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Exceptions.NoFileException;
import Exceptions.NoSavedGameException;
import character.GameCharacter;
import item.Item;
import item.Pokedex;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pokemon.Pokemon;
import pokemon.Species;
import character.Player;
import map.GridMap;
import map.tile.*;
import ui.GameManager;

/**
 *
 * @author ellen
 */
public class JSONConnection {

    private static JSONParser parser;
    private static JSONObject object;
    private static JSONArray partyPokemon;
    private static JSONArray storedPokemon;
    private static JSONArray inventory;

    //TODO make this change based on computer!
    

    /**
     * Reads Player and Pokemon data from a JSON file
     *
     * TODO pokedex
     * @param saveFileLocation
     */
    public static void loadGame(String saveFileLocation) {
        parser = new JSONParser();

        try {
            object = (JSONObject) parser.parse(
                    new FileReader(saveFileLocation));
            JSONArray partyPkmn = (JSONArray) object.get("partyPokemon");
            Pokemon[] playerPkmn = new Pokemon[6];
            for (int i = 0; i < partyPkmn.size(); i++) {
                playerPkmn[i] = (Pokemon) SerializationUtil.deserializeFromString(partyPkmn.get(i) + "");
            }

            JSONArray storedPkmn = (JSONArray) object.get("storedPokemon");
            ArrayList<Pokemon> storedPlayerPkmn = new ArrayList<>();
            for (int i = 0; i < storedPkmn.size(); i++) {
                storedPlayerPkmn.add((Pokemon) SerializationUtil.deserializeFromString(storedPkmn.get(i) + ""));
            }

            inventory = (JSONArray) object.get("inventory");

            GameManager.setPlayer((Player) SerializationUtil.deserializeFromString(object.get("player") + ""));
            GameManager.getPlayer().setTeam(playerPkmn);
            GameManager.getPlayer().setStoredPokemon(storedPlayerPkmn);

            for (int i = 0; i < inventory.size(); i++) {
                GameManager.getPlayer().addToInventory((Item) SerializationUtil.deserializeFromString(inventory.get(i) + ""));
            }

            GameManager.setPokedex((Pokedex) SerializationUtil.deserializeFromString(object.get("pokedex") + ""));
        } catch (IOException ex) {
            throw new NoSavedGameException("No save file");
        } catch (ParseException ex) {
            Logger.getLogger(JSONConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes Player and Pokemon data to a JSON file
     * @param saveFileLocation
     */
    public static void saveGame(String saveFileLocation) {
        object = new JSONObject();
        Player player = GameManager.getPlayer();
        object.put("player", "" + SerializationUtil.serializeToString(player) + "");
        object.put("pokedex", "" + SerializationUtil.serializeToString(GameManager.pokedex) + "");
        object.put("location", "" + GameManager.getGameState() + "");

        partyPokemon = new JSONArray();
        for (Pokemon p : player.getParty()) {
            partyPokemon.add("" + SerializationUtil.serializeToString(p) + "");
        }
        storedPokemon = new JSONArray();
        for (Pokemon p : player.getStoredPokemon()) {
            storedPokemon.add("" + SerializationUtil.serializeToString(p) + "");
        }

        object.put("partyPokemon", partyPokemon);
        object.put("storedPokemon", storedPokemon);

        inventory = new JSONArray();
        ArrayList<? extends Item>[] playerInventory = player.getInventory();
        for (ArrayList<? extends Item> playerInventory1 : playerInventory) {
            for (Item i : playerInventory1) {
                inventory.add("" + SerializationUtil.serializeToString(i) + "");
            }
        }
        object.put("inventory", inventory);
        try (FileWriter saveFile = new FileWriter(saveFileLocation)) {
            saveFile.write(object.toJSONString());
        } catch (IOException ex) {
            Logger.getLogger(JSONConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads in data for all Pokemon species in the game
     *
     * @param speciesFileName
     */
    public static void readSpeciesData(String speciesFileName) {
        parser = new JSONParser();

        try {
            object = (JSONObject) parser.parse(
                    new FileReader(speciesFileName));
            JSONArray speciesArray = (JSONArray) object.get("species");
            for (int pokedexNo = 0; pokedexNo < speciesArray.size(); pokedexNo++) {
                JSONObject speciesDetails = (JSONObject) speciesArray.get(pokedexNo);
                String name = (String) speciesDetails.get("name");
                String desc = (String) speciesDetails.get("description");
                double height = (double) speciesDetails.get("height");
                double weight = (double) speciesDetails.get("weight");

                String[] type = new String[2];
                JSONObject types = (JSONObject) speciesDetails.get("types");
                type[0] = (String) types.get("type1");
                type[1] = (String) types.get("type2");

                int bottomEvol = Math.toIntExact((long) speciesDetails.get("bottomEvolution"));
                int nextEvol = Math.toIntExact((long) speciesDetails.get("nextEvolution"));
                int hatchSteps = Math.toIntExact((long) speciesDetails.get("hatchSteps"));

                int[] baseStats = new int[8];
                JSONObject spBaseStats = (JSONObject) speciesDetails.get("speciesBaseStats");
                baseStats[0] = Math.toIntExact((long) spBaseStats.get("atk"));
                baseStats[1] = Math.toIntExact((long) spBaseStats.get("def"));
                baseStats[2] = Math.toIntExact((long) spBaseStats.get("spAtk"));
                baseStats[3] = Math.toIntExact((long) spBaseStats.get("spDef"));
                baseStats[4] = Math.toIntExact((long) spBaseStats.get("spd"));
                baseStats[5] = Math.toIntExact((long) spBaseStats.get("acr"));
                baseStats[6] = Math.toIntExact((long) spBaseStats.get("evn"));
                baseStats[7] = Math.toIntExact((long) spBaseStats.get("hp"));

                int[] mod = new int[8];
                JSONObject spMod = (JSONObject) speciesDetails.get("modifier");
                mod[0] = Math.toIntExact((long) spMod.get("atk"));
                mod[1] = Math.toIntExact((long) spMod.get("def"));
                mod[2] = Math.toIntExact((long) spMod.get("spAtk"));
                mod[3] = Math.toIntExact((long) spMod.get("spDef"));
                mod[4] = Math.toIntExact((long) spMod.get("spd"));
                mod[5] = Math.toIntExact((long) spMod.get("acr"));
                mod[6] = Math.toIntExact((long) spMod.get("evn"));
                mod[7] = Math.toIntExact((long) spMod.get("hp"));

                int catchChance = Math.toIntExact((long) speciesDetails.get("catchChance"));
                int percentMale = Math.toIntExact((long) speciesDetails.get("percentMale"));
                int evRVal = Math.toIntExact((long) speciesDetails.get("evRewardValue"));
                String evRType = (String) speciesDetails.get("evRewardType");
                boolean canEvolve = (boolean) speciesDetails.get("canEvolve");
                int evolLvl = Math.toIntExact((long) speciesDetails.get("evolveLevel"));
                //TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                boolean[] evolCond = new boolean[1];
                String[] ability = new String[1];
                int[] pctLklhdAblty = new int[1];
                String[] moveSet = new String[1];
                String[] eggGroup = new String[2];

                ArrayList<String> a = new ArrayList<>();
                JSONObject ablty = (JSONObject) speciesDetails.get("abilities");
                for (int idx = 0; idx < ablty.size(); idx++) {
                    a.add((String) ablty.get((idx + 1) + ""));
                }
                ability = a.toArray(ability);

                ArrayList<Boolean> e = new ArrayList<>();
                JSONObject evolConditions = (JSONObject) speciesDetails.get("evolveConditions");
                for (int idx = 0; idx < evolConditions.size(); idx++) {
                    e.add((Boolean) evolConditions.get((idx + 1) + ""));
                }
                Boolean[] temp = new Boolean[1];
                temp = e.toArray(temp);
                evolCond = new boolean[temp.length];
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i] != null) {
                        evolCond[i] = (boolean) temp[i];
                    } else {
                        evolCond[i] = true;
                    }
                }

                Pokedex.addSpecies(new Species(pokedexNo, name,
                        desc, eggGroup, hatchSteps, percentMale, height, weight, type,
                        baseStats, mod, catchChance, bottomEvol, nextEvol,
                        evRVal, evRType, canEvolve, evolLvl, evolCond,
                        ability, pctLklhdAblty, moveSet));
            }
        } catch (IOException ex) {
            throw new NoSavedGameException("No save file");
        } catch (ParseException ex) {
            Logger.getLogger(JSONConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads in data to create world map, gyms and gym leaders, plot and dialog,
     * and all characters
     *
     * TODO
     *
     * @param environmentFileName
     * @return
     */
    public static GridMap[] readEnvironmentData(String environmentFileName) {
        //char[][] charMap = new char[1][1];
        //PokemonCenter.setUp(charMap);
        GridMap[] worldMap = null;
        Tile[][] tiles;

        parser = new JSONParser();

        try {
            object = (JSONObject) parser.parse(
                    new FileReader(environmentFileName));
            JSONArray mapArray = (JSONArray) object.get("worldMap");
            worldMap = new GridMap[mapArray.size()];
            for (int i = 0; i < mapArray.size(); i++) {
                JSONObject map = (JSONObject) mapArray.get(i);
                String name = (String) map.get("name");
                int rows = Math.toIntExact((long) map.get("rows"));
                int cols = Math.toIntExact((long) map.get("cols"));
                String chars = removeWhitespace((String) map.get("map"));
                tiles = new Tile[rows][cols];
                JSONArray tileDetails = (JSONArray) map.get("tileDetails");
                int index = 0;
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        tiles[r][c] = makeTile(chars.charAt(index), (JSONObject) tileDetails.get(index));
                        index++;
                    }
                }
                worldMap[i] = new GridMap(name, tiles);
            }
        } catch (IOException ex) {
            throw new NoFileException("No game data found.");
        } catch (ParseException ex) {
            Logger.getLogger(JSONConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return worldMap;
    }

    public static GameCharacter[] readCharacterData(String characterFileName) {
        GameCharacter[] characters = null;

        parser = new JSONParser();

        try {
            object = (JSONObject) parser.parse(
                    new FileReader(characterFileName));
            JSONArray characterArray = (JSONArray) object.get("characters");
            characters = new GameCharacter[characterArray.size()];
            for (int i = 0; i < characterArray.size(); i++) {
                JSONObject character = (JSONObject) characterArray.get(i);
                String name = (String) character.get("name");
                JSONArray dialogueArray = (JSONArray) character.get("dialogue");
                String[] dialogue = new String[dialogueArray.size()];
                for (int j = 0; j < dialogueArray.size(); j++) {
                    JSONObject line = (JSONObject) dialogueArray.get(j);
                    dialogue[j] = (String) line.get("output");
                }
                characters[i] = new GameCharacter(name, dialogue);
            }
        } catch (IOException ex) {
            throw new NoFileException("No game data found.");
        } catch (ParseException ex) {
            Logger.getLogger(JSONConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return characters;
    }

    private static String removeWhitespace(String string) {
        return string.replaceAll("\\s", "");
    }

    private static Tile makeTile(char c, JSONObject tileDetails) {
        Tile t = null;
        boolean occupantIsObject = false;
        Object occupant = tileDetails.get("occupant");
        if (GameManager.itemsDex.get(occupant.toString()) != null) {
            occupant = GameManager.itemsDex.get(occupant.toString());
            occupant = (Item) occupant;
            occupantIsObject = true;
        }
        boolean exit = (boolean) tileDetails.get("exit");
        int row = 0;
        int col = 0;
        String direction = "";
        String room = "";
        if (exit) {
            row = Math.toIntExact((long) tileDetails.get("row"));
            col = Math.toIntExact((long) tileDetails.get("col"));
            direction = (String) tileDetails.get("direction");
            room = (String) tileDetails.get("room");
        }
        Item[] items = null;
        if ((boolean) tileDetails.get("hasItems")) {
            JSONArray theItems = (JSONArray) tileDetails.get("items");
            items = new Item[theItems.size()];
            for (int i = 0; i < theItems.size(); i++) {
                //This is gonna make a null pointer unless i make all my items beforehand
                items[i] = GameManager.itemsDex.get((String) theItems.get(i));
            }
        }
        switch (c) {
            case 'w':
                if (exit) {
                    t = new WaterTile(occupant, room, row, col, direction);
                } else {
                    t = new WaterTile(occupant);
                }
                break;
            case 'o':
                if (exit) {
                    t = new PlainTile((Item) occupant, room, row, col, direction);
                } else if (occupantIsObject){
                    t = new PlainTile((Item) occupant);
                } else {
                    t = new PlainTile(null);
                }
                break;
            case 'd':
                if (exit) {
                    t = new CaveTile(occupant, room, row, col, direction);
                } else {
                    t = new CaveTile(occupant);
                }
                break;
            case 'c':
                t = new CliffTile();
                break;
            case 'r':
                t = new RockyCliff();
                break;
            case 'f':
                if (exit) {
                    t = new ForestTile(occupant, room, row, col, direction);
                } else {
                    t = new ForestTile(occupant);
                }
                break;
            case 'm':
                if (exit) {
                    t = new MarshTile(occupant, room, row, col, direction);
                } else {
                    t = new MarshTile(occupant);
                }
                break;
            case 't':
                t = new TreeTile(items);
                break;
        }
        return t;
    }
}
