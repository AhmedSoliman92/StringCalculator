package calculator;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoggingListener extends Handler {
	StringBuilder stringBuilder=new StringBuilder();
	

	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publish(LogRecord arg0) {
		this.stringBuilder.append(arg0.getMessage());
	}
}
