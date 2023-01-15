import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * TodayScores class is responsible for creating TodayScoresPanel,
 * connecting to the espn website and parsing data from it
 */
public class TodayScores {

    /**
     * table of today scores in USA
     */
    JTable todayScoresTable;

    JScrollPane scroll;

    /**
     * logger for TodayScores class
     */
    private static final Logger logger = Logger.getLogger(TodayScores.class);

    public TodayScores() {

        String url = "https://www.espn.com/nba/scoreboard/_/date/" + findYesterdayFormattedDate();

        WebDriver driver = null;
        try {
            driver = new FirefoxDriver(new FirefoxOptions().addPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170").addArguments("--headless"));
            driver.get(url);
        } catch (Exception e) {
            logger.error("Error occurred while starting driver, because NBA.com is reloading data");
            logger.info("Please wait and restart application");
            if (driver != null) {
                driver.quit();
            }
            System.exit(1);
        }

        List<WebElement> teamSections = null;
        try {
            WebElement section = driver.findElement(By.xpath("//section[contains(@class, 'Card gameModules')]"));
            teamSections = section.findElements(By.xpath("//ul[contains(@class,'ScoreboardScoreCell__Competitors')]//li"));
        } catch (Exception e) {
            logger.error("Error occurred while parsing Xpaths from rows, because NBA.com is reloading data");
            logger.info("Please wait and restart application");
            driver.quit();
            System.exit(1);
        }

        int numberOfTeamSections = 0;
        List<String> listOfTeamNames = new ArrayList<>();
        List<String> listOfTeamScores = new ArrayList<>();

        for (WebElement teamSection : teamSections) {

            List<WebElement> teamNames = null;
            List<WebElement> teamScores = null;

            try {
                teamNames = teamSection.findElements(By.xpath("//div[contains(@class,'ScoreCell__Truncate--scoreboard')]//a"));
                teamScores = teamSection.findElements(By.xpath("//div[contains(@class,'ScoreCell__Score h4')]"));
            } catch (Exception e) {
                logger.error("Error occurred while parsing Xpaths from columns, because NBA.com is reloading data");
                logger.info("Please wait and restart application");
                driver.quit();
                System.exit(1);
            }

            for(WebElement teamName : teamNames) {
                listOfTeamNames.add(teamName.getText());
            }
            for(WebElement teamScore : teamScores) {
                listOfTeamScores.add(teamScore.getText());
            }
            numberOfTeamSections++;
        }

        Object[][] data = new Object[numberOfTeamSections/2][6];

        for (int match = 1; match <= numberOfTeamSections/2 ; match++) {

            data[match-1][1] = listOfTeamNames.get(2*match);
            data[match-1][2] = listOfTeamScores.get(2*match);
            data[match-1][3] = listOfTeamScores.get((2*match)-1);
            data[match-1][4] = listOfTeamNames.get((2*match)-1);
            data[match-1][0] = new ImageIcon("images/"+listOfTeamNames.get(2*match)+".png");
            data[match-1][5] = new ImageIcon("images/"+listOfTeamNames.get((2*match)-1)+".png");
        }


        Object[] columns = {"----", "----", "----", "----", "----", "----"};
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 1, 2, 3, 4 -> String.class;
                    case 0, 5 -> ImageIcon.class;
                    default -> Object.class;
                };
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment((JLabel.CENTER));

        this.todayScoresTable = new JTable(model);
        todayScoresTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        todayScoresTable.setRowHeight(100);
        todayScoresTable.setFont(new Font("Arial",Font.BOLD,18));
        todayScoresTable.setFillsViewportHeight(true);
        todayScoresTable.setAutoCreateRowSorter(true);
        todayScoresTable.getTableHeader().setVisible(false);
        todayScoresTable.setShowVerticalLines(false);
        todayScoresTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        todayScoresTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        todayScoresTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        todayScoresTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        scroll = new JScrollPane(todayScoresTable);

        driver.quit();
    }

    /**
     * method which parses and formats yesterday date in Poland to get today
     * scores from the USA by using it to create link to the website
     * @return returns yesterday date in good format
     */
    private static String findYesterdayFormattedDate() {
        LocalDate today = LocalDate.now();
        String yesterday = (today.minusDays(2)).format(DateTimeFormatter.ISO_DATE);
        return yesterday.replace("-","");
    }

}

