import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.List;


/**
 * HighestScoresData class is responsible for creating two-dimensional
 * table of data parsed from NBA website. This data is used in HighestScoresPanel later
 */
public class HighestScoresData {

    private static final Logger logger = Logger.getLogger(PlayerStats.class);
    public String[][] data;

    public HighestScoresData() {

        final String url = "https://www.nba.com/stats/alltime-leaders";

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

        data = new String[5][2];

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
                    case 1 -> data[numberOfRow][0] = col.getText();
                    case 4 -> data[numberOfRow][1] = col.getText();
                }
                numberOfColumn++;
            }
            if(numberOfRow == 4) {
                break;
            }
            numberOfRow++;
        }
        driver.quit();
    }

}

