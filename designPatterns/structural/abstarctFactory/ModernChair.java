package structural.abstarctFactory;

public class ModernChair implements Chair{
    @Override
    public void create() {
        System.out.println("creating modern chair");
    }
}
