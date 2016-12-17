import menuitem

CHOICES = ["Yes", "No"]
YES = 1
NO = 2

def backaction():
    pass

BACK = menuitem.MenuItem("Back", backaction)

def print_dots():
    print_a_line("...")


def print_a_line(string):
    input(string)


def make_menu(menu_array, add_back=True):
    if add_back:
        menu = menu_array + ["Back"]
    else:
        menu = menu_array
    for i, item in enumerate(menu):
        print("    {0}.  {1:s}".format(i + 1, item))

    result = get_int("What will you do? ")
    while not (1 <= result <= len(menu)):
        result = get_int("Invalid: retry ")
    print("")
    return result


def make_title_menu(title, menu_array, add_back=True):
    print(title)
    return make_menu(menu_array, add_back)


def make_action_menu(menu_array, add_back=True):
    if add_back:
        menu = menu_array + [BACK]
    else:
        menu = menu_array
    result = make_menu(menu, False)
    return menu[result - 1].invoke()


def get_int(prompt):
    buffer = input(prompt)
    return int(buffer)


def get_string(prompt):
    buffer = input(prompt)
    return buffer.strip()
