package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.duplicateBridgeImps.OneDealImp.MEDIUMNUMBEROFPOINS;

/**
 * Parent class to all bridge exceptions
 */
public class BridgeException extends Exception {

    protected static String wrongExceptionCaseMessage =
        "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony";

    public BridgeException(String message) {
        super(message);
    }

    /**
     * General bridge exception
     */
    public BridgeException() {
        super("Niezgodność z zasadami brydża. ");
    }


    /**
     * Exception of  invalid  parameters to this method of scoring -
     * is not possible both have fit with 20 points because it should be mark only spades in this case according rules
     * @param numberOfpoints of pair
     * @param fitWe          if we have 8 card in major suit
     * @param fitThey        if they have 8 cards in major suit
     */
    public BridgeException(float numberOfpoints, boolean fitWe, boolean fitThey) {
        super(!fitWe || !fitThey || numberOfpoints != MEDIUMNUMBEROFPOINS ? wrongExceptionCaseMessage :
                "Nie mogą obie pary mieć fitu w starszym kolorze  przy "+ MEDIUMNUMBEROFPOINS +" PC -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. " +
                "Podano: " + numberOfpoints + " punktów oraz fity: My:" + fitWe + "  Oni:" + fitThey + " - popraw zaznaczenie fitów lub punktów");

   }


      /**
     * Exception of less then zero points difference (to calculate imps), because imp table have only positive values.
     * Not possible according method of scoring - should be error some where ...
     * @param pointDiff - is points difference between expected and real scoring
     *
     */
    public BridgeException(int pointDiff) {
        super(pointDiff >0 ? wrongExceptionCaseMessage : "Błąd różnicy punktów - "+ pointDiff +" mniejsza od zera- to jakiś błąd programu");
    }




}
