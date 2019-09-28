import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.RenderingHints.Key;
import java.text.SimpleDateFormat;  

public class CountryInvited {
	
	
	private ArrayList<String> Partner_Dates= new ArrayList<String>() ;	
	private int number_parteners=0;
	private String countryName;
	private ArrayList<Partner> listOfPartners;
	//private ArrayList<Integer> number_of_each_date= new ArrayList<Integer>();
	private HashMap<Integer,Integer> number_of_each_date=new HashMap<Integer,Integer>();
	private  String Date_Chosen;
	//private final String
	
	
	
	CountryInvited(String Country){//Constructor 
	    
	    
		//Partner_Dates[(Partner_Dates.length)+1]=Date;
		this.countryName=Country;
		this.listOfPartners=new ArrayList<Partner>();
		
	}
	
	
	public  HashMap<Integer,Integer> getNumberOfEachDate() {
		
		
		return number_of_each_date;
	}
	
	public void setNumberDate(int index,int num) {
		
		this.number_of_each_date.put(index, num);
		
	}
	
	public void AddDate(List<String> list) {
	
		this.Partner_Dates.addAll(list);
	}
	
	
	
	public void AddPartner(Partner partner) {
		
			this.listOfPartners.add(partner);
	
	}
	
	public String getName() {
		return this.countryName;
	}
	
	public ArrayList<Partner> getPartnerList() {
		return this.listOfPartners;
	}
	
	
	public ArrayList<String> getPartner_Dates() {
		return this.Partner_Dates;
	}
	
	
	public void SetDate_Chosen()
	{
		
		Integer key = Collections.max(number_of_each_date.entrySet(), Map.Entry.comparingByValue()).getKey();
		Date_Chosen=Partner_Dates.get(key);
		//this.Date_Chosen.add(Partner_Dates.get(key));
		//this.Date_Chosen.add(Partner_Dates.get(key+1));

		
	}
	
	public String getDate_Chosen(){
		return this.Date_Chosen;
	}
	
	
	
	

}
