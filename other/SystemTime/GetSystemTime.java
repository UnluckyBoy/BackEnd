package SystemTime;

import java.text.SimpleDateFormat;
import java.util.Date;

//��ȡ��ǰϵͳʱ���װ
public class GetSystemTime {
	public String GetTime(){
		//��ȡ��ǰϵͳʱ��
		long time=System.currentTimeMillis();
		//new���ڶ���
		Date date =new Date(time);
		//ת�������������ʽ
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		String st = dateFormat.format(date);
		return st;
	}
}
