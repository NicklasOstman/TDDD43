// cc JoinReducer Reducer for join

package noSQL;


import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// vv JoinReducer
public class JoinReducer extends Reducer<TextPair, Text, Text, Text> {

	@Override
	protected void reduce(TextPair key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		Iterator iter = context.getValues().iterator();
		while(iter.hasNext()){
			iter.next();
			System.out.println("From mapper: "+ context.getCurrentValue() + " TextPair: " + context.getCurrentKey());
			context.write(new Text(context.getCurrentKey().toString()), context.getCurrentValue());
		}
		/* here comes the reducer code */
		
		
	}
}

// ^^ JoinReducer
