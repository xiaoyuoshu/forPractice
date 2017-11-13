import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.html.HTML.Tag;

//查找单元
public class Search {
	//传递一个社团列表和要查找的社团名称，返回查找到的社团，若不成功则返回null；
	public static Club searchClub(List<Club> cList, String name) {
		Club return_club = null;
		for (Club club : cList) {
			if(club.name == name){
				return_club = club;
				break;
			}
		}
		return return_club;
	}
	//传递一个活动列表和要查找的社团名称，返回查找到的社团，若不成功则返回null；
	public static Activity searchActivity(List<Activity> aList, String name) {
		Activity return_activity = null;
		for (Activity activity : aList) {
			if(activity.name == name){
				return_activity = activity;
				break;
			}
		}
		return return_activity;
	}
	//按照用户输入的日期进行筛选，传递一个活动列表，一个日期字符串（格式：“20XX.X.X”），
	//一个bool值，bool为true则返回该日期之后的活动列表，bool值为false则返回该日期之前的活动列表
	//当返回的活动列表为null时，代表没有找到
	public static List<Activity> classifyActivity(List<Activity> aList, String date, boolean isLater){
		List<Activity> activities = new ArrayList<>();
		int Tag = 0;
		for (Activity activity : aList) {
			String[] begin = activity.begin_time.replace('.', ',').split(",");
			String[] now = date.replace('.', ',').split(",");
			if ((Integer.parseInt(begin[0]) > Integer.parseInt(now[0])) == isLater) {
				activities.add(activity);
				Tag = 1;
			} 
			else if(Integer.parseInt(begin[0]) == Integer.parseInt(now[0])){
				if ((Integer.parseInt(begin[1]) > Integer.parseInt(now[1])) == isLater){
					activities.add(activity);
					Tag = 1;
				}
				else if (Integer.parseInt(begin[1]) == Integer.parseInt(now[1])){
					if ((Integer.parseInt(begin[2]) > Integer.parseInt(now[2])) == isLater) {
						activities.add(activity);
						Tag = 1;
					}
				}
				else;
			}
			else;
		}
		return Tag == 1 ? activities:null;
	}
	//查找社团的活动和查找活动的主办方两个模块已经在Club和Activity类里面实现，在此不重写
	
	//查找社团的成员，输入一个社团对象以及全部成员的列表，返回他的成员信息列表
	public static List<Users> searchUsers(Club club, List<Users> all) {
		List<Users> uList = new ArrayList<>();
		for (String string : club.clubers) {
			for (Users users2 : all) {
				if(string == users2.name){
					uList.add(users2);
				}
			}
		}
		return uList;
	}
}
