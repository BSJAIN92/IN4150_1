
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*
 * Remote interface for RMI 
 */


public interface DA_SES_RMI_Interface  extends Remote{

	/*
	 * To send a message to another server
	 * @param url: location of destination server
	 * @throws: RemoteException
	 */
		
	public void sendMessage(String url) throws RemoteException;
	
	/*
	 * To receive a message from another server
	 * @throws: RemoteException
	 */
	
	
	public void receiveMessage() throws RemoteException;
	
	/*
	 * Index of the current server
	 * @return: index
	 * @throws RemoteException
	 */
		
	public int getServerIndex() throws RemoteException;
	
	/*
	 * Return clock
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public List<Integer> getClock() throws RemoteException;
	
	/*
	 * Return serverDetails
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public Map<String, DA_SES_RMI_Interface> getServerDetails() throws RemoteException;
	
	/*
	 * Return sendDetails
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public Map<Integer, List<Integer>> getSendDetails() throws RemoteException;
	
	/*
	 * Return totalServers
	 * Debugging
	 * Private Check: 0000 0000
	 */
	
	public int getTotalServers() throws RemoteException;
	
	/*
	 * Reset Server
	 */
	
	
	public void reset() throws RemoteException;
	

	
	
	
}
