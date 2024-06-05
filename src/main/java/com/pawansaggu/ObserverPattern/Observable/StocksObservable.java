package ObserverPattern.Observable;

import ObserverPattern.Observer.NotificationObserver;

public interface StocksObservable {
    void add(NotificationObserver observer);
    
    void remove(NotificationObserver observer);

    void notifyObserver();

    void setStock(int stock);

    int getStock();
}
