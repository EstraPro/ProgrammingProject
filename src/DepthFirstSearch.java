import java.util.ArrayList;

public class DepthFirstSearch {
	private boolean[] marked;
	private People[] edgeTo;
	@SuppressWarnings("unused")
	private final People s;
	
//=============================================================================	

	/**
	 * Constructor method
	 * @param G
	 * @param s
	 */
	public DepthFirstSearch(Graph G, People s) {
		this.s= s;
		edgeTo= new People[G.V()];
		marked= new boolean[G.V()];
		dfs(G, s);
	}
	
	/**
	 * DFS method
	 * @param G
	 * @param s
	 */
	private void dfs(Graph G, People s) {
		int v = G.getList().indexOf(s);
		marked[v] =true;
		
		for(People p: G.adjacentsToV(s)) {
			int w= G.getList().indexOf(p);
			
			if(!marked[w]) {
				edgeTo[w]=s;
				dfs(G, p);
	
			}

		}
	}
	
//============================================================================
	
	//returns true if a path between to people exists
	public boolean hasPathTo(Graph G, People s) {
		int v = G.getList().indexOf(s);
		return marked[v];
	}
	
	//returns a path between two people if there is any path
	public ArrayList<People> pathTo(Graph G, People s){
		if(!hasPathTo(G, s)) return null;
		ArrayList<People> path = new ArrayList<People>();
		int v = G.getList().indexOf(s);
		for(int x = v; x!= v; x = G.getList().indexOf(edgeTo[x])) {
			path.add(G.getList().get(x));
		}
		path.add(s);
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}