package pl.waw.rubach.points.exceptions;

public class BridgeException extends Exception {
    protected static final float NUMBEROFPOINS = 20;
    protected int numberGiven;


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
     * Not possible according method of scoring - should be error some where?
     * @param pointDiff - is points difference between expected and real scoring (to calculate imps)
     * @param aa - not important parameter to make constructor different :)
     */
    public BridgeException(int pointDiff,boolean aa) {
        super("Błąd różnicy punktów - "+ pointDiff +" mniejsza od zera- to jakiś błąd programu");
        //second option of message: "Róznica punktow nie może być ujemna - bład programu chyba"
    }


    /**
     * Case of invalid  parameters to this method of scoring - is not possible both have fit with 20 points because it should be mark only spades in this case
     * @param numberOfpoints of pair
     * @param fitWe          if we have 8 card in major suit
     * @param fitThey        if they have 8 cards in major suit
     */
    public BridgeException(float numberOfpoints, boolean fitWe, boolean fitThey) {
        super("Nie mogą obie pary mieć fitu w starszym kolorze  przy 20 pkt -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. " +
                "Podano: " + numberOfpoints + " punktów oraz fity: My:" + fitWe + "  Oni:" + fitThey + " - popraw zaznaczenie fitów lub punktów");
        if (!fitWe || !fitThey || numberOfpoints != NUMBEROFPOINS) { //nie jestem pewna warunku bo idea odwracała
            //pyt - czy tak może być - czy ma to sens? - chodziło o to żeby zaraportował jakoś? złe użycie tego wyjatku?
            System.out.print("Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony");
        }

    }

    /**
     * According method of scoring and calculating points number of contrac should be
     * @param numberGiven less then 4 and more then 0
     */
    public BridgeException(int numberGiven) {
        super("Nieprawidłowy parametr rozdania-  podano: " + numberGiven + " a powinna 1, 2, lub 4- spróbuj jeszcze raz!");

        this.numberGiven = numberGiven;
    }

    public int getNumberGiven() {
        return numberGiven;
    }


}
