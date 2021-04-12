package ist361.chatbot.state;

import java.util.Hashtable;
import java.util.List;

public abstract class AbstractDialogueState {
	
	//abstract public String executeAndGetResponse();

	public abstract String executeAndGetResponse(List<String> domainHistory, List<String> intentHistory,
			List<Hashtable<String, String>> slotHistory);

}
