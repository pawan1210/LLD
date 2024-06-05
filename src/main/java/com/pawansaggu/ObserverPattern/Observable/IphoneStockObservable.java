package ObserverPattern.Observable;

import java.util.ArrayList;
import java.util.List;

import ObserverPattern.Observer.NotificationObserver;

public class IphoneStockObservable implements StocksObservable{
    private List<NotificationObserver> observers;
    private int stock;

    public IphoneStockObservable() {
        this.observers = new ArrayList<>();
    }

    public void add(NotificationObserver observer) {
        this.observers.add(observer);
    }

    public void remove(NotificationObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserver() {
        this.observers.stream().forEach((NotificationObserver observer) -> {
            observer.update();
        });
    }

    public void setStock(int stock) {
        if(this.stock==0) {
            this.notifyObserver();
        }
        this.stock += stock;
    }

    public int getStock() {
        return this.stock;
    }
}
