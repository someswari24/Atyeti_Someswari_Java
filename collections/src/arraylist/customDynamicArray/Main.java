package arraylist.customDynamicArray;

public class Main {
    public static void main(String[] args) {
        CustomDynamicArray<Integer> arr = new CustomDynamicArray<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(1, 15);
        System.out.println(arr); // [10, 15, 20, 30]
        arr.remove(2);
        System.out.println(arr); // [10, 15, 30]
    }
}

