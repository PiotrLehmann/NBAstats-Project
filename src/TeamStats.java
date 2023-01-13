import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.*;
import java.util.List;


public class TeamStats {

    public JTable teamStatsTable;
    public JScrollPane scroll2;
    private static final Logger logger = Logger.getLogger(TeamStats.class);

    public TeamStats() {

        final String url = "https://www.nba.com/stats/teams/traditional";

        WebDriver driver = null;
        try {
            driver = new FirefoxDriver(new FirefoxOptions().addPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170").addArguments("--headless"));
            driver.get(url);
        } catch (Exception e) {
            logger.error("Error occurred while starting driver, because NBA.com is reloading");
            logger.info("Please wait and restart application");
            System.exit(1);
        }

        String[][] data = new String[30][8];

        WebElement table = driver.findElement((By.xpath("//table[contains(@class, 'Crom_table__p1iZz')]//tbody")));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        int numberOfRow = 0;
        for (WebElement row: rows) {
            int numberOfColumn = 0;
            List<WebElement> cols = row.findElements(By.xpath("td"));
            for (WebElement col : cols) {
                switch (numberOfColumn) {
                    case 1, 2, 3, 4, 5, 6, 7, 8 -> data[numberOfRow][numberOfColumn - 1] = col.getText();
                }
                numberOfColumn++;
            }
            numberOfRow++;
        }

        Object[] columns = {"Team", "GP", "W", "L", "WIN%", "MIN", "PTS", "FGM"};
        this.teamStatsTable = new JTable(data, columns);
        teamStatsTable.setPreferredScrollableViewportSize(new Dimension(500,400));
        teamStatsTable.setFillsViewportHeight(true);
        teamStatsTable.setAutoCreateRowSorter(true);
        teamStatsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        teamStatsTable.getTableHeader().setOpaque(false);
        teamStatsTable.getTableHeader().setBackground(Color.BLACK);
        teamStatsTable.getTableHeader().setForeground(Color.WHITE);
        scroll2 = new JScrollPane(teamStatsTable);

        driver.quit();
    }

}
