package ist361.chatbot.component;

import java.util.ArrayList;
import java.util.Hashtable;

public class SlotFiller {

	/*
	 * Task 1: Extract slot values from yours message
	 * 
	 * [Input] One user message (e.g., "What's the weather in State College?")
	 * 
	 * [Output] A hash table that contains a set of (key, value) tuples, where
	 * "key" is the name of the slot (e.g., "location") and "value" is the
	 * extracted value (e.g., "State College").
	 * 
	 */
	public Hashtable<String, String> extractSlotValues(String nowInputText) {
		
		//initialize result hash table. You do not need to change this part of code.
		Hashtable<String, String> result = new Hashtable<String, String>();
		
		//modify the following code to implement your own slot extractor
		String[] dayOfWeekList = new String[] {"FRIDAY", "MONDAY", "SATURDAY", "SUNDAY", "THURSDAY", "TUESDAY", "WEDNESDAY"};
		for(String nowDayOfWeek: dayOfWeekList) {
			if(nowInputText.toUpperCase().contains(nowDayOfWeek)) {
				//adding value to the result hash table
				result.put("DayOfWeek", nowDayOfWeek);
			}
		}
		
		
		//modify the following code to implement your own slot extractor
		String[] relativeCloseDays = new String[] {"TODAY", "TOMORROW", "YESTERDAY"};
		for(String nowRelativeDate: relativeCloseDays) {
			if(nowInputText.toUpperCase().contains(nowRelativeDate)) {
				//adding value to the result hash table
				result.put("RelativeCloseDay", nowRelativeDate);
			}
		}
	

		//modify the following code to implement your own slot extractor
		String[] relativeCitiesList = new String[] {"State College", "Pittsburgh", "Harrisburg", "Erie", "Butler", "Greensburg", "Lancaster", 
			"New Castle", "Scranton", "York", "Uniontown"};
		for(String nowRelativeLocation: relativeCitiesList) {
			if(nowInputText.toUpperCase().contains(nowRelativeLocation.toUpperCase())) {
				//adding value to the result hash table
				result.put("RelativeCity", nowRelativeLocation);
			}
		}

		
		ArrayList<String> relativeDatesList = generateDates();
		/*
		for(String date: relativeDatesList){
			System.out.println("DATE: " + date);
		}
		*/
		for(String nowRelativeDate: relativeDatesList) {
			if(nowInputText.toUpperCase().contains(nowRelativeDate)) {
				//adding value to the result hash table
				result.put("RelativeDate", nowRelativeDate);
			}
		}

		//I used the top 50 prescription drugs in the US 
		//https://www.healthgrades.com/right-care/patient-advocate/the-top-50-drugs-prescribed-in-the-united-states
		//I also decided to use common names
		String[] relativePrescriptionList = new String[] {"Levothyroxine", "Lisinopril", "Atorvastatin", "Metformin", "Amlodipine", "Metoprolol", 
			"Omeprazole", "Simvastatin", "Losartan", "Albuterol", "Gabapentin", "Hydrochlorothiazide", "Hydrocodone", "Zoloft", "Furosemide", "Celexa", 
			"Xanax", "Acetaminophen", "Insulin glargine", "Trazodone", "Montelukast", "Protonix", "Lexapro", "Pravachol", "Bupropion", "Prozac", "Carvedilol", 
			"Prednisone", "Flomax", "Potassium", "Clopidogrel", "Klonopin", "Zolpidem", "Tramadol", "Rosuvastatin", "Meloxicam", "Propranolol", "Glipizide", "Zantac", 
			"Zithromax", "Cymbalta" , "Methylphenidate"};
		for(String nowRelativePrescription: relativePrescriptionList) {
			if(nowInputText.toUpperCase().contains(nowRelativePrescription)) {
				//adding value to the result hash table
				result.put("RelativePrescription", nowRelativePrescription);
			}
		}
		
		//return the result hash table. You do not need to change this part of code.
		return result;
		
	}

	public ArrayList<String> generateDates() {
		ArrayList<String> dates = new ArrayList<>();
	
		//A date is month + "/" + day + "/" + year
		for(int tempYear= 1960; tempYear <= 2021; tempYear ++) {
			for(int tempMonth=1; tempMonth<=12; tempMonth++){
				if(tempMonth == 2){
					for(int tempDay=1; tempDay<=28; tempDay ++){
						String tempDate = tempMonth + "/" + tempDay + "/" + tempYear;
						dates.add(tempDate);
					}
				} else if(tempMonth % 2 != 0 ){
					//if month is odd it has 31 days
					for(int tempDay=1; tempDay<= 31; tempDay++){
						String tempDate = tempMonth + "/" + tempDay + "/" + tempYear;
						dates.add(tempDate);
					}
				} else if (tempMonth % 2 ==0){
					for(int tempDay = 1; tempDay<=30; tempDay++){
						String tempDate = tempMonth + "/" + tempDay + "/" + tempYear;
						dates.add(tempDate);
					}
				}
				/*
				for(String date: dates){
					System.out.println("DATE: " + date);
				}
				*/
			}
	
		}
		return dates;
	}

}
