package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors;


import java.net.URL;

import tfg.uo.lightpen.business.impl.networking.externalAPI.HttpAPIConnection;

/**
 * Created by Jose Manuel Redondo
 */

public class DeviceLocatorError extends Error {

    private final static Double version = 0.001;

    private String ip;
    private String hostname;
    private String mac;
    private String manufacturer;
    private static HttpAPIConnection macVendor;

    static {
        try {
            URL url = new URL("https://api.macvendors.com");
            macVendor = new HttpAPIConnection(url);
        } catch (Exception ex) {

        }
    }

    public DeviceLocatorError(String ip, String hostname, String mac) {
        this.ip = ip;
        this.hostname = hostname;
        this.mac = mac;
        if (macVendor == null)
            this.manufacturer = "";
        else
            this.manufacturer = macVendor.getResponse(this.mac.replace(":", "-"));
    }

    public boolean equals(Object obj) {
        if (obj.getClass().equals(DeviceLocatorError.class)) {
            return this.ip.equals(((DeviceLocatorError) obj).ip);
        }
        return false;
    }

    @Override
    public String printError() {

        String err = "<td> " + this.ip + " </td>\n";
        err += "<td> " + hostname + " </td>\n";
        err += "<td> " + mac + " </td>\n";
        err += "<td> " + manufacturer + " </td>\n";

        return err;
    }
}
