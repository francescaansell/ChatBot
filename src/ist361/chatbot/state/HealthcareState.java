package ist361.chatbot.state;

import java.util.Hashtable;
import java.util.List;

public class HealthcareState extends AbstractDialogueState {
	
	public HealthcareState() {
		super();
	}

	@Override
	public String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory) {
		// TODO Auto-generated method stub
		return "TEST executeAndGetResponse HealthcareState";
	}

}
