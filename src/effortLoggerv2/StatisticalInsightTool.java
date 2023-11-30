package effortLoggerv2;

import javafx.collections.ObservableList;

public class StatisticalInsightTool{
	
	//Takes historical data of user stories and uses that to perform functions on that data
	private ObservableList<UserStory> historicalData;
	
	public StatisticalInsightTool(ObservableList<UserStory> historicalData) {
		this.historicalData = historicalData;
	}
	
	public double calculateAverage() {
		double total = 0;
		for(UserStory story : historicalData) {
			total += Double.parseDouble(story.getEstimateStoryPoints());	//Average point scores
		}
		return total / historicalData.size();
	}
	
	public int calcEstimate(UserStory s) {
		double averageStoryPoints = calculateAverage();
		int priorityFactor = Integer.parseInt(s.getPriority());	
		
		if(historicalData.size() == 0)
			return priorityFactor * priorityFactor;		//If no historical data, data == priority score
		
		return (int) (averageStoryPoints * priorityFactor);
	}
}