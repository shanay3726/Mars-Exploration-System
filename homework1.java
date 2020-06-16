import java.util.*;	
import java.io.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
class homework1{
	static int m,n,t;
	static int [][] el = new int[1000][1000];
	static int [][] cost =  new int [1000][1000];
	static double [][] totalpred =  new double [1000][1000];
	static int [][] tr = new int[1000][2];
	static int [][] visited = new int[1000][1000];
	static int [] targetvisited = new int[1000];
	static int [][][] path = new int[1000][1000][2];
	static int [][] parent1 = new int[1000][1000];
	static int [][] parent2 = new int[1000][1000];
	static int [][] queue = new int[250000][2];
	static int lan1,lan2;
	static int targetno,qint,max;
	static int [] targetend = new int[1000];
	//static long startTime;
public static void Neighboura(int a, int b, int index)
{	
	visited[a][b]=1;
	int c=0,x,y;
	c=checktargeta(a,b,index);
	for(int i=-1;i<2;i++)
	{
		for(int j=-1;j<2;j++)
		{	if((a+i)<0 || (b+j)<0 || (a+i)>=m || (b+j)>=n)
			{
			}	
			else
			{	
				if((el[a+i][b+j]-el[a][b]<max) && (el[a+i][b+j]-el[a][b]>((-1)*max))/* && (visited[a+i][b+j]==0)*/)
				{	if(visited[a+i][b+j]==0 && cost[a+i][b+j]==0)
					{
						parent1[a+i][b+j]=a;
						parent2[a+i][b+j]=b;
						////System.out.println("Neighbour class working" + a+i + " " + b+j+" parents " + parent1[a+i][b+j] + parent2[a+i][b+j]);
						if(i+j==(-1) || i+j==1 )
						{	double num1=10*Math.sqrt((Math.pow((tr[index][0]-(a+i)),2))+Math.pow((tr[index][1]-(b+j)),2));
							int num=(int)(num1);
							totalpred[a+i][b+j]=cost[a][b]+10+Math.abs(el[a+i][b+j]-el[a][b])+num+Math.abs(el[tr[index][0]][tr[index][1]]-el[a+i][b+j]);
							cost[a+i][b+j]=10+cost[a][b]+Math.abs(el[a+i][b+j]-el[a][b]);
							////System.out.println("Cost of "+(a+i)+","+(b+j)+" is "+cost[a+i][b+j]);
							////System.out.println("Prediction of "+(a+i)+","+(b+j)+" is "+totalpred[a+i][b+j]);
							//double agg=(10*Math.sqrt((tr[index][0]-(a+i))^2+(tr[index][1]-(b+j))^2));
							////System.out.println("Agg cost is "+num);
						}
						else
						{	double num1=10*Math.sqrt(Math.pow((tr[index][0]-(a+i)),2)+Math.pow((tr[index][1]-(b+j)),2));
							int num=(int)(num1);
							totalpred[a+i][b+j]=cost[a][b]+14+Math.abs(el[a+i][b+j]-el[a][b])+num+Math.abs(el[tr[index][0]][tr[index][1]]-el[a+i][b+j]);
							//totalpred[a+i][b+j]=totalpred[a][b]+14+Math.abs(el[a+i][b+j]-el[a][b])+(10*Math.sqrt((tr[index][0]-(a+i))^2+(tr[index][1]-(b+j))^2))+Math.abs(el[tr[index][0]][tr[index][1]]-el[a+i][b+j]);
							cost[a+i][b+j]=14+cost[a][b]+Math.abs(el[a+i][b+j]-el[a][b]);
							////System.out.println("Cost of "+(a+i)+","+(b+j)+" is "+cost[a+i][b+j]);
							////System.out.println("Prediction of "+(a+i)+","+(b+j)+" is "+totalpred[a+i][b+j]);
							//double agg=(10*Math.sqrt((tr[index][0]-(a+i))^2+(tr[index][1]-(b+j))^2));
							////System.out.println("Agg cost is "+num);
						}
						addqueuea(a+i,b+j,index);
					//visited[a+i][b+j]=1;
					}
					else if(visited[a+i][b+j]==0 && cost[a+i][b+j]!=0)
					{
						int costtemp;
						if(i+j==(-1) || i+j==1)
						{
							costtemp=10+cost[a][b]+Math.abs(el[a+i][b+j]-el[a][b]);
						}
						else
						{
							costtemp=14+cost[a][b]+Math.abs(el[a+i][b+j]-el[a][b]);
						}
						if(costtemp<cost[a+i][b+j])
						{	totalpred[a+i][b+j]=totalpred[a+i][b+j]-cost[a+i][b+j]+costtemp;
							cost[a+i][b+j]=costtemp;
							parent1[a+i][b+j]=a;
							parent2[a+i][b+j]=b;
							x=a+i;
							y=b+j;
							deletenode(x,y);
							addqueuea(a+i,b+j,index);
						}
					}
					else if(visited[a+i][b+j]==1)
					{	int costtemp;
						if(i+j==(-1) || i+j==1)
							costtemp=10+cost[a][b]+Math.abs(el[a+i][b+j]-el[a][b]);
						else
							costtemp=14+cost[a][b]+Math.abs(el[a+i][b+j]-el[a][b]);
						if(costtemp<cost[a+i][b+j])
						{
							totalpred[a+i][b+j]=totalpred[a+i][b+j]-cost[a+i][b+j]+costtemp;
							cost[a+i][b+j]=costtemp;
							parent1[a+i][b+j]=a;
							parent2[a+i][b+j]=b;
							x=a+i;
							y=b+j;
							visited[a+i][b+j]=0;
							addqueuea(a+i,b+j,index);
						}
					}
				}
			}
		}
	}
	removequeue(a,b);	
	if(c==0 && queue[0][0]==-999)
	{
		////System.out.println("FAIL");
		targetvisited[index]=1;
		try{
			if(index==0)
			{
			FileWriter file1 = new FileWriter("output.txt");
			PrintWriter p1 = new PrintWriter(file1);
			//System.out.println("FAIL");
			p1.print("FAIL");
			p1.println();
			file1.close();
			}
			else
			{
			FileWriter file1 = new FileWriter("output.txt",true);
			PrintWriter p1 = new PrintWriter(file1);
			//System.out.println("FAIL");
			p1.print("FAIL");
			p1.println();
			file1.close();
			}
		}
		catch(IOException e)
		{
			//System.out.print("FAIL");
		}
	}
}
public static void Neighbourc(int a, int b)
{	
	visited[a][b]=1;
	checktargetbc(a,b);
	int c=0,x,y;
	for(int i=-1;i<2;i++)
	{
		for(int j=-1;j<2;j++)
		{	if((a+i)<0 || (b+j)<0 || (a+i)>=m || (b+j)>=n)
			{
			}	
			else
			{	
				if((el[a+i][b+j]-el[a][b]<max) && (el[a+i][b+j]-el[a][b]>((-1)*max))/* && (visited[a+i][b+j]==0)*/)
				{	if(visited[a+i][b+j]==0 && cost[a+i][b+j]==0)
					{
						parent1[a+i][b+j]=a;
						parent2[a+i][b+j]=b;
						////System.out.println("Neighbour class working" + a+i + " " + b+j+" parents " + parent1[a+i][b+j] + parent2[a+i][b+j]);
						if(i+j==(-1) || i+j==1 )
						{
							cost[a+i][b+j]=10+cost[a][b];
							////System.out.println("Cost of "+(a+i)+","+(b+j)+" is "+cost[a+i][b+j]);
						}
						else
						{
							cost[a+i][b+j]=14+cost[a][b];
							////System.out.println("Cost of "+(a+i)+","+(b+j)+" is "+cost[a+i][b+j]);
						}
						addqueuec(a+i,b+j);
					//visited[a+i][b+j]=1;
					}
					else if(visited[a+i][b+j]==0 && cost[a+i][b+j]!=0)
					{
						int costtemp;
						if(i+j==(-1) || i+j==1)
							costtemp=10+cost[a][b];
						else
							costtemp=14+cost[a][b];
						if(costtemp<cost[a+i][b+j])
						{
							cost[a+i][b+j]=costtemp;
							parent1[a+i][b+j]=a;
							parent2[a+i][b+j]=b;
							x=a+i;
							y=b+j;
							deletenode(x,y);
							addqueuec(a+i,b+j);
						}
					}
					else if(visited[a+i][b+j]==1)
					{	int costtemp;
						if(i+j==(-1) || i+j==1)
							costtemp=10+cost[a][b];
						else
							costtemp=14+cost[a][b];
						if(costtemp<cost[a+i][b+j])
						{
							cost[a+i][b+j]=costtemp;
							parent1[a+i][b+j]=a;
							parent2[a+i][b+j]=b;
							x=a+i;
							y=b+j;
							visited[a+i][b+j]=0;
							addqueuec(a+i,b+j);
						}
					}
				}
			}
		}
	}
	removequeue(a,b);	
	if(queue[0][0]==-999)
	{
		////System.out.println("FAIL");
		printhalfpathbc();		
	}
}
public static void Neighbourb(int a, int b)
{	
	visited[a][b]=1;
	int c=0;
	
	for(int i=-1;i<2;i++)
	{
		for(int j=-1;j<2;j++)
		{		
			if((a+i)<0 || (b+j)<0 || (a+i)>=m || (b+j)>=n)
			{
				//System.out.println("Cannot proceed "+(a+i)+","+(b+j));
			}
			else
			{
				if((el[a+i][b+j]-el[a][b]<max) && (el[a+i][b+j]-el[a][b]>((-1)*max)) && (visited[a+i][b+j]==0))
				{	
					parent1[a+i][b+j]=a;
					parent2[a+i][b+j]=b;
					//System.out.println("Neighbour class working" + (a+i) + " " + (b+j)+" parents " + parent1[a+i][b+j] + parent2[a+i][b+j]);
					addqueueb(a+i,b+j);
					visited[a+i][b+j]=1;
				}
			}
		}
	}
	c=checktargetb(a,b);
	removequeue(a,b);
	//System.out.println("After remove");
	if(queue[0][0]==(-999))
		{
		//	System.out.println("FAIL");
			printhalfpathbc();
			
		}
	
	//System.out.println("After condition");
}
public static void printhalfpathbc()
{	int i,j=0;
	//System.out.println("IN half path");
	try{
	FileWriter file1 = new FileWriter("output.txt");
	PrintWriter p1 = new PrintWriter(file1);
	for(i=0;i<t;i++)
	{	//System.out.println(i + " th iteration");
		if(path[i][targetend[i]][0]==lan1 && path[i][targetend[i]][1]==lan2 && path[i][0][0]==tr[i][0] && path[i][0][1]==tr[i][1])
		{//System.out.println("if loop satisfy");
		for(j=targetend[i];j>=0;j--)
		{	
			//System.out.print(path[i][j][1]+","+path[i][j][0]+" ");
			p1.print(path[i][j][1]+","+path[i][j][0]+" ");
		}
		}
		else 
		{
			//System.out.println("FAIL");
			p1.print("FAIL");
			p1.println();
			continue;
		}
		System.out.println();
		p1.println();
	}
	//for(i=0;i<t;i++)
				//System.out.println("Cost of path = "+cost[tr[i][0]][tr[i][1]]);
	//final long duration = (//System.nanoTime() - startTime)/1000000;
		//System.out.println(duration);
	file1.close();
	System.exit(0);
	}
	catch(IOException e)
	{
	//System.out.print("FAIL");
	}
}
public static int checktargetb(int a,int b)
{	//System.out.println("Check target class working");
	int c=0;
	for(int i=0;i<t;i++)
	{
		if(a==tr[i][0] && b==tr[i][1])
		{	//System.out.println("Writing path in checktarget class");
			targetvisited[targetno]=1;
			targetno++;
			pathbc(a,b,i);
			c=1;
		}
		
	}
	return c;
}
public static void checktargetbc(int a,int b)
{	////System.out.println("Check target class working");
	for(int i=0;i<t;i++)
	{
		if(a==tr[i][0] && b==tr[i][1])
		{	////System.out.println("Writing path in checktarget class");
			targetvisited[targetno]=1;
			targetno++;
			pathbc(a,b,i);
		}
	}
}
public static int checktargeta(int a,int b,int index)
{	////System.out.println("Check target class working");
		if(a==tr[index][0] && b==tr[index][1])
		{	////System.out.println("Writing path in checktarget class");
			targetvisited[index]=1;
			targetno++;
			patha(a,b,index);
			return 1;
		}
		else
			return 0;
}
public static void pathbc(int x,int y,int index)
{	////System.out.println("Path working");
	
	int i=0,j;
	int tar1,tar2;
	tar1=x;
	tar2=y;
	try{
	FileWriter file1 = new FileWriter("output.txt");
	PrintWriter p1 = new PrintWriter(file1);
    //printWriter.println(textToAppend);  //New line
	
	
	//BufferedWriter ab = new BufferedWriter(new FileWriter("output.txt"));
	////System.out.println(lan1+","+lan2);
	
	path[index][i][0]=tar1;
	path[index][i][1]=tar2;
	i++;
	while((x!=lan1) || (y!=lan2))
	{		////System.out.println("y is "+y);
			////System.out.println("Path method while working");
			path[index][i][0]=parent1[x][y];
			path[index][i][1]=parent2[x][y];
		//	//System.out.println(x+","+y+" "+ parent1[x][y] +"," + parent2[x][y]+" ");
			x=path[index][i][0];
			y=path[index][i][1];
			i=i+1;
	}
	targetend[index]=i-1;
	for(j=i-1;j>=0;j--)
	{
		////System.out.print(path[index][j][1]+","+path[index][j][0]+" ");
		//p1.print(path[index][j][1]+","+path[index][j][0]+" ");
	}
	////System.out.println(tar2+","+tar1);
	//p1.print(tar2+","+tar1);
	////System.out.println("PATH IS : ");
	if(targetno==t)
	{
		for(i=0;i<t;i++)
		{
			for(j=targetend[i];j>=0;j--)
			{
		//		//System.out.print(path[i][j][1]+","+path[i][j][0]+" ");
				p1.print(path[i][j][1]+","+path[i][j][0]+" ");
			}
			////System.out.println();
			p1.println();
		}
		for(i=0;i<t;i++)
				//System.out.println("Cost of path = "+cost[tr[i][0]][tr[i][1]]);
	//final long duration = (//System.nanoTime() - startTime)/1000000;
	//System.out.println(duration);
	file1.close();
	System.exit(0);
	}
	//p1.println();
	
	}
	catch(IOException e)
	{
	//System.out.print("FAIL");
	}	
}
public static void patha(int x,int y,int index)
{	////System.out.println("Path working");
	int [][] pathsa = new int[5000][2];
	int i=0;
	int tar1,tar2;
	tar1=x;
	tar2=y;
	try{
	if(index==0)
	{
	FileWriter file1 = new FileWriter("output.txt");
	}
	FileWriter file1 = new FileWriter("output.txt",true);
	PrintWriter p1 = new PrintWriter(file1);
	pathsa[i][0]=tar1;
	pathsa[i][1]=tar2;
	i++;
	////System.out.print(lan2+","+lan1+" ");
	while((x!=lan1) || (y!=lan2))
	{		////System.out.println("y is "+y);
			////System.out.println("Path method while working");
			pathsa[i][0]=parent1[x][y];
			pathsa[i][1]=parent2[x][y];
			////System.out.println(x+","+y+" "+ parent1[x][y] +"," + parent2[x][y]+" ");
			x=pathsa[i][0];
			y=pathsa[i][1];
			i=i+1;
	}
	//p1.print(lan2+","+lan1+" ");
	for(int j=i-1;j>=0;j--)
	{
		//System.out.print(pathsa[j][1]+","+pathsa[j][0]+" ");
		p1.print(pathsa[j][1]+","+pathsa[j][0]+" ");
	}
	p1.println();
	file1.close();
	}
	catch(IOException e)
	{
	//System.out.print("FAIL");
	}	
	////System.out.print(tar2+","+tar1);
		
}
public static void removequeue(int a,int b)
{	
	if(queue[0][0]==a && queue[0][1]==b)
		{//	//System.out.println("Remove working" + a + " " + b);
			for(int j=0;j<m*n;j++)
			{
				queue[j][0]=queue[j+1][0];
				queue[j][1]=queue[j+1][1];
			}
			
		}
		////System.out.println("Qint is "+qint);
		//queue[qint-1][0]=-999;
		//queue[qint-1][1]=-999;
		qint--;
		////System.out.println("Qint is "+qint);
		////System.out.println("Queue at index 0 "+queue[0][0]+queue[0][1] );
	
}
public static void deletenode(int a,int b)
{	
	for(int i=0;i<qint;i++)
	{
		if(queue[i][0]==a && queue[i][1]==b)
		{
			for(int j=i;j<qint-1;j++)
			{
				queue[j][0]=queue[j+1][0];
				queue[j][1]=queue[j+1][1];
			}
		}
	}
	qint--;
}
public static void addqueueb(int a,int b)
{	//System.out.println("Add queue working");
	queue[qint][0]=a;
	queue[qint][1]=b;
	qint++;
	queue[qint][0]=-999;
	queue[qint][1]=-999;
	for(int i=0;i<=qint;i++)
	{
		//System.out.print(queue[i][0]+","+queue[i][1]+" ");
	}
}
public static void addqueuec(int a,int b)
{	
//	//System.out.println("Add queue working");
	queue[qint][0]=a;
	queue[qint][1]=b;
	qint++;
	sortqueuec(a,b,qint);
	queue[qint][0]=-999;
	queue[qint][1]=-999;
	////System.out.println("Queue is ");
	for(int i=0;i<qint;i++)
	{
		////System.out.print(queue[i][0]+","+queue[i][1]+" ");
	}
				
}
public static void addqueuea(int a,int b,int index)
{	
	////System.out.println("Add queue working");
	queue[qint][0]=a;
	queue[qint][1]=b;
	qint++;
	sortqueuea(a,b,qint,index);
	queue[qint][0]=-999;
	queue[qint][1]=-999;
	////System.out.println("Queue is ");
	for(int i=0;i<qint;i++)
	{
		////System.out.print(queue[i][0]+","+queue[i][1]+" ");
	}
}
public static void sortqueuec(int a, int b, int ite)
{	int temp1,temp2;
	if(ite!=1)
	{
		for(int i=0;i<ite;i++)
		{
			for(int j=0;j<ite-1;j++)
			{
				if(cost[queue[j+1][0]][queue[j+1][1]] < cost[queue[j][0]][queue[j][1]])
				{
					temp1=queue[j][0];
					temp2=queue[j][1];
					queue[j][0]=queue[j+1][0];
					queue[j][1]=queue[j+1][1];
					queue[j+1][0]=temp1;
					queue[j+1][1]=temp2;
				}
			}
		}
	}
}
public static void sortqueuea(int a, int b, int ite,int index)
{	int temp1,temp2;
	if(ite!=1)
	{
		for(int i=0;i<ite;i++)
		{
			for(int j=0;j<ite-1;j++)
			{
				if(totalpred[queue[j][0]][queue[j][1]] > totalpred[queue[j+1][0]][queue[j+1][1]])
				{
					temp1=queue[j][0];
					temp2=queue[j][1];
					queue[j][0]=queue[j+1][0];
					queue[j][1]=queue[j+1][1];
					queue[j+1][0]=temp1;
					queue[j+1][1]=temp2;
				}
			}
		}
	}
}
public static void main(String[] args) throws Exception
{		//startTime = //System.nanoTime();
		int k,l,a,b,x,y;
		Scanner sc= new Scanner(System.in);
		File file = new File("input.txt");  
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st,algo;
		algo=br.readLine();
		st=br.readLine();
		String[] div=st.split("\\s");
		n=Integer.parseInt(div[0]);
		m=Integer.parseInt(div[1]);
		//System.out.println("Width and Height are " +n+" "+m);
		st=br.readLine();
		div=st.split("\\s");
		lan2=Integer.parseInt(div[0]);
		lan1 = Integer.parseInt(div[1]);
		//System.out.println("Landing site is " +lan1+" "+lan2);
		st=br.readLine();
		max=Integer.parseInt(st);
		max=max+1;
		//System.out.println("Max elevation is "+max);
		st=br.readLine();
		t=Integer.parseInt(st);
		//System.out.println("Number of target sites are "+t);
		targetno=0;
		qint=0;
		for(int i=0;i<t;i++)
		{
			st=br.readLine();
			div=st.split("\\s");
			tr[i][1]=Integer.parseInt(div[0]);
			tr[i][0]=Integer.parseInt(div[1]);
		}
		//for(int i=0;i<t;i++)
		//System.out.println("Target is "+tr[i][0]+","+tr[i][1]);
		for(int i=0;i<m;i++)
		{	
			st=br.readLine();
			div=st.split("\\s");
			for(int j=0;j<n;j++)
			{	
				el[i][j]=Integer.parseInt(div[j]);
				//System.out.print(el[i][j] + " ");
			}
			//System.out.println(i);
			//System.out.println();
		}
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				//System.out.print(el[i][j] + " ");
				visited[i][j]=0;
			}
			//System.out.println();
		}
		a=lan1;
		b=lan2;
		for(int i=0;i<t;i++)
		{
			targetvisited[i]=0;
		}		
		visited[a][b]=1;
		if(algo.compareTo("BFS")==0)
		{
			addqueueb(a,b);
			Neighbourb(a,b);
			while(targetvisited[t-1]==0)
			{
				x=queue[0][0];
				y=queue[0][1];
				Neighbourb(x,y);
			}
		}
		else if(algo.compareTo("UCS")==0)
		{
			addqueuec(a,b);
			Neighbourc(a,b);
			while(targetvisited[t-1]==0)
			{
				x=queue[0][0];
				y=queue[0][1];
				Neighbourc(x,y);
			}
			//for(int i=0;i<t;i++)
			//	//System.out.println("Cost of path = "+cost[tr[i][0]][tr[i][1]]);
		}
		else if(algo.compareTo("A*")==0)
		{
			for(int i=0;i<t;i++)
			{	//queue=null;
				for(int abc=0;abc<m*n;abc++)
				{
					queue[abc][0]=-999;
					queue[abc][1]=-999;
				}
				qint=0;
				for(int j=0;j<m;j++)
				{
					for(k=0;k<n;k++)
					{
						visited[j][k]=0;
						cost[j][k]=0;
						totalpred[j][k]=0;
					}
				}	
				addqueuea(a,b,i);
				Neighboura(a,b,i);
				while(targetvisited[i]==0)
				{
					x=queue[0][0];
					y=queue[0][1];
					Neighboura(x,y,i);
				}
				//System.out.println("Cost of path = "+cost[tr[i][0]][tr[i][1]]);
			}
		}
		else
		{
			//System.out.println("FAIL FAIL FAIL because input is wrong");
			try{
					FileWriter file1 = new FileWriter("output.txt",true);
					PrintWriter p1 = new PrintWriter(file1);
					for(int i=0;i<t;i++)
					{
						p1.println("FAIL");
					}
					file1.close();
			}
			catch(IOException e)
			{
				//System.out.print("FAIL");
				FileWriter file1 = new FileWriter("output.txt",true);
				PrintWriter p1 = new PrintWriter(file1);
				for(int i=0;i<t;i++)
				{
					p1.println("FAIL");
				}
					file1.close();
			}
		}
		//final long duration = (//System.nanoTime() - startTime)/1000000;
		//System.out.println(duration);
	}
}