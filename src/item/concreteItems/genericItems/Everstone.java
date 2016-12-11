/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item.concreteItems.genericItems;

import item.GenericItem;

/**
 *
 * @author ellen
 */
public class Everstone extends GenericItem {

    public Everstone() {
        super("Everstone",
              "An item to be held by a Pokémon. The Pokémon holding this peculiar stone is prevented from evolving.",
              200);
    }

    @Override
    public void use() {
        super.getPokemonHoldingThis().addEvolveCondition(false);
    }

}
