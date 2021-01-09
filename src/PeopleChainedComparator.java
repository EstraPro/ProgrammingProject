import java.util.Comparator;
import java.util.Arrays;
import java.util.List;

public class PeopleChainedComparator implements Comparator<People> {

	 private List<Comparator<People>> Complist;
	 
	    @SafeVarargs
	    public PeopleChainedComparator(Comparator<People>... Comps) {
	        this.Complist = Arrays.asList(Comps);
	    }
	 
	    @Override
	    public int compare(People p1, People p2) {
	        for (Comparator<People> Comp1 : Complist) {
	            int result = Comp1.compare(p1, p2);
	            if (result != 0) {
	                return result;
	            }
	        }
	        return 0;
	    }
} 
