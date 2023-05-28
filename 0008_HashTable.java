public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        byte[] array = value.getBytes();
        int sumOfByte = 0;
        for (int i = 0; i < array.length; i++) {
            sumOfByte += array[i];
        }
        // всегда возвращает корректный индекс слота
        return Math.abs(sumOfByte % size);
    }

    public int seekSlot(String value) {
        int indexSlotByHashFun = hashFun(value);
        int collision = 0;
        while (collision <= size) {
            if (slots[indexSlotByHashFun] == null) {
                return indexSlotByHashFun;
            }
            indexSlotByHashFun = (indexSlotByHashFun + step) % size;
            collision++;
        }
        // находит индекс пустого слота для значения, или -1

        return -1;
    }

    public int put(String value) {
        int trueIndex = seekSlot(value);
        if (trueIndex == -1) {
            return -1;
        }
        slots[trueIndex] = value;
        // записываем значение по хэш-функции

        // возвращается индекс слота или -1
        // если из-за коллизий элемент не удаётся разместить
        return trueIndex;
    }

    public int find(String value) {
        int findIndex = hashFun(value);
        int nonIndex = 0;
        while (nonIndex <= size) {
            if (slots[findIndex].equals(value)) {
                return findIndex;
            }
            findIndex = (findIndex + step) % size;
            nonIndex++;
        }

        // находит индекс слота со значением, или -1
        return -1;
    }
}
