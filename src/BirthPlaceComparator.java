import java.util.Comparator;

public class BirthPlaceComparator implements Comparator<People>{
	 @Override
	    public int compare(People p1, People p2) {
	        return p1.getBirthPlace().compareTo(p2.getBirthPlace());
	    }
}
