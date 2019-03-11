package tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.impl;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import tfg.uo.lightpen.R;
import tfg.uo.lightpen.business.impl.networking.impl.networkOperations.HttpOperations.Resources;
import tfg.uo.lightpen.business.impl.networking.networktools.SubnetDevices;
import tfg.uo.lightpen.business.impl.networking.networktools.subnet.Device;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.DeviceLocatorError;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.Error;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.RobotsError;
import tfg.uo.lightpen.business.impl.pluginSystem.impl.plugins.errors.riskRating.OwaspRiskRating;
import tfg.uo.lightpen.infrastructure.factories.Factories;
import tfg.uo.lightpen.model.ContextData;

/**
 * Created by Iñigo Llaneza Aller on 22/4/17.
 * Plugin realizado para el analisis del fichero robots.txt
 */
public class DeviceLocatorImpl implements IPluginImplementation {

    //private static final String TAG = "DEVICELOC_IMPL";
    private ArrayList<Error> errors = new ArrayList<>();
    private URL url;
    private ContextData ctxD;
    ArrayList<Device> devices = new ArrayList<>();
    private boolean started = false;
    private SubnetDevices subnetDevices;

    class DeviceFindImpl implements SubnetDevices.OnSubnetDeviceFound {

        @Override
        public void onDeviceFound(Device device) {
            synchronized (this) {
                devices.add(device);
            }
        }

        @Override
        public void onFinished(ArrayList<Device> devicesFound) {
        }
    }
    public void initialize(URL url, ContextData ctxD) {
        setUrl(url);
        setContextData(ctxD);
    }

    public ArrayList<Error> getPentest() {
        analyze();
        return getErrors();
    }

    private void analyze() {
        if (!started) {
            subnetDevices = SubnetDevices.fromIPAddress(url.getHost()).findDevices(new DeviceFindImpl());
            started = true;
        }
        if (devices != null) {
            for (Device device: devices) {
                DeviceLocatorError dle = new DeviceLocatorError(device.ip, device.hostname, device.mac);
                if (!errors.contains(dle))
                    errors.add(dle);
            }
        }
    }

    //region Getters & Setters
    private void setUrl(URL url) {
        this.url = url;
    }

    private URL getUrl() {
        return url;
    }

    private ArrayList<Error> getErrors() {
        return errors;
    }

    public ContextData getContextData() {
        return ctxD;
    }

    public void setContextData(ContextData ctxD) {
        this.ctxD = ctxD;
    }
    //endregion
}

