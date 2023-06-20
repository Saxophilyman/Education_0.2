import java.lang.reflect.Array;

public class NativeCache <T> {
    public int size;
    public String [] slots;
    public T [] values;
    public int [] hits;

    public NativeCache(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        hits = new int[size];
    }
    public int hashFun(String key) {
        int hashCode = Math.abs(key.hashCode());
        return (hashCode % size);
    }

    public void put(String key, T value) {
        int index = hashFun(key);
        int nonIndex = 0;
        while (nonIndex <= size) {
            if (slots[index] == null || slots[index].equals(key)){
                slots[index] = key;
                values[index] = value;
                hits[index] += 1;
                return;
            }
            index = (index + 3) % size;
            nonIndex++;
        }
        int min = hits[0];
        int minIndex = 0;
        for (int i = 0; i < hits.length; i++) {
            if (hits[i] < min) {
                min = hits[i];
                minIndex = i;
            }
        }
        slots[minIndex] = key;
        values[minIndex] = value;
        hits[minIndex] = 1;
    }
}
