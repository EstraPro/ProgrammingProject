import java.util.ArrayList;

public class BreathFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private People[] edgeTo;
	private int[] distTo;
	
	
//=================================================================================
	
	//From a single source
	private void bfs(Graph G, People s) throws InterruptedException {
		
		Queue<People> q = new Queue<People>();
		
		for(int v=0; v<G.V(); v++) {
			distTo[v]= INFINITY;
		}
		
		distTo[G.getList().indexOf(s)] = 0;
		marked[G.getList().indexOf(s)] = true;
		q.enqueue(s);
		

		while(!q.isEmpty()) {
			People v = q.dequeue();
			for(People w: G.adjacentsToV(v)) {
				if(!marked[G.getList().indexOf(w)]) {
					edgeTo[G.getList().indexOf(w)]=v;
					distTo[G.getList().indexOf(w)]=distTo[G.getList().indexOf(v)] + 1;
					marked[distTo[G.getList().indexOf(w)]]= true;
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
		edgeTo = new People[G.V()];
	
		//make the BFS
		bfs(G, s);
		}
	
//=================================================================================

	//From multiple sources
			
	//is it really necessary?
	
//=================================================================================
	
	//is there a path between s and v?
	public boolean hasPathTo(Graph G, People v) {
		return marked[G.getList().indexOf(v)];
	}
	
	//length of the sortest path btween s and v
	public int distTo(Graph G, People v) {
		return distTo[G.getList().indexOf(v)];
	}

	//Shortest path to a person
	public ArrayList<People> pathTo(Graph G, People v){
		if(!hasPathTo(G, v)) return null;
		ArrayList<People> path = new ArrayList<People>();
		int x;
		for (x = G.getList().indexOf(v) ; distTo[x] !=0; x=G.getList().indexOf(edgeTo[x])) {
			path.add(G.getList().get(x));
		}
		path.add(G.getList().get(x));
		return path;
	}
	
}