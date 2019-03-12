package tfg.uo.lightpen.business.impl.networking.externalAPI;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * This class allows connecting to external APIs that use the HTTP protocol
 * @author Jose Manuel Redondo
 */
public class HttpAPIConnection {
    private static String TAG = "HttpAPIConnection";
    private URL url;
    private String connectionMethod = "GET";
    /**
     * Local cache to reduce external service querys
     */
    private HashMap<String, String> cache = new HashMap<>();

    public HttpAPIConnection(URL url) {
        this.url = url;
    }

    public HttpAPIConnection(URL url, String method) {
        this.url = url;
        this.connectionMethod = method;
    }

    public String getResponse(String parameters) {
        String result = cache.get(parameters);
        if (result != null)
            return result;

        HttpURLConnection connection = null;
        try {
            URL connectURL = new URL(url.toString() + "/" + parameters);
            connection = (HttpURLConnection) connectURL.openConnection();
            connection.setRequestMethod(connectionMethod);
            connection.connect();
            if (connection.getResponseCode() == 200) { //codigo correcto
                BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String sCurrentLine;
                StringBuilder resp = new StringBuilder();
                while ((sCurrentLine = buf.readLine()) != null) {
                    resp.append(sCurrentLine);
                    resp.append("\n");
                }
                String retStr = resp.toString().trim();
                cache.put(parameters, retStr);
                return retStr;
            }
        } catch (SocketTimeoutException e) {
            Log.e(TAG, " HttpAPIConnection: ", e);
        } catch (UnknownHostException e) {
            Log.e(TAG, " HttpAPIConnection: ", e);
        } catch (Exception e) {
            Log.e(TAG, " HttpAPIConnection: ", e);
        } finally {
            if (connection != null)
                connection.disconnect();
        }

        return "";
    }
}
