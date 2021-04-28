package chatbot.state;

import java.util.Hashtable;
import java.util.List;
import chatbot.components.ResponseGenerator;

public class StartState extends AbstractDialogueState {
	
	
	public StartState() {
		super();
		
	}

	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory) {
				return ResponseGenerator.getResponseInEnglish("StartState", null, null, null, null);
	}

}
