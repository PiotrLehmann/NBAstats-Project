import org.apache.log4j.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class InternetAvailabilityChecker {

    private static final Logger logger = Logger.getLogger(InternetAvailabilityChecker.class);

    public static boolean isInternetAvailable() throws IOException {
        return isHostAvailable("google.com") || isHostAvailable("amazon.com")
                || isHostAvailable("facebook.com")|| isHostAvailable("apple.com");
    }

    private static boolean isHostAvailable(String hostName) throws IOException {
        try(Socket socket = new Socket()) {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);
            logger.info("Internet connection CAN BE established");
            return true;
        }
        catch(UnknownHostException unknownHost) {
            logger.info("Internet connection CAN NOT BE established");
            return false;
        }
    }
}
