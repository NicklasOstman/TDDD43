import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentProcess;
import org.semanticweb.owl.align.Cell;

import fr.inrialpes.exmo.align.impl.method.StringDistAlignment;
import fr.inrialpes.exmo.align.impl.method.SubsDistNameAlignment;


public class S_SubstringMatcherImpl implements Iterable<Object[]>{

	Set<Object[]> result;
	
	public S_SubstringMatcherImpl(){}
	
	public static int matchStrings(String s1,String s2){
		
		ArrayList<String> set1 = new ArrayList<String>();
		ArrayList<String> set2 = new ArrayList<String>();
		if(s1.equals(s2)){
			return 0;
		}
		else{
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
		int totalCount = 0;
		
		for (Iterator iterator = set2.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			
			System.out.println(string);
			System.out.println(set1.contains(string));

			if (set2.contains(string)){
				totalCount = totalCount + string.length();
			}
			
			
		}
		for (int i = 0; i < set1.size();i++){
			System.out.println(set1.get(i));
		}
		
		System.out.println("X-----X");
		
		for (int i = 0; i < set2.size();i++){
			System.out.println(set2.get(i));
			
		}
		
		
		System.out.println("X-----X");
		
		System.out.println(totalCount);
		
		double count = (double) totalCount;
		double total = Math.abs(1 - (count / (((double)s1.length()+(double)s2.length()) * 2)));
		
		System.out.println(total);
		
		return 0;
		
	}

	public void match(URI u1, URI u2) {
		// TODO Auto-generated method stub
		result = new HashSet<Object[]>();
		
		AlignmentProcess a1 = new StringDistAlignment();
		try {
			a1.init(u1, u2);
			a1.align((Alignment) null,new Properties());
			
			for (Cell c : a1){
				Object[] r = new Object[4];
				r[0] = c.getObject1AsURI(a1);
				r[1] = c.getObject2AsURI(a1);
				r[2] = c.getRelation().toString();
				r[3] = new Double (c.getStrength());
				result.add(r);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public Iterator<Object[]> iterator() {
		// TODO Auto-generated method stub
		return result.iterator();
	}
	
//	public static void main(String [] args)
//	{
//	
//		int y = matchStrings("patrik","patrikk");
//	}
//	
	
	
	
	
	
	
}
