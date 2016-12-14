package basicFunctions;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author ellen
 */
public class MenuItem {
    private final String LABEL;
    private Function function;
    private Consumer<Scanner> consumer;

    /**
     * 
     * @param LABEL
     * @param consumer 
     */
    public MenuItem(String LABEL, Consumer consumer) { 
        this.LABEL = LABEL;
        this.consumer = consumer;
    }

    public String getLabel() {
        return LABEL;
    }
    
    
    public void doBehavior() {
        //function.apply(/*GameManager.getPlayer()*/);
    }
    
    public void doBehavior(Scanner t) {
        function.apply(t);
    }
}
