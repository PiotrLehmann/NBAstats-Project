import org.apache.log4j.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

public class NoInternetWindow extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Logger logger = Logger.getLogger(NoInternetWindow.class);

    public NoInternetWindow() {

        final int windowWidth = 400;
        final int windowHeight = 200;

        int screenHeight = (int) screenSize.getHeight();
        int screenWidth = (int) screenSize.getWidth();

        JPanel jp = new JPanel(new BorderLayout());

        JLabel informationLabel = new JLabel("No Internet connection.. Application is closing in:");
        jp.add(informationLabel, BorderLayout.CENTER);

        JLabel secondsLabel = new JLabel();
        jp.add(secondsLabel, BorderLayout.SOUTH);

        this.add(jp);
        this.setSize(windowWidth, windowHeight);
        this.setLocation((screenWidth - windowWidth)/2, (screenHeight - windowHeight)/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        logger.trace("NoInternetWindow created successfully");

        int seconds = 5;

        while (seconds > -1) {
            secondsLabel.setText(String.valueOf(seconds));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            seconds --;
        }

        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

}
