package tfg.uo.lightpen.business.impl.networking.impl.networkOperations.SocketOperations;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.NetworkOperation;
import tfg.uo.lightpen.business.impl.networking.networktools.portscanning.PortScanTCP;
import tfg.uo.lightpen.business.impl.networking.networktools.portscanning.PortScanUDP;

/**
 * Runs port scanning operations using the AndroidNetworkTools library functionalities
 * <p>
 * Created by Iñigo Llaneza Aller on 3/5/17.
 * AndroidNetworkTools integration and major feature enhancements by Jose Manuel Redondo
 */

public class Ports extends NetworkOperation {

    private static final String TAG = "PORTS";
    private HttpURLConnection con;
    private ArrayList<String> response = new ArrayList<>();

    private int timeout;

    public Ports() {
    }

    @Override
    public void run() {
    }

    public static Future<ScanResult> portIsOpenTCP(final ExecutorService es, final InetAddress ia, final int port,
                                                   final int timeout) {
        return es.submit(new Callable<ScanResult>() {
            @Override
            public ScanResult call() {
                try {
                    boolean isOpen = PortScanTCP.scanAddress(ia, port, timeout);
                    return new ScanResult(port, isOpen, "TCP");
                } catch (Exception ex) {
                    return new ScanResult(port, false, "TCP");
                }
            }
        });
    }

    public static Future<ScanResult> portIsOpenUDP(final ExecutorService es, final InetAddress ia, final int port,
                                                   final int timeout) {
        return es.submit(new Callable<ScanResult>() {
            @Override
            public ScanResult call() {
                try {
                    boolean isOpen = PortScanUDP.scanAddress(ia, port, timeout);
                    return new ScanResult(port, isOpen, "UDP");
                } catch (Exception ex) {
                    return new ScanResult(port, false, "UDP");
                }
            }
        });
    }

    /**
     * Holds port scanning information results
     */
    public static class ScanResult implements Comparable<ScanResult> {
        private int port;

        private boolean isOpen;

        private String protocol;

        public ScanResult(int port, boolean isOpen, String protocol) {
            super();
            this.port = port;
            this.isOpen = isOpen;
            this.protocol = protocol;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean isOpen) {
            this.isOpen = isOpen;
        }

        public String getProtocol() {
            return protocol;
        }

        @Override
        public int compareTo(ScanResult o) {
            if (!this.protocol.equals(o.protocol))
                return this.protocol.compareTo(o.protocol);
            else
                return this.port - o.port;
        }
    }

    //region Setters & Getters

    public HttpURLConnection getCon() {
        return con;
    }

    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public ArrayList<String> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<String> response) {
        this.response = response;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    //endregion
}
