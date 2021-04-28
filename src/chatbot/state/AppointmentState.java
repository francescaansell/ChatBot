package chatbot.state;

import java.util.Hashtable;
import java.util.List;

import chatbot.components.ResponseGenerator;

public class AppointmentState extends AbstractDialogueState {
	
	public AppointmentState() {
		super();
	}

	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory, List<Hashtable<String, String>> slotHistory) {
		if(slotHistory.size()>0) {
			String latestLocation = null;
			String latestDate = null;
			//Iterate over end to get most recent slot history
			for(int i=0;i<slotHistory.size();i++) {
				int nowIndex = slotHistory.size()-1-i;
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get("RelativeCity")!=null) {
					latestLocation = slotHistory.get(nowIndex).get("RelativeCity");			
					break;
				}
			}
			for(int i=0;i<slotHistory.size();i++){
				int nowIndex = slotHistory.size()-1-i;
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get("RelativeDate")!=null){
					latestDate = slotHistory.get(nowIndex).get("RelativeDate");
					break;
				}
			}

		if(latestLocation!=null && latestDate != null) {
			return ResponseGenerator.getResponseInEnglish("AppointmentState", "RESPOND", domainHistory, intentHistory, slotHistory);	
		}else if (latestLocation != null && latestDate == null){
			return ResponseGenerator.getResponseInEnglish("AppointmentState", "ASK DATE", domainHistory, intentHistory, slotHistory);	
		}else if (latestLocation == null && latestDate != null){
			return ResponseGenerator.getResponseInEnglish("AppointmentState", "ASK CITY", domainHistory, intentHistory, slotHistory);	
		}else if (latestLocation == null && latestDate == null){
			return ResponseGenerator.getResponseInEnglish("AppointmentState", "ASK DATE AND CITY", domainHistory, intentHistory, slotHistory);	
		} else {
			return "I am not sure.";
		}
	}else {//no history slotHistory is 0
		return "Okay, when do you want to schedule an appoitnment, and what city are you in?";
		



			
	}
}
}