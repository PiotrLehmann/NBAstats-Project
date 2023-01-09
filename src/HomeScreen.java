import org.apache.log4j.Logger;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
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
    public LocalDate today;
    public LocalDate endOfSeason;
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
        welcomeLabel.setFont(myFont);
        welcomeLabel.setText("""
                <html>WELCOME TO THE<br>
                NEW NBA-STATS APP</br></html>""");

        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel.setText("""
                <html>Check your favourite team record,<br>
                look at the best players stats,</br><br>
                follow their ranking and more!</br></html>""");

        dateFormat = new SimpleDateFormat("E dd.MM.yy");
        dateLabel = new JLabel();
        dateLabel.setFont(myFont);
        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText("Its " + date);

        // TODO: tu trzeba jeszcze zrobić
        daysLeftToEndOfSeasonLabel = new JLabel();
        daysLeftToEndOfSeasonLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        daysLeftToEndOfSeasonLabel.setText("Season ends in: " + 69 + " days");

        infoPanel = new JPanel(new GridLayout(6,1,0,0));
        infoPanel.setPreferredSize(new Dimension(300,200));
        infoPanel.add(new JLabel(""));
        infoPanel.add(welcomeLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(daysLeftToEndOfSeasonLabel);

    }
}
