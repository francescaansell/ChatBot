package chatbot.state;

import java.util.Hashtable;
import java.util.List;

public class HealthcareState extends AbstractDialogueState {
	
	public HealthcareState() {
		super();
	}

	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory) {
		
		
		if(slotHistory.size()>0) {
			String latestLocation = null;
			for(int i=0;i<slotHistory.size();i++) {
				int nowIndex = slotHistory.size()-1-i;
				if(slotHistory.get(nowIndex)!=null&&slotHistory.get(nowIndex).get("location")!=null) {
					latestLocation = slotHistory.get(nowIndex).get("location");			
					break;
				}
			}
			if(latestLocation!=null) {
				return "WeatherState HAS LOCATION, RESPOND";
			}else {
				return "WeatherState ASK LOCATION (HISTORY)";
			}
		}else {//no history
			return "WeatherState ASK LOCATION (NO HISTORY)";
		}
		
		
		/*
		if(super.hasSlotValue("location", slotHistory)) {
			return "WeatherState HAS LOCATION, RESPOND";
		}else {
			return "WeatherState ASK LOCATION";
		}
		*/
	
		
	}

}