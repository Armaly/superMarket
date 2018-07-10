public class myNode {

	private myNode next;
	private int arrivalTime;
	private int serviceTime;
	private int creationTime;
	private int waitTime;
	private int serviceCounter;
	private String custID;
	
	public myNode()//default node constructor
	{
		next=null;
		arrivalTime=0;
		serviceTime=0;
		creationTime = 0; 
		waitTime=0;
		serviceCounter=0;
		custID="";
	}
	public myNode(String custID)//constructor that takes in data of any type and custID 
	{
		this.custID=custID;
		arrivalTime = (int)(Math.random() * (5-2)) + 2;
		serviceTime = (int)Math.random() * 4 + 1;
		creationTime = 0;
		waitTime=0;
		serviceCounter=serviceTime;
		next = null;
	}

	public myNode(String custID, int a)//constructor that takes in data of any type and custID 
	{
		this.custID=custID;
		arrivalTime = (int)Math.random() * 4 + 1;
		serviceTime = (int)(Math.random() * 10) + 1;
		creationTime = a;
		waitTime=0;
		serviceCounter=serviceTime;
		next = null;
	}
	public myNode(String custID, int arrivalTime, int serviceTime)//for empty node calls 
	{
		
		this.custID=custID;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		next = null;
	}
	public void decServiceCounter()
	{
		serviceCounter--;
	}
	public int getServiceCounter()
	{
		return serviceCounter;
	}
	public void setServiceCounter(int num)
	{
		serviceCounter = num;
	}
	public boolean checkServiceCounter()
	{
		if(serviceCounter==0)
		{
			return true;
		}
		return false;
	}
	public int getCreation()
	{
		return creationTime;
	}
	public void setCreation(int initial)
	{
		creationTime = initial;
	}
	public int getWait()
	{
		return waitTime;
	}
	public void setWait(int wait)
	{
		waitTime = wait; 
	}
	public int getService()
	{
		return serviceTime;
	}
	public int getArrival()
	{
		return arrivalTime;
	}
	public String getCust()
	{
		return custID;
	}
	public myNode getNext()//getter, returns next
	{
		return next;
	}
	public void setNext(myNode a)//setter, sets next
	{
		next = a;
	}
	
}