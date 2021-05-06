package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.*;

/**
 * Exception of invalid undoubted/ double/ redouble signature
 */
public class InvalidNDRSignatureException extends BridgeException {

    private final int nDRSign;


    public InvalidNDRSignatureException(int nRDsign) {
        super((nRDsign == IS_UNDOUBTED || nRDsign == IS_DOUBLE || nRDsign == IS_REDOUBLE)
                ? WRONG_EXCEPTION_CASE_MESSAGE :
                "Są tylko trzy opcje (1 = bez kontry, 2 - kontra, 4 - rekontra. Podałeś "
                        + nRDsign + " - spróbuj jeszcze raz");

        this.nDRSign = nRDsign;
    }

    public int getNDRSignature() {
        return nDRSign;
    }
}
