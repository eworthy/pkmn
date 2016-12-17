import userinput

test_menu = ["hello", "goodbye"]

def test_make_menu():
    choice = userinput.make_menu(test_menu)
    print(choice)


if __name__ == "__main__":
    test_make_menu()


