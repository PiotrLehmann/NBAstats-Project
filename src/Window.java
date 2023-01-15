import org.apache.log4j.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;



public class Window extends JFrame {

    JPanel panelContainer;
    private static final Logger logger = Logger.getLogger(Window.class);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Window() {

        int screenHeight = (int) screenSize.getHeight();
        int screenWidth = (int) screenSize.getWidth();
        int windowHeight = 600;
        int windowWidth = 800;
        logger.info("Screen resolution & window size set");

        ImageIcon logo = new ImageIcon("images/LOGO500GIT.png");

        this.setTitle("NBA Stats");
        this.setIconImage(logo.getImage());

        CardLayout cl = new CardLayout();

        panelContainer = new JPanel();
        panelContainer.setLayout(cl);

        JMenuBar menuBar = new JMenuBar();

        JMenu home = new JMenu("Home");
        JMenu rankingsMenu = new JMenu("Rankings");
        JMenu scoresMenu = new JMenu("Scores");

        JMenuItem backHomeItem = new JMenuItem("Back Home");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem teamsRankingItem = new JMenuItem("Teams Ranking");
        JMenuItem playersRankingItem = new JMenuItem("Players Ranking");
        JMenuItem todayScoresItem = new JMenuItem("Today Scores");
        JMenuItem highestScoresItem = new JMenuItem("Highest All Time Scores");

        rankingsMenu.add(teamsRankingItem);
        rankingsMenu.add(playersRankingItem);

        scoresMenu.add(todayScoresItem);
        scoresMenu.add(highestScoresItem);

        home.add(backHomeItem);
        home.add(exitItem);

        menuBar.add(home);
        menuBar.add(rankingsMenu);
        menuBar.add(scoresMenu);

        backHomeItem.setEnabled(false);
        exitItem.setEnabled(false);
        teamsRankingItem.setEnabled(false);
        playersRankingItem.setEnabled(false);
        todayScoresItem.setEnabled(false);
        highestScoresItem.setEnabled(false);

        HomeScreenThread homeScreenThread = new HomeScreenThread();
        TeamsRankingThread teamsRankingThread = new TeamsRankingThread();
        PlayersRankingThread playersRankingThread = new PlayersRankingThread();
        TodayScoresThread todayScoresThread = new TodayScoresThread();
        HighestScoresThread highestScoresThread = new HighestScoresThread();

        homeScreenThread.start();
        teamsRankingThread.start();
        playersRankingThread.start();
        todayScoresThread.start();
        highestScoresThread.start();

        try {
            homeScreenThread.join();
            teamsRankingThread.join();
            playersRankingThread.join();
            todayScoresThread.join();
            highestScoresThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        backHomeItem.setEnabled(true);
        exitItem.setEnabled(true);
        teamsRankingItem.setEnabled(true);
        playersRankingItem.setEnabled(true);
        todayScoresItem.setEnabled(true);
        highestScoresItem.setEnabled(true);

        cl.show(panelContainer, "Home");

        backHomeItem.addActionListener(e -> {
            cl.show(panelContainer, "Home");
            logger.trace("You are on Home");
        });

        exitItem.addActionListener(e -> {
            logger.warn("Initially closed application");
            System.exit(0);
        });

        teamsRankingItem.addActionListener(e -> {
            cl.show(panelContainer, "Teams Ranking");
            logger.trace("Clicked Teams Ranking - currently on Teams Ranking");
        });

        playersRankingItem.addActionListener(e -> {
            cl.show(panelContainer, "Players Ranking");
            logger.trace("Clicked Players Ranking - currently on Players Ranking");
        });

        todayScoresItem.addActionListener(e -> {
            cl.show(panelContainer, "Today Scores");
            logger.trace("Clicked Today Scores - currently on Today Scores");
        });

        highestScoresItem.addActionListener(e -> {
            cl.show(panelContainer, "Highest Scores");
            logger.trace("Clicked Today Scores - currently on Highest Scores");
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

    private class HomeScreenThread extends  Thread {
        @Override
        public void run() {
            JPanel homePanel = new JPanel(new GridLayout(1,2));
            HomeScreen homeScreen = new HomeScreen();
            homePanel.add(homeScreen.logoLabel);
            homePanel.add(homeScreen.textLabel);
            panelContainer.add(homePanel, "Home");
            logger.info("Home Panel - successfully created");
        }
    }

    private class TeamsRankingThread extends  Thread {
        @Override
        public void run() {
            JPanel teamsRankingPanel = new JPanel(new BorderLayout());
            TeamStats teamStats = new TeamStats();
            teamsRankingPanel.add(teamStats.scroll2, BorderLayout.CENTER);
            panelContainer.add(teamsRankingPanel, "Teams Ranking");
            logger.info("Teams Ranking Panel - successfully created");
        }
    }

    private class PlayersRankingThread extends  Thread {
        @Override
        public void run() {
            JPanel playersRankingsPanel = new JPanel(new BorderLayout());
            PlayerStats playerStats = new PlayerStats();
            playersRankingsPanel.add(playerStats.scroll, BorderLayout.CENTER);
            panelContainer.add(playersRankingsPanel, "Players Ranking");
            logger.info("Players Ranking Panel - successfully created");
        }
    }

    private class TodayScoresThread extends  Thread {
        @Override
        public void run() {
            JPanel todayScoresPanel = new JPanel(new BorderLayout());
            TodayScores todayScores = new TodayScores();
            todayScoresPanel.add(todayScores.scroll, BorderLayout.CENTER);
            panelContainer.add(todayScoresPanel, "Today Scores");
            logger.info("Today Scores Panel - successfully created");
        }
    }

    private class HighestScoresThread extends  Thread {
        @Override
        public void run() {
            HighestScoresPanel highestScoresPanel = new HighestScoresPanel();
            HighestScoresData highestScoresData = new HighestScoresData();
            highestScoresPanel.addHistogramColumn(highestScoresData.data[4][0], Integer.parseInt(highestScoresData.data[4][1]), Color.DARK_GRAY);
            highestScoresPanel.addHistogramColumn(highestScoresData.data[3][0], Integer.parseInt(highestScoresData.data[3][1]), Color.RED);
            highestScoresPanel.addHistogramColumn(highestScoresData.data[2][0], Integer.parseInt(highestScoresData.data[2][1]), Color.ORANGE);
            highestScoresPanel.addHistogramColumn(highestScoresData.data[1][0], Integer.parseInt(highestScoresData.data[1][1]), Color.YELLOW);
            highestScoresPanel.addHistogramColumn(highestScoresData.data[0][0], Integer.parseInt(highestScoresData.data[0][1]), Color.GREEN);
            highestScoresPanel.layoutHistogram();
            panelContainer.add(highestScoresPanel, "Highest Scores");
            logger.info("Highest Scores Panel - successfully created");
        }
    }

}
