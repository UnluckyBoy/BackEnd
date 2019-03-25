package SystemTime;

import java.text.SimpleDateFormat;
import java.util.Date;

//获取当前系统时间分装
public class GetSystemTime {
	public String GetTime(){
		//获取当前系统时间
		long time=System.currentTimeMillis();
		//new日期对象
		Date date =new Date(time);
		//转换提日期输出格式
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		String st = dateFormat.format(date);
		return st;
	}
}
