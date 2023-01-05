import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Duration;

public class PlayerStats {

    public JTable playerStatsTable;
    public JScrollPane scroll;

    public PlayerStats() {

        final String url = "https://www.nba.com/stats/players/traditional";

        //ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Document document = Jsoup.parse(driver.getPageSource());

        String[][] data = new String[50][6];

        try {
            int rowNumber = 0;
            for (Element row : document.select("tbody.Crom_body__UYOcU tr")) {

                final String playerName = row.select(".Crom_stickySecondColumn__29Dwf.Crom_primary__EajZu.Crom_text__NpR1_").text();
                final String teamName = row.select("td.Crom_text__NpR1_:nth-of-type(3)").text();
                final String points = row.select("td:nth-of-type(9)").text();
                final String fieldGoalPercentage = row.select("td:nth-of-type(12)").text();
                final String assists = row.select("td:nth-of-type(22)").text();
                final String rebounds = row.select("td:nth-of-type(21)").text();

                data[rowNumber] = new String[] {playerName, teamName, points, fieldGoalPercentage, assists, rebounds};
                rowNumber ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] columns = {"Name", "Team", "Points", "FG%", "Assists", "Rebounds"};
        this.playerStatsTable = new JTable(data, columns);
        playerStatsTable.setPreferredScrollableViewportSize(new Dimension(600,400));
        playerStatsTable.setFillsViewportHeight(true);
        playerStatsTable.setAutoCreateRowSorter(true);

        scroll = new JScrollPane(playerStatsTable);
    }

}
