import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.html.HTML.Tag;

//���ҵ�Ԫ
public class Search {
	//����һ�������б��Ҫ���ҵ��������ƣ����ز��ҵ������ţ������ɹ��򷵻�null��
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
	//����һ����б��Ҫ���ҵ��������ƣ����ز��ҵ������ţ������ɹ��򷵻�null��
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
	//�����û���������ڽ���ɸѡ������һ����б�һ�������ַ�������ʽ����20XX.X.X������
	//һ��boolֵ��boolΪtrue�򷵻ظ�����֮��Ļ�б�boolֵΪfalse�򷵻ظ�����֮ǰ�Ļ�б�
	//�����صĻ�б�Ϊnullʱ������û���ҵ�
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
	//�������ŵĻ�Ͳ��һ�����췽����ģ���Ѿ���Club��Activity������ʵ�֣��ڴ˲���д
	
	//�������ŵĳ�Ա������һ�����Ŷ����Լ�ȫ����Ա���б��������ĳ�Ա��Ϣ�б�
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
