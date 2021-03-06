import uuid
import enum
import random

import pokedex
from species import EggGroup
import testpokemon

FEMALE = 0
MALE = 1
NO_GENDER = 2

MIN_LEVEL = 1
MAX_LEVEL = 100

MAX_EV = 510
MAX_EV_PER_STAT = 252

MAX_IV = 31

BASE_FRIENDLINESS = 70

SHINY_CHANCE = 10000

CONDITION_EFFECT_VALUES = {'PSN': 1.5, 'BRN': 1.5, 'PRZ': 1.5,
                           'FRZ': 2, 'SLP': 2}

class Condition(enum.Enum):
    PSN = 1.5
    BRN = 1.5
    PRZ = 1.5
    FRZ = 2
    SLP = 2


class Stats(enum.IntEnum):
    ATTACK = 0
    DEFENSE = 1
    SP_ATTACK = 2
    SP_DEFENSE = 3
    SPEED = 4
    ACCURACY = 5
    EVASIVENESS = 6
    HP = 7
    



class LevelGrowthRate(enum.Enum):
    FAST = 0
    MEDIUM_FAST = 1
    MEDIUM_SLOW = 2
    SLOW = 3




class Nature(enum.IntEnum):
    HARDY, LONELY, BRAVE, ADAMANT, NAUGHTY, BOLD, DOCILE = range(7)
    RELAXED, IMPISH, LAX, TIMID, HASTY, SERIOUS, JOLLY = range(7, 14)
    NAIVE, MODEST, MILD, QUIET, BASHFUL, RASH, CALM = range(14, 21)
    SASSY, CAREFUL, QUIRKY = range(21, 24)




class Stat:
    def __init__(self, base, iv, ev, current=-1):
        self.base = base
        if current == -1:
            self.current = base
        else:
            self.current = current
        self.iv = iv
        self.ev = ev



TESTING = True

class Pokemon:
    def __init__(self, pokedex_no, **kwargs):
        ##self.shiny = None
        ##self.nature = None
        ##self.ivs = None
        ##self.nickname = None
        ##self.level = None
        ##self.moves = None
        ##self.trainer = None

        allowed_keys = ['pokedex_no', 'name', 'nickname', 'level',
                        'gender', 'shiny', 'nature', 'moves',
                        'trainer', 'ivs']
        for key in allowed_keys:
            setattr(self, key, None)
            if key == 'level':
                setattr(self, key, 0)
        self.__dict__.update((k, v) for k, v in kwargs.items() if
                             k in allowed_keys)
        for key, value in kwargs.items():
            setattr(self, key, value)

        self.pokedex_no = pokedex_no
        self.pkmn_id = uuid.uuid4()
        self.species = testpokemon.PKDX.get_pokemon_species_info(
            self.pokedex_no)
        self.generate_gender()

        if self.shiny == None:
            self.randomize_shiny()
        self.randomize_level_growth_rate()
        self.update_exp_to_next_level()
        self.exp = 0

        self.stats = [None] * 8
        self.generate_stats(self.ivs)

        self.calculate_max_hp()
        self.current_hp = self.max_hp
        self.friendliness = BASE_FRIENDLINESS

        self.current_condition = None

        if self.nature == None:
            self.randomize_nature()

    def generate_stats(self, ivs):
        for i in range(0, len(self.stats)):
            if ivs == None:
                iv = random.randint(0, MAX_IV)
            else:
                iv = ivs[i]
            ev = 0
            if i != len(self.stats) - 1:
                base = (((2 * self.species.base_stats[i] + iv + (ev / 4)
                          * self.level) / 100) + 5)
            else:
                base = (((2 * self.species.base_stats[i] + iv + (ev / 4))
                         *self.level) / 100) + self.level + 10
            self.stats[i] = Stat(int(base), int(iv), int(ev))

    def randomize_shiny(self):
        self.shiny = random.randint(0, SHINY_CHANCE) == 0

    def randomize_level_growth_rate(self):
        self.level_growth_rate = LevelGrowthRate(random.randint(0, 3))

    def randomize_nature(self):
        i = random.randint(0, 23)
        self.nature = Nature(i)
        stat_args = [(-1, -1),
                     (Stats.ATTACK, Stats.DEFENSE),
                     (Stats.ATTACK, Stats.SPEED),
                     (Stats.ATTACK, Stats.SP_ATTACK),
                     (Stats.ATTACK, Stats.SP_DEFENSE),
                     (Stats.DEFENSE, Stats.ATTACK),
                     (-1, -1),
                     (Stats.DEFENSE, Stats.SPEED),
                     (Stats.DEFENSE, Stats.SP_ATTACK),
                     (Stats.DEFENSE, Stats.SP_DEFENSE),
                     (Stats.SPEED, Stats.ATTACK),
                     (Stats.SPEED, Stats.DEFENSE),
                     (-1, -1),
                     (Stats.SPEED, Stats.SP_ATTACK),
                     (Stats.SPEED, Stats.SP_DEFENSE),
                     (Stats.SP_ATTACK, Stats.ATTACK),
                     (Stats.SP_ATTACK, Stats.DEFENSE),
                     (Stats.SP_ATTACK, Stats.SPEED),
                     (-1, -1),
                     (Stats.SP_ATTACK, Stats.SP_DEFENSE),
                     (Stats.SP_DEFENSE, Stats.ATTACK),
                     (Stats.SP_DEFENSE, Stats.DEFENSE),
                     (Stats.SP_DEFENSE, Stats.SPEED),
                     (Stats.SP_DEFENSE, Stats.SP_ATTACK),
                     (-1, -1)]
        self.nature_stats(stat_args[self.nature])

    def nature_stats(self, affected_stats):
        raised_stat = affected_stats[0]
        lowered_stat = affected_stats[1]
        self.stats[raised_stat].base = int(self.stats[raised_stat].base * 1.1)
        self.stats[lowered_stat].base = int(self.stats[lowered_stat].base * 0.9)

    def randomize_ability():
        pass

    def generate_gender(self):
        if self.species.percent_male == -1:
            self.gender =  NO_GENDER
        if random.randint(0, 100) <= self.species.percent_male:
            self.gender = MALE
        else:
            self.gender = FEMALE

    def calculate_max_hp(self):
        hp_stat = self.stats[Stats.HP]
        self.max_hp = (((hp_stat.iv + (2 * hp_stat.base)
                         + (hp_stat.ev / 4)) * self.level)
                       / 100) + 10 + self.level
        self.max_hp = int(self.max_hp)

    def add_exp(self, exp):
        self.exp += exp
        if self.exp <= self.exp_to_next_level:
            exp = (self.exp + exp) - self.exp_to_next_level
            self.level_up()
            self.exp += exp

    def set_friendliness(self, friendliness):
        self.friendliness += (friendliness * friendliness_multiplier)
        if self.friendliness < 0:
            self.friendliness = 0
        elif self.friendliness > MAX_FRIENDLINESS:
            self.friendliness = MAX_FRIENDLINESS

    def set_nickname(self, nickname):
        self.nickname = nickname

    def can_breed(self, mate):
        if (EggGroup.UNDISCOVERED in self.egg_group or
        EggGroup.UNDISCOVERED in mate.egg_group):
            return False
        return mate.egg_group == self.egg_group and mate.gender != self.gender

    def breed(self, mate):
        if self.can_breed(mate):
            pass

    def evolve(self):
        if self.can_evolve():
            pass

    def level_up(self):
        self.level += 1
        self.evolve()
        self.update_stats()
        self.exp = 0
        self.update_exp_to_next_level()

    def update_exp_to_next_level(self):
        if self.level_growth_rate == LevelGrowthRate.FAST:
            self.exp_to_next_level = int(.8 * self.level**3)
        elif self.level_growth_rate == LevelGrowthRate.MEDIUM_FAST:
            self.exp_to_next_level = int(self.level**3)
        elif self.level_growth_rate == LevelGrowthRate.MEDIUM_SLOW:
            self.exp_to_next_level = int((1.2 * self.level**3)
            - (15 * self.level**2) + (100 * self.level) - 140)
        elif self.level_growth_rate == LevelGrowthRate.SLOW:
            self.exp_to_next_level = int(1.25 * self.level**3)

        if self.exp_to_next_level < 1:
            self.exp_to_next_level = 1

    def is_fainted(self):
        return self.current_hp == 0

    def __str__(self):
        str = ""
        if self.nickname != None:
            str = "{0} [{1}] ".format(self.nickname, self.species.name)
        else:
            str = "{0} ".format(self.species.name)
        gender = "FMN"[self.gender]
        return "{0} {1} Lvl: {2}  Exp: {3}/{4}  HP: {5}/{6}    {7}{8}".format(
            str, gender, self.level, self.exp, self.exp_to_next_level,
            self.current_hp, self.max_hp,
            self.condition_to_string() if self.current_condition != None
            else "",
            self.shiny if TESTING else None)

    def condition_to_string(self):
        if  self.current_condition != None:
            return "[{0}]".format(self.condition.name)
