import java.util.Stack;

public class DepthFirstSearch {
	
	private boolean[] marked;
	private int[] edgeTo;
	@SuppressWarnings("unused")
	private final People s;

//=============================================================================	

	/**
	 * Constructor method
	 * 
	 * @param G
	 * @param s
	 */
	public DepthFirstSearch(Graph G, People s) {
		
		this.s = s;
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	/**
	 * DFS method
	 * 
	 * @param G
	 * @param s
	 */
	private void dfs(Graph G, People s) {
		
		int v = G.getList().indexOf(s);
		marked[v] = true;

		for (People p : G.adjacentsToV(s)) {
			
			int w = G.getList().indexOf(p);

			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, p);
			}
		}
	}

//============================================================================

	// returns true if a path between to people exists
	public boolean hasPathTo(Graph G, People s) {
		
		int v = G.getList().indexOf(s);
		return marked[v];
	}

	// returns a path between two people if there is any path
	public Stack<People> pathTo(Graph G, People s) {
		
		if (!hasPathTo(G, s)) return null;
		
		Stack<Integer> path = new Stack<Integer>();
		int v = G.getList().indexOf(s);

		for (int x = v; x != v; x = edgeTo[x]) {
			
			path.push(x);
		}
		path.push(v);
		Stack<People> Ppath = new Stack<People>();
		
		for (Integer i : path) {
			
			People pb = G.getList().get(i);
			Ppath.push(pb);
		}
		return Ppath;
	}
}