public class Decorator implements Component {
    //component class
    private Component component;

    public Decorator(Component c) {
        component = c;
    }

    public String operation() {
        return component.operation();
    }

}
