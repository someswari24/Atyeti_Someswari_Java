package structural.singleton;

public class SingletonEagerInitialization {
    private static SingletonEagerInitialization obj=new SingletonEagerInitialization();

    private SingletonEagerInitialization(){

    }

    public static SingletonEagerInitialization getInstance(){
        return obj;
    }

    public static void main(String[] args) {
        SingletonEagerInitialization obj1=SingletonEagerInitialization.getInstance();
        SingletonEagerInitialization obj2=SingletonEagerInitialization.getInstance();
        System.out.println(obj1==obj2);
    }
}
