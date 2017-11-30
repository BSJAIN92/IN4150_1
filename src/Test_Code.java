import java.rmi.RemoteException;
import java.util.*;

public class Test_Code {
	
	public static void main(String args[]){
		
		try {

			/*
			 * Creating a server and testing functionalities using it
			 */
			
			/*
			 * *****************
			 * DA_SES_RMI class
			 * *****************
			 */
			
			DA_SES_RMI server1 = new DA_SES_RMI(3, 1);

			
			System.out.println("Tests for DA_SES_RMI class:");
			System.out.println(server1.getServerIndex());
			System.out.println(server1.getTotalServers());
			System.out.println(server1.getClock());
			System.out.println(server1.getSendDetails());
			System.out.println(server1.getServerDetails());
			
			/*
			 * *****************
			 * Message class
			 * *****************
			 */
			
			
			
			System.out.println("Tests for Message class");
			Message msg = new Message(1, 1, 2);
			List<Integer> clock = new ArrayList<Integer>();
			clock.add(0);
			clock.add(0);
			msg.setClock(clock);
			Map<Integer, List<Integer>> sentMessagesBuffer = new HashMap<Integer, List<Integer>>();
			List<Integer> message = new ArrayList<Integer>();
			message.add(1);
			message.add(1);
			sentMessagesBuffer.put(1, message);
			msg.setSentMessagesBuffer(sentMessagesBuffer);
			msg.setDelay(10);
			msg.setSysTime(System.currentTimeMillis());
			
			System.out.println(msg.getMsgId());
			System.out.println(msg.getSrcServer());
			System.out.println(msg.getDestServer());
			System.out.println(msg.getClock());
			System.out.println(msg.getSentMessagesBuffer());
			System.out.println(msg.getSysTime());
			System.out.println(msg.getDelay());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}	
}
