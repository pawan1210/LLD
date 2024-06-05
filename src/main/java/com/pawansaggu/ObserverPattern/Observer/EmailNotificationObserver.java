package ObserverPattern.Observer;

public class EmailNotificationObserver implements NotificationObserver{
    public void update() {
        System.out.println("Stocks became zero");
    }
}
