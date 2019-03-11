package tfg.uo.lightpen.business.impl.networking.networktools.portscanning;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * * **** DISCLAIMER *****
 * * This is part of the AndroidNetworkTools library created by the user stealthcopter that can
 * * be found here: https://github.com/stealthcopter/AndroidNetworkTools
 * * We just integrated the source into our project to reuse part of their functionalities
 * * *********************
 * Created by mat on 13/12/15.
 */
public class PortScanUDP {

    // This class is not to be instantiated
    private PortScanUDP() {
    }

    /**
     * Check if a port is open with UDP, note that this isn't reliable
     * as UDP will does not send ACKs
     *
     * @param ia            - address to scan
     * @param portNo        - port to scan
     * @param timeoutMillis - timeout
     * @return - true if port is open, false if not or unknown
     */
    public static boolean scanAddress(InetAddress ia, int portNo, int timeoutMillis) {

        try {
            byte[] bytes = new byte[128];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);

            DatagramSocket ds = new DatagramSocket();
            ds.setSoTimeout(timeoutMillis);
            ds.connect(ia, portNo);
            ds.send(dp);
            ds.isConnected();
            ds.receive(dp);
            ds.close();

        } catch (SocketTimeoutException e) {
            return false; //Timeout: suppose not open!
        } catch (Exception ignore) {

        }

        return false;
    }

}
