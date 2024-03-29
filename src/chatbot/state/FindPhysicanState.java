package chatbot.state;

import java.util.Hashtable;
import java.util.List;

import chatbot.components.ResponseGenerator;

public class FindPhysicanState extends AbstractDialogueState {
	
	public FindPhysicanState() {
		super();
	}

	/**
	 * slotHistory lists that stores every slot value that has been extracted from user input
	 */
	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory, List<Hashtable<String, String>> slotHistory) {
		if(slotHistory.size()>0) {
			String latestLocation = null;
			String latestPhysican = null;
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
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get("RelativeSpecialty")!=null){
					latestPhysican = slotHistory.get(nowIndex).get("RelativeSpecialty");
					break;
				}
			}

			if(latestLocation!=null && latestPhysican != null) {
		
				return ResponseGenerator.getResponseInEnglish("FindPhsyicanState", "RESPOND", domainHistory, intentHistory, slotHistory);
			}else if (latestLocation != null && latestPhysican == null){
				return ResponseGenerator.getResponseInEnglish("FindPhsyicanState", "ASK SPECIALTY", domainHistory, intentHistory, slotHistory);
			}else if (latestLocation == null && latestPhysican != null){
				return ResponseGenerator.getResponseInEnglish("FindPhsyicanState", "ASK CITY", domainHistory, intentHistory, slotHistory);
			}else if (latestLocation == null && latestPhysican == null){
				return ResponseGenerator.getResponseInEnglish("FindPhsyicanState", "ASK SPECIALTY AND CITY", domainHistory, intentHistory, slotHistory);
			} else {
				return "I am not sure.";
			}
		}else {//no history slotHistory is 0
			return "Okay, what type of phsyican do you want to see and what city are you in?";
		}	
	}
}