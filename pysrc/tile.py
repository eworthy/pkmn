include random

##Tile types:
"""
  PLAIN:
    Grass
    SnowCovered
    Sand

  ATTACK RISK:
    Water
    Marsh
    Cave
    Forest

  MOVEMENT SLOWED:
    Sand (Deep)
    Snow (Deep)

  SPECIAL:
    Ice

  OTHER:
    Berry (soil)
    Cliff
    WaterFall
    RockyCliff
    Tree
"""
class Direction:
    NORTH, EAST, SOUTH, WEST = range(4)

class Tile:
    def __init__(self, occupant):
        self.occupant = occupant
        ##TODO how do i do constructors w varying numbers of args?

    def isOccupied(self):
        return self.occupant not none



class RiskTile(Tile):
    ##has an encounter rate
    def encounter(self):
        return random.randint(100) <= 10


class CaveTile(RiskTile):
    pass
