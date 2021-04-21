package chatbot.state;

import java.util.Hashtable;
import java.util.List;

public class EndState extends AbstractDialogueState {
	
	public EndState() {
		super();
	}

	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory, List<Hashtable<String, String>> slotHistory) {
		return "Thanks for using healthbot!";
	}

}
