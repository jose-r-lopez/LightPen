package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors;


/**
 * Created by Jose Manuel Redondo
 */

public class DeviceLocatorError extends Error {

    private final static Double version = 0.001;

    private String ip;
    private String hostname;
    private String mac;

    public DeviceLocatorError(String ip, String hostname, String mac) {
        this.ip = ip;
        this.hostname = hostname;
        this.mac = mac;
    }

    public boolean equals(Object obj) {
        if (obj.getClass().equals(DeviceLocatorError.class)) {
            return this.ip.equals(((DeviceLocatorError)obj).ip);
        }
        return false;
    }
    @Override
    public String printError() {

        String err = "<td> " + this.ip + " </td>\n";
        err += "<td> " + hostname + " </td>\n";
        err += "<td> " + mac + " </td>\n";

        return err;
    }
}
