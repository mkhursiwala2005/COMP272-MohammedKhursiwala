/*
 * *** Mohammed Khursiwala / 002 ***
 *
 * This java file contains several simple tree problems that need to be
 * codified. These routines  must use the TreeMap and TreeSet library
 * classes from the Java Collection Framework.
 *
 */

import java.util.*;

public class TreeProblems {

  /**
   * Method different()
   *
   * Given two TreeSets of integers, return a TreeSet containing all elements 
   * that are NOT in both sets. In other words, return a TreeSet of all the
   * elements that are in one set but not the other.
   */
  
    public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {

        // This can be done numerous ways, but once such will only that
        // *several* lines of code. Hint: create two temporary TreeSets and utilize the
        // methods retainAll(), addAll(), and removeAll(). But in the end, get something to work.

        Set<Integer> temp = new TreeSet<>();
        for (int i : setA) {
            if (!setB.contains(i)) {
                temp.add(i);
            }
        }

        for (int j : setB) {
            if (!setA.contains(j)) {
                temp.add(j);
            }
        }

        return temp;
    }


    /**
     * Method removeEven()
     *
     * Given a treeMap with the key as an integer, and the value as a String,
     * remove all <key, value> pairs where the key is even.
     */

    public static void removeEven(Map<Integer, String> treeMap) {
        Set<Integer> keys = new TreeSet<>();
        for (int key : treeMap.keySet()) {
            if (key % 2 == 0) {
                keys.add(key);
            }
        }

        for (int k : keys) {
            treeMap.remove(k);
        }

        return;
    }


    /**
    * Method treesEqual()
    *
    * Given two treeMaps, each with the key as an integer, and the value as a String,
    * return a boolean value indicating if the two trees are equal or not.
    */

    public boolean treesEqual(Map<Integer, String> tree1, Map<Integer, String> tree2) {
        for (int k1 : tree1.keySet()) {
            if (tree2.containsKey(k1)) {
                if (!tree1.get(k1).equals(tree2.get(k1))) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

} // end treeProblems class