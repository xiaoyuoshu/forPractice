package seedcup2017;

import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Team{
	int id;
	double shootTime;//投篮次数
	double succeedShootTime;//成功投篮次数
	double succeedShootRate;//投篮成功率
	double sumShowtimes;//出场总人时
	public Team(int id, double shootTime,double succeedShootTime, double succeedShootRate, double sumShowtimes) {
		this.id = id;
		this.shootTime = shootTime;
		this.succeedShootTime = succeedShootTime;
		this.succeedShootRate = succeedShootRate;
		this.sumShowtimes = sumShowtimes;
	}
}

class TrainDataList{
	boolean isWon;
	double visitingWinRate;
	double homeWinRate;
	double subShootTime, subSucceedShootTime, subSucceedShootRate;
	public TrainDataList(boolean isWon, double visitingWinRate, double homeWinRate, double subShootTime, double subSucceedShootTime, double subSucceedShootRate){
		this.isWon = isWon;
		this.visitingWinRate = visitingWinRate;
		this.homeWinRate = homeWinRate;
		this.subShootTime = subShootTime;
		this.subSucceedShootTime = subSucceedShootTime;
		this.subSucceedShootRate = subSucceedShootRate;
	}
}

public class seedcup2017 {
	
	public static void main(String[] args) {
		Team[] teams = new Team[208];
		for(int i = 0;i < 208;i++){
			teams[i] = new Team(i, 0, 0, 0, 0);
		}
		getTeamData(teams);
		List<TrainDataList> trainDataLists = new ArrayList<>();
		getTrainData(trainDataLists, teams);
		List<TrainDataList> testDataLists = new ArrayList<>();
		getTestData(testDataLists, teams);
		System.out.println(classify(trainDataLists, testDataLists.get(0)));
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream ("D:/output.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.write("主场赢得比赛的置信度\n".getBytes());
			for(TrainDataList testDataList : testDataLists){
				fos.write((String.valueOf(classify(trainDataLists, testDataList)) + "\n").getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*FileOutputStream fos = null;
		try {
			fos = new FileOutputStream ("D:/trainData.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.write("是否胜利,客场前胜率,主场前胜率,投篮次数差,投篮成功次数差,投篮成功率\n".getBytes());
			for(TrainDataList trainDataList : trainDataLists){
				fos.write((String.valueOf(trainDataList.isWon) 
						+ "," + String.valueOf(trainDataList.visitingWinRate) 
						+ "," + String.valueOf(trainDataList.homeWinRate) 
						+ "," + String.valueOf(trainDataList.subShootTime)
						+ "," + String.valueOf(trainDataList.subSucceedShootTime)
						+ "," + String.valueOf(trainDataList.subSucceedShootRate)
						+ "\n").getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
			/*FileOutputStream fos = null;
			try {
				fos = new FileOutputStream ("D:/test.csv");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fos.write("队伍编号,投篮次数,投篮成功次数,投篮成功率\n".getBytes());
				for(int i = 0;i < 208;i++){
					fos.write((String.valueOf(teams[i].id) 
							+ "," + String.valueOf(teams[i].shootTime) 
							+ "," + String.valueOf(teams[i].succeedShootTime) 
							+ "," + String.valueOf(teams[i].succeedShootRate) 
							+ "\n").getBytes());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/
		
	}
	private static double classify(List<TrainDataList> trainDataLists, TrainDataList testDataList) {
		double win=0, faile=0;
		for(TrainDataList trainDataList : trainDataLists){
			double sum =Math.pow(trainDataList.visitingWinRate - testDataList.visitingWinRate, 2)*1.2
					+ Math.pow(trainDataList.homeWinRate - testDataList.homeWinRate, 2)*1.2
					+ Math.pow(trainDataList.subShootTime - testDataList.subShootTime, 2)
					+ Math.pow(trainDataList.subSucceedShootTime - testDataList.subSucceedShootTime, 2)
					+ Math.pow(trainDataList.subSucceedShootRate - testDataList.subSucceedShootRate, 2);
			if(trainDataList.isWon==true){
				win+=sum;
			}
			else {
				faile+=sum;
			}
		}
		double for_return = (1.0/win) / (1.0/win + 1.0/faile);
		return for_return;
	}
	
	public static void getTrainData(List<TrainDataList> trainDataLists, Team[] teams) {
		File csv = new File("C:\\Users\\DELL\\workspace\\seedcup2017\\bin\\matchDataTrain.csv");  // CSV文件路径
	    BufferedReader br = null;
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    int tag = 0;
	    try {
	    		line = br.readLine();
	            while ((line = br.readLine()) != null)
	            {
	            	String[] forSplit = line.split(",");
	            	boolean isWon;
	            	int visit = Integer.parseInt(forSplit[0]);
	            	int home = Integer.parseInt(forSplit[1]);
	            	double visitRate = 1.0*Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[0]) / (( Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[1]) )==0?1:( Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[1]) ));
	            	double homeRate = 1.0*Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[0]) / (( Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[1]) )==0?1:( Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[1]) ));
	            	if(  Integer.parseInt(forSplit[4].split(":")[0]) > Integer.parseInt(forSplit[4].split(":")[1])){
	            		isWon = false;
	            	}
	            	else {
						isWon = true;
					}
	            	TrainDataList trainDataList =new TrainDataList(isWon, visitRate, homeRate, 
	            			teams[home].shootTime-teams[visit].shootTime, 
	            			teams[home].succeedShootTime - teams[visit].succeedShootTime,
	            			teams[home].succeedShootRate - teams[visit].succeedShootRate);
	            	trainDataLists.add(trainDataList);
	            }
	            
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public static void getTestData(List<TrainDataList> testDataLists, Team[] teams) {
		File csv = new File("C:\\Users\\DELL\\workspace\\seedcup2017\\bin\\matchDataTest.csv");  // CSV文件路径
	    BufferedReader br = null;
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    int tag = 0;
	    try {
	    		line = br.readLine();
	            while ((line = br.readLine()) != null)
	            {
	            	String[] forSplit = line.split(",");
	            	boolean isWon;
	            	int visit = Integer.parseInt(forSplit[0]);
	            	int home = Integer.parseInt(forSplit[1]);
	            	double visitRate = 1.0*Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[0]) / (( Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[1]) )==0?1:( Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[2].split("[\u4e00-\u9fa5]")[1]) ));
	            	double homeRate = 1.0*Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[0]) / (( Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[1]) )==0?1:( Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[0]) + Integer.parseInt(forSplit[3].split("[\u4e00-\u9fa5]")[1]) ));
	            	TrainDataList testDataList =new TrainDataList(true, visitRate, homeRate, 
	            			teams[home].shootTime-teams[visit].shootTime, 
	            			teams[home].succeedShootTime - teams[visit].succeedShootTime,
	            			teams[home].succeedShootRate - teams[visit].succeedShootRate);
	            	testDataLists.add(testDataList);
	            }
	            
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public static void getTeamData(Team[] teams) {
		File csv = new File("C:\\Users\\DELL\\workspace\\seedcup2017\\bin\\teamData.csv");  // CSV文件路径
	    BufferedReader br = null;
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    int tag = 0;
	    try {
	    		line = br.readLine();
	            while ((line = br.readLine()) != null)
	            {
	                String[] forSplit = line.split(",");
	                double showTimes = Double.parseDouble(forSplit[4]);
	                teams[Integer.parseInt(forSplit[0])].shootTime += showTimes * Double.parseDouble(forSplit[7]);
	                teams[Integer.parseInt(forSplit[0])].succeedShootTime += showTimes * Double.parseDouble(forSplit[6]);
	                teams[Integer.parseInt(forSplit[0])].sumShowtimes += showTimes;
	            }
	            for(int i = 0;i < 208;i++){
	    			teams[i].shootTime /= teams[i].sumShowtimes;
	    			teams[i].succeedShootTime /= teams[i].sumShowtimes;
	    			teams[i].succeedShootRate = teams[i].succeedShootTime / teams[i].shootTime;
	    		}
	             
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}
}


