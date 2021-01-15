import java.util.ArrayList;
import java.util.Stack;

public class Cliques {
	private ArrayList<int[][]> L1 = new ArrayList<int[][]>();
	private int MAX=100;
	private ArrayList<ArrayList<People>> PArr = new ArrayList<ArrayList<People>>();
	boolean baliozkoa = false;
	//==========================================
	public Stack<People> aP = new Stack<People>();//actual path	
	public ArrayList<Stack<People>> allPaths = new ArrayList<Stack<People>>();
	
	
	
	/**
	 * Constructor method
	 */
	@SuppressWarnings("unused")
	public Cliques() {
		for(int[][] M1: L1) {
			M1= new int[MAX][MAX];
		}
		for(ArrayList<People> L: PArr) {
			L= new ArrayList<People>();
		}
	}

	
	//==========================================================
	//
	//
	//		BACKTACK METHOD
	//
	//
	//=========================================================
	
	
	/**
	 * Method that will call to the backtrack method to find all non repeated
	 * cycles for one person
	 * @param G
	 * @param p1
	 */
	public void fCliques(Graph G, People p1) {
		
		for(@SuppressWarnings("unused") Stack<People> s: this.allPaths) {
		
			s = new Stack<People>();
		
		}
		
		aP.push(p1);
		
		for(People p2: G.adjacentsToV(p1)) {
			
			aP.push(p2);
			backTrack(G, p2, p1);
				
		}
		
		aP.pop();
		
	}
	
	
	/**
	 * Backtracking method
	 * @param G1
	 * @param p2
	 * @param p1
	 */
	public void backTrack(Graph G1, People p2, People p1) {
		
		// if this node is the node we are looking for and the path has a size of >5
		if(p2.equals(p1) && aP.size() >5) {
					
			Stack<People> newPath = new Stack<People>();
			
			// if this path isn't already stored in the array of paths
			if(!isThePathAlreadyVisited(allPaths, aP)) {
				
				newPath.addAll(aP);
				
				//store the path
				allPaths.add(newPath);
					
			}
		
			
		}else{
			
			if(!p2.equals(p1)) {
				
				//for all adjacents to the previous node (if that node isn't the
				//one we are looking for)
				
				for(People p3: G1.adjacentsToV(p2)) {
			
					if(!aP.contains(p3) || p3.equals(p1)) {
						
						aP.push(p3);
					
						backTrack(G1, p3, p1);
					}
				}
		
		}
		
	}
		aP.pop();
			
	}
	
	/**
	 * method to determine if the cycle has already been visited
	 * @param Al
	 * @param s
	 * @return
	 */
	public boolean isThePathAlreadyVisited(ArrayList<Stack<People>> Al, Stack<People> s) {
		boolean em = false;
		
		if(Al.isEmpty()) {
			em=false;
		}else{
			for(Stack<People> s1: Al) {
			
				if(s1.containsAll(s) && s1.size()==s.size()) {
				
					em=true;
			
				}
			}
		}
		
		return em;
	}
	
	
	
	
	
	
	
	
	
	
	//==========================================================
	//
	//
	//		MATRIX METHOD
	//
	//		This is the other method we tried. The problem with 
	//		this method is that we thought the clique friends 
	//		were going to be next to each other in the "people
	//		list, but this may not happen. So we don't use this
	//		method in the main program, but we let it here for 
	//		you if you want to see it.
	//	
	//=========================================================
	public void findCliques(Graph G, int[][] M1) {
		
		for(int z = 4; z<=M1.length; z++) {
			for(int i = 0; i<= M1.length-z; i++) {
				for(int j = 0; j<=M1.length-z; j++) {
					baliozkoa = false;

					ArrayList<People> LL= new ArrayList<People>();
					LL = createAM(G, z, M1, i, j);
					
					if (baliozkoa) {
						System.out.println(LL.toString());
						PArr.add(LL);
					}
				}
			}
		}
		System.out.println("");
		soutCliques();
		
		
	}
	
	//=======================================================
	
	private void soutCliques(){
		System.out.println("those are all cliques");
		System.out.println("");
		int c=0;
		for(ArrayList<People> M: PArr) {
			System.out.println("Clique " + c + " :");
			System.out.println("");
			
			for(People p1: M) {
				System.out.println(p1.getId());
			}
			
			System.out.println("");
			c++;
		}
	}
	
			
	private ArrayList<People> createAM(Graph G, int z, int[][]M, int i, int j){
		int[][] M2 = new int[z][z];
		ArrayList<People> AL= new ArrayList<People>();
		
		for(int i1 = 0; i1<z; i1++) {
			People p1 = G.getList().get(i+i1);
			AL.add(p1);
			for(int j1 = 0; j1<z; j1++) {
				M2[i1][j1] = M[i+i1][j+j1];
				
			}
		}
		if(probeMatrix(M2)) {
			baliozkoa = true;
			
			L1.add(M);
			
			System.out.println("");
		}
		return AL;
	}
	
	
	
	private boolean probeMatrix(int[][]M) {
		boolean bt=true;
		boolean b1=true;
		boolean b2=true;
		
		for(int i = 0; i<M.length; i++) {
			for (int j = 0; j <M.length; j++) {
				if(i==j) {
					
					if(M[i][j]!=0) {
						b1=false;
					}
					
				}
				if(i!=j){
					
					if(M[i][j]!=1) {
						b2=false;
					}
					
				}
			}
		}
		
		if(b1!=true || b2!=true) {
			bt=false;
		}

		if(bt) {
		System.out.println("true");
		}
		
		return bt;
		
	}
}