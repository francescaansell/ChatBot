package ist361.chatbot.component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class ResponseGenerator {
	
	static public String getResponseInEnglish(String nowDialogueState, String nowDialogueAct, List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory) {
		
		String result = "This is the chatbot's default response. There might be something wrong if you see this.";
		
        switch(nowDialogueState){
            case "StartState":
            	result = getStartStateResponse();
                break;
            case "FoodState":
            	result = getFoodStateResponse(nowDialogueAct, slotHistory);
                break;
            case "WeatherState":
            	result = getWeatherStateResponse(nowDialogueAct, slotHistory);
                break;
            default:
                System.err.println("ResponseGenerator: You're not in any dialogue state!");
                System.exit(1);
        }
		
		return result;
		
	}

	private static String getWeatherStateResponse(String nowDialogueAct, List<Hashtable<String, String>> slotHistory) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getFoodStateResponse(String nowDialogueAct, List<Hashtable<String, String>> slotHistory) {
		if(nowDialogueAct.equals("RESPOND")) {
			
			/*
			String latestLocation = null;
			if(slotHistory.size()>0) {
				for(int i=0;i<slotHistory.size();i++) {
					int nowIndex = slotHistory.size()-1-i;
					if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get("location")!=null) {
						latestLocation = slotHistory.get(nowIndex).get("location");
						break;
					}
				}
			}
			*/
			
			String latestLocation = getLatestSlotValue("location", slotHistory);
			if(latestLocation!=null) {
				if(latestLocation.toUpperCase().equals("STATE COLLEGE")) {
					return "You can try The Field Burger and Tap.";
				}else if(latestLocation.toUpperCase().equals("SEATTLE")) {
					return "You can try Pike Place Chowder.";
				}else {
					return "Sorry. We didn't find any food in your location."; 
				}
			}else {
				return "Something went wrong....:("; 
			}
			
			
		
		}else {//ASK-LOCATION
			
			List<String> sentencesAskLocation = new ArrayList<String>();
			sentencesAskLocation.add("What's your location?");
			sentencesAskLocation.add("Where are you?");
			sentencesAskLocation.add("Could you tell me the name of the city you're in?");
			sentencesAskLocation.add("Which city are you in?");
			
			Random rand = new Random();
	        return sentencesAskLocation.get(rand.nextInt(sentencesAskLocation.size()));
			
		}
	}
	
	private static String getLatestSlotValue(String keyName, List<Hashtable<String, String>> slotHistory) {
		
		if(slotHistory.size()>0) {
			for(int i=0;i<slotHistory.size();i++) {
				int nowIndex = slotHistory.size()-1-i;
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get(keyName)!=null) {
					return slotHistory.get(nowIndex).get(keyName);			
				}
			}
		}
		
		return null;
		
	}

	private static String getStartStateResponse() {
		//return "Hello! I can find food near you or tell you the weather. What do you want to know?";
		return "How can I help you?";
	}

}
