import java.lang.reflect.Array;

class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        int hashCode = Math.abs(key.hashCode());
        return (hashCode % size);
    }

    public boolean isKey(String key) {
        int index = hashFun(key);
        return slots[index] != null && values[index] != null;
    }

    public void put(String key, T value) {
        int index = hashFun(key);
        slots[index] = key;
        values[index] = value;
    }

    public T get(String key) {
        final int index = hashFun(key);
        return values[index];
    }
}
