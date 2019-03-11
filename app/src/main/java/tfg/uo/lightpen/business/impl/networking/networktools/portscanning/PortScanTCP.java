package tfg.uo.lightpen.business.impl.networking.networktools.portscanning;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * * **** DISCLAIMER *****
 * * This is part of the AndroidNetworkTools library created by the user stealthcopter that can
 * * be found here: https://github.com/stealthcopter/AndroidNetworkTools
 * * We just integrated the source into our project to reuse part of their functionalities
 * * *********************
 * Created by mat on 13/12/15.
 */
public class PortScanTCP {

    // This class is not to be instantiated
    private PortScanTCP() {
    }

    /**
     * Check if a port is open with TCP
     *
     * @param ia            - address to scan
     * @param portNo        - port to scan
     * @param timeoutMillis - timeout
     * @return - true if port is open, false if not or unknown
     */
    public static boolean scanAddress(InetAddress ia, int portNo, int timeoutMillis) {

        Socket s = null;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(ia, portNo), timeoutMillis);
            return true;
        } catch (IOException e) {
            // Don't log anything as we are expecting a lot of these from closed ports.
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
