package observer;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Notification {
	private NotificationEnum type;
	private Object data;
}
