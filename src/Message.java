
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * Implements messages that are shared among servers.
 */


public class Message implements Serializable{
	
	private static final long serialVersionUID = 4745507L;
	
	/*
	 * ID for each message
	 */
	
	int msgId = 0;
	
	/*
	 * Buffer to keep list of messages sent
	 */
	
	Map<Integer, List<Integer>> sentMessagesBuffer;
	
	/*
	 * Clock attribute for Messages
	 */
	
	List<Integer> clock;
	
	/*
	 * ID of Server sending the message
	 */
	
	int srcServer;
	
	/*
	 * ID of Server to receive the message
	 */
	
	int destServer;
	
	/*
	 * Delay to check the implementation
	 */
	
	int delay = 0;
	
	/*
	 * System time at message delivery for debugging for delays
	 */
	
	private long sysTime;
	
	
	/*
	 * Message Object Constructor
	 */
	
	public Message(int msgId, int srcServer, int destServer) {
		this.msgId = msgId;
		this.srcServer = srcServer;
		this.destServer = destServer;
		
	}
	
	/*
	 * ***************************************************************
	 * Implementing set functions (For functionality and testing both)
	 * ***************************************************************
	 */
	
	/*
	 * set sentMessagesBuffer
	 */
	
	public void setSentMessagesBuffer(Map<Integer, List<Integer>> sentMessagesBuffer) {
		this.sentMessagesBuffer = sentMessagesBuffer;
	}
	
	/*
	 * set clock
	 */
	
	public void setClock(List<Integer> clock) {
		this.clock = clock;
	}
	
	/*
	 * set delay. If delay is more than 0ms, then process is set to sleep. This is to test proper implementation of Algo.
	 */
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	/*
	 * set system time
	 */
	
	public void setSysTime(long sysTime) {
		this.sysTime = sysTime;
	}
	
	
	/*
	 * ***************************************************************
	 * Implementing get functions (For functionality and testing both)
	 * ***************************************************************
	 */
	
	/*
	 * get message ID
	 */
	
	public int getMsgId() {
		return this.msgId;
	}
	
	/*
	 * get list of sent messages
	 */
	
	public Map<Integer, List<Integer>> getSentMessagesBuffer() {
		return this.sentMessagesBuffer;
	}
	
	/*
	 * get clock status
	 */
	
	public List<Integer> getClock(){
		return this.clock;
	}
	
	/*
	 * get source of message
	 */
	
	public int getSrcServer() {
		return this.srcServer;
	}
	
	/*
	 * get destination of message
	 */
	
	public int getDestServer() {
		return this.destServer;
	}
	
	/*
	 * get the delay in message delivery
	 */
	
	public int getDelay() {
		return this.delay;
	}
	
	/*
	 * get system time at time of message delivery
	 */
	
	public long getSysTime() {
		return this.sysTime;
	}
	
	
	

}
