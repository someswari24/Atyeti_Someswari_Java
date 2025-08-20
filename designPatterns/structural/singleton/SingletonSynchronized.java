package structural.singleton;

public class SingletonSynchronized {
    private static SingletonSynchronized instance;

    private SingletonSynchronized() {

    }

    public static synchronized SingletonSynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronized();
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonSynchronized obj1 = SingletonSynchronized.getInstance();
        SingletonSynchronized obj2 = SingletonSynchronized.getInstance();
        System.out.println(obj1 == obj2);
    }
}
