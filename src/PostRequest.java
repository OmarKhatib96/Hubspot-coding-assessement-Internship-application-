import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.omg.CORBA.portable.OutputStream;

public class PostRequest {



private static String  JsonReader(String jsonFilePath) {//from stack overflow: to convert json data into a string
	try {
	Reader reader =new FileReader(jsonFilePath);

	BufferedReader in = new BufferedReader(reader);
	String line = null;
	StringBuilder rslt = new StringBuilder();
	while ((line = in.readLine()) != null) {
	    rslt.append(line);
	}
	
	String result=rslt.toString();

	return result;


	}catch (IOException e) {
	    e.printStackTrace();
	    return "Json reading failed!";
	}

}

public static void POSTRequestOperation(URL obj,String jsonFilePath) 
{
   try {
	String body=  JsonReader( jsonFilePath);
	
    System.out.println(body);
    HttpURLConnection RequestHttp = (HttpURLConnection) obj.openConnection();
    RequestHttp.setRequestMethod("POST");
    RequestHttp.setRequestProperty("Content-Type", "application/json");
    RequestHttp.setDoOutput(true);
    java.io.OutputStream output_stream =  RequestHttp.getOutputStream();
    output_stream.write(body.getBytes());
    output_stream.flush();
    output_stream.close();
    int Code = RequestHttp.getResponseCode();
    System.out.println("POST  Code :  " + Code);
    System.out.println("POST  Message : " + RequestHttp.getResponseMessage());
    if (Code == HttpURLConnection.HTTP_CREATED) { //success
        BufferedReader input_buffer = new BufferedReader(new InputStreamReader(
        		RequestHttp.getInputStream()));//To retireve what we've written
        String input;
        StringBuffer output = new StringBuffer();
        while ((input = input_buffer .readLine()) != null) {
        	output.append(input);
        } input_buffer .close();
        // print result
        System.out.println(output.toString());
    } else {
        System.out.println("Post method has failed! ");
    }
}catch (IOException e) {
    e.printStackTrace();
}
}

}


