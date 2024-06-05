package Factory;

import java.util.HashMap;
import java.util.Map;

class CalculatorFactory {
    private Map<String, Operation> operations;

   public CalculatorFactory() {
        this.operations = new HashMap<>();
    }

    public Operation GetOperation(String operationName) {
        return this.operations.get(operationName);
    }
    
    public void SetOperation(String operationName, Operation operationInstance) {
        this.operations.put(operationName, operationInstance);
    }
}

interface Operation {
    int operate(int a, int b);
}

class AddOperation implements Operation {
    public AddOperation() {

    }

    public int operate(int a, int b) {
        return a+b;
    }
}

class Main {
    public static void main(String[] args) {
        CalculatorFactory factory = new CalculatorFactory();
        factory.SetOperation("add", new AddOperation());
        System.out.println(factory.GetOperation("add").operate(1, 2));
    }
}
