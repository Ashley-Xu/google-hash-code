import java.io.File;
import java.util.ArrayList;
import java.util.Scanner; 

public class GoogleHashCode {

		public static ArrayList<Integer> pizzasChosen = new ArrayList<Integer>();
		
		public static void main (String[] args) throws Exception{
			
			Scanner s = new Scanner (new File("/Users/ashleyxu/Downloads/b_small.in")).useDelimiter("\\s+");
			ArrayList<Integer> pizzas = new ArrayList<Integer>();
			
			
			int CAPACITY = Integer.parseInt(s.next());
			int PIZZASCONSIDERED = Integer.parseInt(s.next());
			
			
			while (s.hasNext()) {
		        pizzas.add(Integer.parseInt(s.next()));
		    }
	
			pizzas.add(0, 0); //this fixes annoying "off by one" errors
		
			forLoop(CAPACITY, PIZZASCONSIDERED, pizzas);
					
	}
	
	public static void forLoop(int CAPACITY, int PIZZASCONSIDERED, ArrayList<Integer> pizzas ) {
		//now we have our file digested into the variables MAX, TYPES, and pizzas. we can begin the algorithm:
		
		
		int [][] sol = new int[PIZZASCONSIDERED+1][CAPACITY+1];
		
		for(int m = 0; m <= CAPACITY; m++) {
			sol[0][m] = 0;
		}
					
		for(int n = 1; n <= PIZZASCONSIDERED; n++) {
			
			for (int m = 1; m <= CAPACITY; m++) {
				if(pizzas.get(n) > m) {
					sol[n][m] = sol[n-1][m];
				}
				else {
					sol[n][m] = Math.max(sol[n-1][m], pizzas.get(n)+sol[n-1][m-pizzas.get(n)]);
				}
				
			}
			
		}
		
		//now we need to use "sol" to back track and extract the solution. 
					
        backtracker(sol,PIZZASCONSIDERED,CAPACITY,pizzas);
        pizzas.remove(0);
        //now we print out the solution
        System.out.println(pizzasChosen.size());
		for(int i = 0; i < pizzasChosen.size(); i++) {
			System.out.print(pizzasChosen.get(i) + " ");
		}		
	}
	
	public static void backtracker(int[][] sol, int n, int m, ArrayList<Integer> pizzas) {
		if (m == 0 || n == 0) {
			//basecase
		}
		
		else if(sol[n][m] == sol[n-1][m]) {
			backtracker(sol, n-1, m, pizzas);
		}
		
		else {
			
			pizzasChosen.add(0,n-1);
			backtracker(sol, n-1, m-pizzas.get(n), pizzas);
			
		}
		
	}
	

		
}
