package f;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalTest {
	public static final ThreadLocal<SimpleDateFormat> dateFormat 
	= ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
	
	int upperBound = 100;
	
	Runnable r  = () -> {
		//只属于每个线程自己的那份实例
		String dateStamp = dateFormat.get().format(new Date());
		
		int random = ThreadLocalRandom.current().nextInt(upperBound);
		
		
	};
}
