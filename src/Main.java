import org.apache.log4j.Logger;
import javax.swing.SwingUtilities;

/**
 * Main class is responsible for launching an application,
 * whether there is, or there is no internet connection provided by user
 */
public class Main {

    /**
     * logger for class Main
     */
    private static final Logger logger = Logger.getLogger(Main.class);

    /**
     * main method checks the internet connection
     * if there is internet connection this method launches window from Window class
     * if there is no internet connection this method launches window from NoInternetWindow class
     * @param args arguments given by user
     */
    public static void main(String[] args) {

        try {
            if (!InternetAvailabilityChecker.isInternetAvailable()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new NoInternetWindow();
                    }
                });
                Thread.sleep(7000);
                System.exit(0);
            }
        } catch (Exception e) {
            logger.debug("Exception thrown while checking the internet availability");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}