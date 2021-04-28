package chatbot.components;

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
            case "FindPhsyicanState":
            	result = getFindPhysicanStateResponse(nowDialogueAct, slotHistory);
                break;
            case "AppointmentState":
            	result = getAppointmentStateResponse(nowDialogueAct, slotHistory);
                break;
			case "RefillState":
				result = getRefillStateResponse(nowDialogueAct, slotHistory);
				break;
            default:
                System.err.println("ResponseGenerator: You're not in any dialogue state!" + nowDialogueState);
                System.exit(1);
        }
		
		return result;
		
	}

	private static String getFindPhysicanStateResponse(String nowDialogueAct, List<Hashtable<String, String>> slotHistory) {
		if(nowDialogueAct.equals("RESPOND")) {
				
			String latestCity = getLatestSlotValue("RelativeCity", slotHistory);
			String latestSpecialty = getLatestSlotValue("RelativeSpecialty", slotHistory);
			if(latestCity!=null) {
				return "I found a " + latestSpecialty + " for you in " + latestCity; 
			}else {
				return "Something went wrong....:("; 
			}
			
		}else if (nowDialogueAct.equals("ASK CITY")){//ASK CITY
			
			List<String> sentencesAskLocation = new ArrayList<String>();
			sentencesAskLocation.add("What's your location?");
			sentencesAskLocation.add("Where are you?");
			sentencesAskLocation.add("Could you tell me the name of the city you're in?");
			sentencesAskLocation.add("Which city are you in?");
			
			Random rand = new Random();
	        return sentencesAskLocation.get(rand.nextInt(sentencesAskLocation.size()));
			
		} else if (nowDialogueAct.equals("ASK SPECIALTY")){//ASK DATE
			List<String> sentencesAskDate = new ArrayList<String>();
			sentencesAskDate.add("What type of doctor do you want to see?");
			sentencesAskDate.add("What specialty of doctor do you want to find?");
			sentencesAskDate.add("What specialty do you want the doctor am I looking for to be?");

			Random rand = new Random();
			return sentencesAskDate.get(rand.nextInt(sentencesAskDate.size()));

		} else if (nowDialogueAct.equals("ASK SPECIALTY AND CITY")){//ASK FOR BOTH
			List<String> sentencesAskDate = new ArrayList<String>();
			sentencesAskDate.add("To do that I will need the name of the city you want the doctor to be located in and their specialty.");
			sentencesAskDate.add("Can you tell me the city and specialty of the doctor you are looking for?");
			sentencesAskDate.add("What type of doctor do you want to see and what city should they be located in?");

			Random rand = new Random();
			return sentencesAskDate.get(rand.nextInt(sentencesAskDate.size()));
		} else {
			return "somthing went very wrong";
		}
	}

	private static String getRefillStateResponse(String nowDialogueAct, List<Hashtable<String, String>> slotHistory) {
		if(nowDialogueAct.equals("RESPOND")) {
				
			String latestCity = getLatestSlotValue("RelativeCity", slotHistory);
			String latestPrescription = getLatestSlotValue("RelativePrescription", slotHistory);
			if(latestCity!=null) {
				return "I sent a refill for " + latestPrescription + " in " + latestCity;  
			}else {
				return "Something went wrong....:("; 
			}
			
		}else if (nowDialogueAct.equals("ASK CITY")){//ASK CITY
			
			List<String> sentencesAskLocation = new ArrayList<String>();
			sentencesAskLocation.add("What's your location?");
			sentencesAskLocation.add("Where are you?");
			sentencesAskLocation.add("Could you tell me the name of the city you're in?");
			sentencesAskLocation.add("Which city are you in?");
			
			Random rand = new Random();
	        return sentencesAskLocation.get(rand.nextInt(sentencesAskLocation.size()));
			
		} else if (nowDialogueAct.equals("ASK PRESCRIPTION")){//ASK DATE
			List<String> sentencesAskDate = new ArrayList<String>();
			sentencesAskDate.add("What prescription do you need filled?");
			sentencesAskDate.add("What medication do you need to be refilled?");
			sentencesAskDate.add("What do you you need to be filled?");

			Random rand = new Random();
			return sentencesAskDate.get(rand.nextInt(sentencesAskDate.size()));

		} else if (nowDialogueAct.equals("ASK PRESCRIPTION AND CITY")){//ASK FOR BOTH
			List<String> sentencesAskDate = new ArrayList<String>();
			sentencesAskDate.add("I will need the name of the city and prescription to send that for you.");
			sentencesAskDate.add("Can you tell me the name of the medication you are taking and what city you would like it to be filled in?");
			sentencesAskDate.add("To do that I will need to know the name of the city and the name of the prescription. ");

			Random rand = new Random();
			return sentencesAskDate.get(rand.nextInt(sentencesAskDate.size()));
		} else {
			return "somthing went very wrong";
		}
	}

	private static String getAppointmentStateResponse(String nowDialogueAct, List<Hashtable<String, String>> slotHistory) {
		if(nowDialogueAct.equals("RESPOND")) {
				
			String latestCity = getLatestSlotValue("RelativeCity", slotHistory);
			String latestDate = getLatestSlotValue("RelativeDate", slotHistory);
			if(latestCity!=null) {
				return "Great! I made you an appointment on " + latestDate + " in " + latestCity; 
			}else {
				return "Something went wrong....:("; 
			}
			
			
		
		}else if (nowDialogueAct.equals("ASK CITY")){//ASK-LOCATION
			
			List<String> sentencesAskLocation = new ArrayList<String>();
			sentencesAskLocation.add("What's your location?");
			sentencesAskLocation.add("Where are you?");
			sentencesAskLocation.add("Could you tell me the name of the city you're in?");
			sentencesAskLocation.add("Which city are you in?");
			
			Random rand = new Random();
	        return sentencesAskLocation.get(rand.nextInt(sentencesAskLocation.size()));
			
		} else if (nowDialogueAct.equals("ASK DATE")){//ASK DATE
			List<String> sentencesAskDate = new ArrayList<String>();
			sentencesAskDate.add("What day should I make the appointment?");
			sentencesAskDate.add("When would you like the appointment to be?");
			sentencesAskDate.add("What day works best for you?");

			Random rand = new Random();
			return sentencesAskDate.get(rand.nextInt(sentencesAskDate.size()));

		} else if (nowDialogueAct.equals("ASK DATE AND CITY")){//ASK FOR BOTH
			List<String> sentencesAskDate = new ArrayList<String>();
			sentencesAskDate.add("What city and what date should I make your appointment for?");
			sentencesAskDate.add("Where and when should I make you appointment?");
			sentencesAskDate.add("When would you like to be seen and in what city?");

			Random rand = new Random();
			return sentencesAskDate.get(rand.nextInt(sentencesAskDate.size()));
		} else {
			return "somthing went very wrong";
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
		return "Hi! I can schedule an appointment, find you a doctor, or refill a prescription";
	}

}
