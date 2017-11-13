import java.util.ArrayList;
import java.util.List;
//���
public class Activity {
	int num_people;//�������ģ��δʹ�ã�
	String name;//���
	String begin_time;//���ʼʱ��
	String begin_address;//��ص�
	String information;//���飨����Ϊ�������
	String extra_imformation;//���ע
	
	List<Club> holdList;//����췽�б�
	List<String> clStrList;//�������������췽�б�
	//���췽����δʹ�õĻ������ģ���㣬��ע����
	public Activity(String name, String begin_time, String begin_address, String information, String extra) {
		this.name = name;
		this.begin_time = begin_time;
		this.begin_address = begin_address;
		this.information = information;
		this.extra_imformation = extra == "" ? "��":extra;
		this.holdList = new ArrayList<>();
		this.num_people = 0;
		this.clStrList = new ArrayList<>();
	}
	//��װ��������췽
	public void addClub(Club club){
		this.holdList.add(club);
	}
	//��������¶clStrList
	public List<String> getclStrList() {
		return this.clStrList;
	}
	//���ػ����Ϣ�ַ���
	public String getString() {
		return "�����" + this.name
				+"\n���ʼʱ��" + this.begin_time
				+"\n��ص�" + this.begin_address
				+"\n����" + this.information
				+"\n���ע" + this.extra_imformation;
	}
}
