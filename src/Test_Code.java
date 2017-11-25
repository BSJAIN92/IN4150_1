import java.rmi.RemoteException;
import java.util.*;

public class Test_Code {
	
	public static void main(String args[]){
		
		try {

			/*
			 * Creating a server and testing functionalities using it
			 */
			
			DA_SES_RMI server1 = new DA_SES_RMI(3, 1);

			/*
			 * Checking object values for server using get functions
			 */
			
			System.out.println(server1.getServerIndex());
			System.out.println(server1.getTotalServers());
			System.out.println(server1.getClock());
			System.out.println(server1.getSendDetails());
			System.out.println(server1.getServerDetails());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}	
}
