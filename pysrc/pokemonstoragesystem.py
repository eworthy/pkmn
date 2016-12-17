import userinput

TITLE = "Pokemon Storage System:"
CHOICES = ["Deposit Pokémon",
           "Pick Up Pokémon",
           "Switch Pokémon",
           "Move Items"]

NUM_BOXES = 18

class Box:
    def __init__(self, name):
        self.name = name
        self.pokemon = []

    def set_name(self, name):
        if name in storage:
            raise ValueError("Duplicate name")
        storage[name] = self
        if self.name in storage:
            del storage[self.name]
        self.name = name


storage = {}

def set_initial_boxes():
    for i in range(NUM_BOXES):
        name = "BOX {0}".format(i + 1)
        storage[name] = Box(name)
    

def rename(old_name, new_name):
    ##todo decide where to handle exceptions (keyerror, valueerror)
    storage[old_name].set_name(new_name)


def access_system():
    choice = userinput.make_title_menu(TITLE, CHOICES)
    ##todo this should be an action menu


def deposit_pokemon():
    pkmn_index = userinput.make_title_menu("Which Pokémon?",
                                           gamemanager.get_party_pkmn_list)
    deposit(gamemanager.get_player().get_party_member(pkmn_index))


def deposit(pkmn):
    box_idx = view_box_list()
    if add_to_box(box_idx, pkmn):
        input("{0} was placed in {1}.".format(pkmn.get_name(), 0)) ##TODO
    else:
        choice = userinput.make_menu(
            "{0} is full. Choose another box?".format(1), ##TODO
            userinput.CHOICES)
        if choice == userinput.YES:
            deposit(pkmn)


def get_pokemon():
    box_idx = view_box_list()
    pkmn_idx = view_box_contents(box_idx)
    if gamemanager.get_player().add_to_party(storage[box_idx][pkmn_idx]):
        input("{0} was added to {1}'s party.".format(
            storage[box_idx][pkmn_idx].get_name(),
            gamemanager.get_player().get_name()))


def switch_pkmn():
    pass


def move_items():
    pass


def view_box_list():
    return userinput.make_title_menu(TITLE, storage) ##will these print right?


def view_box_contents():
    pass


def add_to_box(box_idx, pkmn):
    pass
