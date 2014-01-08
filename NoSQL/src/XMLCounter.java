

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.xml.sax.*;
public class XMLCounter implements ContentHandler {

	/* here comes var declaration */
	private ArrayList<Integer> dewey_id = new ArrayList<Integer>();
	private ArrayList<String> currentPath = new ArrayList<String>();
	private int currentLevel = 0;
	//private File f = new File("/Users/patrikbjurling/Documents/TDDD43/NoSQL/File01.txt");
	private BufferedWriter tagOutput=null;
	private BufferedWriter textOutput=null;
	private BufferedWriter attributeOutput=null;


	public void startDocument( ) throws SAXException {

	}

	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {
		/* here comes code after the startElement is met */
		/* for attributes too */
		
		//Handle the counting of dewey_id
		if(dewey_id.isEmpty()){
			dewey_id.add(1);
			currentPath.add(qName);
			currentLevel++;
		}
		else if(dewey_id.size() <= currentLevel){
			dewey_id.add(1);
			currentPath.add(qName);
			currentLevel++;
		}
		else if(dewey_id.size() > currentLevel){
			dewey_id.set(currentLevel, dewey_id.get(currentLevel)+1);
			currentPath.set(currentLevel, qName);
			currentLevel++;
		}
		String id = dewey_idToString(dewey_id);
		String path = currentPathToString(currentPath);
		System.out.println(id + " " + path);
		System.out.println("Start Element: " + qName);
		System.out.println("URI: " + uri + " Localname: " + localName);
		
		//Create the files
		File f = new File("/Users/patrikbjurling/Documents/TDDD43/NoSQL/test/" + path + "_tag.txt");
		File f1 = new File("/Users/patrikbjurling/Documents/TDDD43/NoSQL/test/" + path + "_texts.txt");
		File f2 = new File("/Users/patrikbjurling/Documents/TDDD43/NoSQL/test/" + path + "_attributes.txt");
		try {
			tagOutput = new BufferedWriter(new FileWriter(f.getAbsoluteFile(),true));
			textOutput = new BufferedWriter(new FileWriter(f1.getAbsoluteFile(),true));	
			attributeOutput = new BufferedWriter(new FileWriter(f2.getAbsoluteFile(),true ));
			
			tagOutput.append(id + " " + qName + "\n");
			for (int i = 0; i< attributes.getLength();i++) {
				attributeOutput.append(id);
				attributeOutput.append(" " + attributes.getLocalName(i) + " " + attributes.getValue(i) + '\n');
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			tagOutput.close();
			textOutput.close();
			attributeOutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Helper function
	private String dewey_idToString(ArrayList<Integer> dewey_id){
		StringBuilder s = new StringBuilder();
		for (int i = 0; i<dewey_id.size();i++){
			s.append(dewey_id.get(i).toString());
			if(dewey_id.size()-1!=i){
				s.append('.');
			}
		}
		return s.toString();
	}
	//Helper function
	private String currentPathToString(ArrayList<String> curPatch){
		StringBuilder s = new StringBuilder();
		for (int i = 0; i<curPatch.size();i++){
			s.append(curPatch.get(i).toString());
			if(curPatch.size()-1!=i){
				s.append(" | ");
			}
		}
		return s.toString();
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		/* here comes code after the endElement is met */
		
		//Handle the counting of dewey_id
		if(dewey_id.size() > currentLevel){
			dewey_id.remove(dewey_id.size()-1);
			currentPath.remove(dewey_id.size()-1);
		}
		currentLevel--;
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		/* here comes code after the startElement is met */


	}


	public void endDocument( ) throws SAXException {
		System.out.println("End of docuement ");
	}

	// Do-nothing methods we have to implement only to fulfill
	// the interface requirements:

	public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {}

	public void processingInstruction(String target, String data) 	   
			throws SAXException {}
	public void setDocumentLocator(Locator locator) {}
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {}
	public void endPrefixMapping(String prefix) throws SAXException {}
	public void skippedEntity(String name) throws SAXException {}

}