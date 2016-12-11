package character;

import java.io.Serializable;
import pokemon.Pokemon;

public class Trainer extends GameCharacter implements Serializable {

    private Pokemon[] party = new Pokemon[6];

    public Trainer(String name, String[] dialogue, String[] spriteFiles) {
        super(name, dialogue, spriteFiles);
    }

    public Pokemon[] getParty() {
        return party;
    }

    public Pokemon[] getNonNullParty() {
        Pokemon[] pArray = new Pokemon[numNonNullMembers()];
        int indexToAddAt = 0;
        for (Pokemon p : party) {
            if (p != null) {
                pArray[indexToAddAt] = p;
                indexToAddAt++;
            }
        }
        return pArray;
    }

    private int numNonNullMembers() {
        int i = 0;
        for (Pokemon p : party) {
            if (p != null) {
                i++;
            }
        }
        return i;
    }

    public Pokemon getPartyMember(int i) {
        return party[i];
    }

    public boolean addToParty(Pokemon p) {
        if (p.getTrainer() != this) {
            p.setTrainer(this);
        }
        int i = 0;
        for (Pokemon pkmn : party) {
            while (pkmn != null) {
                i++;
            }
        }
        if (i != party.length) {
            party[i] = p;
            return true;
        } else {
            return false;
        }
    }

    public void setTeam(Pokemon[] team) {
        if (team.length == 6) {
            party = team;
        }
    }
}
