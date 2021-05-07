package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.IS_DOUBLE;
import static pl.waw.rubach.points.AbstractOneDeal.IS_REDOUBLE;
import static pl.waw.rubach.points.AbstractOneDeal.IS_UNDOUBTED;

/**
 * Exception of invalid undoubted/ double/ redouble signature.
 */
public class InvalidNDRSignatureException extends BridgeException {
  /**
   * Number to have is contract normal/double/redouble.
   * If it is:
   * 1 - is normal
   * 2 - is double
   * 4 - is redouble
   * Other value are not possible.
   */
  private final int nDRSign;

  /**
   * Exception checking if state of game is correct.
   *
   * @param nRDsign parameter to describe
   *                if contract is normal/double/redouble is correct.
   */
  public InvalidNDRSignatureException(final int nRDsign) {
    super((nRDsign == IS_UNDOUBTED
        || nRDsign == IS_DOUBLE
        || nRDsign == IS_REDOUBLE)
        ? WRONG_EXCEPTION_CASE_MESSAGE
        : String.format(ExceptionMessages.INVALID_NDRSIGNATURE_MESSAGE,
        nRDsign));

    this.nDRSign = nRDsign;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return contract suit
   */
  public int getNDRSignature() {
    return nDRSign;
  }
}
