import java.util.Collections;
import java.util.List;

public class Sort {
	//按开办活动的次数排序
	public static void sortByNumsOfActivities(List<Club> cList) {
		//Club类已经实现了comparable接口，在此直接调用Collections来Sort。
		Collections.sort(cList);
	}
}
