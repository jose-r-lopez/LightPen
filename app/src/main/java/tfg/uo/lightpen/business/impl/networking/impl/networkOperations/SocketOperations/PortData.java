package tfg.uo.lightpen.business.impl.networking.impl.networkOperations.SocketOperations;

/**
 * DAO containing port data
 *
 * @author Jose Manuel Redondo
 */
public class PortData {
    private int portNumber;

    private String serviceName;

    private double frequency;

    private String description;

    public PortData(int portNumber,
                    String serviceName,
                    double frequency,
                    String description) {
        this.portNumber = portNumber;
        this.serviceName = serviceName;
        this.frequency = frequency;
        this.description = description;
    }

    /**
     * Gets the port number
     *
     * @return Port number
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Gets the service name
     *
     * @return The sort service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Get the scanning frequency
     *
     * @return Worldwide percentage in which this port gets scanned
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Get the service description
     *
     * @return A long description about the service commonly found in this port
     */
    public String getServiceDescription() {
        return description;
    }
}
