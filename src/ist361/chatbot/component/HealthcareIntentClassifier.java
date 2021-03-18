package ist361.chatbot.component;

public class HealthcareIntentClassifier {
	
	private static String[] intentDictionary;
	
	public HealthcareIntentClassifier() {
		initializeIntentDictionary();
	}
	
	/*
	 * Initialize the intent dictionary
	 */
	private void initializeIntentDictionary() {
		intentDictionary = new String[]{"Appointment", "Refill","FindPhysican" };
        
		System.out.print("Intents: (");
		for(int i=0;i<intentDictionary.length;i++) {
			System.out.print(intentDictionary[i]);
			if(i!=intentDictionary.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println(")");
	}
	
	/*
	 * Calculate the score for each intent
	 * 
	 * [Input] One user message
	 * 
	 * [Output] A Double array that has the score of each intent. The scores
	 * can simply be 0.0 or 1.0, can be probabilities (from 0.0 to 1.0), or
	 * any scores you design. Later the getLabel() method will select the intent 
	 * with *highest* score. 
	 */
	private Double[] calculateIntentScores(String nowInputText) {
		Double[] scoreArray = new Double[intentDictionary.length];
        for(int i=0; i < scoreArray.length; i++){
            scoreArray[i] = new Double(0.0);
        }

		//Intent 1 appointment
        String[] appointmentDictionary  = new String[] {"appointment", "sceduale"};
		for(String appointmentKeyword: appointmentDictionary) {
			if (nowInputText.toLowerCase().indexOf(appointmentKeyword)>=0) {
				scoreArray[0] = new Double(scoreArray[0])+1;
			}
		}

		//Intent 2 refill
		String[] refillDictionary = new String[] {"refill", "prescription"};
		for(String refillKeyword: refillDictionary) {
			if (nowInputText.toLowerCase().indexOf(refillKeyword)>=0) {
				scoreArray[1] = new Double(scoreArray[1])+1;
			}
		}
		
		//Intent 3 findPhysican
        String[] findPhysicanDictionary = new String[] {"doctor"};
		for(String findPhyiscanKeyword: findPhysicanDictionary) {
			if (nowInputText.toLowerCase().indexOf(findPhyiscanKeyword)>=0) {
				scoreArray[2] = new Double(scoreArray[2])+1;
			}
		}

		if(scoreArray.length!=intentDictionary.length) {
			System.err.println("The score array size does not equal to the intent array size.");
			System.exit(1);
		}
		for(Double nowValue: scoreArray) {
			if(nowValue==null) {
				System.err.println("The score array contains null values.");
				System.exit(1);
			}
		}
		return scoreArray;
	}
	
	/*
	 * Input: One user message
	 * Output: The intent with the highest score calculated by calculateIntentScores()
	 */
	public String getLabel(String nowInputText) {
		Double[] intentScores = calculateIntentScores(nowInputText);
		Double nowMaxScore = null;
		int nowMaxIndex = -1;
		System.out.print("Intent Scores: (");
		for(int i=0;i<intentScores.length;i++){
			System.out.print(intentScores[i].doubleValue());
			if(i!=intentScores.length-1) {
				System.out.print(", ");
			}
			if(nowMaxScore==null||nowMaxIndex==-1||intentScores[i].doubleValue()>nowMaxScore.doubleValue()) {
				nowMaxIndex = i;
				nowMaxScore = intentScores[i].doubleValue();
			}
		}
		System.out.println(")");
		return intentDictionary[nowMaxIndex];
	}
}
