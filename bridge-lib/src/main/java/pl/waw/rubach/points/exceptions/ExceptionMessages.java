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

package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.MAX_CONTRACT_LEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.MIN_CONTRACT_LEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.NUMBER_OF_TRICKS;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MAX_NUMBER_OF_POINTS;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MEDIUM_NUMBER_OF_POINTS;

class ExceptionMessages {


  public static final String GENERAL_BRIDGE_EXCEPTION_CASE_MESSAGE =
      "Niezgodność z zasadami brydża. ";

  public static final String END_OF_RUBBER_NEGATIVE_MESSAGE =
      "Wyjątek ustawiony w złym przypadku - "
      + "warunek nie jest spełniony";

  public static final String END_OF_RUBBER_POSITIVE_MESSAGE =
      "Rober jest zakończony z wynikiem: %d dla nas ";

  public static final String INVALID_NUMBER_OF_POINTS_MESSAGE =
      "Nieprawidłowo podana liczba punktów na obu rękach partnerów"
      + "-  podano: %.2f a powinna być liczba dodatnia  i nie większa od "
      + MAX_NUMBER_OF_POINTS + " - spróbuj jeszcze raz!";

  public static final String INVALID_CONTRACT_SUIT_MESSAGE =
      "Nie ma takiego koloru: podałeś %s- wpisz jeszcze raz.";

  public static final String INVALID_NUMBER_OF_TRICKS_TAKEN_MESSAGE =
         "Liczba wziętych lew źle podana: podano %d"
        + " a powinno być mniej niż" + NUMBER_OF_TRICKS + " - spróbuj jeszcze raz";

  public static final String INVALID_CONTRACT_LEVEL_MESSAGE =
      "Nie ma takiego poziomu gry, podano: %d a powinno być między "
          + MIN_CONTRACT_LEVEL + " a " + MAX_CONTRACT_LEVEL
          + " (szlem) - spróbuj jeszcze raz";

  public static final String INVALID_FIT_MESSAGE =
      "Nie mogą obie pary mieć fitu w starszym kolorze  przy "
          + MEDIUM_NUMBER_OF_POINTS
          + " PC -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. "
          + "Podano: %.2f punktów oraz że są oba  fity"
          + "- popraw zaznaczenie fitów lub punktów";

  public static final String INVALID_NDRSIGNATURE_MESSAGE =
      "Są tylko trzy opcje (1 = bez kontry, 2 - kontra, 4 - rekontra. Podałeś "
          + "%s - spróbuj jeszcze raz";

  public static final String INVALID_NUMBER_OF_GAME_MESSAGE =
      "Nieprawidłowy numer gry-  podano: %d, "
          + "a powinna 1, 2, 3 lub 4- spróbuj jeszcze raz!";

  public static final String INVALID_POINTS_TO_IMPS_MESSAGE =
      "Błąd różnicy punktów - %d nie mieści się w zakresie"
         + "- to jakiś błąd programu";
}
