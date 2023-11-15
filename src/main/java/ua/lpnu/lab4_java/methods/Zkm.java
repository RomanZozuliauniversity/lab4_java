package ua.lpnu.lab4_java.methods;

import java.util.ArrayList;
import java.util.List;

public class Zkm {
    boolean checkPeriod;
    int count, x0, a, b, c, countIter = 0;
    List<Double> arr = new ArrayList<>();

    public Zkm(int x0, int a, int c, int b, int count, boolean checkPeriod) {
        this.checkPeriod = checkPeriod;
        this.count = count;
        this.a = a;
        this.b = b;
        this.c = c;
        this.x0 = x0;
        double prev = (x0 * a + c) % Math.pow(2, b);
        countIter++;

        arr.add(prev / Math.pow(2, b));
        if (!checkPeriod)
            generator(prev);
        else
            generatorForPeriod(prev);
    }

    private void generator(double prevNum) {
        double num;
        for (int i = 1; i < count; i++) {
            num = (prevNum * a + c) % Math.pow(2, b);
            arr.add(num / Math.pow(2, b));
            countIter++;
            prevNum = num;
        }
    }

    private void generatorForPeriod(double prevNum) {
        double num, temp;
        for (; ; countIter++) {
            num = (prevNum * a + c) % Math.pow(2, b);
            temp = num / Math.pow(2, b);
            if (arr.get(0) == temp)
                break;
            arr.add(temp);
            prevNum = num;
        }
    }

    public List<Double> getArray() {
        return arr;
    }

    public int getCountIter() {
        return countIter;
    }
}