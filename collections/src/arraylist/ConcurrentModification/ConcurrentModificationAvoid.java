package arraylist.ConcurrentModification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class ConcurrentModificationAvoid {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();

        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);


        Iterator<Integer> iterator = nums.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(30)) {
                iterator.remove();
            }
        }
        System.out.println(nums);

        ListIterator<Integer> listIterator = nums.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().equals(40)) {
                listIterator.remove();
                listIterator.add(30);
            }
        }
        System.out.println(nums);
    }
}
