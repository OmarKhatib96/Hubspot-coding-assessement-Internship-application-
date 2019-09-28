
//Import for reading the JSON file
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/*Imports to write in the JSON*/
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;



public class Jsonparsing{
	
   private  List<Partner> partnerList=new ArrayList<Partner>(); 
   private List<CountryInvited> listCountryInvited=new ArrayList<CountryInvited>();
   private List<String> listCountry=new ArrayList<String>();
   private JSONObject jsonObject;


   
   	public  List<Partner> getPartnerList() {
   		
   		return partnerList;
   	}
   	
   	

   	public  List<CountryInvited> getListCountryInvited() {
   		
   		return listCountryInvited;
   	}
   	
   	
   	
   	
   	
   
	public  void Processing(String filePath) {
		
		List<Map.Entry> ListObjects=new ArrayList<Map.Entry>();
        HashMap map = new HashMap(); 

		JSONParser parser =new JSONParser();
		
		try (Reader reader =new FileReader(filePath)){
			
			try {
				jsonObject = (JSONObject) parser.parse(reader);
				JSONArray  partners=(JSONArray) jsonObject.get("partners");
		           	            

		            Iterator<Object> iterator = partners.iterator();
		            while(iterator.hasNext()) {
		            	JSONObject jsonit= (JSONObject) iterator.next();
		            	String fn=(String) jsonit.get("firstName");
		                String ln=(String) jsonit.get("lastName");
		            	String country=(String) jsonit.get("country");
		            	String email=(String) jsonit.get("email");
		            	JSONArray dates=(JSONArray) jsonit.get("availableDates");
		            	partnerList.add(new Partner(fn,ln,email,country,dates));
		            	}
		            
		            this.getCountries();
		            this.fillDates();
		   		    this.gettingDatesWithMostOccurences();
		            this.settingRightDate();
		            
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
		
		private void getCountries() {
            
            for(int i=0;i<partnerList.size();i++) {//Get all Countries names wihout repetition
            	
            	if(listCountry.contains(partnerList.get(i).getCountry())==false)
            		listCountry.add(partnerList.get(i).getCountry());
            			
            	
            
            }
            
            
            System.out.println(listCountry);
            for(String country:listCountry)//Fill the List Countries 
            	listCountryInvited.add(new CountryInvited(country));
            
           
            
		}     


            
	
		private void fillDates() {

                    
           for(int i=0;i<partnerList.size();i++) {//Fill dates in a row for each partner
               partnerList.get(i).getDatesIR();
        	  
         }
           
		
           
           for (CountryInvited countryInvited:listCountryInvited) {
        	   for(Partner partner:partnerList) {
        		   if(partner.getCountry().equals(countryInvited.getName())) {

        			   countryInvited.AddDate(partner.getDatesInaRow());
        			   countryInvited.AddPartner(partner);

        			   
        		   }
        	   }
        	   
           }
           
		}
        
         
           
          private void gettingDatesWithMostOccurences() {
        	 int size=listCountryInvited.size();
           for (int country=0;country<size;country++) {
        	   
        	   
        	  
                   
        	   		for (int i=0;i<listCountryInvited.size();i=i+2) {
        	   			int occurence=Collections.frequency(listCountryInvited.get(country).getPartner_Dates(), listCountryInvited.get(country).getPartner_Dates().get(i));
                	 
                	   listCountryInvited.get(country).setNumberDate(i,occurence);
        	 }
                	   
    		     

                   }  

          }
           
          private void settingRightDate() {
        	  
        	for( int i=0;i<listCountryInvited.size();i++)
        	{
                listCountryInvited.get(i).SetDate_Chosen();

        	}
        		
        	  
          }
          
          
       public void writingJsonFile() {  

    	   
    	
     		JSONObject jowriting=new JSONObject();
     		JSONArray countrieslist = new JSONArray();
     		JSONArray finalJson=new JSONArray();
     		JSONObject finalObject=new JSONObject();

    	   
  		/*To write */
  		//Creating JSOnObject
  		//putting data to JSOnObject
  			System.out.println(listCountryInvited.size());
            for (int i=0;i<listCountryInvited.size();i++) {
            	jowriting=new JSONObject();

            	jowriting.put("attendeeCount", listCountryInvited.get(i).getPartnerList().size());
  		
            	//jowriting.put("lastName","Smith");
          		JSONArray ja=new JSONArray();
          		for(int j=0;j<listCountryInvited.get(i).getPartnerList().size();j++)
          			
          				ja.add(listCountryInvited.get(i).getPartnerList().get(j).getEmail());
          		
          		
          		jowriting.put("attendees",ja);
          		jowriting.put("name",listCountryInvited.get(i).getName());
          		jowriting.put("startDate",listCountryInvited.get(i).getDate_Chosen());
          		countrieslist.add(jowriting);
            }
            finalObject.put("countries",countrieslist);
  		
  		//wrting JSON to file:"JSONWriting.json"
  		PrintWriter pw;
  		try {
  			pw = new PrintWriter("JsonOutput.json");
  			pw.write(finalObject.toJSONString());
  			pw.flush();
  			pw.close();
  			
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		
       
  				
  	}  
          
          
          
          
          
       
}
				



