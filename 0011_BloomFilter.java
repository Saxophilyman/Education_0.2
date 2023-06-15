public class BloomFilter {
    public int filter_len;
    public int mask;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        mask = 0; 
    }

    // хэш-функции
    // организуем цикл до длины строки, результат в этом цикле считаем как
    // его версия с предыдущей итерации,
    // умноженная на случайное число, // случайные числа 17 и 223
    // к которой прибавляется код очередного символа, // (int) str1.charAt(i);
    // и берём результат тут же по модулю длины таблицы. // кратное длине таблицы
    public int hash1(String str1) {
        int magikNumber = 17;
        int res = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            res = res * magikNumber + code;
        }
        return res % filter_len;
    }

    public int hash2(String str1) {
        int magikNumber = 223;
        int res = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            res = res * magikNumber + code;
        }
        return res % filter_len;
    }

    public void add(String str1) {
        int bitHash1 = hash1(str1);
        int bitHash2 = hash2(str1);
        mask = (mask | (1 << bitHash1)) | (mask | (1 << bitHash2));
    }

    public boolean isValue(String str1) {
        int bitHash1 = hash1(str1);
        int bitHash2 = hash2(str1);
        return ((mask & (1 << bitHash1)) !=0) && ((mask & (1 << bitHash2)) !=0);
    }
}
