package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.MAXCONTRACTLEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.MINCONTRACTLEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.NUMBEROFTRICS;
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
        + " a powinno być mniej niż" + NUMBEROFTRICS + " - spróbuj jeszcze raz";

  public static final String INVALID_CONTRACT_LEVEL_MESSAGE =
      "Nie ma takiego poziomu gry, podano: %d a powinno być między "
          + MINCONTRACTLEVEL + " a " + MAXCONTRACTLEVEL
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
      "Błąd różnicy punktów - %d mniejsza od zera- to jakiś błąd programu";
}
