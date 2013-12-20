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

		/* here the code for retrieving the triples from file01 and send the prefix of the dewey_pid as key */
		if(value.toString().contains("P_KK")){
			String[] split = value.toString().split(" ");
			//Retrieve the prefix for the specific reaction
			String prefix = split[0].substring(0, split[0].length()-4);
			TextPair dewey_id = new TextPair(prefix, split[2]);
			System.out.println(dewey_id.toString());
			//send to reducer with reaction key as dewey_id and identifier for this mapper
			context.write(dewey_id, new Text("Mapper2"));
		}
	}
}

// ^^ JoinMapper2
