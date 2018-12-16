package pl.waw.rubach.points.bridgeExeption;

public class NotPosibleBothFitAnd20Exception extends BridgeException {

     private float numberOfpoints =20;
     private boolean fitWe;
     private boolean fitThey;

    public NotPosibleBothFitAnd20Exception(String message) {
        super(message);

    }

    public NotPosibleBothFitAnd20Exception(float numberOfpoints, boolean fitWe, boolean fitThey) {
        super("Nie mogą obie pary mieć fitu w starszym kolorze  przy 20 pkt -bo wtedy dla pików zapisuje się fit dla kierów brak fitu. " +
                "Podano: " + numberOfpoints + " punktów oraz fity: My:"+fitWe+"  Oni:"+fitThey+" - popraw zaznaczenie fitów lub punktów");
        if (!fitWe || !fitThey || numberOfpoints != 20) { //nie jestem pewna warunku bo idea odwracała
            this.numberOfpoints = numberOfpoints;  //powinien alertować że źle wyjątek działa!!
        }

    }

    public float getContractLevel() {
        return numberOfpoints;
    }
}

