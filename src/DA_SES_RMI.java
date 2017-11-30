
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

public class DA_SES_RMI extends UnicastRemoteObject
		implements DA_SES_RMI_Interface, Runnable{
	
	private static final long serialVersionUID = 4745507L;
	
	/*
	 * For logging
	 */
	
	private static Logger logger = Logger.getLogger(DA_SES_RMI.class.getName());
	
	/*
	 * To store url of server
	 */
	
	private Map<String, DA_SES_RMI_Interface> serverDetails;
	
	/*
	 * Timestamp of latest message to particular server
	 */
	
	private Map<Integer, List<Integer>> sendDetails;
	
	/*
	 * Clock of the server, incremented by 1 on events
	 */
	
	private List<Integer> clock;
	
	/*
	 * Total number of servers in the Network
	 */
	
	private int totalServers;
	
	/*
	 * Index number of the server
	 */
	
	private int serverIndex;
	
	/*
	 * Return clock
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public List<Integer> getClock() {
		return this.clock;
	}

	
	/*
	 * Return serverDetails
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public Map<String, DA_SES_RMI_Interface> getServerDetails(){
		return this.serverDetails;
	}
	
	
	/*
	 * Return sendDetails
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public Map<Integer, List<Integer>> getSendDetails(){
		return this.sendDetails;
	}
	
	/*
	 * Return totalServers
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public int getTotalServers() {
		return this.totalServers;
	}
	
	/*
	 * Return serverIndex
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public int getServerIndex() {
		return this.serverIndex;
	}
	
	
	
	/*
	 * Default Constructor
	 */
		
	
	protected DA_SES_RMI (int totalServers, int serverIndex) throws RemoteException{
		super();
		serverDetails = new HashMap<String, DA_SES_RMI_Interface>();
		sendDetails = new HashMap<Integer, List<Integer>>();
		
		
		this.totalServers = totalServers;
		this.serverIndex = serverIndex;
		this.clock = new ArrayList<Integer>(totalServers);
		for (int i = 0; i < totalServers; i++) {
			clock.add(0);
		}
	}
	
	
	/*
	 * Reset Server
	 */
	
	
	public void reset() {
		sendDetails = new HashMap<Integer, List<Integer>>();
		this.clock = new ArrayList<Integer>(totalServers);
		for (int i = 0; i < totalServers; i++) {
			clock.add(0);
		}
	}
	
	/*
	 * Server start Logger
	 */
	
	public void run() {
		logger.info("Server successfully initialized and started");
	}
	
	/*
	 * (non-Javadoc)
	 * @see DA_SES_RMI_Interface#sendMessage(java.lang.String)
	 * Send Messages to different server(s)
	 */
	
	public void sendMessage(String url) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see DA_SES_RMI_Interface#receiveMessage()
	 * Receive Messages from different server(s)
	 */
	
	public synchronized void receiveMessage() {
		
	}
}
	
