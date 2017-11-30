
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
	 * List of messages waiting to be delivered
	 */
	
	private List<Message> pendingMessages;
	
	/*
	 * List of messages received
	 */
	
	private List<Message> receivedMessages;
	
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
	
	public List<Message> getReceivedMessage() {
		return this.receivedMessages;
	}
	
	/*
	 * Default Constructor
	 */
		
	
	protected DA_SES_RMI (int totalServers, int serverIndex) throws RemoteException{
		super();
		serverDetails = new HashMap<String, DA_SES_RMI_Interface>();
		sendDetails = new HashMap<Integer, List<Integer>>();
		pendingMessages = new LinkedList<Message>();
		receivedMessages = new LinkedList<Message>();
		
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
		pendingMessages = new LinkedList<Message>();
		receivedMessages = new LinkedList<Message>();
		
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
	 * Send Messages to different server(s)
	 */
	
	public void sendMessage(String url, Message message) {
		
		//Increasing local clock
		
		clock.set(serverIndex, clock.get(serverIndex)+1);
		
		//Retrieving Destination Server
		
		DA_SES_RMI_Interface destination;
		
		try{
			destination = (DA_SES_RMI_Interface) Naming.lookup(url);
			/*
			 * Set clock for message
			 * This will be used for comparing with local clock of destination server
			 */
			
			message.setClock(clock);
			
			// Passing details of latest sent messages
			
			message.setSentMessagesBuffer(sendDetails);
			
			/*
			 * Calling for other server to receive the message
			 */
			
			destination.receiveMessage(message);
			
			//Adding Message to list of latest sent messages
			
			sendDetails.put(destination.getServerIndex(), new ArrayList<Integer>(clock));
			
		}	catch (RemoteException e1) {
			e1.printStackTrace();
		}	catch (MalformedURLException e2) {
			e2.printStackTrace();
		}	catch (NotBoundException e3) {
			e3.printStackTrace();
		}
		
		
	}
	
	/*
	 * Receive Messages from different server(s)
	 */
	
	public synchronized void receiveMessage(Message message) {
		
		/*
		 * Implementing Delay
		 */
		
		if (message.getDelay() > 0) {
			new Thread(new Implement_Delay(this, message)).start();
			return;
		}
		
		if (checkDeliveryPossibility(message)) {
			
			//send message
			send(message);
			
			/*
			 * Deliver pendingMessages
			 */
			
			List<Message> sent = new LinkedList<Message>();
			for (Message msg : pendingMessages) {
				if (checkDeliveryPossibility(msg)) {
					send(msg);
					sent.add(msg);
				}
			}
			
			pendingMessages.removeAll(sent);
			
		}else {
			/*
			 * Deliver later, add to pendingMessages
			 */
			pendingMessages.add(message);
			
		}
		
	}


	/*
	 * Check conditions for delivery
	 */

	public boolean checkDeliveryPossibility(Message message) {
		
		/*
		 * Check is message is already delivered
		 * Search in the the latest sent messages
		 */
		
		if(!message.getSentMessagesBuffer().containsKey(serverIndex)) {
			return true;
		}
		
		/*
		 * else compare whether arrival of message is before time
		 * and add to pendingMessages
		 */
		
		boolean possibility = true;
		List<Integer> localClock = new ArrayList<Integer>(clock);
		localClock.set(serverIndex, clock.get(serverIndex)+1);
		List<Integer> messageClock = new ArrayList<Integer>(message.getSentMessagesBuffer().get(serverIndex));
		
		for (int i = 0; i < totalServers; i++) {
			if (localClock.get(i) < messageClock.get(i)) {
				possibility = false;
				break;
			}
		}
		return possibility;
	}
	

	/*
	 * function to include message in list of sent messages
	 */
	
	
	public void send(Message message) {
		//increase local clock
		receivedMessages.add(message);
		clock.set(serverIndex, clock.get(serverIndex)+1);
		
		//update local clock using message clock
		List<Integer> newClock = new ArrayList<Integer>(totalServers);
		for (int i = 0; i < totalServers; i++) {
			newClock.add(Math.max(clock.get(i), message.getClock().get(i)));
		}
		
		clock = newClock;
		
		List<Map.Entry<Integer, List<Integer>>> messages = new ArrayList<Map.Entry<Integer, List<Integer>>>(message.getSentMessagesBuffer().entrySet());
		
		for (Map.Entry<Integer, List<Integer>> entry : messages) {
			List<Integer> currentClock = sendDetails.get(entry.getKey());
			if (currentClock == null) {
				sendDetails.put(entry.getKey(), entry.getValue());
			}	else {
				List<Integer> nClock = new ArrayList<Integer>(totalServers);
				for (int i =0; i < totalServers; i++) {
					nClock.add(Math.max(entry.getValue().get(i), sendDetails.get(entry.getKey()).get(i)));
				}
				sendDetails.put(entry.getKey(), nClock);
			}
		}
		
	}
		
}
