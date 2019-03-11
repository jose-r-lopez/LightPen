package tfg.uo.lightpen.business.impl.networking.networktools.subnet;

import java.net.InetAddress;

/**
 * * **** DISCLAIMER *****
 * * This is part of the AndroidNetworkTools library created by the user stealthcopter that can
 * * be found here: https://github.com/stealthcopter/AndroidNetworkTools
 * * We just integrated the source into our project to reuse part of their functionalities
 * * *********************
 */
public class Device {
    public String ip;
    public String hostname;
    public String mac;
    public float time = 0;

    public Device(InetAddress ip) {
        this.ip = ip.getHostAddress();
        this.hostname = ip.getCanonicalHostName();
    }

    @Override
    public String toString() {
        return "Device{" +
                "ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", mac='" + mac + '\'' +
                ", time=" + time +
                '}';
    }
}

