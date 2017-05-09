package solution;

import framework.Dispatcher;
import solution.interfaces.InputStrategy;

public class InputContext {

    private InputStrategy inputStrategy;

    public void setInputStrategy(InputStrategy inputStrategy){
        this.inputStrategy = inputStrategy;
    }

    public void executeInputStrategy(Dispatcher dispatcher) {
        inputStrategy.execute(dispatcher);
    }
}
