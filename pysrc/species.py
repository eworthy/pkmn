import enum

class PkmnType(enum.Enum):
    FIRE = 0
    WATER = 1
    GRASS = 2
    NORMAL = 3
    FLYING = 4
    ELECTRIC = 5
    GROUND = 6
    ICE = 7
    DARK = 8
    GHOST = 9
    DRAGON = 10
    PSYCHIC = 11
    BUG = 12
    POISON = 13
    ROCK = 14
    STEEL = 15
    FAIRY = 16
    FIGHTING = 17




class EggGroup(enum.Enum):
    MONSTER = 0
    AMPHIBIOUS = 1
    BUG = 2
    FLYING = 3
    FIELD = 4
    FAIRY = 5
    GRASS = 6
    BIPEDAL = 7
    WATER = 8
    MINERAL = 9
    AMORPHOUS = 10
    FISH = 11
    DRAGON = 12
    UNDISCOVERED = 13




class Species:
    def __init__(self, pokedex_no, name, desc, egg_group, hatch_steps,
                 base_stats, percent_male, height, weight,
                 types, catch_chance,
                 bottom_evol, next_evol, ev_reward_value, ev_reward_type,
                 abilities, moveset, can_evolve, evolve_level,
                 evolve_conditions, seen=False, found=False):
        self.pokedex_no = pokedex_no
        self.name = name
        self.desc = desc
        self.egg_group = egg_group
        self.hatch_steps = hatch_steps
        self.base_stats = base_stats
        self.percent_male = percent_male
        self.height = height
        self.weight = weight
        self.types = types
        self.catch_chance = catch_chance
        self.bottom_evol = bottom_evol
        self.next_evol = next_evol
        self.ev_reward_value = ev_reward_value
        self.ev_reward_type = ev_reward_type
        self.seen = seen
        self.found = found
