package observer;

public interface Publisher {
	void addSubrscriber(Subscriber sub);
	void removeSubscriber(Subscriber sub);
	void notifySubscribers(Notification notification);

}
