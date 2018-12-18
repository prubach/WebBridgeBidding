package pl.waw.rubach.points.exceptions;

public class InvalidParameterException extends BridgeException {

     private float numberOfpoints =20;
     private int numberGiven;
     private boolean fitWe;
     private boolean fitThey;

    public InvalidParameterException() {
        super("Błąd różnicy punktów - mniejsza od zera- to jakiś błąd programu");
        //second option of message: "Róznica punktow nie może być ujemna - bład programu chyba"
    }



    public InvalidParameterException(String message) {
        super(message);

    }

    public InvalidParameterException(float numberOfpoints, boolean fitWe, boolean fitThey) {
        super("Nie mogą obie pary mieć fitu w starszym kolorze  przy 20 pkt -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. " +
                "Podano: " + numberOfpoints + " punktów oraz fity: My:"+fitWe+"  Oni:"+fitThey+" - popraw zaznaczenie fitów lub punktów");
        if (!fitWe || !fitThey || numberOfpoints != 20) { //nie jestem pewna warunku bo idea odwracała
            this.numberOfpoints = numberOfpoints;  //powinien alertować że źle wyjątek działa!!
        }

    }

    public InvalidParameterException(int numberGiven) {
        super("Nieprawidłowy parametr gry-  podano: " + numberGiven + " a powinna 1, 2, lub 4- spróbuj jeszcze raz!");
        //todo opisać instrukcje co jest co?
        this.numberGiven = numberGiven;}


    public float getContractLevel() {
        return numberOfpoints;
    }
}

