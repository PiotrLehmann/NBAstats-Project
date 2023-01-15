import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class HomeScreen {

    public JLabel logoLabel;
    public JLabel textLabel;
    public int today;
    public int endOfSeason;
    public SimpleDateFormat dateFormat;
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
