import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * HomeScreen class is responsible for creating HomeScreen,
 * displaying today date with day name, number of days to end of
 * an NBA season and picture of Kyrie Irving as logo
 */
public class HomeScreen {

    /**
     * contains logo Image
     */
    public JLabel logoLabel;

    /**
     * contains all text displayed on HomeScreen
     */
    public JLabel textLabel;

    /**
     * number of days from 1970 till today in USA
     */
    public int today;

    /**
     * number of days from 1970 till NBA season end
     */
    public int endOfSeason;

    /**
     * format in which date is displayed
     */
    public SimpleDateFormat dateFormat;

    /**
     * contains unformatted date
     */
    public String date;

    public HomeScreen() {

        ImageIcon logo = new ImageIcon("images/LOGO500GIT.png");
        logoLabel = new JLabel(logo);

        dateFormat = new SimpleDateFormat("E dd.MM.yy");
        date = dateFormat.format(Calendar.getInstance().getTime());

        today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        endOfSeason = 169;

        textLabel = new JLabel(
                "<html><span style='font-family:Arial;font-size:25px;font-weight:bold'>"+"""
                WELCOME TO THE
                <br>NEW NBA-STATS APP</br>"""
                + "</span>"
                + "<span style='font-family:Arial;font-size:13px;font-weight:normal'>" + """
                <br>Check your favourite team record</br>
                <br>look at the best players stats</br>
                <br>follow their ranking and more!</br>"""
                + "</span>"
                + "<span style='font-family:Arial;font-size:13px;font-weight:normal'>"
                + "<br>"+"Today is: " + date + "</br>"
                + "</span>"
                + "<span style='font-family:Arial;font-size:13px;font-weight:normal'>"
                + "<br>"+"Season ends in: "
                + "<span style='color:orange'>" + (endOfSeason - today) + " days" + "<span/>" + "</br>"
                + "</span></html>");

    }
}
