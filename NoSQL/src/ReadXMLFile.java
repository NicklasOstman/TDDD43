
import org.xml.sax.*;
import org.xml.sax.helpers.*; 


public class ReadXMLFile {
 
   public static void main(String argv[]) {
 
    try {
 
        XMLReader parser;
        try {
         parser = XMLReaderFactory.createXMLReader( );
        }
        catch (SAXException e) {
          // fall back on Xerces parser by name
          try {
            parser = XMLReaderFactory.createXMLReader(
             "org.apache.xerces.parsers.SAXParser");
          }
          catch (SAXException ee) {
            System.err.println("Couldn't locate a SAX parser");
            return;
          }
        }

	   parser.setContentHandler(new XMLCounter( ));
	   long time = System.currentTimeMillis();

       parser.parse("BIOMD0000000009.xml");
       System.out.println("Time spended: " + (System.currentTimeMillis()-time));
 
     } catch (Exception e) {
       e.printStackTrace();
     }
 
   }
 
}
