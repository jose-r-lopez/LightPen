package tfg.uo.lightpen.business.impl.networking.networktools.ping;

/**
 * **** DISCLAIMER *****
 * This is part of the AndroidNetworkTools library created by the user stealthcopter that can
 * be found here: https://github.com/stealthcopter/AndroidNetworkTools
 * We just integrated the source into our project to reuse part of their functionalities
 * *********************
 */
public class PingOptions {
    private int timeoutMillis;
    private int timeToLive;

    public PingOptions() {
        timeToLive = 128;
        timeoutMillis = 1000;
    }

    public int getTimeoutMillis() {
        return timeoutMillis;
    }

    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = Math.max(timeoutMillis, 1000);
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = Math.max(timeToLive, 1);
    }
}
