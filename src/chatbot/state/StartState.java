package chatbot.state;

import java.util.Hashtable;
import java.util.List;

public class StartState extends AbstractDialogueState {
	
	public StartState() {
		super();
	}

	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory) {
		// TODO Auto-generated method stub
		return "Hi! I can find food near you or tell you the weather. What do you want to know?";
	}

}
