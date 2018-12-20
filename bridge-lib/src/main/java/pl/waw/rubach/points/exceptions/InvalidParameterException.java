package pl.waw.rubach.points.exceptions;

public class InvalidParameterException extends BridgeException {

     private float numberOfpoints =20;
     private int numberGiven;
     private boolean fitWe;
     private boolean fitThey;

    /**
     * Not posible acording method of scoring - shoud be error some where?
     */
    public InvalidParameterException() {
        super("Błąd różnicy punktów - mniejsza od zera- to jakiś błąd programu");
        //second option of message: "Róznica punktow nie może być ujemna - bład programu chyba"
    }



    public InvalidParameterException(String message) {
        super(message);

    }

    /**
     * Case of invalid  parameters to this method of scoring - is not possible both have fit with 20 points because it should be mark only spades in this case
     * @param numberOfpoints of pair
     * @param fitWe if we have 8 card in major suit
     * @param fitThey if they have 8 cards in major suit
     */
    public InvalidParameterException(float numberOfpoints, boolean fitWe, boolean fitThey) {
        super("Nie mogą obie pary mieć fitu w starszym kolorze  przy 20 pkt -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. " +
                "Podano: " + numberOfpoints + " punktów oraz fity: My:"+fitWe+"  Oni:"+fitThey+" - popraw zaznaczenie fitów lub punktów");
        if (!fitWe || !fitThey || numberOfpoints != 20) { //nie jestem pewna warunku bo idea odwracała
            //pyt - czy tak może być - czy ma to sens? - chodziło o to żeby zaraportował jakoś? złe użycie tego wyjatku?
            this.numberOfpoints = numberOfpoints;  System.out.print("Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony");
        }

    }

    /**
     * According method of scoring and calculating points number of contrac should be
     * @param numberGiven less then 4 and more then 0
     */
    public InvalidParameterException(int numberGiven) {
        super("Nieprawidłowy parametr gry-  podano: " + numberGiven + " a powinna 1, 2, lub 4- spróbuj jeszcze raz!");
        //todo opisać instrukcje co jest co?
        this.numberGiven = numberGiven;}

    public float getContractLevel() {
        return numberOfpoints;
    }
}

