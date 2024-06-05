This is used to avoid class explosion. Say we are trying build a Pizza Making system.
The base Pizza will be same for all but there can be various topping. So all those toppings
can be used as a decorator class.

In coding terms there will be an interface/abstract class of Base Pizza/object. There can different types of base pizza.

Now the decorator itself will be an interface/abstract class. So that different types of decorators can be implemented.
Each decorator will be instantiated with a base object. Now the base object can itself be a Decorator.
