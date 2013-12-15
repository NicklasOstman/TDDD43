// cc JoinMapper2 Mapper for a reduce-side join
package noSQL;


import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

// vv JoinMapper2
public class JoinMapper2
    extends Mapper<LongWritable, Text, TextPair, Text> {

        /* here define the variables */
	int round = 0;
  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	  round++;
	  System.out.println("MAPROUND2 " + round);
	  System.out.println(value);
	 
          
/* here the code for retrieving the triples from file01 and send the prefix of the dewey_pid as key */
	  if(value.toString().contains("P_KK")){
			 //send to reducer with key as dewey_id
		  }
    }
  }

// ^^ JoinMapper2
