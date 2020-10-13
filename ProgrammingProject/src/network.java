
import java.util.ArrayList;

public class network {
	public ArrayList<people> s1 = new ArrayList<>();
	
	public void addPeople(String n1) {
		people p1 = new people(null,null,null,null,null,null,null,null,null,null,null);
		String atribute="";
		while(n1!=null) {
			for (int i=0; i<n1.length();i++) {
				atribute= atribute+ n1.substring(i);
				System.out.println(atribute);
				if (n1.charAt(i)!=',') {
					
				}
			}
			
		}
	}
	
}