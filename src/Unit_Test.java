
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.*;



public class Unit_Test {

	private final static Logger logger = Logger.getLogger(Unit_Test.class.getName());
	
	private List<DA_SES_RMI_Interface> servers;
	private String[] urls;
	
	@Before
	public void init() {
		servers = new ArrayList<DA_SES_RMI_Interface>();
		Configuration config = null;
		
		try {
			config = new PropertiesConfiguration("network.cfg");
		}	catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		urls = config.getStringArray("node.url");
		servers = new ArrayList<DA_SES_RMI_Interface>();
		
		for (String url : urls) {
			try {
				DA_SES_RMI_Interface server = (DA_SES_RMI_Interface) Naming.lookup(url);
				server.reset();
				servers.add(server);
			}	catch (RemoteException e1) {
				e1.printStackTrace();
			}	catch (NotBoundException e2) {
				e2.printStackTrace();
			}	catch (MalformedURLException e3) {
				e3.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void test() {
		
		DA_SES_RMI_Interface server1 = servers.get(0);
		DA_SES_RMI_Interface server2 = servers.get(1);
		DA_SES_RMI_Interface server3 = servers.get(2);
		DA_SES_RMI_Interface server4 = servers.get(3);
		
		try {
			
			Message message1 = new Message(1, server1.getServerIndex(), server2.getServerIndex());
			message1.setDelay(0);
			server1.sendMessage(urls[server2.getServerIndex()], message1);
			
			Message message2 = new Message(2, server2.getServerIndex(), server3.getServerIndex());
			message2.setDelay(10);
			server2.sendMessage(urls[server3.getServerIndex()], message2);
			
			
			Message message3 = new Message(3, server2.getServerIndex(), server3.getServerIndex());
			message3.setDelay(15);
			server2.sendMessage(urls[server3.getServerIndex()], message3);
			
			Thread.sleep(200);
			List<Message> server2Messages = server2.getReceivedMessage();
			Assert.assertTrue(1 == server2Messages.size());
			Assert.assertTrue(message1.getMsgId() == server2Messages.get(0).getMsgId());
			
			List<Message> server3Messages = server3.getReceivedMessage();
			
			//System.out.println(server3Messages.get(0).getMsgId());
			//System.out.println(server3Messages.get(1).getMsgId());
			//System.out.println(server3Messages.get(2).getMsgId());
			Assert.assertTrue(2 == server3Messages.size());
			Assert.assertTrue(message2.getMsgId() == server3Messages.get(0).getMsgId());
			Assert.assertTrue(message3.getMsgId() == server3Messages.get(1).getMsgId());
			
			
			System.out.println(message1.getDelay());
		}	catch (RemoteException e1) {
			e1.printStackTrace();
			Assert.fail();
		}	catch (InterruptedException e2) {
			e2.printStackTrace();
			Assert.fail();
		}
		
	}

}
