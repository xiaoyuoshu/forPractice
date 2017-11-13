import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

//给管理员使用，来添加活动备注
public class WriteCSV {
	public static void addExtraInformation(Activity activity, String exstra) {
		File csv_activity = new File("活动表.csv");
		BufferedReader br_activity = null;
		try
		{
			br_activity = new BufferedReader(new FileReader(csv_activity));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		String line_activity = "";
		try {
			line_activity = br_activity.readLine();
			FileOutputStream fos = null;
			try {
				//debug使用。正式使用时将活动表edit的edit去掉
				fos = new FileOutputStream ("活动表edit.csv");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fos.write((line_activity + "\n").getBytes());
				line_activity = br_activity.readLine();
				fos.write((line_activity + "\n").getBytes());
				while ((line_activity = br_activity.readLine()) != null){
					if(line_activity.startsWith(activity.name)){
						String[] temp = line_activity.split(",");
						fos.write((temp[0] + ","
								+  temp[1] + ","
								+  temp[2] + ","
								+  temp[3] + ","
								+  temp[4] + ","
								+  exstra  +  ",,\n"
								).getBytes());
					}
					else {
						fos.write((line_activity + "\n").getBytes());
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } catch (IOException e){
	        e.printStackTrace();
	    }
		
		
	}
}
