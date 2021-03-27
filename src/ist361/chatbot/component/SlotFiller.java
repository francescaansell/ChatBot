package ist361.chatbot.component;

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
		String[] relativeDateList = new String[] {"TODAY", "TOMORROW", "YESTERDAY"};
		for(String nowRelativeDate: relativeDateList) {
			if(nowInputText.toUpperCase().contains(nowRelativeDate)) {
				//adding value to the result hash table
				result.put("RelativeDate", nowRelativeDate);
			}
		}
		
		//return the result hash table. You do not need to change this part of code.
		return result;
		
	}

}
