public class superMarket {

	//Preconditions: Is called
	//Postconditions: Returns a customer node
	public static myNode createCustomer(int i, int a)throws Exception //creates customer node and returns it 
	{
		String id="ID#: ";
		System.out.println("ID :"+ i+" has been created.");
		id = id+i;
		//System.out.println("ID CHECK : "+id);
		myNode temp = new myNode(id, a); 
		System.out.println("Customer # " + temp.getCust()+" has arrived.");

		return temp;	
	}
	//Preconditions: Customer node is has arrived and wants to be put in lane
	//Postconditions: Places a customer in smallest lane(queue) for checkout
	public static void enqueue(myNode cust, myQueue<myNode> lineA, myQueue<myNode> lineB, myQueue<myNode> lineC, int initialStart)
	{
		//Line Size Array  to determine shortest line
		int lineSize[] = new int[3];


		cust.setCreation(initialStart);//set initial wait time marker
		//Empty Checks 
		if(lineA.isEmpty())
		{
			lineA.add(cust);

			System.out.println("Customer "+ cust.getCust() + " has been added to Line A" );
		}
		else if(lineB.isEmpty())
		{
			lineB.add(cust);
			System.out.println("Customer "+ cust.getCust() + " has been added to Line B" );
		}
		else if(lineC.isEmpty())
		{
			lineC.add(cust);
			System.out.println("Customer "+ cust.getCust() + " has been added to Line C" );
		}
		else 
		{
			lineSize[0] = lineA.getSize();
			lineSize[1] = lineB.getSize();
			lineSize[2] = lineC.getSize();

			//Quick check to find smallest line 
			int smallest = 5000000;
			int place=0;
			for(int i=0;i<lineSize.length;i++)
			{
				System.out.println("LANE "+i+" SIZE: "+lineSize[i]);
				if(lineSize[i] < smallest)
				{
					smallest = lineSize[i];
					place = i;
				}
			}

			//Adds customer into line based on shortest line available
			if(place == 0)
			{
				lineA.add(cust);
				System.out.println("Customer "+ cust.getCust() + " has been added to Line A" );
			}
			else if(place == 1)
			{
				lineB.add(cust);
				System.out.println("Customer "+ cust.getCust() + " has been added to Line B" );
			}
			else if(place == 2)
			{
				lineC.add(cust);
				System.out.println("Customer "+ cust.getCust() + " has been added to Line C" );
			}	
		}	
	}

	//Preconditions: Is called
	//Postconditions: Waits the service time for the node then removes node from queue
	public static void service(myQueue<myNode> currLine, int a)throws Exception
	{
		if(!currLine.isEmpty())
		{
			myNode temp = currLine.peek(); //peeks top of line 

			System.out.println("Customer # "+temp.getCust()+" is now being serviced."); //starts servicing 


			currLine.peek().setServiceCounter(currLine.peek().getService());//sets service counter to the time to be serviced
		}
	}
	//Preconditions: Is called
	//Postconditions: Returns max wait
	public static int checkMaxWait(myNode cust, int a, int maxWait)
	{
		System.out.println("Checking max wait");

		int timeWaited = a-cust.getCreation(); //subtracts the current iteration from the customer creation iteration 

		System.out.println("Checking customer "+cust.getCust() +" wait time is "+cust.getCreation());
		System.out.println("A iteration: "+a);
		if(timeWaited > maxWait) //if it is larger than the previous maxwait returns the new one 
		{
			return a;
		}
		else
		{
			return maxWait; 
		}
	}
	public static int remove(myQueue<myNode> currLine, int a, int maxWait)
	{
		if(!currLine.isEmpty())
		{
			myNode temp = currLine.peek(); //peeks top of line 

			System.out.println("Customer # "+temp.getCust()+" is now leaving."); //starts servicing 

			maxWait = checkMaxWait(temp, a, maxWait);

			currLine.remove();

			return maxWait;
		}

		return maxWait;
	}
	//Preconditions: Program ran
	//Postconditions: Customers added to initial line 
	public static int preLaunch(int i, myQueue<myNode> lineA, myQueue<myNode> lineB, myQueue<myNode> lineC)throws Exception
	{
		for(int j=0;j<6;j++)
		{
			myNode cust = createCustomer(i,-1); //creates customer
			i++;

			enqueue(cust, lineA, lineB, lineC, 0); //adds customer to one of the lines	
		}
		return i;
	} 
	//Preconditions: Is Ran
	//Postconditions: maxLine array is checked to see if it is no longer max and rewritten if so
	public static int[] maxCheck(int[] maxLine, myQueue<myNode> lineA, myQueue<myNode> lineB, myQueue<myNode> lineC)
	{
		int sum=0;
		//System.out.println("Previous max is "+ maxLine[3]);
		//Quick check to find smallest line 
		int largest = 0;
		int place=0;
		/*System.out.println("A: "+lineA.getSize());
		System.out.println("B: "+lineB.getSize());
		System.out.println("C: "+lineC.getSize()); */
		sum = lineA.getSize() + lineB.getSize() + lineC.getSize();
		//System.out.println(sum);

		if(sum > maxLine[3])
		{
			//System.out.println("New Max Found!");
			maxLine[0] = lineA.getSize();
			maxLine[1] = lineB.getSize();
			maxLine[2] = lineC.getSize();
			maxLine[3] = sum;
		}	
		return maxLine;
	}
	public static void main(String[] args)throws Exception 
	{	
		int i= 1;
		int maxLine[] = {0,0,0,0};
		int maxWait =0;
		boolean checkA,checkB,checkC=true; 
		//Creates 3 "Lanes" 
		myQueue<myNode> lineA = new myQueue();
		myQueue<myNode> lineB = new myQueue();
		myQueue<myNode> lineC = new myQueue();

		i=preLaunch(i,lineA,lineB,lineC);
		//Timer to run for day

		int anotherCust =(int)(Math.random() * 4)+1;//random int between 1 and 4;
		int custCounter =1; 

		for(int a =0; a<720;a++)//while a minute has not gone by
		{
			maxLine=maxCheck(maxLine, lineA, lineB, lineC); //checks to see if the max num of customers across all lanes is reached and returns i so
			//System.out.println("RETUUURNNED :"+maxLine[3]);
			//System.out.println("A VALUE: "+a+" CUSTCOUNTER: "+custCounter +" ANOTHER CUST VALUE: "+anotherCust);
			if(custCounter==anotherCust)//if the desired amount of turns for another customer has been reached
			{
				//Thread 1
				myNode cust = createCustomer(i,a); //creates customer
				//System.out.println(cust.getCust());
				i++; 							// increments id counter

				System.out.println("Service time for "+ cust.getCust() +" is: "+cust.getService());
				//Thread 2
				enqueue(cust, lineA, lineB, lineC, a); //adds customer to one of the lines	


				anotherCust = (int)(Math.random() *4)+1;//random int between 1 and 4
				custCounter=1; //reset custcounter
			}	
			else
			{
				custCounter++; //otherwise increment cust counter
			}


			//Thread 3
			//service
			if(!lineA.isEmpty())
			{
				//System.out.println("A SERVICE TIMING: "+ lineA.peek().getServiceCounter());

				if(lineA.peek().checkServiceCounter()==true)//if the time for servicing is done 
				{
					maxWait = remove(lineA, a, maxWait);
					service(lineA,a);
				}
				else
				{
					//System.out.println("waiting");
					lineA.peek().decServiceCounter(); //decrement customer service by 1

				}
			}
			if(!lineB.isEmpty())
			{
				//System.out.println("B SERVICE TIMING: "+ lineB.peek().getServiceCounter());
				if(lineB.peek().checkServiceCounter()==true)//if the time for servicing is done 
				{
					maxWait = remove(lineB, a, maxWait);	
					service(lineB,a);
				}
				else
				{
					lineB.peek().decServiceCounter(); //decrement customer service by 1
				}
			}
			if(!lineC.isEmpty())
			{
				//System.out.println("C SERVICE TIMING: "+ lineC.peek().getServiceCounter());
				if(lineC.peek().checkServiceCounter()==true)//if the time for servicing is done 
				{
					maxWait = remove(lineC, a, maxWait);	
					service(lineC,a);
				}
				else
				{
					lineC.peek().decServiceCounter(); //decrement customer service by 1
				} 
			}
			maxLine=maxCheck(maxLine, lineA, lineB, lineC); //checks to see if the max num of customers across all lanes is reached and returns i so
		}
		maxLine=maxCheck(maxLine, lineA, lineB, lineC); //checks to see if the max num of customers across all lanes is reached and returns i so


		System.out.println("Simulation has finished.");

		System.out.println("Max number of customers across all lines: "+maxLine[3] +" Lane A:"+maxLine[0] +" Lane B:" +maxLine[1] +" Lane C: "+maxLine[2]);

		System.out.println("Longest wait time for a customer was: "+maxWait);

	}

}