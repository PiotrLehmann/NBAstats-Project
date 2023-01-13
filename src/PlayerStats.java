import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Color;
import java.util.List;

public class PlayerStats {

    public JTable playerStatsTable;
    public JScrollPane scroll;
    private static final Logger logger = Logger.getLogger(PlayerStats.class);

    public PlayerStats() {

        final String url = "https://www.nba.com/stats/players/traditional";

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

        Object[][] data = new Object[50][6];

        List<WebElement> rows = null;
        try {
            WebElement table = driver.findElement((By.xpath("//table[contains(@class, 'Crom_table__p1iZz')]//tbody")));
            rows = table.findElements(By.tagName("tr"));
        } catch (Exception e) {
            logger.error("Error occurred while parsing Xpaths from rows, because NBA.com is reloading data");
            logger.info("Please wait and restart application");
            driver.quit();
            System.exit(1);
        }

        int numberOfRow = 0;
        for (WebElement row : rows) {

            int numberOfColumn = 0;
            List<WebElement> cols = null;

            try {
                cols = row.findElements(By.xpath("td"));
            } catch (Exception e) {
                logger.error("Error occurred while parsing Xpaths from columns, because NBA.com is reloading data");
                logger.info("Please wait and restart application");
                driver.quit();
                System.exit(1);
            }

            assert cols != null;
            for (WebElement col : cols) {
                switch (numberOfColumn) {
                    case 1, 2 -> data[numberOfRow][numberOfColumn - 1] = col.getText();
                    case 3, 4, 5, 6 -> data[numberOfRow][numberOfColumn - 1] = Double.parseDouble(col.getText());
                }
                numberOfColumn++;
            }
            numberOfRow++;
        }

        Object[] columns = {"Name", "Team", "Points", "FG%", "Assists", "Rebounds"};
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0, 1 -> String.class;
                    case 2, 3, 4, 5 -> Double.class;
                    default -> throw new IllegalStateException("Unexpected value: " + column);
                };
            }
        };

        this.playerStatsTable = new JTable(model);
        playerStatsTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        playerStatsTable.setFillsViewportHeight(true);
        playerStatsTable.setAutoCreateRowSorter(true);
        playerStatsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        playerStatsTable.getTableHeader().setOpaque(false);
        playerStatsTable.getTableHeader().setBackground(Color.BLACK);
        playerStatsTable.getTableHeader().setForeground(Color.WHITE);
        scroll = new JScrollPane(playerStatsTable);

        driver.quit();
    }

}
