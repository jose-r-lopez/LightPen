package tfg.uo.lightpen.business.impl.networking.impl.networkOperations.HttpOperations;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.NetworkOperation;

/**
 * Performs a connection to a URL using the corresponding HTTP method
 * Created by Jose Manuel Redondo
 */

public class HttpMethod extends NetworkOperation {

    private static final String TAG = "HTTPMethodSOBTAINER";
    //private int TIMEOUT = 3000;
    private String method;
    private HttpURLConnection con = null;
    private URL url;
    private boolean lastConnectionPossible = false;

    public HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * Performs a connection to the provided URL using the associated method
     *
     * @param url URL to connect to
     * @return If the connection was possible
     */
    public boolean connectionPossibleTo(URL url) {
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.connect();
            if (con.getResponseCode() == 200) { //codigo correcto
                lastConnectionPossible = true;
                return true;
            }
        } catch (SocketTimeoutException e) {
            Log.e(TAG, " getHttpMethodFromURL: ", e);
        } catch (UnknownHostException e) {
            Log.e(TAG, " getHttpMethodFromURL: ", e);
        } catch (Exception e) {
            Log.e(TAG, " getHttpMethodFromURL: ", e);
        } finally {
            if (con != null)
                con.disconnect();
        }
        lastConnectionPossible = false;
        return false;
    }

    @Override
    public void run() {
        if (url != null)
            connectionPossibleTo(url);
    }

    @Override
    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public boolean isLastConnectionPossible() {
        return lastConnectionPossible;
    }
}
