package battle;

import basicFunctions.UserInput;
import move.Move;
import pokemon.Pokemon;
import ui.GameManager;

public class Battle {

    private static final int MOVE = 0;
    private static final int TARGET = 2;
    private static final int ATTACKER = 1;

    private static final String[] LOCATION_CHOICES = {"Attack", "Bag", "Flee", "Pokemon"};

    private static final int ATTACK = 0;
    private static final int BAG = 1;
    private static final int FLEE = 2;
    private static final int POKEMON = 3;

    private Pokemon[] opponents;
    private Pokemon[] playerPkmn;
    private int numOpponents;
    private Zone battleZone;
    private BattleType battleType;
    /* This must be negative! */
    private int lossPenalty;

    private int turnCounter = 0;

    public Battle(BattleType battleType, int lossPenalty) {
        switch (battleType) {
            case SINGLE:
                numOpponents = 1;
                break;
            case DOUBLE:
                numOpponents = 2;
                break;
            case TRIPLE:
            case ROTATION:
                numOpponents = 3;
                break;
        }
        this.lossPenalty = lossPenalty;
    }

    public Battle(BattleType battleType, Pokemon[] opponents, int lossPenalty) {
        this(battleType, lossPenalty);
        this.opponents = opponents;
        playerPkmn = new Pokemon[numOpponents];
        populatePokemon();
    }

    private void populatePokemon() {
        for (int i = 0; i < numOpponents; i++) {
            Pokemon p = null;
            while (GameManager.getPlayer().getPartyMember(i) != null
                    && !GameManager.getPlayer().getPartyMember(i).isFainted()) {
                p = GameManager.getPlayer().getPartyMember(i);
            }
            playerPkmn[i] = p;
        }
    }

    public Pokemon getOpponent() {
        return getOpponent(0);
    }

    public int getNumOpponents() {
        return numOpponents;
    }

    public String[] getOpponentsList() {
        String[] s = new String[numOpponents];
        for (int i = 0; i < s.length; i++) {
            s[i] = opponents[i].battleString();
        }
        return s;
    }

    public Pokemon getOpponent(int pkmnIndex) {
        return opponents[pkmnIndex];
    }

    public Zone getBattleZone() {
        return battleZone;
    }

    public BattleType getBattleType() {
        return battleType;
    }

    private String[] getPlayerPkmnList() {
        String[] s = new String[numOpponents];
        for (int i = 0; i < numOpponents; i++) {
            if (playerPkmn[i] != null) {
                s[i] = playerPkmn[i].battleString();
            }
        }
        return s;
    }

    private String[] getOpponentPkmnList() {
        String[] s = new String[numOpponents];
        for (int i = 0; i < numOpponents; i++) {
            if (opponents[i] != null) {
                s[i] = opponents[i].battleString();
            }
        }
        return s;
    }
    
    public String[] getLocationChoices() {
        return LOCATION_CHOICES;
    }

    /**
     * --------------------------------------------USER INPUT
     * BEHAVIORS---------------------------------------------
     */
    public void attack() {
        Object[][] playerMoves = new Object[numOpponents][3]; //row: Move, attacker, target
        //Move[] playerMoves = new Move[numOpponents];
        switch (battleType) {
            case SINGLE:
            case DOUBLE:
            case TRIPLE:
                for (int i = 0; i < numOpponents; i++) {
                    int attackerIndex = UserInput.makeMenu(UserInput.keyInput, "Which Pokemon?", getPlayerPkmnList());
                    playerMoves[i][ATTACKER] = playerPkmn[attackerIndex];
                    playerMoves[i][MOVE] = ((Pokemon) playerMoves[i][ATTACKER]).getMoves()[(UserInput.makeMenu(UserInput.keyInput, "Which move?",
                            ((Pokemon) playerMoves[i][ATTACKER]).movesToStringArray()))];
                    if (((Move) playerMoves[i][MOVE]).hasTarget()) {
                        int index;
                        if (((Move) playerMoves[i][MOVE]).canUseOnSelf()) {
                            index = UserInput.makeMenu(UserInput.keyInput, "Which Pokemon?", allPokemon());
                        } else {
                            index = UserInput.makeMenu(UserInput.keyInput, "Which Pokemon?", allPokemonButMe(attackerIndex));
                        }
                        if (index >= numOpponents) {
                            playerMoves[i][TARGET] = opponents[index];
                        } else {
                            playerMoves[i][TARGET] = playerPkmn[index];
                        }
                    } else if (!((Move) playerMoves[i][MOVE]).hasTarget()) {
                        playerMoves[i][TARGET] = playerMoves[i][ATTACKER];
                    }
                }
                Object[][] enemyMoves = getEnemyPokemonMoves();

                Object[][] sortedMoves = sortMoves(playerMoves, enemyMoves);
                for (int i = 0; i < sortedMoves.length; i++) {
                    ((Move) sortedMoves[i][MOVE]).perform((Pokemon) sortedMoves[i][ATTACKER],
                            (Pokemon) sortedMoves[i][TARGET]);
                }
                break;
            case ROTATION:
                break;
        }
    }

    /**
     * Sorts the moves of the player pokemon and the AI pokemon by speed.
     * Handles special moves, such as Quick Attack, which always go first, and
     * if multiple Pokemon are using these special moves, they are sorted by
     * speed of Pokemon within there as well. If two Pokemon have the same
     * speed, the order of attack is determined randomly, with approximately
     * equal chance for either to go first.
     *
     * @param playerMoves Object[][] containing the information for each Player
     * Pokemon's move, target, and the user
     * @param enemyMoves Object[][] containing the information for each enemy/AI
     * Pokemon's move, target, and the user
     * @return the sorted Object[][] containing the complete list of moves,
     * users, and targets in the order they should be performed. The format of
     * the array is: [move, attacker, target]
     */
    private Object[][] sortMoves(Object[][] playerMoves, Object[][] enemyMoves) {
        Object[][] temp = new Object[numOpponents * 2][3];
        int indexLastSorted = 0;
        // check special order moves (eg QuickAttack)
        // TODO need to check *every* move in each array until we find which ones are special
        // TODO go through until we've made sure we've found any and all special moves
        for (int i = indexLastSorted; i < numOpponents; i++) {
            int idxPlayerMove = -1;
            int idxEnemyMove = -1;
            if (((Move) playerMoves[i][MOVE]).goesFirst()) {
                idxPlayerMove = i;
            }
            if (((Move) enemyMoves[i][MOVE]).goesFirst()) {
                idxEnemyMove = i;
            }
            if (idxPlayerMove != -1 && idxEnemyMove != -1) {
                int playerSpeed = ((Pokemon) playerMoves[i][ATTACKER]).getSpeed();
                int enemySpeed = ((Pokemon) enemyMoves[i][ATTACKER]).getSpeed();
                if (playerSpeed > enemySpeed) {
                    temp[i] = playerMoves[i];
                    indexLastSorted++;
                } else if (enemySpeed > playerSpeed) {
                    temp[i] = enemyMoves[i];
                    indexLastSorted++;
                } else if (playerSpeed == enemySpeed) {
                    if (GameManager.generator.nextBoolean()) {
                        temp[i] = playerMoves[i];
                        indexLastSorted++;
                    } else {
                        temp[i] = enemyMoves[i];
                        indexLastSorted++;
                    }
                }
            }
        }
        // Sort moves by Pokemon speed
        int movesSorted = 0;
        while (movesSorted < numOpponents * 2) {
            for (int i = 0; i < numOpponents; i++) {

            }
            movesSorted++;
        }
        return temp;
    }

    private Object[][] getEnemyPokemonMoves() {
        Object[][] s = new Object[numOpponents][3]; // [Move] [Attacker] [Target]

        for (int i = 0; i < numOpponents; i++) {
            s[i][ATTACKER] = opponents[i];
            Object[] temp = getBestMove((Pokemon) s[i][ATTACKER]);
            s[i][MOVE] = temp[MOVE];
            s[i][TARGET] = temp[TARGET];
        }

        return s;
    }

    /**
     * Picks move/target combos and chooses the best
     *
     * @param attacker
     * @param target
     * @param m
     * @param i
     */
    private Object[] getBestMove(Pokemon attacker) {
        Object[][] s = new Object[numOpponents][3];
        Pokemon target = null;
        Move[] possibleMoves = new Move[numOpponents];
        int indexBestMove = -1;
        double maxRatio = 0; //TODO actual logic for choice

        /* Get the best move for each target */
        for (int i = 0; i < numOpponents; i++) {
            s[i][ATTACKER] = opponents[i];
            s[i][TARGET] = playerPkmn[i];
            s[i][MOVE] = attacker.pickMove(target);
        }
        /* Get which of the moves is the best */
        for (int i = 0; i < numOpponents; i++) {
            double ratio = (possibleMoves[i].getPower() / possibleMoves[i].getAccuracy());
            if (ratio >= maxRatio) {
                maxRatio = ratio;
                indexBestMove = i;
            }
        }
        //possibleMoves[indexBestMove];
        return s[indexBestMove]; // if I did this right it should return the row containing the target/move for the best move
    }

    public void bag() {
        GameManager.viewBattleInventory(UserInput.keyInput);
    }

    public void flee() {
        //TODO
    }

    public void pickPokemon() {
        int switchOutIndex = UserInput.makeMenu(UserInput.keyInput,
                "Which Pokemon?",
                UserInput.convertToMakeMenuArray(getPlayerPkmnList()));
        if (switchOutIndex != UserInput.convertToMakeMenuArray(getPlayerPkmnList()).length) {
            int switchInIndex = UserInput.makeMenu(UserInput.keyInput,
                    "Which Pokemon?",
                    UserInput.convertToMakeMenuArray(GameManager.getPartyPokemonList()));
            if (switchInIndex != UserInput.convertToMakeMenuArray(GameManager.getPartyPokemonList()).length) {
                Pokemon switchOut = playerPkmn[switchOutIndex];
                Pokemon switchIn = GameManager.getPlayer().getPartyMember(switchInIndex);
                int choice = UserInput.makeMenu(UserInput.keyInput, switchIn.pkmnSummary(), UserInput.CHOICES);
                while (choice != UserInput.YES) {
                    while (switchIn != null && (switchIn.isFainted() || switchIn == switchOut)) {
                        //TODO separate statements for fainted v already in battle
                        UserInput.printALine(UserInput.keyInput, switchIn.getName() + " cannot battle!");
                        switchInIndex = UserInput.makeMenu(UserInput.keyInput, "Which Pokemon?", GameManager.getPartyPokemonList());
                    }
                }
                playerPkmn[switchOutIndex] = switchIn;
            }
        }
    }

    private String[] allPokemon() {
        String[] s = new String[numOpponents * 2];
        for (int i = 0; i < numOpponents; i++) {
            if (playerPkmn[i] != null) {
                s[i] = playerPkmn[i].battleString();
            }
        }
        for (int i = 0; i < numOpponents; i++) {
            if (opponents[i] != null) {
                s[i + numOpponents] = opponents[i].battleString();
            }
        }
        return s;
    }

    private String[] allPokemonButMe(int myIndex) {
        String[] s = new String[numOpponents * 2 - 1];
        for (int i = 0; i < numOpponents; i++) {
            if (playerPkmn[i] != null && i != myIndex) {
                s[i] = playerPkmn[i].battleString();
            }
        }
        for (int i = 0; i < numOpponents; i++) {
            if (opponents[i] != null) {
                s[i + numOpponents] = opponents[i].battleString();
            }
        }
        return s;
    }

    protected boolean battleEnd(Pokemon[] opponents) {
        boolean b = true;
        for (Pokemon p : opponents) {
            if (!p.isFainted()) {
                b = false;
            }
        }
        for (Pokemon p : GameManager.getPlayer().getParty()) {
            if (!p.isFainted()) {
                b = false;
            }
        }
        return b;
    }

    private boolean battleWon() {
        boolean b = false;
        if (battleEnd(opponents)) {
            for (Pokemon p : GameManager.getPlayer().getParty()) {
                if (!p.isFainted()) {
                    b = true;
                }
            }
        }
        return b;
    }

    public String runBattle() {
        String name = GameManager.getPlayer().getName();
        while (!battleEnd(opponents)) {
            int choice = UserInput.makeMenu(UserInput.keyInput,
                    "What will you do?",
                    LOCATION_CHOICES);
            switch (choice) {
                case ATTACK:
                    attack();
                    break;
                case BAG:
                    bag();
                    break;
                case FLEE:
                    flee();
                    break;
                case POKEMON:
                    pickPokemon();
                    break;
            }
            
            turnCounter++;
        }
        //TODO pkmn experience, move to Pkmn Centre if lost
        String s;
        if (!battleWon()) {
            GameManager.getPlayer().setMoney(lossPenalty);
            s = name + " panicked and dropped $" + lossPenalty + "!\n"
                    + name + " managed to get to the Pokemon Center.";
        } else {
            s = name + " won!";
        }
        return s;
    }

    public int getNumTurns() {
        return turnCounter;
    }
}
