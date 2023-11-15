package ua.lpnu.lab4_java.methods;

import java.util.ArrayList;
import java.util.List;

public class Akm {
    boolean checkPeriod;
    int count, x0, x1, b, countIter = 0;
    double x0Gen, x1Gen;
    List<Double> arr = new ArrayList<>();

    public Akm(int x0, int x1, int b, int count, boolean checkPeriod) {
        this.count = count;
        this.b = b;
        this.x0 = x0;
        this.x1 = x1;

        double num = (x0 + x1) % Math.pow(2, b);
        countIter++;
        arr.add(num / Math.pow(2, b));
        if (!checkPeriod)
            generator(x1, num);
        else
            generatorForPeriod(x1, num);
    }

    private void generator(double prevNum, double curr) {
        double num;
        for (int i = 1; i < count; i++) {
            num = (prevNum + curr) % Math.pow(2, b);
            arr.add(num / Math.pow(2, b));
            countIter++;
            prevNum = curr;
            curr = num;
        }
    }

    private void generatorForPeriod(double prevNum, double curr) {
        double num, temp;
        for (; ; countIter++) {
            num = (prevNum + curr) % Math.pow(2, b);
            temp = num / Math.pow(2, b);
            if (x0 == curr)
                break;
            arr.add(temp);
            prevNum = curr;
            curr = num;
        }
    }

    public List<Double> getArray() {
        return arr;
    }

    public int getCountIter() {
        return countIter;
    }
}