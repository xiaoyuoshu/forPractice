import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	//创建三个列表
	private static List<Club> clubList = new ArrayList<>();
	private static List<Activity> activityList = new ArrayList<>();
	private static List<Users> usersList = new ArrayList<>();
	public static void main(String[] args) {
		//提取数据
		ReadCsv.readcsv(clubList, activityList);
		ReadCsv.readUsersCsv(usersList);
		Sort.sortByNumsOfActivities(clubList);
		List<Activity> testClassify = Search.classifyActivity(activityList, "2017.1.1", true);
		System.out.println(testClassify.get(0));
		WriteCSV.addExtraInformation(testClassify.get(0), "lalala");
	}
}
