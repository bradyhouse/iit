package fp.itm455.iit.com.puzzler.util;

import fp.itm455.iit.com.puzzler.R;
import java.util.ArrayList;
import java.util.Iterator;

public final class Util {
    private Util() {

    }

    public static int[] rowIds = {
            0,
            R.id.row1,
            R.id.row2,
            R.id.row3,
            R.id.row4,
            R.id.row5
    };

    public static int[] squareIds = {
            R.id.square1,
            R.id.square2,
            R.id.square3,
            R.id.square4,
            R.id.square5,
            R.id.square6,
            R.id.square7,
            R.id.square8,
            R.id.square9,
            R.id.square10,
            R.id.square11,
            R.id.square12,
            R.id.square13,
            R.id.square14,
            R.id.square15,
            R.id.square16,
            R.id.square17,
            R.id.square18,
            R.id.square19,
            R.id.square20,
            R.id.square21,
            R.id.square22,
            R.id.square23,
            R.id.square24,
            R.id.square25
    };


    public static ArrayList<Integer> generateSequence(Integer min, Integer max, Integer count) {
        ArrayList<Integer> range = new ArrayList<>();
        Integer number, i = 0;
        while (i < count) {
            number = (int) (Math.floor(min + (int) (Math.random() * ((max - min) + 1))));
            if (!range.contains(number)) {
                range.add(number);
                i++;
            }
        }
        return range;
    }

    public static ArrayList<Integer> generateGameSequence(Integer min, Integer max, Integer size) {
        ArrayList<Integer> sequence = Util.generateSequence(min, max, size);
        while (!Util.isValid(sequence)) {
            sequence = Util.generateSequence(min, max, size);
        }
        return sequence;
    }

    public static boolean isEven(Integer x) {
        return (x % 2) == 0;
    }

    public static ArrayList<Integer> filterListAfter(ArrayList<Integer> list, Integer criteria, Integer index) {
        ArrayList<Integer> filteredList = new ArrayList<>();
        Iterator iterator = list.iterator();
        Integer pos = 0;
        Integer item;
        while (iterator.hasNext()) {
            item = (Integer) iterator.next();

            if (pos > index && item < criteria) {
                filteredList.add(item);
            }
            pos++;
        }
        return filteredList;
    }

    public static Integer sumList(ArrayList<Integer> list) {
        Integer sum = 0;
        Iterator iterator = list.iterator();
        Integer item;
        while (iterator.hasNext()) {
            item = (Integer) iterator.next();
            sum += item;
        }
        return sum;
    }

    public static boolean isValid(ArrayList<Integer> sequence) {
        ArrayList<Integer> inversionCounts = new ArrayList<>();
        Integer inversionSum = 0;
        Iterator iterator = sequence.iterator();
        Integer pos = 0;
        Integer item;
        while (iterator.hasNext()) {
            item = (Integer) iterator.next();
            ArrayList<Integer> inversions = Util.filterListAfter(sequence, item, pos);
            inversionCounts.add(inversions.size());
            pos++;
        }

        inversionSum = Util.sumList(inversionCounts);
        return Util.isEven(inversionSum);
    }

    public static Integer[] mapGameDimensions(Integer level) {
        switch (level) {
            case 1:
                return new Integer[]{3, 3};
            case 2:
                return new Integer[]{4, 4};
            case 3:
                return new Integer[]{5, 5};
            default:
                return new Integer[]{};
        }
    }
}