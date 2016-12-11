package ui;

/**
 * Usese
 */



import basicFunctions.UserInput;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;


public class GameBoardGUI {
    private static final Dimension SIZE = new Dimension(750, 450);

    //window frame
    private final JFrame frame;
    private Container contentPane;
    
    //grid
    //make me!
    
    // label
    private JLabel exitPointLabel;
    private JLabel scoreLabel;
    private JLabel validGuess;
    
    //text entry
    private JTextField guessRowText, guessColText;
    
    //dropdown menu
    private JComboBox dropdown;
    private static String[] baffleArray;    // dropdown menu contents
    
    //text area for history
    private JTextArea theHistory;
    
    
    /**
     * Constructs a new game GUI
     */
    public GameBoardGUI() {
        frame = new JFrame("Baffle Box");
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
    }
    
    /**
     * Makes the gameboard contents
     */
    private void makeContent() {
        contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        frame.setContentPane(contentPane);
        
        makeLeftRegion();
        makeRightRegion(); 
    }

    /**
     * Contains the theGrid and numbers
     */
    private void makeLeftRegion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(6,6));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // put grid here
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.black);
        //gridPanel.setToolTipsEnabled(false);
        panel.add(contentPanel, BorderLayout.CENTER);
        //gridPanel.setGrid(theGrid);
        //final int cellSize = gridPanel.getCellSize();
        
        JLabel gameText = new JLabel("Test String");
       gameText.setForeground(Color.white);
       //gameText.setFont();
       contentPanel.add(gameText);
        
        JPanel controlsPanel = new JPanel();
        panel.add(controlsPanel, BorderLayout.SOUTH);
       
        contentPane.add(panel);
    }
    
        /**
     * Contains the gameplay options
     */
    private void makeRightRegion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.PINK);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        panel.add(optionsPanel);
                
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.red);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        optionsPanel.add(imagePanel);
        
        JPanel choicesPanel = new JPanel();
        choicesPanel.setBackground(Color.blue);
        choicesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        optionsPanel.add(choicesPanel);
        
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        contentPane.add(panel);
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
     * Handles behavior of the 'quit' menu item
     */
    private class exitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
