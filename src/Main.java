import org.apache.log4j.Logger;
import javax.swing.SwingUtilities;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            if (!InternetAvailabilityChecker.isInternetAvailable()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new NoInternetWindow();
                    }
                });
            }
        } catch (Exception e) {
            logger.debug("Exception thrown during checking of the internet availability");
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });


    }
}