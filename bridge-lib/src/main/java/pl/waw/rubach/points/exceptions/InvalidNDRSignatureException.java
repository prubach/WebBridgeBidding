package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.*;

public class InvalidNDRSignatureException extends BridgeException {
    private int nDRSign;


    public InvalidNDRSignatureException(int nRDsign) {
        super( (nRDsign ==IS_UNDOUBLE || nRDsign==IS_DOUBLE || nRDsign==IS_REDOUBLE) ?
                "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony":
                "Są tylko trzy opcje (1 = bez kontry, 2 - kontra, 4 - rekontra. Podałeś : " + nRDsign + " - spróbuj jeszcze raz");

    this.nDRSign =nRDsign;}

    public int getnDRSign() {
        return nDRSign;
    }
}
