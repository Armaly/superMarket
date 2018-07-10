public class myQueue<Type>{
	
	myNode front, back;
	int size;
	
	public myQueue()//generic constructor
	{
		front = null;
		back = null;
		size = 0;
	}
	public void add(myNode a)//adding, assuming it's already a node 
	{	
		if(size==0)//if first element
		{
			front = a;
			back = a;
		}
		else
		{
			back.setNext(a); //sets back's next to a
			back = a; //back now becomes a;
		}
		size++; //size incremented
	}
	public myNode remove()
	{
		/*if(isEmpty())//If queue is empty
		{
			System.out.println("This queue is already empty");
			System.exit(0);
		}*/
		myNode temp = new myNode();
		try
		{
		/*	 temp = front; //sets temp to front
			//front.setNext(front.getNext());  //sets front to front.next */
			front = front.getNext();
			size--; //size decremented
			
			
			return temp; //return the node
		}
		catch(Exception e)
		{
			if(isEmpty())//If queue is empty
			{
			System.out.println("Queue is now empty");
			front = null;
			back = null;
			}
		
		}
		return temp;
		
	}
	public myNode peek()
	{
		if(isEmpty())
		{
			System.out.println("Queue is currently empty. Returning blank node.");
			String z ="";
			myNode temp = new myNode(z,0,0); //makes a blank node and returns that 
			return temp;
		}
		myNode temp = front; //sets temp to front node and returns 
		return temp; 
	}
	public int getSize()//returns size
	{
		return size;
	}
	public boolean isEmpty()
	{
		if(size==0)
			return true;
		else
			return false;
	}

}
