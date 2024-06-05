There are two entities in this pattern. Observable and Observer. Observable is an entity, whenever its state changes the observers will be notified. Each observable can be observed by number of observer.

An example of this can be.
-> Iphone stock on amazon -> Observable
-> Email Notification -> Observer
-> SMS Notification -> Observer
-> Whenever stock becomes zero notify the user via email and sms.

Observable will be an interface that will have 3 methods
List<Observer>
-> add(observer)
-> remove(observer)
-> notifyObserver() -> will call update method of all the observers

Observer will also be an interface that will have 1 method
-> update()
