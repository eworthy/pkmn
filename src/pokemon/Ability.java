/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

/**
 *
 * @author ellen
 */
public enum Ability {
    ADAPTABILITY, //my same type attack bonus is 2
    AFTERMATH, //the foe dealing the finishing hit against me loses 1/4th max HP if it made physical contact
    ANALYTIC, //if I'm the last pkmn to attack this turn, my attack increases 30% for just this turn
    ANGER_POINT, //if I take a crit hit, my attack is maximised
    ANTICIPATION, //i am warned of my foe's one hit KO or supereffective moves
    ARENA_TRAP, //my foe cannot flee (unless they are flying type, or have Levitate); stays in effect for one turn after i switch out
    BATTLE_ARMOR, //my foe cannot land crit hits against me
    BIG_PECKS, //my defense cannot be lowered
    BLAZE, //when my HP is less than 1/3, Fire type moves boosted 50%
    CHLOROPHYLL, //in Sunny weather, my speed doubles
    CLEAR_BODY, //opponents cannot lower my stats
    COMPETITIVE, //whenever one of my stats is lowered, my SpAtk is raised twice
    COMPOUND_EYES, //my accuracy is boosted 30%
    CUTE_CHARM, //if my foe makes physical contact, there's a 30% chance they'll be infatuated
    DAMP, //Explosion and Selfdestruct don't work while i am in battle
    DRY_SKIN, //my HP is restored in rain or when hit by water moves, but I am weak against Fire and lose HP in Sunny weather
    EARLY_BIRD, //I wake up from sleep in half the number of turns, rounded down
    EFFECT_SPORE, //if my foe makes physical contact, there's a 10% chance they'll be paralysed, poisoned, or fall asleep
    FLAME_BODY, //30% chance of burning foe on physical hit
    FLASH_FIRE, //when hit by fire attack, fire type moves deal 1.5x damage, immune to fire
    FOREWARN, //reveals foe's strongest move
    FRISK, //check what item the foe has
    GLUTTONY, //use a berry when HP <= 50%
    GUTS, //attack increased 50% when i have a condition
    HEALER, //30% chance of healing allie's status conditions
    HUGE_POWER, //doubles my attack stat
    HUSTLE, //damage by me from physical attacks increased by 50%, accuracy only 80% of max
    HYDRATION, //heals my status problems in the rain
    HYPER_CUTTER, //foe cannot lower my attack
    ICE_BODY, //i regain 1/16 max HP in a hailstorm
    IMMUNITY, //prevents me from getting poisoned
    INFILTRATOR, //ignore certain dodges/block
    INNER_FOCUS, //i don't flinch
    INSOMNIA, //i can't fall asleep
    INTIMIDATE, //lowers all foes' attack unless they have abilities otherwise
    KEEN_EYE, //my accuracy cannot be lowered, i ignore changes to foe's evasiveness
    KLUTZ, //i can't use my held item
    LEAF_GUARD, //i can't get conditions in Strong Sunlight
    LEVITATE, //i am immune to ground type moves, can't be trapped by arena trap, no damage from spikes
    LIGHTNING_ROD, //all electric moves hit me, do no damage, SpAtk is raised unless i'm ground type
    LIMBER, //I can't be paralysed
    LIQUID_OOZE, //if a foe tries to take my HP, they lose that HP instead
    MAGIC_GUARD, //I only take damage from direct attack moves
    MAGMA_ARMOR, //I can't be frozen
    MAGNET_PULL, //Steel tyeps can't escape
    MOLD_BREAKER, //I ignore the effect of abilities like big pecks
    MOTOR_DRIVE, //speed increases if I'm hit by an electric type move (I take no damage from electric attacks)
    MOXIE, //my attack increases when I knock out an opponent
    NATURAL_CURE, //my status problems are healed when I switch out
    NO_GUARD, //ensures all attacks land (mine and my foes' against me)
    NORMALIZE, //all my moves act as Normal type
    OBLIVIOUS, //I can't become infatuated or be taunted
    OVERCOAT, //I'm immune to damage from weather or 'powder' moves
    OVERGROW, //when my HP is less than 1/3, Grass type moves boosted 50%
    OWN_TEMPO, //im immune to confusion
    PICKPOCKET, //if my foe makes physical contact, I steal their item
    PICKUP, //i have a 10% chance of picking up an item after a battle, or get someone else's item if it was consumed in battle
    POISON_POINT, //if my foe makes physical contact, there's a 30% chance they'll be poisoned
    POISON_TOUCH, //if i use a physical contact move, 20% chance of poisoning my foe
    PRANKSTER, //status moves have increased speed priority
    PURE_POWER, //doubles my attack stat
    QUICK_FEET, //my speed is 150% if I have a status (or if I'm paralysed it's at 100%)
    RECKLESS, //power up moves that have recoil damage (120%)
    RIVALRY, //my attack + sp attack are increased 25% if my foe is the same gender, decreased if 'opposite'
    ROCK_HEAD, //I don't take recoil damage
    ROUGH_SKIN, //opponent takes 1/16 of their max HP when hitting me physically
    RUN_AWAY, //guarantees escape from wild battle (unless a trapping move is in effect)
    SANDSTREAM, //starts a sandstorm when I enter the battle
    SAND_VEIL, //boosts my evasion in a sandstorm
    SAP_SIPPER, //my attack is boosted if I'm hit by grass move, i take no damage from grass attacks
    SCRAPPY, //my normal and fighting moves can hit ghost types
    SERENE_GRACE, //if my move has a chance of an additional effect, that chance is doubled
    SHED_SKIN,  //every turn I have a 33% chance of healing my condition
    SHEER_FORCE, //moves with a secondary effect are boosted 33% but lose the effect
    SHELL_ARMOR, //opponent cannot crit hit against me
    SHIELD_DUST, //secondary effects of attacks against me can't occur
    SIMPLE, //effectiveness of all stat changes to me is doubled
    SKILL_LINK, //my moves that hit 2-5 times always hit 5 times
    SNIPER, //my crit hits deal 225% damage rather than 150%
    SNOW_CLOAK, //my evasion is boosted in a hailstorm
    SNOW_WARNING, //starts a hailstorm when I enter the battle
    SOLAR_POWER, //in Sunny weather, my SpAtk is 150%, but I lose 1/8 max HP every turn
    SOLID_ROCK, //damage by supereffective moves against me decreased 25%
    SPEED_BOOST, //my speed increases at the end of every turn
    STATIC, //if my foe makes physical contact, there's a 30% chance they'll be paralysed
    STEADFAST, //my speed is boosted every time I flinch
    STICKY_HOLD, //my item cannot be stolen
    STORM_DRAIN, //all water moves hit me, do no damage, SpAtk is raised
    STURDY, //i'm immune to One Hit KO moves; if I was at full HP and a move would have killed me, I keep 1 HP
    SUPER_LUCK, //increases my crit hit chance
    SWARM, //when my HP is less than 1/3, Bug type moves boosted 50%
    SWIFT_SWIM, //in Rain, my speed doubles
    SYNCHRONIZE, //if I'm poisoned, burned, or paralysed, so is my foe
    TANGLED_FEET, //if I'm confused, my evasion is raised
    TECHNICIAN, //if my move's power is <= 60, it is boosted 50%
    THICK_FAT, //Fire and Ice moves against me deal only 50% damage
    TINTED_LENS, //if my move is "not very effective" against my foe, the power is doubled
    TORRENT, //when my HP is less than 1/3, Water type moves boosted 50%
    TRACE, //my ability becomes my opponents (goes back if I'm switched out)
    UNAWARE, //i ignore my foe's stat changes
    UNBURDEN, //my speed is doubled after I consume my item
    UNNERVE, //my opponent is too scared to use their berry
    VITAL_SPIRIT, //I can't fall asleep
    VOLT_ABSORB, //I heal up to 1/4 max HP when hit with an electric move
    WATER_ABSORB, //I heal up to 1/4 max HP when hit with an water move
    WATER_VEIL, //i can't be burned
}   
