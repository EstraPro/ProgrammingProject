import java.util.Comparator;
import java.util.Arrays;
import java.util.List;

public class PeopleChainedComparator implements Comparator<People> {

	 private List<Comparator<People>> listComparators;
	 
	    @SafeVarargs
	    public PeopleChainedComparator(Comparator<People>... comparators) {
	        this.listComparators = Arrays.asList(comparators);
	    }
	 
	    @Override
	    public int compare(People p1, People p2) {
	        for (Comparator<People> comparator : listComparators) {
	            int result = comparator.compare(p1, p2);
	            if (result != 0) {
	                return result;
	            }
	        }
	        return 0;
	    }
}

