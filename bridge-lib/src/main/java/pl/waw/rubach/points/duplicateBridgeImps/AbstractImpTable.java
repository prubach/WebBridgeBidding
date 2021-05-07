/*
 *
 *  *                     GNU GENERAL PUBLIC LICENSE
 *  *                        Version 0.8 ,  8. 05. 2021
 *  *
 *  *                   "Bridge-lib"  application as help
 *  *                  for bidding, and points counting
 *   *     Copyright (C) {2017}  {Paweł Rubach, Magdalena Wilska}
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *      any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *
 *
 */

package pl.waw.rubach.points.duplicateBridgeImps;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class AbstractImpTable {


  /**
   * Map of points (as key) and  result imp as ???
   */
  protected Map<Integer, Integer> ptsMap = new HashMap<>();

  /**
   * set of key (so points borders?
   */
  protected NavigableSet<Integer> ptsSet;


  //Getters ? (no setters because is not possible to change sth here ?

  /**
   * Function to give imps form points (map value from key?)
   *
   * @param points difference between expected and wined point in game
   * @return imp points
   */
  public int getImpPoints(int points) {
    Integer key = ptsSet.ceiling(points);
    return ptsMap.get(key);
  }

  /**
   * Function to give imps form points (map value from key?)
   *
   * @param imps difference between expected and wined poinst in game
   * @return imp points
   */
  public int getExpectedPointsRevers(int imps) {

    Map<Integer, Integer> map = getPtsMap();
    SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
    for (Integer key : ptsMapSet) {
      if (map.get(key).equals(imps)) {
        return key;
      }
    }
    return 0;
  }

  public static String impDeclination(int i) {
    String imp;
    if (i == 1) {
      imp = " imp";
    } else if (i < 5 | i > 21 && i < 25) {
      imp = " impy";
    } else {
      imp = " impów";
    }
    return imp;


  }

  static int[] findingDifferenceFromImp(int imps) {
    return new int[]{ImpTable.getInstance().getExpectedPointsRevers(imps - 1),
        ImpTable.getInstance().getExpectedPointsRevers(imps)};
  }


  // pyt probably it is better then above (to find key having value) but dont know how :(
  public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
    return map.entrySet()
        .stream()
        .filter(entry -> Objects.equals(entry.getValue(), value))
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
  }

  /**
   * Function to get the whole map (point as key and imp as value)
   *
   * @return ImpTable value for all possibilities
   */
  public Map<Integer, Integer> getPtsMap() {
    return ptsMap;
  }


  /**
   * Function to check condition if imput values are correct
   *
   * <p>return boolean true if good false if wrong
   */
  public boolean checkInputValue(int begin, int end, int value) {

    return value <= end && value >= begin;
  }

  /**
   * @return string with the description of the table
   */
  public String toString() {
    String s = ("*** Tabela impów *** \n");

    Map<Integer, Integer> map = getPtsMap();
    SortedSet<Integer> ptsMapSet = new TreeSet<>(map.keySet());
    int prev = 0;
    for (Integer key : ptsMapSet) {
      s = s + "\n " + (map.get(key) + " imp " + (prev > 0 ? prev + 10 : 0) + "-" + key);
      prev = key;
    }
    return s;
  }
}
