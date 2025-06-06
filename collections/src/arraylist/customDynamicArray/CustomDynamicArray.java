package arraylist.customDynamicArray;
import java.util.Arrays;

public class CustomDynamicArray<T> {
    private T[] array;
    private int size;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;

    public CustomDynamicArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void add(T element) {
        ensureCapacity(size + 1);
        array[size++] = element;
    }

    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    public T set(int index, T element) {
        checkIndex(index);
        T old = array[index];
        array[index] = element;
        return old;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null; // Avoid memory leak
        return removed;
    }

    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null) || (element != null && element.equals(array[i]))) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            int newCapacity = capacity * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
            capacity = newCapacity;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }
}

