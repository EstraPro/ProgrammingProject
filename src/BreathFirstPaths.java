import java.util.Stack;

public class BreathFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	
	
//=================================================================================
	
	//From a single source
	private void bfs(Graph G, People s) throws InterruptedException {
		
		Queue<People> q = new Queue<People>();
		
		for(int v=0; v<G.V(); v++) {
			distTo[v]= INFINITY;
		}
		
		int S = G.getList().indexOf(s);
		
		distTo[S] = 0;
		marked[S] = true;
		q.enqueue(s);
		

		while(!q.isEmpty()) {
			People v = q.dequeue();
			int V = G.getList().indexOf(v);
			
			for(People w: G.adjacentsToV(v)) {
				int W = G.getList().indexOf(w);
				
				if(!marked[W]) {
					edgeTo[W]=V;
					distTo[W]=distTo[V] + 1;
					marked[W]= true;
					q.enqueue(w);
				}
			}
		}
	}

	/*
	 * constructor of the BFS object.
	 * When we create a BFS object, we are passing a person as a parameter(s). So when we
	 * use methods like distTo, hasPathTo... that also use a person as an in parameter(v)
	 * we are looking for the information between those two persons (s and v)
	 */
	public BreathFirstPaths(Graph G, People s) throws InterruptedException {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
	
		//make the BFS
		bfs(G, s);
		}
	
//=================================================================================

	//From multiple sources
			
	//is it really necessary?
	
//=================================================================================
	
	//is there a path between s and v?
	public boolean hasPathTo(Graph G, People v) {
		int V = G.getList().indexOf(v);
		return marked[V];
	}
	
	//length of the shortest path between s and v
	public int distTo(Graph G, People v) {
		int V = G.getList().indexOf(v);
		return distTo[V];
	}

	//Shortest path to a person
	public Stack<People> pathTo(Graph G, People v){
		if(!hasPathTo(G, v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		int V = G.getList().indexOf(v);
		int x;
		
		for (x= V ; distTo[x] !=0; x=edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		
		Stack<People> Ppath = new Stack<People>();
		for (Integer i : path) {
			People pb = G.getList().get(i);
			Ppath.push(pb);
		}
		return Ppath;
	}
	
}