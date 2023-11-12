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
			sum += Integer.parseInt(story.getEstimateStoryPoints());
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
				sum += Integer.parseInt(story.getEstimateStoryPoints());
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
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {
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
	    if (historicalData.isEmpty()) {
	        return getPriorityEstimete(currentStory.getPriority());
	    }

	    double weightedTotal = 0;
	    double totalWeight = 0;

	    for (UserStory story : historicalData) {
	        int weight = getPriorityEstimete(story.getPriority());

	        int normalizedEstimate = limitStoryPoints(Integer.parseInt(story.getEstimateStoryPoints()));
	        weightedTotal += normalizedEstimate * weight;
	        totalWeight += weight;

	        if (!story.getActualPointScore().equals("Not Assigned")) {
	            int normalizedActual = limitStoryPoints(Integer.parseInt(story.getActualPointScore()));
	            weightedTotal += normalizedActual * weight;
	            totalWeight += weight;
	        }
	    }

	    if (totalWeight == 0) {
	        return 0;
	    }

	    double weightedAverage = weightedTotal / totalWeight;
	    return limitStoryPoints((int) Math.round(weightedAverage));
	}
	
	public int getPriorityEstimete(String priority) {
		switch (priority) {
		case "High":
			return 300*3;
		case "Medium":
			return 200*2;
		default:
			return 100;
		}
	}
	
	public int limitStoryPoints(int points) {
		return Math.min(Math.max(points, 100), 1000);
	}
}