import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//读取csv文件中的信息
//备注：csv为文本文件制表。逗号为制表分隔符

//读取社团表以及活动表
public class ReadCsv {
	public static void readcsv(List<Club> clubList, List<Activity> activitiyList) {

    	File csv_club = new File("社团表.csv");  // CSV文件路径
    	File csv_activity = new File("活动表.csv");	// CSV文件路径
	    BufferedReader br_club = null;//创建reader
	    BufferedReader br_activity = null;//创建reader
	    //尝试打开文件
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
	    //创建辅助行数组
	    String line_club = "";
	    String line_activity = "";
	    String[] temp;
	    //读取社团信息
	    try {
	    		br_club.readLine();//跳过第一行
	    		br_club.readLine();//跳过第二行
	    		//循环读取每一行
	            while ((line_club = br_club.readLine()) != null){
	            	if(line_club == "") break;
	            	String[] infor = line_club.split(",",-1);
	            	//将元素拆分（CSV文件中的每个格子进入一个字符串）
	            	//-1参数用于保留空字符串
	            	if(infor.length < 5) break;
	            	//舍去多余干扰行
	            	
	            	//创建社团
	            	Club club = new Club(infor[0], infor[1], infor[7], infor[2]);
	            	
	            	//添加社团联系方式
	            	club.addCommunication("联系电话"+infor[3]);
	            	club.addCommunication("邮箱"+infor[4]);
	            	
	            	//添加社团成员信息
	            	String[] cluber = infor[5].split("、");
	            	for (String name : cluber) {
						club.addClubers(name);
					}
	            	
	            	//添加社团活动（未完成，先添加好活动名以便后续添加）
	            	temp = infor[6].split("、");
	            	for (String string : temp) {
						club.getacStrList().add(string);
						club.nums++;
					}
	            	
	            	//将社团添加至社团列表
	            	clubList.add(club);
	            }
	            
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	    //读取活动信息，其他同上
	    try {
    		br_activity.readLine();
    		br_activity.readLine();
            while ((line_activity = br_activity.readLine()) != null){
            	if(line_activity == "") break;
            	String[] infor = line_activity.split(",",-1);
            	if(infor.length < 5) break;
            	Activity activity = new Activity(infor[0], infor[1], infor[2], infor[4], infor[5]);
            	temp = infor[3].split("、");
            	for (String string : temp) {
					activity.getclStrList().add(string);
				}
            	activitiyList.add(activity);
            }
            
	    } catch (IOException e){
	        e.printStackTrace();
	    }
	    
	    //为社团添加活动
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
	    
	    //为活动添加社团
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
	
	//读取成员信息，同上
	public static void readUsersCsv(List<Users> userList) {
		File csv_users = new File("用户表.csv");  // CSV文件路径
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
