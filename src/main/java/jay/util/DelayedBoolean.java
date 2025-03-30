package jay.util;

import org.w3c.dom.html.HTMLImageElement;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class DelayedBoolean {

    private final double delay;

    public DelayedBoolean(double delay) {
        this.delay = delay;
    }

    public boolean update(double timestamp, Supplier<Boolean> condition) {
        if(delay >= timestamp) {
            return condition.get();
        }
        return false;
    }

    public boolean update(double timestamp, boolean condtion) {
        return update(timestamp, () -> condtion);
    }

}