import java.util.Vector;


public class knap {
	static Vector<Item> items = new Vector<Item>();
	static int weightToEject;
	static int totalWeight;
	static int totalCost;
	static KnapItem P[][];
	public static int knap(){
		int n = items.size();
		P= new KnapItem[n][n];
		int S[] = new int[n];
		int V[] = new int[n];
		int K = weightToEject;
		
		//Initialize S & V
		for(int i=0;i<=n;i++){
			S[i]=items.get(i).weight;
			V[i]=items.get(i).cost;
		}
		
		P[0][0].exist = true;
		P[0][0].value = 0;
		for(int k=1;k<=K;k++)
		        P[0][k].exist = false;
		for(int i=1;i<=n;i++)
		{       for(int k=0;k<=K;k++)
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
		for(int i=1;i<=K;i++)
		        if(P[n][i].value>P[n][max].value)
		                 max = i;
		return P[n][max].value;

	}
}
