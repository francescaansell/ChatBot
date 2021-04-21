package chatbot.state;

import java.util.Hashtable;
import java.util.List;

public class RefillState extends AbstractDialogueState {
	
	public RefillState() {
		super();
	}

	/**
	 * slotHistory lists that stores every slot value that has been extracted from user input
	 */
	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory, List<Hashtable<String, String>> slotHistory) {
	
		if(slotHistory.size()>0) {
			String latestLocation = null;
			String latestRefill = null;
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
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get("RelativePrescription")!=null){
					latestRefill = slotHistory.get(nowIndex).get("RelativePrescription");
					break;
				}
			}

		

			if(latestLocation!=null && latestRefill != null) {
				intentHistory.clear();
				return "Okay, Let me refill " + latestRefill + " in " + latestLocation + ".";
			}else if (latestLocation != null && latestRefill == null){
				return "Okay, I know you are in " + latestLocation + ", but what type of medication do you want me to refill?";
			}else if (latestLocation == null && latestRefill != null){
				return "Okay, I know you want to refill " + latestRefill + ", but what city are you are in?";
			}else if (latestLocation == null && latestRefill == null){
				return "Okay, what type of medication do you want to refill, and what city are you in?";
			} else {
				return "I am not sure.";
			}
		}else {//no history slotHistory is 0
			return "Okay, what type of medication do you want to refill, and what city are you in?";
		}	
	}
}