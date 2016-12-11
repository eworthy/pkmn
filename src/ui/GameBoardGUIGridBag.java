package ui;


import basicFunctions.UserInput;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import ui.enums.Screen;

/**
 * Grid squares will be 15 x 15 pixels
 * @author ellen
 */
public class GameBoardGUIGridBag {

    private static final Dimension SIZE = new Dimension(750, 450);

    //window frame
    private final JFrame frame;
    private Container contentPane;
    private GridBagConstraints c;

    /**
     * Constructs a new game GUI
     */
    public GameBoardGUIGridBag() {
        frame = new JFrame("Pokemon");
        frame.setMinimumSize(SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        makeMenus();
        makeContent();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Makes the menu bar and menus
     */
    private void makeMenus() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //set up Game menu
        menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(menu);

        // add Game menu items
        menuItem = new JMenuItem("New Game");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.addActionListener(new newListener());
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        Event.CTRL_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(new exitListener());
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                        Event.CTRL_MASK));
        menu.add(menuItem);

        // set up Help menu
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(menu);

        menuItem = new JMenuItem("How to Play");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.addActionListener(new helpListener());
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A,
                        Event.CTRL_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("About");
        menuItem.addActionListener(new aboutListener());
        menu.add(menuItem);
    }

    /**
     * Makes the gameboard contents
     */
    private void makeContent() {
        contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        frame.setContentPane(contentPane);

        makeLeftRegion();
        contentPane.add(new JSeparator(JSeparator.VERTICAL));
        makeRightRegion();
    }

    /**
     * Contains the theGrid and numbers
     */
    private void makeLeftRegion() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(7, 15, 10, 10));
        // put grid here
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.black);
        c.anchor = GridBagConstraints.NORTH;
        c.gridy = 0;
        contentPanel.setPreferredSize(new Dimension(450, 300)); //275
        contentPanel.setMinimumSize(new Dimension(450, 300));
        contentPanel.setMaximumSize(new Dimension(450, 300));
        panel.add(contentPanel, c);

        //allows changing content display based on game mode
        switch (GameManager.getGameState()) {
            case POKEDEX:
            //just like map
            case MAP:
                //shows ur character walkin around
                contentPanel.add(mapScreen(Screen.MAIN));
                break;
            case BATTLE:
                //shows the battleground w pkmn
                contentPanel.add(battleScreen(Screen.MAIN));
                break;
            case INPUT:
                //shows what you're entering + maybe a keyboard thing?
                contentPanel.add(inputScreen(Screen.MAIN));
                break;
            case CONVERSATION:
                //shows who ur talking to plus their dialogue
                contentPanel.add(conversationScreen(Screen.MAIN));
                break;
        }
//        JLabel gameText = new JLabel("Long Test String");
//       gameText.setForeground(Color.white);
//       //gameText.setFont();
//       contentPanel.add(gameText);

        JPanel controlsPanel = new JPanel();
        //controlsPanel.setBackground(Color.red);
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        controlsPanel.setPreferredSize(new Dimension(450, 100));
        controlsPanel.setPreferredSize(new Dimension(450, 100));
        controlsPanel.setPreferredSize(new Dimension(450, 100));
        panel.add(controlsPanel, c);

        contentPane.add(panel);
    }

    /**
     * Contains the gameplay options
     */
    private void makeRightRegion() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        panel.setPreferredSize(new Dimension(275, 400));
        panel.setPreferredSize(new Dimension(275, 400));
        panel.setPreferredSize(new Dimension(275, 400));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        panel.setBackground(Color.GREEN);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.red);
        imagePanel.setPreferredSize(new Dimension(250, 150));
        imagePanel.setPreferredSize(new Dimension(250, 150));
        imagePanel.setPreferredSize(new Dimension(250, 150));
        //imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        c.gridy = 0;
        panel.add(imagePanel, c);

        JPanel optionsPanel = new JPanel();
        c.gridy = 1;
        optionsPanel.setBackground(Color.PINK);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
//        optionsPanel.setPreferredSize(new Dimension(250, 250));
//        optionsPanel.setPreferredSize(new Dimension(250, 250));
//        optionsPanel.setPreferredSize(new Dimension(250, 250));
        panel.add(optionsPanel, c);

        switch (GameManager.getGameState()) {
            case INPUT:
            //just like map
            case CONVERSATION:
            //just like map
            case MAP:
                //shows the town map or the dowsing mchn
                imagePanel.add(mapScreen(Screen.SECONDARY));
                break;
            case BATTLE:
                //shows rest of team thumbnails maybe?
                imagePanel.add(battleScreen(Screen.SECONDARY));
                break;
            case POKEDEX:
                //shows pkmn info etc in pokedex
                imagePanel.add(pokedexScreen(Screen.SECONDARY));
                break;
        }

        switch (GameManager.getGameState()) {
            case INPUT:
            //just like map
            case CONVERSATION:
            //just like map
            case MAP:
                //regular options (like bottom screen on ds)
                optionsPanel.add(mapScreen(Screen.OPTIONS));
                break;
            case BATTLE:
                //battle choices (eg attack, etc)
                optionsPanel.add(battleScreen(Screen.OPTIONS));
                break;
            case POKEDEX:
                //control the pokedex (search, etc)
                optionsPanel.add(pokedexScreen(Screen.OPTIONS));
                break;
        }

        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        contentPane.add(panel);
    }

    private JPanel mapScreen(Screen screen) {
        JPanel p = new JPanel();
        JLabel text = new JLabel("MAP Test");
        p.add(text);
        return p;
    }

    private JPanel battleScreen(Screen screen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JPanel pokedexScreen(Screen screen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JPanel inputScreen(Screen screen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JPanel conversationScreen(Screen screen) {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        switch (screen) {
            case MAIN:
                int index = 0;
                JButton b = new JButton("this is just a placeholder");
                c.gridy = 0;
                p.add(b, c);
                JLabel text = new JLabel("");
                c.gridy = 1;
                p.add(text, c);
                p.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int keyCode = e.getKeyCode();
                        switch(keyCode) {
                            case KeyEvent.VK_ENTER:
                                text.setText(GameManager.getConversationPartner().getDialogue()[index]);
                                break;
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                });
                break;
        }
        return p;
    }

    /**
     * Handles behavior of 'new game' menu item
     */
    private class newListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameManager.startGame(UserInput.keyInput);
            frame.repaint();
        }
    }

    /**
     * Handles behavior of 'how to play' menu item
     */
    private class helpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame,
                    "Put help text here");
        }
    }

    /**
     * Handles behavior of 'about' menu item
     */
    private class aboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame,
                    "Put about text here");
        }
    }

    /**
     * Handles behavior of the 'quit' menu item
     */
    private class exitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
