package ObserverPattern;

import ObserverPattern.Observable.IphoneStockObservable;
import ObserverPattern.Observable.StocksObservable;
import ObserverPattern.Observer.EmailNotificationObserver;

public class Main {
    public static void main(String args[]) {
        StocksObservable observable = new IphoneStockObservable();
        observable.add(new EmailNotificationObserver());
        observable.setStock(10);
    };
}
