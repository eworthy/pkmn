/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.Serializable;
import move.Move;

/**
 *
 * @author ellen
 */
public class Species implements Serializable {

    private final Move[] moveSet;
    //private final PkmnSpecies species;
    /**
     * Pokedex no of bottom evolution
     */
    private final int bottomEvolution;
    /**
     * Pokedex no of next evolution (-1 if none)
     */
    private final int nextEvolution;
    private final PkmnType[] types;
    private final EggGroup[] eggGroup;
    private final int hatchSteps;
    private final String name;
    private final String description;
    private final int[] speciesBaseStats, modifier;
    private final int pokedexNo;
    private final int catchChance; // 0 - 100
    private final int evRewardValue;
    private final Stat evRewardType;
    private final int percentMale;
    private final boolean canEvolve;
    private final int evolveLevel;
    private final boolean[] evolveCondtions;
    private final Ability[] abilities;
    private final int[] percentLikelihoodAbilities;

    private final double height;
    private final double weight;

//    /**
//     * Makes a Species with no individual variables to store in the Pokedex
//     *
//     * @param species
//     * @param description
//     * @param eggGroup
//     * @param percentMale
//     * @param height
//     * @param weight
//     * @param type
//     * @param speciesBaseStats
//     * @param modifier
//     * @param catchChance
//     * @param bottomEvolution
//     * @param nextEvolution
//     * @param evRewardValue
//     * @param evRewardType
//     * @param canEvolve
//     * @param evolveLevel
//     * @param evolveConditions
//     * @param abilities
//     * @param percentLikelihoodAbilities
//     *
//     */
//    public Species(PkmnSpecies species, String description,
//            EggGroup[] eggGroup, int percentMale, double height, double weight,
//            PkmnType[] type, int[] speciesBaseStats, int[] modifier,
//            int catchChance, PkmnSpecies bottomEvolution,
//            PkmnSpecies nextEvolution,
//            int evRewardValue, Stat evRewardType,
//            boolean canEvolve, int evolveLevel, boolean[] evolveConditions,
//            Ability[] abilities, int[] percentLikelihoodAbilities) {
////        this.species = species;
//        this.name = species.name();
//        this.description = description;
//        this.eggGroup = eggGroup;
//        this.percentMale = percentMale;
//        this.height = height;
//        this.weight = weight;
//        this.speciesBaseStats = speciesBaseStats;
//        this.modifier = modifier;
//        this.types = type;
//        this.catchChance = catchChance;
////        this.bottomEvolution = bottomEvolution;
////        this.nextEvolution = nextEvolution;
//        this.evRewardValue = evRewardValue;
//        this.evRewardType = evRewardType;
//        this.canEvolve = canEvolve;
//        this.evolveLevel = evolveLevel;
//        this.evolveCondtions = evolveConditions;
//        this.abilities = abilities;
//        this.percentLikelihoodAbilities = percentLikelihoodAbilities;
//        
//        pokedexNo = species.ordinal();
//
//        Pokedex.addSpecies(this);
//    }
    public Species(int pokedexNo, String name, String desc, String[] eggGroup,
            int hatchSteps, int percentMale, double height, double weight,
            String[] types, int[] baseStats, int[] mod, int catchChance,
            int bottomEvol, int nextEvol, int evRewardValue, String evRewardType,
            boolean canEvolve, int evolveLevel, boolean[] evolCond,
            String[] abilities, int[] pctLklhdAblty, String[] moveSet) {
        this.pokedexNo = pokedexNo;
        this.name = name;
        this.description = desc;
        this.eggGroup = stringToEggGroup(eggGroup);
        this.hatchSteps = hatchSteps;
        this.percentMale = percentMale;
        this.height = height;
        this.weight = weight;
        this.types = stringToTypes(types);
        this.speciesBaseStats = baseStats;
        this.modifier = mod;
        this.catchChance = catchChance;
        this.bottomEvolution = bottomEvol;
        this.nextEvolution = nextEvol;
        this.evRewardValue = evRewardValue;
        this.evRewardType = stringToStat(evRewardType);
        this.canEvolve = canEvolve;
        this.evolveLevel = evolveLevel;
        this.evolveCondtions = evolCond;
        this.abilities = stringToAbilities(abilities);
        this.percentLikelihoodAbilities = pctLklhdAblty;
        this.moveSet = stringToMoves(moveSet);
    }

    public Move[] getMoveSet() {
        return moveSet;
    }

//    public PkmnSpecies getSpecies() {
//        return species;
//    }
    public int getBottomEvolution() {
        return bottomEvolution;
    }

    public int getNextEvolution() {
        return nextEvolution;
    }

    public PkmnType[] getTypes() {
        return types;
    }

    public EggGroup[] getEggGroup() {
        return eggGroup;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int[] getSpeciesBaseStats() {
        return speciesBaseStats;
    }

    public int[] getModifier() {
        return modifier;
    }

    public int getPokedexNo() {
        return pokedexNo;
    }

    public int getCatchChance() {
        return catchChance;
    }

    public int getEvRewardValue() {
        return evRewardValue;
    }

    public Stat getEvRewardType() {
        return evRewardType;
    }

    public int getPercentMale() {
        return percentMale;
    }

    public boolean canEvolve() {
        return canEvolve;
    }

    public int getEvolveLevel() {
        return evolveLevel;
    }

    public boolean[] getEvolveCondtions() {
        return evolveCondtions;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public int[] getPercentLikelihoodAbilities() {
        return percentLikelihoodAbilities;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String typesToString() {
        String s = "";
        if (types.length == 1) {
            return s + types[0].name();
        }
        for (int i = 0; i < types.length; i++) {
            s += types[i].name();
            if (i < types.length - 1) {
                s += "/";
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return pokedexNo + ": " + name + "[" + typesToString() + "] + \n"
                + description;
    }

    private EggGroup[] stringToEggGroup(String[] eggGroup) {
        int nonNullCount = 0;
        EggGroup[] e = new EggGroup[eggGroup.length];
        for (int i = 0; i < e.length; i++) {
            if (eggGroup[i] != null) {
                e[i] = EggGroup.valueOf(eggGroup[i]);
                nonNullCount++;
            }
        }
        EggGroup[] eFinal = new EggGroup[nonNullCount];
        System.arraycopy(e, 0, eFinal, 0, eFinal.length);
        return eFinal;
    }

    private PkmnType[] stringToTypes(String[] types) {
        int nonNullCount = 0;
        PkmnType[] t = new PkmnType[types.length];
        for (int i = 0; i < t.length; i++) {
            if (types[i] != null) {
                t[i] = PkmnType.valueOf(types[i]);
                nonNullCount++;
            }
        }
        PkmnType[] tFinal = new PkmnType[nonNullCount];
        System.arraycopy(t, 0, tFinal, 0, tFinal.length);
        return tFinal;
    }

    private Stat stringToStat(String evRewardType) {
        return Stat.valueOf(evRewardType);
    }

    private Ability[] stringToAbilities(String[] abilities) {
        int nonNullCount = 0;
        Ability[] a = new Ability[abilities.length];
        for (int i = 0; i < a.length; i++) {
            if (abilities[i] != null) {
                a[i] = Ability.valueOf(abilities[i]);
                nonNullCount++;
            }
        }
        Ability[] aFinal = new Ability[nonNullCount];
        System.arraycopy(a, 0, aFinal, 0, aFinal.length);
        return aFinal;
    }

    private Move[] stringToMoves(String[] moveSet) {
        return null;
    }

    public int getHatchSteps() {
        return hatchSteps;
    }
}
