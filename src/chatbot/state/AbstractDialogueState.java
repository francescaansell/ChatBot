package chatbot.state;

import java.util.Hashtable;
import java.util.List;
public abstract class AbstractDialogueState {

	public abstract String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory);
	
	protected boolean hasSlotValue(String keyName, List<Hashtable<String, String>> slotHistory) {
		
		if(slotHistory.size()>0) {
			String latestLocation = null;
			for(int i=0;i<slotHistory.size();i++) {
				int nowIndex = slotHistory.size()-1-i;
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get(keyName)!=null) {
					latestLocation = slotHistory.get(nowIndex).get(keyName);			
					break;
				}
			}
			if(latestLocation!=null) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}	
	}
}
