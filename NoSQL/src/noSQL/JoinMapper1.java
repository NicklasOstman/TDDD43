// cc JoinMapper1 Mapper for a reduce-side join

package noSQL;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//vv JoinMapper1
public class JoinMapper1
    extends Mapper<LongWritable, Text, TextPair, Text> {

/* here define the variables */
	int round = 0;
        
  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
  
/* here the code for retrieving the triples from file01 and send the prefix of the dewey_pid as key */
	  round++;
	  System.out.println("MAPROUND1 " + round);
	  System.out.println(value);
	  //System.out.println(readFile("/Users/patrikbjurling/Documents/TDDD43/NoSQL/test/sbml | model | " +
	  		//"listOfReactions | reaction | listOfProducts | speciesReference_attributes.txt"));
	  if(value.toString().contains("P_KK")){
		  String[] split = value.toString().split(" ");
		  TextPair dewey_key = new TextPair(split[0], split[2]);
		  
		  
		//send to reducer with key as dewey_id
	  }
	  
          
          
      }
  
  public String readFile(String filename)
  {
     String content = null;
     File file = new File(filename); //for ex foo.txt
     try {
         FileReader reader = new FileReader(file);
         char[] chars = new char[(int) file.length()];
         reader.read(chars);
         content = new String(chars);
         reader.close();
     } catch (IOException e) {
         e.printStackTrace();
     }
     return content;
  }
    }

//^^ JoinMapper1
