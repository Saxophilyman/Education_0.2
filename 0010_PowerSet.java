public class PowerSet {
    public int size;
    public int countFullSlots;
    public int step;
    public String[] slots;

    public PowerSet(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        countFullSlots = 0;
        for (int i = 0; i < size; i++) slots[i] = null;

    }

    public int size() {
        return countFullSlots;
    }

    public void put(String value) {
        if (value == null) return;
        if (get(value)) {
            return;
        }
        int hashCode = Math.abs(value.hashCode()) % size;
        int collision = 0;
        while (collision <= size) {
            if (slots[hashCode] != null) {
                hashCode = (hashCode + step) % size;
                collision++;
            } else {
                slots[hashCode] = value;
                countFullSlots++;
                return;
            }
        }
    }

    public boolean get(String value) {
        if (value == null) return false;
        int hashCode = Math.abs(value.hashCode()) % size;
        int nonIndex = 0;
        while (nonIndex <= size) {
            if (slots[hashCode] != null && slots[hashCode].equals(value)) {
                return true;
            }
            hashCode = (hashCode + step) % size;
            nonIndex++;
        }
        return false;
    }

    public boolean remove(String value) {
        if (value == null) return false;
        int hashCode = Math.abs(value.hashCode()) % size;
        int nonIndex = 0;
        while (nonIndex <= size) {
            if (slots[hashCode] != null && slots[hashCode].equals(value)) {
                slots[hashCode] = null;
                countFullSlots--;
                return true;
            }
            hashCode = (hashCode + step) % size;
            nonIndex++;
        }
        return false;
    }

    public PowerSet intersection(PowerSet set2) {
        PowerSet set3 = new PowerSet(set2.countFullSlots, set2.step);
        int index = 0;
        while (index < set2.size) {
            if (set2.slots[index] != null && get(set2.slots[index])) {
                set3.put(set2.slots[index]);
            }
            index++;
        }
        return set3;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet set3 = new PowerSet(set2.countFullSlots + countFullSlots, set2.step);
        int indexSet = 0;
        int indexSet2 = 0;
        while (indexSet < size) {

            set3.put(slots[indexSet]);
            indexSet++;
        }
        while (indexSet2 < set2.size) {
            set3.put(set2.slots[indexSet2]);
            indexSet2++;
        }
        return set3;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet set3 = new PowerSet(countFullSlots, step);
        int indexSet = 0;
        while (indexSet < size) {
            set3.put(slots[indexSet]);
            indexSet++;
        }
        int indexSet2 = 0;
        while (indexSet2 < set2.size) {
            set3.remove(set2.slots[indexSet2]);
            indexSet2++;
        }

        return set3;
    }

    public boolean isSubset(PowerSet set2) {
        int indexSet2 = 0;
        while (indexSet2 < set2.size) {
            if (set2.slots[indexSet2] == null) {
                indexSet2++;
                continue;
            }
            if (!get(set2.slots[indexSet2])) {
                return false;
            }
            indexSet2++;
        }
        return true;
    }
}
