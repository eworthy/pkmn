class Pokedex:
    NAME = "POKEDEX"
    NUM_PKMN = 150

    def __init__(self):
        self.pkmn_info = {}

    def add(self, pokedex_no, species):
        self.pkmn_info[pokedex_no] = species

    def get_pokemon_species_info(self, pokedex_no):
        return self.pkmn_info[pokedex_no]
