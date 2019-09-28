import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;

public class Partner {

	
	private String firstname;
	private String lastname;
	private String email;
	private String country;
	private JSONArray availableDates;
	private List<String> DatesInARow=new ArrayList<String>();
	
	Partner( String firstname,String lastname,String email,String country,JSONArray availableDates){
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
		this.country=country;
		this.availableDates=availableDates;			
	}
	
	
	public List<String> getDatesInaRow(){
		
		return this.DatesInARow;
	}
	
	
	public void getDatesIR() {
		 List<Date> dateArray=new ArrayList< Date>();
		 
		 //Converting dates strings into Dates array
			 List<String> dates=new ArrayList< String>();
			   DateTimeFormatter formatter=  DateTimeFormatter.ISO_LOCAL_DATE;

		      List<LocalDate> localDateList = new ArrayList<>();

		for(int dateIndex=0;dateIndex<availableDates.size();dateIndex++) {
			String date=(String) availableDates.get(dateIndex);
			

			dates.add(date);

		}
		for(int dateIndex=0;dateIndex<availableDates.size();dateIndex++) {//Parsing dates strings
	         String[] data = dates.get(dateIndex).split("-");
	         Month m = Month.of(Integer.parseInt(data[1]));
	         LocalDate localDate =
	          LocalDate.of(Integer.parseInt(data[0]),m,Integer.parseInt(data[2]));
	         localDateList.add(localDate);
	         Date date = java.sql.Date.valueOf(localDate);

			
		}

	      Collections.sort(localDateList);//Sorting dates
	      for (int i = 0; i < localDateList.size() - 1; i++) {
	          LocalDate date1 = localDateList.get(i);
	          LocalDate date2 = localDateList.get(i + 1);
	          if (date1.plusDays(1).equals(date2)) {
	        	  String Date1=date1.format(formatter);
	        	  String Date2=date2.format(formatter);
	        	  DatesInARow.add(Date1);
	        	  DatesInARow.add(Date2);

	          }
	       }


		    	
		}
		
		
		
//Dates processing here
			
		
		
	
	
	
	public String getFn(){
		return this.firstname;
	}

	public String getLn(){
		return this.lastname;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public JSONArray getAvailableDates(){
		return this.availableDates;
	}

	
}
