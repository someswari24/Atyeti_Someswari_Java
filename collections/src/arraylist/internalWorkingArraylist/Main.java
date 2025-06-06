package arraylist.internalWorkingArraylist;

public class Main {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(4);

        for (int i = 0; i < 20; i++) {
            list.add("Element " + i);
            System.out.println("Added: Element " + i + ", Size: " + list.size());
        }

        System.out.println("\nFinal contents:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.get(i));
        }
    }
}
