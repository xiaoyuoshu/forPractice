import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

//������Աʹ�ã�����ӻ��ע
public class WriteCSV {
	public static void addExtraInformation(Activity activity, String exstra) {
		File csv_activity = new File("���.csv");
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
				//debugʹ�á���ʽʹ��ʱ�����edit��editȥ��
				fos = new FileOutputStream ("���edit.csv");
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
