/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package move;

import move.enums.Category;
import move.enums.CritHitRatio;
import move.enums.MoveEffect;
import pokemon.PkmnType;
import pokemon.Pokemon;

/**
 *
 * @author ellen
 */
public class TestMove extends Move {

    public TestMove() {
        super("Test", "Does nothing", " didn't do anything!", Category.PHYSICAL,
                PkmnType.NORMAL, 30, MoveEffect.DAMAGE);
    }

    @Override
    int performOtherMove(Pokemon user, Pokemon target, CritHitRatio critHitRatio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canUseOnSelf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnvironmental() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean goesFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
