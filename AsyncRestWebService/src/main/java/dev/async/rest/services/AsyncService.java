package dev.async.rest.services;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ChunkedOutput;
import org.springframework.stereotype.Component;

@Component
@Path("/chunks")
@Produces({MediaType.APPLICATION_JSON})
public class AsyncService {
    @GET
    @Path("/string")
    public ChunkedOutput<String> getChunkedResponse() {
        final ChunkedOutput<String> output = new ChunkedOutput<String>(String.class);
 
        new Thread() {
            public void run() {
                try {
                    String chunk;
                    int index = 0;
 
                    while ((chunk = getWordAtIndex(index)) != null) {
                        output.write(chunk);
                        index++;
                    }
                } catch (IOException e) {
                    //Add code to handle the IO Exception during this operation
                } finally {
                    try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        }.start();
 
        return output; // This output object may be returned way before output is created
    }
    
    private String getWordAtIndex(int index) {
    	String someRandomSentence = "Each words in this sentence will be sent to the server "
    							  + "seperately with the delay of one second to mimic the "
    							  + "external operation taking long time";
    	
    	if (someRandomSentence.split(" ").length <= index) {
    		return null;
    	}
    	
    	try {
    		Thread.currentThread().sleep(500);//Sleep to imitate the delay caused by external operations
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	return someRandomSentence.split(" ")[index]+"\n";
    }
}