import java.lang.reflect.Array;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz;
        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        if (count == 0) {
            array = (T[]) Array.newInstance(this.clazz, new_capacity);
            capacity = new_capacity;
            return;
        }
        if (new_capacity < 16) {
            new_capacity = 16;
        }
        T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);
        System.arraycopy(array, 0, newArray, 0, count);
        array = newArray;
        capacity = new_capacity;
    }

    public T getItem(int index) {
        if (index < 0 || index >= count) throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        return array[index];
    }

    public void append(T itm) {
        if (count + 1 > capacity) {
            makeArray(capacity * 2);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > count) throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        if (count + 1 > capacity) {
            makeArray(capacity * 2);
        }
        if (index == count) {
            append(itm);
            return;
        }
        T[] arrayForInsert = (T[]) Array.newInstance(this.clazz, capacity);
        System.arraycopy(array, 0, arrayForInsert, 0, index);
        arrayForInsert[index] = itm;
        System.arraycopy(array, index, arrayForInsert, index + 1, count - index);
        array = arrayForInsert;
        count++;
    }

    public void remove(int index) {
        if (index < 0 || index > count) throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        if (count - 1 < capacity / 2) {
            makeArray((int) (capacity / 1.5));
        }
        T[] arrayForInsert = (T[]) Array.newInstance(this.clazz, capacity);
        System.arraycopy(array, 0, arrayForInsert, 0, index);
        System.arraycopy(array, index + 1, arrayForInsert, index, count - index);

        array = arrayForInsert;
        count--;
    }
}
