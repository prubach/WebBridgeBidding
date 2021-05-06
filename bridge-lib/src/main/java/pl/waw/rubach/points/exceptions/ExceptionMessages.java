package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.*;
import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MAXNUBEROFPOINTS;

public class ExceptionMessages {


  public static final String GENERAL_BRIDGE_EXCEPTION_CASE_MESSAGE =
      "Niezgodność z zasadami brydża. ";

  public static final String END_OF_RUBBER_NEGATIVE_MESSAGE =
      "Wyjątek ustawiony w złym przypadku - "
      + "warunek nie jest spełniony";

  public static final String END_OF_RUBBER_POSITIVE_MESSAGE =
      "Rober jest zakończony z wynikiem: %d dla nas ";

  public static final String INVALID_NUMBER_OF_POINTS_MESSAGE =
      "Nieprawidłowo podana liczba punktów na obu rękach partnerów"
      + "-  podano: %.1f a powinna być liczba dodatnia  i nie większa od "
      + MAXNUBEROFPOINTS + " - spróbuj jeszcze raz!";

  public static final String INVALID_CONTRACT_SUIT_MESSAGE =
      "Nie ma takiego koloru: podałeś %s- wpisz jeszcze raz.";

  public static final String INVALID_NUMBER_OF_TRICKS_TAKEN_MESSAGE =
         "Liczba wziętych lew źle podana: podano %d"
        + " a powinno być mniej niż" + NUMBEROFTRICS + " - spróbuj jeszcze raz";
  public static final String INVALID_CONTRACT_LEVEL_MESSAGE =
      "Nie ma takiego poziomu gry, podano: %d a powinno być między "
          + MINCONTRACTLEVEL + " a " + MAXCONTRACTLEVEL
          + " (szlem) - spróbuj jeszcze raz";

}
