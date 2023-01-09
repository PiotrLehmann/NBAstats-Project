import org.apache.log4j.Logger;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class HomeScreen {

    public JPanel infoPanel;
    public JLabel logoLabel;
    public JLabel daysLeftToEndOfSeasonLabel;
    public JLabel dateLabel;
    public JLabel welcomeLabel;
    public JLabel descriptionLabel;
    public int today;
    public int endOfSeason;
    public SimpleDateFormat dateFormat;
    public String date;
    private static final Logger logger = Logger.getLogger(HomeScreen.class);
    public Font myFont = new Font("Arial", Font.BOLD, 30);

    public HomeScreen() {

        // Logo for the left side of HomeScreen
        ImageIcon logo = new ImageIcon("images/LOGO500GIT.png");
        logoLabel = new JLabel(logo);

        // Info for the right side of HomeScreen
        welcomeLabel = new JLabel();
        welcomeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        welcomeLabel.setFont(myFont);
        welcomeLabel.setText("""
                <html>WELCOME TO THE<br>
                NEW NBA-STATS APP</br></html>""");

        descriptionLabel = new JLabel();
        descriptionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel.setText("""
                <html>Check your favourite team record<br>
                look at the best players stats</br><br>
                follow their ranking and more!</br></html>""");

        dateFormat = new SimpleDateFormat("E dd.MM.yy");
        dateLabel = new JLabel();
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText("It's " + date);

        today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        endOfSeason = 169;

        daysLeftToEndOfSeasonLabel = new JLabel();
        daysLeftToEndOfSeasonLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        daysLeftToEndOfSeasonLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        daysLeftToEndOfSeasonLabel.setText("Season ends in: " + (endOfSeason - today) + " days");

        infoPanel = new JPanel(new GridLayout(0,1));
        infoPanel.setPreferredSize(new Dimension(300,100));
        infoPanel.add(welcomeLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(daysLeftToEndOfSeasonLabel);

    }
}
