import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerStats {

    public JTable playerStatsTable;
    public JScrollPane scroll;

    public PlayerStats() {

        final String url = "https://www.nba.com/stats/players/traditional";

        WebDriver driver = new ChromeDriver();
        driver.get(url);

        String[][] data = new String[50][6];

        WebElement table = driver.findElement((By.xpath("//table[contains(@class, 'Crom_table__p1iZz')]//tbody")));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        int numberOfRow = 0;
        for (WebElement row: rows) {
            int numberOfColumn = 0;
            List<WebElement> cols = row.findElements(By.xpath("td"));
            for (WebElement col : cols) {
                switch (numberOfColumn) {
                    case 1, 2, 3, 4, 5, 6 -> data[numberOfRow][numberOfColumn - 1] = col.getText();
                }
                numberOfColumn++;
            }
            numberOfRow++;
        }

        Object[] columns = {"Name", "Team", "Points", "FG%", "Assists", "Rebounds"};
        this.playerStatsTable = new JTable(data, columns);
        playerStatsTable.setPreferredScrollableViewportSize(new Dimension(600,400));
        playerStatsTable.setFillsViewportHeight(true);
        playerStatsTable.setAutoCreateRowSorter(true);

        scroll = new JScrollPane(playerStatsTable);
    }

}
