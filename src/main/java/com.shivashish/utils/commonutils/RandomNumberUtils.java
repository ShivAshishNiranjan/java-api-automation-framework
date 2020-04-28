package com.shivashish.utils.commonutils;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-20
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberUtils {
    /**
     * This Method will give the random number between 0 to upperRange(exclusive)
     *
     * @param upperRange
     * @return
     */
    public static int generateRandomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    /**
     * This Method will give the random number between minimum and maximum
     *
     * @param minimum
     * @param maximum
     * @return
     */
    public static int generateRandomInt(int minimum, int maximum) {
        Random rand = new Random();
        return minimum + rand.nextInt((maximum - minimum) + 1);
    }

    /**
     * This function will give the random list of size sizeofRandomLists from give list
     *
     * @param list
     * @param sizeofRandomLists
     * @param <E>
     * @return
     */
    public static <E> List<E> getRandomElements(List<E> list, int sizeofRandomLists) {
        if (sizeofRandomLists >= list.size() || sizeofRandomLists <= 0)
            return list;

        Random rand = new Random();
        List<E> newList = new ArrayList<>();

        for (int i = 0; i < sizeofRandomLists; i++) {
            int randomIndex = rand.nextInt(list.size());
            newList.add(list.get(randomIndex));
            // Remove selected element from original list
            list.remove(randomIndex);
        }
        return newList;
    }

}

