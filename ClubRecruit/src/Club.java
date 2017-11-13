import java.util.ArrayList;
import java.util.List;
//社团类,实现comparable接口，便于后续排序
public class Club implements Comparable<Club>{
	String name;//社团名
	String init_date;//社团开办时间
	String information;//社团简介

	String f_name;//社团负责人姓名
	
	List<String> clubers;//社团成员列表

	List<String> communicationList;//社团联系方式列表
	List<Activity> activitiyList;//社团举办活动列表
	int nums = 0;//开展活动次数
	
	private List<String> acStrList;//辅助：创建社团举办活动列表
	//构造方法，其中四个列表并未添加元素
	public Club(String name, String init_date, String information, String f_name) {
		this.name = name;
		this.init_date = init_date;
		this.information = information;
		this.f_name = f_name;
		this.communicationList = new ArrayList<>();
		this.activitiyList = new ArrayList<>();
		this.clubers = new ArrayList<>();
		this.acStrList = new ArrayList<>();
	}
	//封装：添加联系方式
	public void addCommunication(String communication) {
		this.communicationList.add(communication);
	}
	//封装：添加活动
	public void addActivity(Activity activity) {
		this.activitiyList.add(activity);
	}
	//封装：添加成员
	public void addClubers(String name) {
		this.clubers.add(name);
	}
	//辅助：暴露acStrList
	public List<String> getacStrList() {
		return this.acStrList;
	}
	@Override
	public int compareTo(Club o) {
		return this.nums - o.nums;
	}
}
