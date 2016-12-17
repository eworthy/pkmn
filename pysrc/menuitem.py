class MenuItem:
    def __init__(self, title, action):
        self.title = title
        self.action = action

    def __str__(self):
        return self.title

    def invoke(self, *args):
        return self.action(*args)

