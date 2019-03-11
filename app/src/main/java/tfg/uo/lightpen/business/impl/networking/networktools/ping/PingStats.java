package tfg.uo.lightpen.business.impl.networking.networktools.ping;

import java.net.InetAddress;

/**
 *  * **** DISCLAIMER *****
 *  * This is part of the AndroidNetworkTools library created by the user stealthcopter that can
 *  * be found here: https://github.com/stealthcopter/AndroidNetworkTools
 *  * We just integrated the source into our project to reuse part of their functionalities
 *  * *********************
 * Created by mat on 09/12/15.
 */
public class PingStats {
    private final InetAddress ia;
    private final long noPings;
    private final long packetsLost;
    private final float averageTimeTaken;
    private final float minTimeTaken;
    private final float maxTimeTaken;
    private final boolean isReachable;

    public PingStats(InetAddress ia, long noPings, long packetsLost, float totalTimeTaken, float minTimeTaken, float maxTimeTaken) {
        this.ia = ia;
        this.noPings = noPings;
        this.packetsLost = packetsLost;
        this.averageTimeTaken = totalTimeTaken / noPings;
        this.minTimeTaken = minTimeTaken;
        this.maxTimeTaken = maxTimeTaken;
        this.isReachable = noPings - packetsLost > 0;
    }

    public InetAddress getAddress() {
        return ia;
    }

    public long getNoPings() {
        return noPings;
    }

    public long getPacketsLost() {
        return packetsLost;
    }

    public float getAverageTimeTaken() {
        return averageTimeTaken;
    }

    public float getMinTimeTaken() {
        return minTimeTaken;
    }

    public float getMaxTimeTaken() {
        return maxTimeTaken;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public long getAverageTimeTakenMillis() {
        return (long) (averageTimeTaken * 1000);
    }

    public long getMinTimeTakenMillis() {
        return (long) (minTimeTaken * 1000);
    }

    public long getMaxTimeTakenMillis() {
        return (long) (maxTimeTaken * 1000);
    }

    @Override
    public String toString() {
        return "PingStats{" +
                "ia=" + ia +
                ", noPings=" + noPings +
                ", packetsLost=" + packetsLost +
                ", averageTimeTaken=" + averageTimeTaken +
                ", minTimeTaken=" + minTimeTaken +
                ", maxTimeTaken=" + maxTimeTaken +
                '}';
    }
}
