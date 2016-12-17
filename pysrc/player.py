import enum
import uuid

class GameCharacter:
    def __init__(self, name):
        self.name = name

    def set_name(self, name):
        self.name = name



class Trainer(GameCharacter):
    PARTY_SIZE = 6

    def __init__(self, name):
        super().__init__(self, name)
        self.party = []

    def add_to_party(self, pkmn):
        if len(self.party) == self.PARTY_SIZE:
            return False
        self.party.append(pkmn)
        return True

    def set_team(self, party):
        self.party = party



INVENTORY_TITLES = ["Items", "Medicine", "Pokeballs", "Berry Pouch",
                    "Battle Items", "TMs and HMs", "Key Items"]

@enum.unique
class Inventories(enum.IntEnum):
    GENERAL_ITEMS = 0
    MEDICINE = 1
    POKEBALLS = 2
    BERRY_POUCH = 3
    BATTLE_ITEMS = 4
    TMS_AND_HMS = 5
    KEY_ITEMS = 6

class Player(Trainer):
    def __init__(self, name):
        super().__init__(self, name)
        self.inventory = Inventory()
        self.my_pkmn = []
        self.money = 0
        self.player_id = uuid.uuid4()

    def set_money(self, change):
        self.money += change

    def add_pokemon(self, pkmn):
        self.my_pkmn + [pkmn]
        pkmn.set_trainer(self)
        return self.my_pkmn

    def get_stored_pokemon(self):
        return self.my_pkmn

    def set_stored_pokemon(self, stored_pkmn):
        self.my_pkmn = stored_pkmn

    def choose_pokemon(self):
        pass



class Inventory:
    def __init__(self):
        self.inventory = {
            Inventories.GENERAL_ITEMS: [],
            Inventories.MEDICINE: [],
            Inventories.POKEBALLS: [],
            Inventories.BERRY_POUCH: [],
            Inventories.BATTLE_ITEMS: [],
            Inventories.TMS_AND_HMS: [],
            Inventories.KEY_ITEMS: []
        }

    def add_to_inventory(self, item):
        pass

    def get_inventory_item(self, inventory_idx, item_idx):
        return self.inventory[inventory_idx][item_idx]

    def get_sub_inventory(self, inventory_idx):
        return self.inventory[inventory_idx]
