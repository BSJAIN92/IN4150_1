
import java.rmi.*;


public class Implement_Delay implements Runnable{
	
	private DA_SES_RMI server;
	private Message message;
	private long delay = 0;
	
	public Implement_Delay(DA_SES_RMI server, Message message) {
		this.server = server;
		this.message = message;
	}
	
	public void run() {
		delay = message.getDelay();
		
		try{
			Thread.sleep(delay);
			message.setDelay(0);
			server.receiveMessage(message);
			Thread.currentThread().interrupt();
	}	catch (InterruptedException e) {
		e.printStackTrace();
	}
	}

}
