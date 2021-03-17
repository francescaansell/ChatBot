package ist361.chatbot.component;

public class FoodIntentClassifier {
	
	private static String[] intentDictionary;
	
	public FoodIntentClassifier() {
		initializeIntentDictionary();
	}
	
	/*
	 * Task 2-1: Initialize the intent dictionary
	 */
	private void initializeIntentDictionary() {
		intentDictionary = new String[]{"FindFood", "OrderFood"};
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
	 * Task 2-2: Calculate the score for each intent
	 * 
	 * [Input] One user message
	 * 
	 * [Output] A Double array that has the score of each intent. The scores
	 * can simply be 0.0 or 1.0, can be probabilities (from 0.0 to 1.0), or
	 * any scores you design. Later the getLabel() method will select the intent 
	 * with *highest* score. 
	 */
	private Double[] calculateIntentScores(String nowInputText) {
		//do not change the following 4 lines
		Double[] scoreArray = new Double[intentDictionary.length];
		for(Double nowValue: scoreArray) {
			nowValue = 0.0;
		}
	
		//The following is the part you need to modify. 
		//This current version just assign random values to each intent.
		for(int i=0;i<scoreArray.length;i++) {
			scoreArray[i] = Math.random();
		}
		
		//do not change the following lines
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
