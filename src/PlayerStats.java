import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PlayerStats {

    public JTable playerStatsTable;
    public JScrollPane scroll;
    private static final Logger logger = Logger.getLogger(PlayerStats.class);

    public PlayerStats() {

        final String url = "https://www.nba.com/stats/players/traditional";

        WebDriver driver = new FirefoxDriver(new FirefoxOptions().addPreference("general.useragent.override","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170").addArguments("--headless"));
        driver.get(url);

        Object[][] data = new Object[50][6];

        WebElement table = driver.findElement((By.xpath("//table[contains(@class, 'Crom_table__p1iZz')]//tbody")));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        int numberOfRow = 0;
        for (WebElement row: rows) {
            int numberOfColumn = 0;
            List<WebElement> cols = row.findElements(By.xpath("td"));
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
                return switch(column) {
                    case 0,1 -> String.class;
                    case 2,3,4,5 -> Double.class;
                    default -> throw new IllegalStateException("Unexpected value: " + column);
                };
            }
        };

        this.playerStatsTable = new JTable(model);
        playerStatsTable.setPreferredScrollableViewportSize(new Dimension(500,400));
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
