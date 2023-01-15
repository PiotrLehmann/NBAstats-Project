import org.apache.log4j.Logger;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;


/**
 * NoInternetWindow class behaves like JFrame and is responsible
 * for displaying NoInternetWindow in Main class
 */
public class NoInternetWindow extends JFrame {

    /**
     * gets the screen size of users screen
     */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * logger for NoInternetWindow class
     */
    private static final Logger logger = Logger.getLogger(NoInternetWindow.class);

    public NoInternetWindow() {

        final int windowWidth = 400;
        final int windowHeight = 350;

        int screenHeight = (int) screenSize.getHeight();
        int screenWidth = (int) screenSize.getWidth();

        this.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("images/noInternet.jpg");
        logger.info("Photo loaded successfully");
        JLabel imageLabel = new JLabel(image);

        JLabel informationLabel = new JLabel(
                "<html><span style='font-family:Arial;font-weight:bold;font-size:12px;color:red'>" +
                "No Internet connection - shutting down..." +
                "<span/></html>");
        informationLabel.setHorizontalAlignment(JLabel.CENTER);
        informationLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        this.add(imageLabel, BorderLayout.CENTER);
        this.add(informationLabel, BorderLayout.SOUTH);
        this.setSize(windowWidth, windowHeight);
        this.setLocation((screenWidth - windowWidth)/2, (screenHeight - windowHeight)/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        logger.trace("Window created successfully");
        logger.trace("Counting till application shutdown..");
    }

}
