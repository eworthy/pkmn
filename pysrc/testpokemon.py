import pokemon
import pokedex
from species import Species

PKDX = pokedex.Pokedex()

def test_pokemon():
    s = Species(0, "TEST", "desc", "egg", 12,
                [1, 1, 1, 1, 1, 1, 1, 1], 50, 1, 1,
                "type", 25, "bottom", "next", 1, "ev type",
                "abilities", "moveset", True, 16,
                True)
    PKDX.add(0, s)

    for i in range(0, 100):
        p = pokemon.Pokemon(0, level=10)
        ##if p.shiny:
        print(p)
    p = pokemon.Pokemon(0, nickname="Honey", level=100)
    print(p)

