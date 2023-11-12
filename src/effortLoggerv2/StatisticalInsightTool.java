//This file was made by Joseph Prainito

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
		int size = 0;
		for(UserStory story : historicalData) {
			total += Integer.parseInt(story.getEstimateStoryPoints());	//Average point scores
			size++;
			if(!story.getActualPointScore().equals("Not Assigned")) {
				total += Integer.parseInt(story.getActualPointScore());
				size++;
			}
			
		}
		return total / size;
	}
	
	public int calculateStandardDeviation() {
		
		int sum = 0;
		int stdDev = 0;
		
		for(UserStory story : historicalData) {
			sum += Integer.parseInt(story.getEstimateStoryPoints());		//Standard deviation
		}	
		
		int average = sum/historicalData.size();
		
		for(UserStory story : historicalData) {
			stdDev += (int) Math.pow(Integer.parseInt(story.getEstimateStoryPoints()) - average,2);
		}
		
		return (int) Math.sqrt(stdDev/historicalData.size());
	}
	
public int calculateStandardDeviationByType(UserStory currentStory) {
		
		int sum = 0;
		int size = 0;
		int stdDev = 0;
		
		for(UserStory story : historicalData) {
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {
				sum += Integer.parseInt(story.getEstimateStoryPoints());		//std dev by type
				size++;
			}
		}
		
		int average = sum/size;
		
		for(UserStory story : historicalData) {
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {
			stdDev += (int) Math.pow(Integer.parseInt(story.getEstimateStoryPoints()) - average,2);
			}
		}
		
		return (int) Math.sqrt(stdDev/size);
	}
	
	public int calculateAverageByType(UserStory currentStory) {
		double total = 0;
		int size = 0;
		
		for(UserStory story : historicalData) {
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {		//Average by type
				total += Integer.parseInt(story.getEstimateStoryPoints());
				size++;
			}
		}
		if(size == 0)
			return Integer.parseInt(currentStory.getEstimateStoryPoints());
		else
			return (int) (total/size);
	}
	
	public int calcEstimate(UserStory currentStory) {
	    int baseEstimate = getBaseEstimateFromPriority(currentStory.getPriority());
	    double adjustmentFactor = getAdjustmentFactor(currentStory.getPriority());

	    return limitStoryPoints((int) Math.round(baseEstimate * adjustmentFactor));
	}
	
	public int getBaseEstimateFromPriority(String priority) {
	    switch (priority) {
	        case "High":
	            return 900; // Base estimate for high priority
	        case "Medium":
	            return 300; // Base estimate for medium priority
	        default:
	            return 100; // Base estimate for low/default priority
	    }
	}
	
	public double getAdjustmentFactor(String priority) {
	    double totalDifference = 0;
	    int count = 0;

	    for (UserStory story : historicalData) {
	        if (story.getPriority().equals(priority) && !story.getActualPointScore().equals("Not Assigned")) {
	            int estimated = Integer.parseInt(story.getEstimateStoryPoints());
	            int actual = Integer.parseInt(story.getActualPointScore());
	            totalDifference += (actual - estimated);
	            count++;
	        }
	    }

	    if (count == 0) return 1.0; // No adjustment if no historical data is available
	    return 1.0 + (totalDifference / count) / 100.0; // Adjusting the base estimate by an average percentage
	}
	
	public int limitStoryPoints(int points) {
		return Math.min(Math.max(points, 100), 1000);	//Keeps score between 100-1000
	}
}