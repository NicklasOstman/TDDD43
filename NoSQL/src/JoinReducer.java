// cc JoinReducer Reducer for join

package noSQL;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// vv JoinReducer
public class JoinReducer extends Reducer<TextPair, Text, Text, Text> {

	@Override
	protected void reduce(TextPair key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		//Output will be grouped based on key value by default
		Iterator iter = context.getValues().iterator();

		//Keep track of keys from mapper2
		ArrayList<String> temp = new ArrayList<String>();
		//Boolean to keep track if some <key,value> pair have been sent by mapper1
		boolean keyFromMapper1 = false;

		//Iterate through all <key,value> pairs sent to this reducer
		while(iter.hasNext()){
			iter.next();
			System.out.println("From mapper: "+ context.getCurrentValue() + 
					" TextPair: " + context.getCurrentKey());
			//If a <key, value> pair is from mapper1, set boolean to true. So if we get <key,value> pairs 
			//from mapper 2 with the same key, these values will be written to output.
			if(context.getCurrentValue().toString().equals("Mapper1")){
				keyFromMapper1 = true;
			}
			//If a <key,value> pair is from mapper2, save the it to our temp arraylist
			else if(context.getCurrentValue().toString().equals("Mapper2")){
				temp.add(context.getCurrentKey().toString());
			}
		}
		//If keyFromMapper1 is true, print all collected values from mapper2 that exists 
		//in our arraylist temp. (Meaning that if this temp arraylist is empty, no texts have been 
		//sent by mapper2 and thus nothing will be added to output).
		if (keyFromMapper1){
			for(int i = 0;i<temp.size();i++){
				context.write(new Text(temp.get(i)), new Text(" "));
			}
		}
	}
}

// ^^ JoinReducer
