import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.AlignmentProcess;

import fr.inrialpes.exmo.align.impl.method.StringDistAlignment;
import fr.inrialpes.exmo.ontowrap.OntowrapException;


public class S_SubstringMatcher extends StringDistAlignment implements AlignmentProcess{

	public S_SubstringMatcher(){}
	
	@Override
	public void align(Alignment alignment, Properties param)
			throws AlignmentException {
		
		try {
			//Add alignment cells
			for(Object cl2: ontology2().getClasses()){
				for (Object cl1: ontology1().getClasses()){
					addAlignCell(cl1,cl2, "=", match(cl1,cl2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

	//Match each element based on substring matching
	private double match(Object cl1, Object cl2) throws AlignmentException {
		try {
			String s1 = ontology1().getEntityName(cl1);
			String s2 = ontology2().getEntityName(cl2);
					
			//SUBSTRING MATCHER
			ArrayList<String> set1 = new ArrayList<String>();
			ArrayList<String> set2 = new ArrayList<String>();
			if(s1.equals(s2)){
				return 0;
			}
			else{
				//Retrieve 1-,2- and 3-grams (string1)
				for(int i = 0;i<s1.length();i++){
					set1.add(String.valueOf(s1.charAt(i)));
					if (i > 1){
						set1.add(s1.substring(i-2,i));
					}
					if (i > 2){
						set1.add(s1.substring(i-3,i));
					}
					
					if(i == s1.length()-1){
						set1.add(s1.substring(i-1,i+1));
						set1.add(s1.substring(i-2,i+1));
						
					}
				}
				//Retrieve 1-,2- and 3-grams (string2)
				for(int i = 0;i<s2.length();i++){
					set2.add(String.valueOf(s2.charAt(i)));
					if (i > 1){
						set2.add(s2.substring(i-2,i));
					}
					if (i > 2){
						set2.add(s2.substring(i-3,i));
					}
					
					if(i == s2.length()-1){
						set2.add(s2.substring(i-1,i+1));
						set2.add(s2.substring(i-2,i+1));
					}
				}
				
			}
			//Calculate length
			int totalLength = 0;
			for (Iterator iterator = set1.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();

				if (set2.contains(string)){
					totalLength = totalLength + string.length();
				}
			}			
			double total = Math.abs(1.0 - ((double) totalLength / (((double)s1.length()+(double)s2.length()) * 2)));
			
			return total;
			//return 1.0;
			
		} catch (OntowrapException owex) {
			throw new AlignmentException("Error getting entity name", owex);
		}

	}

}
