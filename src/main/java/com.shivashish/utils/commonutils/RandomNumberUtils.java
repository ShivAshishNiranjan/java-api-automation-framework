package com.shivashish.utils.commonutils;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-20
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberUtils {

    public static int generateRandomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange);
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

