//Edit EffortLogs - Anton Nguyen
//used for editing LocalDateTime in time start and stop
package effortLoggerv2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class DateTimeConverter extends StringConverter {

	@Override
	public LocalDateTime fromString(String arg) {
		// TODO Auto-generated method stub
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime localDateTime = LocalDateTime.parse(arg, dateTimeFormatter);
		return localDateTime;
	}

	public String toString(LocalDateTime arg) {
		// TODO Auto-generated method stub
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		return arg.format(dateTimeFormatter);
	}

	@Override
	public String toString(Object arg) {
		// TODO Auto-generated method stub
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		return ((LocalDateTime) arg).format(dateTimeFormatter);
	}

}
