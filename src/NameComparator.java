import java.util.Comparator;

public class NameComparator implements Comparator<People>{
	 @Override
	    public int compare(People p1, People p2) {
	        return p1.getName().compareTo(p2.getName());
	    }
}
