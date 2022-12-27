import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {

    // Logger
    private static final Logger logger = Logger.getLogger(Window.class);

    // To get the resolution of the display (in px)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Window() {

        // local variables
        int screenHeight = (int) screenSize.getHeight(); // down-casting is safe here
        int screenWidth = (int) screenSize.getWidth(); // same here
        int windowHeight = 600;
        int windowWidth = 800;
        logger.info("Screen resolution & window size set");

        // Images
        ImageIcon logo = new ImageIcon("images/LOGO500GIT.png");

        // Adjusting window appearance
        this.setTitle("NBA Stats");
        this.setIconImage(logo.getImage());

        // Creating a CardLayout
        CardLayout cl = new CardLayout();

        // Panel Container
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(cl);

        // Front Panel with LOGO
        JPanel homePanel = new JPanel(new BorderLayout());
        panelContainer.add(homePanel, "Home");
        JLabel logoLabel = new JLabel(logo);
        homePanel.add(logoLabel, BorderLayout.CENTER);

        // Western Conference Ranking Panel
        JPanel westRankingsPanel = new JPanel(new BorderLayout());
        panelContainer.add(westRankingsPanel, "West Ranking");

        JPanel eastRankingsPanel = new JPanel(new BorderLayout());
        panelContainer.add(eastRankingsPanel, "East Ranking");

        JPanel playersRankingsPanel = new JPanel(new BorderLayout());
        panelContainer.add(playersRankingsPanel, "Players Ranking");

        JPanel todayScoresPanel = new JPanel(new BorderLayout());
        panelContainer.add(todayScoresPanel, "Today Scores");

        JPanel highestScoresPanel = new JPanel(new BorderLayout());
        panelContainer.add(highestScoresPanel, "Highest Scores");

        cl.show(panelContainer, "Home");

        JMenuBar menuBar = new JMenuBar();

        JMenu home = new JMenu("Home");
        JMenu rankingsMenu = new JMenu("Rankings");
        JMenu scoresMenu = new JMenu("Scores");

        JMenuItem backHome = new JMenuItem("Back Home");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem westRankings = new JMenuItem("Western Conference");
        JMenuItem eastRankings = new JMenuItem("Eastern Conference");
        JMenuItem playersRanking = new JMenuItem("Players Ranking");
        JMenuItem todayScores = new JMenuItem("Today Scores");
        JMenuItem highestScores = new JMenuItem("Highest Scores");

        rankingsMenu.add(westRankings);
        rankingsMenu.add(eastRankings);
        rankingsMenu.add(playersRanking);

        scoresMenu.add(todayScores);
        scoresMenu.add(highestScores);

        home.add(backHome);
        home.add(exit);

        menuBar.add(home);
        menuBar.add(rankingsMenu);
        menuBar.add(scoresMenu);

        // action listeners to all menu items
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Home");
                logger.trace("You are on Home");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.warn("Initially closed application");
                System.exit(0);
            }
        });

        westRankings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "West Ranking");
                logger.trace("Clicked Western Ranking - currently on Western Ranking");
            }
        });

        eastRankings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "East Ranking");
                logger.trace("Clicked Eastern Ranking - currently on Eastern Ranking");
            }
        });

        playersRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Players Ranking");
                logger.trace("Clicked Players Ranking - currently on Players Ranking");
            }
        });

        todayScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Today Scores");
                logger.trace("Clicked Today Scores - currently on Today Scores");
            }
        });

        highestScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Clicked Highest Scores - currently on Highest Scores");
            }
        });

        // Creating the window
        this.setJMenuBar(menuBar);
        this.add(panelContainer);
        this.setSize(windowWidth, windowHeight);
        this.setLocation((screenWidth - windowWidth)/2, (screenHeight - windowHeight)/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        logger.trace("Window created successfully");
    }

}
