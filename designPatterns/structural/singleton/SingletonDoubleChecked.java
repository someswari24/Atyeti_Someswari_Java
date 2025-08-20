package structural.singleton;

public class SingletonDoubleChecked {
    private static volatile SingletonDoubleChecked instance;

    private SingletonDoubleChecked(){

    }

    public static SingletonDoubleChecked getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleChecked.class) {
                if (instance == null) {
                    instance = new SingletonDoubleChecked();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonDoubleChecked obj1 = SingletonDoubleChecked.getInstance();
        SingletonDoubleChecked obj2 = SingletonDoubleChecked.getInstance();
        System.out.println(obj1 == obj2);
    }
}
