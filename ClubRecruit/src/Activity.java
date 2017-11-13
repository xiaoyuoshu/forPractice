import java.util.ArrayList;
import java.util.List;
//活动类
public class Activity {
	int num_people;//活动人数规模（未使用）
	String name;//活动名
	String begin_time;//活动开始时间
	String begin_address;//活动地点
	String information;//活动简介（表中为宣传标语）
	String extra_imformation;//活动备注
	
	List<Club> holdList;//活动主办方列表
	List<String> clStrList;//辅助：创建主办方列表
	//构造方法，未使用的活动人数规模置零，备注置无
	public Activity(String name, String begin_time, String begin_address, String information, String extra) {
		this.name = name;
		this.begin_time = begin_time;
		this.begin_address = begin_address;
		this.information = information;
		this.extra_imformation = extra == "" ? "无":extra;
		this.holdList = new ArrayList<>();
		this.num_people = 0;
		this.clStrList = new ArrayList<>();
	}
	//封装：添加主办方
	public void addClub(Club club){
		this.holdList.add(club);
	}
	//辅助：暴露clStrList
	public List<String> getclStrList() {
		return this.clStrList;
	}
	//返回活动的信息字符串
	public String getString() {
		return "活动名称" + this.name
				+"\n活动开始时间" + this.begin_time
				+"\n活动地点" + this.begin_address
				+"\n活动简介" + this.information
				+"\n活动备注" + this.extra_imformation;
	}
}
