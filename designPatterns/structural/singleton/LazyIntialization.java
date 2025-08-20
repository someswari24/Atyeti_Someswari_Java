package structural.singleton;

public class LazyIntialization {
    private static LazyIntialization lazy;

    private LazyIntialization(){

    }

    public static LazyIntialization getInstance(){

        if (lazy == null) {
            lazy = new LazyIntialization();
        }return lazy;
    }

    public static void main(String[] args) {
        LazyIntialization obj1=LazyIntialization.getInstance();
        LazyIntialization obj2=LazyIntialization.getInstance();
        System.out.println(obj1==obj2);
    }
}
