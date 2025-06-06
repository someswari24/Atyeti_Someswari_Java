package arraylist.ConcurrentModification;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationExample {
    public static void main(String[] args) {
        ArrayList<Integer> nums=new ArrayList<>();

        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);

        Iterator<Integer> iterator= nums.iterator();
        while (iterator.hasNext()){
            nums.add(50);   //occurs ConcurrentModificationException
        }

        Iterator<Integer> iterator1 = nums.iterator();
        while(iterator1.hasNext()) {
            if(iterator1.next().equals(30)) {
                nums.remove(30);
            }
        }
    }
}
