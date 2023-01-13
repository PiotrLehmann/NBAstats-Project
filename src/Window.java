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
        int screenHeight = (int) screenSize.getHeight();
        int screenWidth = (int) screenSize.getWidth();
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
        JPanel homePanel = new JPanel(new GridLayout(1,2));
        HomeScreen homeScreen = new HomeScreen();
        homePanel.add(homeScreen.logoLabel);
        homePanel.add(homeScreen.textLabel);
        panelContainer.add(homePanel, "Home");
        logger.info("Home Panel - successfully created");

        // Teams Statistics Panel
        JPanel teamsRankingPanel = new JPanel(new BorderLayout());
        TeamStats teamStats = new TeamStats();
        teamsRankingPanel.add(teamStats.scroll2, BorderLayout.CENTER);
        panelContainer.add(teamsRankingPanel, "Teams Ranking");
        logger.info("Teams Ranking Panel - successfully created");

        // Players statistics Panel
        JPanel playersRankingsPanel = new JPanel(new BorderLayout());
        PlayerStats playerStats = new PlayerStats();
        playersRankingsPanel.add(playerStats.scroll, BorderLayout.CENTER);
        panelContainer.add(playersRankingsPanel, "Players Ranking");
        logger.info("Players Ranking Panel - successfully created");

        JPanel todayScoresPanel = new JPanel(new BorderLayout());
        panelContainer.add(todayScoresPanel, "Today Scores");

        HighestScoresPanel highestScoresPanel = new HighestScoresPanel();
        HighestScoresData highestScoresData = new HighestScoresData();
        highestScoresPanel.addHistogramColumn(highestScoresData.data[4][0], Integer.parseInt(highestScoresData.data[4][1]), Color.DARK_GRAY);
        highestScoresPanel.addHistogramColumn(highestScoresData.data[3][0], Integer.parseInt(highestScoresData.data[3][1]), Color.RED);
        highestScoresPanel.addHistogramColumn(highestScoresData.data[2][0], Integer.parseInt(highestScoresData.data[2][1]), Color.ORANGE);
        highestScoresPanel.addHistogramColumn(highestScoresData.data[1][0], Integer.parseInt(highestScoresData.data[1][1]), Color.YELLOW);
        highestScoresPanel.addHistogramColumn(highestScoresData.data[0][0], Integer.parseInt(highestScoresData.data[0][1]), Color.GREEN);
        highestScoresPanel.layoutHistogram();
        panelContainer.add(highestScoresPanel, "Highest Scores");

        cl.show(panelContainer, "Home");

        JMenuBar menuBar = new JMenuBar();

        JMenu home = new JMenu("Home");
        JMenu rankingsMenu = new JMenu("Rankings");
        JMenu scoresMenu = new JMenu("Scores");

        JMenuItem backHome = new JMenuItem("Back Home");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem teamsRanking = new JMenuItem("Teams Ranking");
        JMenuItem playersRanking = new JMenuItem("Players Ranking");
        JMenuItem todayScores = new JMenuItem("Today Scores");
        JMenuItem highestScores = new JMenuItem("Highest All Time Scores");

        rankingsMenu.add(teamsRanking);
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

        teamsRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Teams Ranking");
                logger.trace("Clicked Teams Ranking - currently on Teams Ranking");
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
                cl.show(panelContainer, "Highest Scores");
                logger.trace("Clicked Today Scores - currently on Highest Scores");
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
