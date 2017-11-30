import java.util.List;
import java.util.Map;

public interface Message_Interface {
	
	/*
	 * set sentMessagesBuffer
	 */
	
	public void setSentMessagesBuffer(Map<Integer, List<Integer>> sentMessagesBuffer);
	
	/*
	 * set clock
	 */
	
	public void setClock(List<Integer> clock);
	
	/*
	 * set delay. If delay is more than 0ms, then process is set to sleep. This is to test proper implementation of Algo.
	 */
	
	public void setDelay(int delay);
	
	/*
	 * set system time
	 */
	
	public void setSysTime(long sysTime);
	
	
	/*
	 * ***************************************************************
	 * Implementing get functions (For functionality and testing both)
	 * ***************************************************************
	 */
	
	/*
	 * get message ID
	 */
	
	public int getMsgId();
	
	/*
	 * get list of sent messages
	 */
	
	public Map<Integer, List<Integer>> getSentMessagesBuffer();
	
	/*
	 * get clock status
	 */
	
	public List<Integer> getClock();
	
	/*
	 * get source of message
	 */
	
	public int getSrcServer();
	
	/*
	 * get destination of message
	 */
	
	public int getDestServer();
	
	/*
	 * get the delay in message delivery
	 */
	
	public int getDelay();
	
	/*
	 * get system time at time of message delivery
	 */
	
	public long getSysTime();


}
