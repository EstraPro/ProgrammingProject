import java.util.ArrayList;


public class Graph {
    private int V;
    private int E;
    private ArrayList<People>[] adj;  
    private ArrayList<People> List;
    
    

    public ArrayList<People> getList() {
		return List;
	}


	public void setList(ArrayList<People> list) {
		List = list;
	}


	/**
     * method constructor
     * @param list
     */
    public Graph(ArrayList<People> list) {
		super();
		List = list;
		V = list.size();
		E = 0;
		adj = (ArrayList<People>[])new ArrayList[V];
		for (int i = 0; i < List.size(); i++) {
			adj[i] = new ArrayList<People>();
			
		}
		
	}


   /**
     * Return the number of vertices in the graph.
     */
    public int V() { return V; }

    
   /**
     * Return the number of edges in the graph.
     */
    public int E() { return E; }


  
    
   /**
     * Add the undirected edge v-w to graph.
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(People v, People w) {
        int a=0,b=0;
        
        
    	a=List.indexOf(v);
    	b=List.indexOf(w);
    	
    	if(adj[a].contains(w)==false && adj[b].contains(v)==false) {
        
    		adj[a].add(w);
    		adj[b].add(v);
    
    		E++;
        }
    }

    /**
     * method addAPerson to the List 
     * @param p1
     */
    public void addAPerson(People p1) {
    	V++;
    	List.add(p1);
    	adj[List.indexOf(p1)] = new ArrayList<People>();
    }

    /**
     * method to return every friend of a people "v"
     * @param v
     * @return
     */
    public ArrayList<People> adjacentsToV(People v) {
    	int a = List.indexOf(v);
    	return adj[a];
    }


}