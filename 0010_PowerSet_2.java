package PowerSet;

public class PowerSet {
    public int size;
    public int countFullSlots;
    public int step;
    public String[] slots;

    public PowerSet() {
        size = 20_000;
        step = 3;
        slots = new String[size];
        countFullSlots = 0;
        for (int i = 0; i < size; i++) slots[i] = null;

        // ваша реализация хранилища
    }

    public int size() {
        // количество элементов в множестве
        return countFullSlots;
    }

    //возможность добавления отсутствующего элемента и невозможность добавления присутствующего в множестве элемента с помощью put();
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
        // всегда срабатывает
    }

    public boolean get(String value) {
        if (value == null) return false;
        // возвращает true если value имеется в множестве,
        // иначе false
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
        // возвращает true если value удалено
        // иначе false
        return false;
    }

    public PowerSet intersection(PowerSet set2) {
        // пересечение текущего множества и set2
        PowerSet set3 = new PowerSet();
        int index = 0;
        while (index < set2.size) {
            if (set2.slots[index] != null && get(set2.slots[index])) {
                set3.put(set2.slots[index]);
            }
            index++;
        }
        return set3;
    }

    //когда оба параметра непустые, и когда один из параметров -- пустое множество;
    public PowerSet union(PowerSet set2) {
        PowerSet set3 = new PowerSet();
        // объединение текущего множества и set2
        // в качестве параметра выступает другое множество,
        // а возвращается объединение этих множеств
        // (множество, в котором есть все элементы из каждого множества
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
        // разница текущего множества и set2
        // в качестве параметра выступает другое множество,
        // а возвращается подмножество текущего множества из таких элементов,
        // которые не входят в множество-параметр;
        PowerSet set3 = new PowerSet();
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
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
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
