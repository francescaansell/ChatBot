package chatbot.state;

import java.util.Hashtable;
import java.util.List;

public class AppointmentState extends AbstractDialogueState {
	
	public AppointmentState() {
		super();
	}

	/**
	 * slotHistory lists that stores every slot value that has been extracted from user input
	 */
	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory, List<Hashtable<String, String>> slotHistory) {
		if(slotHistory.size()>0) {
			String latestLocation = null;
			String latestDate = null;
			//Iterate over end to get most recent slot history
			for(int i=0;i<slotHistory.size();i++) {
				int nowIndex = slotHistory.size()-1-i;
				//This may produce an error if there is both and one before other etc. 
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
				intentHistory.clear();
				return "Okay, Let me schedule you an appointment on " + latestDate + " in " + latestLocation + ".";	
			}else if (latestLocation != null && latestDate == null){
				return "Okay, I know you are in " + latestLocation + ", but what when should I schedule your appointment?";
			}else if (latestLocation == null && latestDate != null){
				return "Okay, I know you want to schedule an appointment on " + latestDate + ", but what city are you are in?";
			}else if (latestLocation == null && latestDate == null){
				return "Okay, when do you want to schedule an appoitnment, and what city are you in?";
			} else {
				return "I am not sure.";
			}
		}else {//no history slotHistory is 0
			return "Okay, when do you want to schedule an appoitnment, and what city are you in?";
		}	
	}
}