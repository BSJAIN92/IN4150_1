
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;


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
	
	
	
}
