import java.util.ArrayList;
import java.util.List;
//������,ʵ��comparable�ӿڣ����ں�������
public class Club implements Comparable<Club>{
	String name;//������
	String init_date;//���ſ���ʱ��
	String information;//���ż��

	String f_name;//���Ÿ���������
	
	List<String> clubers;//���ų�Ա�б�

	List<String> communicationList;//������ϵ��ʽ�б�
	List<Activity> activitiyList;//���žٰ��б�
	int nums = 0;//��չ�����
	
	private List<String> acStrList;//�������������žٰ��б�
	//���췽���������ĸ��б�δ���Ԫ��
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
	//��װ�������ϵ��ʽ
	public void addCommunication(String communication) {
		this.communicationList.add(communication);
	}
	//��װ����ӻ
	public void addActivity(Activity activity) {
		this.activitiyList.add(activity);
	}
	//��װ����ӳ�Ա
	public void addClubers(String name) {
		this.clubers.add(name);
	}
	//��������¶acStrList
	public List<String> getacStrList() {
		return this.acStrList;
	}
	@Override
	public int compareTo(Club o) {
		return this.nums - o.nums;
	}
}
