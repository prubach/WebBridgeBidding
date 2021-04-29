package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.*;

/**
 * Exeption of invalid undouble/ double/ redouble signature
 */
public class InvalidNDRSignatureException extends BridgeException {

    private final int nDRSign;


    public InvalidNDRSignatureException(int nRDsign) {
        super((nRDsign == IS_UNDOUBTED || nRDsign == IS_DOUBLE || nRDsign == IS_REDOUBLE)
                ? wrongExceptionCaseMessage :
                "Są tylko trzy opcje (1 = bez kontry, 2 - kontra, 4 - rekontra. Podałeś : "
                        + nRDsign + " - spróbuj jeszcze raz");

        this.nDRSign = nRDsign;
    }

    public int getnDRSign() {
        return nDRSign;
    }
}
