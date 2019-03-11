package tfg.uo.lightpen.business.impl.networking.networktools.ping;

import java.net.InetAddress;

/**
 * * **** DISCLAIMER *****
 * * This is part of the AndroidNetworkTools library created by the user stealthcopter that can
 * * be found here: https://github.com/stealthcopter/AndroidNetworkTools
 * * We just integrated the source into our project to reuse part of their functionalities
 * * *********************
 * Created by mat on 09/12/15.
 */
public class PingResult {
    public final InetAddress ia;
    public boolean isReachable;
    public String error = null;
    public float timeTaken;
    public String fullString;
    public String result;

    public PingResult(InetAddress ia) {
        this.ia = ia;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public boolean hasError() {
        return error != null;
    }

    public float getTimeTaken() {
        return timeTaken;
    }

    public String getError() {
        return error;
    }

    public InetAddress getAddress() {
        return ia;
    }

    @Override
    public String toString() {
        return "PingResult{" +
                "ia=" + ia +
                ", isReachable=" + isReachable +
                ", error='" + error + '\'' +
                ", timeTaken=" + timeTaken +
                ", fullString='" + fullString + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
