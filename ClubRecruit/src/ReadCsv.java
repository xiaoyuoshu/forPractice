import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//��ȡcsv�ļ��е���Ϣ
//��ע��csvΪ�ı��ļ��Ʊ�����Ϊ�Ʊ�ָ���

//��ȡ���ű��Լ����
public class ReadCsv {
	public static void readcsv(List<Club> clubList, List<Activity> activitiyList) {

    	File csv_club = new File("���ű�.csv");  // CSV�ļ�·��
    	File csv_activity = new File("���.csv");	// CSV�ļ�·��
	    BufferedReader br_club = null;//����reader
	    BufferedReader br_activity = null;//����reader
	    //���Դ��ļ�
	    try
	    {
	        br_club = new BufferedReader(new FileReader(csv_club));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    try
	    {
	        br_activity = new BufferedReader(new FileReader(csv_activity));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    //��������������
	    String line_club = "";
	    String line_activity = "";
	    String[] temp;
	    //��ȡ������Ϣ
	    try {
	    		br_club.readLine();//������һ��
	    		br_club.readLine();//�����ڶ���
	    		//ѭ����ȡÿһ��
	            while ((line_club = br_club.readLine()) != null){
	            	if(line_club == "") break;
	            	String[] infor = line_club.split(",",-1);
	            	//��Ԫ�ز�֣�CSV�ļ��е�ÿ�����ӽ���һ���ַ�����
	            	//-1�������ڱ������ַ���
	            	if(infor.length < 5) break;
	            	//��ȥ���������
	            	
	            	//��������
	            	Club club = new Club(infor[0], infor[1], infor[7], infor[2]);
	            	
	            	//���������ϵ��ʽ
	            	club.addCommunication("��ϵ�绰"+infor[3]);
	            	club.addCommunication("����"+infor[4]);
	            	
	            	//������ų�Ա��Ϣ
	            	String[] cluber = infor[5].split("��");
	            	for (String name : cluber) {
						club.addClubers(name);
					}
	            	
	            	//������Ż��δ��ɣ�����Ӻû���Ա������ӣ�
	            	temp = infor[6].split("��");
	            	for (String string : temp) {
						club.getacStrList().add(string);
						club.nums++;
					}
	            	
	            	//����������������б�
	            	clubList.add(club);
	            }
	            
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	    //��ȡ���Ϣ������ͬ��
	    try {
    		br_activity.readLine();
    		br_activity.readLine();
            while ((line_activity = br_activity.readLine()) != null){
            	if(line_activity == "") break;
            	String[] infor = line_activity.split(",",-1);
            	if(infor.length < 5) break;
            	Activity activity = new Activity(infor[0], infor[1], infor[2], infor[4], infor[5]);
            	temp = infor[3].split("��");
            	for (String string : temp) {
					activity.getclStrList().add(string);
				}
            	activitiyList.add(activity);
            }
            
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	    
	    //Ϊ������ӻ
	    for (Club club : clubList) {
			for (String ac : club.getacStrList()) {
				for (Activity activity : activitiyList) {
					if(activity.name == ac){
						club.activitiyList.add(activity);
						break;
					}
				}
			}
		}
	    
	    //Ϊ��������
	    for (Activity activity : activitiyList) {
			for (String cl : activity.getclStrList()) {
				for (Club club : clubList) {
					if(club.name == cl){
						activity.holdList.add(club);
						break;
					}
				}
			}
		}
	}
	
	//��ȡ��Ա��Ϣ��ͬ��
	public static void readUsersCsv(List<Users> userList) {
		File csv_users = new File("�û���.csv");  // CSV�ļ�·��
		BufferedReader br_users = null;
	    try
	    {
	        br_users = new BufferedReader(new FileReader(csv_users));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line_users;
	    try {
    		br_users.readLine();
    		br_users.readLine();
            while ((line_users = br_users.readLine()) != null){
            	if(line_users == "") break;
            	String[] infor = line_users.split(",",-1);
            	if(infor.length < 3) break;
            	Users user = new Users(infor[0],infor[1],infor[2]);
            	userList.add(user);
            }
            
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	}
}
