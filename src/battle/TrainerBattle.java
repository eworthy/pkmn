/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import basicFunctions.UserInput;
import pokemon.Pokemon;
import character.Trainer;

/**
 * TODO special rewards!
 * @author ellen
 */
public class TrainerBattle extends Battle {

    private Trainer[] opponents;
    private Pokemon[] opponentPkmn;
    private int rewardMoney;
    private Object specialReward;

    public TrainerBattle(BattleType battleType,
            Trainer[] opponents, int rewardMoney, int lossPenalty) {
        super(battleType, lossPenalty);
        this.opponents = opponents;
        opponentPkmn = setOpponentPkmn();
        this.rewardMoney = rewardMoney;
    }
    
    public TrainerBattle(BattleType battleType,
            Trainer[] opponents, int rewardMoney, int lossPenalty,
            Object specialReward) {
        this(battleType, opponents, rewardMoney, lossPenalty);
        this.specialReward = specialReward;
    }

    /**
     * Get the value of opponents
     *
     * @return the value of opponents
     */
    public Trainer[] getOpponents() {
        return opponents;
    }
    
    private Pokemon[] setOpponentPkmn() {
        int numPkmn = 0;
        for (Trainer t : opponents) {
            numPkmn += t.getNonNullParty().length;
        }
        Pokemon[] p = new Pokemon[numPkmn];
        //TODO add the pokemon to p
        return p;
    }

    protected boolean battleEnd() {
        return super.battleEnd(opponentPkmn);
    }

    @Override
    public void flee() {
        UserInput.printALine(UserInput.keyInput, "There's no running away!");
    }

    public Pokemon[] getOpponentPkmn() {
        return opponentPkmn;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public Object getSpecialReward() {
        return specialReward;
    }
}
