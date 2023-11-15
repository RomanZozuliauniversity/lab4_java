package ua.lpnu.lab4_java.methods;

import java.util.ArrayList;
import java.util.List;

public class Distribution {
    private List<Double> arr;
    private int N;
    private List<Double> intervals = new ArrayList<>();
    private List<Integer> countElemOnIntervals = new ArrayList<>();

    public Distribution(List<Double> arr) {
        this.arr = arr;
        N = arr.size();

        findDist();
    }

    private void findDist() {
        double min, max;
        int K = (int)(1 + 3.2 * Math.log10(N));
        min = arr.get(0);
        max = arr.get(0);
        for(Double elem : arr) {
            if (elem > max)
                max = elem;
            else if (elem < min)
                min = elem;
        }

        double delta = (max - min) / K;
        int countOnInterval = 0;
        for (double start = min, end = start + delta; end <= max; start = end, end = end + delta) {
            intervals.add(start);
            for(Double elem : arr)
            if ((elem >= start && elem < end) || (elem >= start && elem <= end && elem == max))
                countOnInterval++;
            countElemOnIntervals.add(countOnInterval);
            countOnInterval = 0;
        }
        intervals.add(max);
    }

    public List<Double> getIntervals() {
        return intervals;
    }

    public List<Integer> getCountElementOnInterval() {
        return countElemOnIntervals;
    }
}