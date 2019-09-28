
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest{
public static void MyGETRequest(URL get_url) 

{
	try {
	    String input = null;
	    HttpURLConnection http_request = (HttpURLConnection) get_url.openConnection();
	    http_request.setRequestMethod("GET");
	    int responseCode = http_request.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader inputBuffer = new BufferedReader(
	        new InputStreamReader(http_request.getInputStream()));
	        StringBuffer output = new StringBuffer();
	        while ((input = inputBuffer .readLine()) != null) {
	        	output.append(input);
	        } 
	        //close buffer
	        inputBuffer .close();
	        // print the result
	        System.out.println("JSON retrieved: " + output.toString());
	        //write into a json file
	        BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(new File("./dataRetrieved.json")));
			
	        bufferwriter.write(output.toString());
			
	        bufferwriter.flush();
			
	        bufferwriter.close();
	    } else {
	        System.out.println("Get method has failed");
	    }
    
	}catch (IOException e) {
	    e.printStackTrace();
	}
}



public static void main(String[] args) 
{
	
	try {
		//Getting from API
		URL urlForGetRequest = new URL("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=6819dd358568de5ce75a4d4c451b");
		GetRequest getquest=new GetRequest();
		getquest.MyGETRequest(urlForGetRequest);
		
		 Jsonparsing JsonOb =new Jsonparsing();
		
		 
		 JsonOb.Processing("dataRetrieved.json");
		
		 System.out.println( JsonOb.getListCountryInvited().get(3).getDate_Chosen());
		 JsonOb.writingJsonFile();
		 
		PostRequest postrequest =new PostRequest();
		URL post_url = new URL("https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=6819dd358568de5ce75a4d4c451b");


	
		
		postrequest.POSTRequestOperation(post_url,"./JsonOutput.json");
	   
	}catch (IOException e) {
	    e.printStackTrace();
	}


}
}