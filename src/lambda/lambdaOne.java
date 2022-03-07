package lambda;

import java.util.ArrayList;

/**
 * lambda interface for taking an array and computing the average.
 */
@FunctionalInterface
public interface lambdaOne {
    /**
     * LAMBDA Expression: This lambda interface takes in an array and returns an integer.
     * @param i ArrayList
     * @return integer
     */
    int average(ArrayList<Integer> i);
}