


import java.util.*;
import java.io.*;

public class usrbincrash {
	static Vector<Item> items = new Vector<Item>();
	static int weightToEject;
	static int totalWeight;
	static int totalCost;
	
	/**
	 * Takes propperly formatted file as input and calculates the minimum value of items that must be ejected
	 * using a dynamic programming approach to the Knapsack problem. 
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFile;
		if(args.length==0){
			System.err.println("Please give input file location as arg 0");
			System.exit(0);
		}
		inputFile = args[0];
		try{
			readFile(inputFile);
		}catch(FileNotFoundException e){
			System.err.println(e.toString());
			System.exit(0);
		}catch(IOException e){
			System.err.println(e.toString());
			System.exit(0);
		}
		System.out.println(knap());
		int min = calculateMin();
		System.out.println(min);
	}
	
	/**
	 * Read the file and set local variables
	 * @param inputFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void readFile(String inputFile) throws FileNotFoundException,IOException{
		FileInputStream fstream = new FileInputStream(inputFile);
	    DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String buf;
	    String[] temp = null;
	    String sku;
	    int weight;
	    int cost;
	    //Get the weight to eject
	    if((buf = br.readLine()) != null){
	    	weightToEject = Integer.parseInt(buf.trim());
	    }
	    
	    while ((buf = br.readLine()) != null){
	    	temp = buf.split("\\s+");
	    	sku = temp[0].trim();
	    	weight = Integer.parseInt(temp[1].trim());
	    	cost = -1*Integer.parseInt(temp[2].trim());
	    	items.add(new Item(sku,weight,cost));
	    	
	    	//Include this items weight in the total weight and cost
	    	totalWeight = totalWeight + weight;
	    	totalCost = totalCost + cost;
	    }
	}
	
	/**
	 * Dynamic programming solution to solve this Knapsack problem
	 */
	private static int calculateMin(){
		//Number of items
		int N = items.size();
		//Max Weight
		int W = totalWeight - weightToEject;
		
		int[] profit = new int[N+1];
        int[] weight = new int[N+1];
        
        //Initialize arrays
        for (int n = 1; n <= N; n++) {
            profit[n] = items.get(n-1).cost;
            weight[n] = items.get(n-1).weight;
        }
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];

        
        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w){
                	option2 = profit[n] + opt[n-1][w-weight[n]];
                }

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }
        
        //Compute and return the ammount that must the lost
        return totalCost-opt[N][W];
        
	}
	
	
	
	
	
	static KnapItem P[][];
	public static int knap(){
		int K = weightToEject;
		int n = items.size();
		P= new KnapItem[n][K];
		int S[] = new int[n];
		int V[] = new int[n];
		
		//Initialize S & V
		for(int i=0;i<n;i++){
			S[i]=items.get(i).weight;
			V[i]=items.get(i).cost;
		}
		for(int i=0;i<n;i++){
			for(int k=0;k<K;k++){
				P[i][k]=new KnapItem();
			}
		}
		
		P[0][0].exist = true;
		P[0][0].value = 0;
		for(int k=1;k<K;k++)
		        P[0][k].exist = false;
		for(int i=1;i<n;i++)
		{       for(int k=0;k<K;k++)
		        {
		                 P[i][k].exist = false;
		                 if(P[i-1][k].exist)
		                 {
		                         P[i][k].exist = true;
		                         P[i][k].copies = 0;
		                         P[i][k].value = P[i-1][k].value;
		                 }
		                 if(k-S[i]>=0 && P[i-1][ k-S[i]].exist)
		                 {       if((!P[i][k].exist) || P[i][k].value< P[i-1][ k-S[i]].value + V[i])
		                         {
		                                  P[i][k].exist = true;
		                                  P[i][k].copies = 1;
		                                  P[i][k].value = P[i-1][ k-S[i]].value + V[i];
		                         }
		                 }
		                 if(k-S[i]>=0 && P[i][ k-S[i]].exist)
		                 {       if((!P[i][k].exist) || P[i][k].value< P[i][ k-S[i]].value + V[i])
		                         {
		                                  P[i][k].exist = true;
		                                  P[i][k].copies = P[i][k-S[i]].copies+1;
		                                  P[i][k].value = P[i][ k-S[i]].value + V[i];
		                         }
		                 }
		        }
		}
		int max = 0;
		for(int i=1;i<K;i++)
		        if(P[n-1][i].value>P[n-1][max].value)
		                 max = i;
		return P[n-1][max].value;

	}
}

