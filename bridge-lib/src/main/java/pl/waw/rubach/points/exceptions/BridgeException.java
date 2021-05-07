package pl.waw.rubach.points.exceptions;

/**
 * Parent class to all bridge exceptions.
 */
public class BridgeException extends Exception {
  /**
   * String to mention wrong use of exception.
   */
  protected static final String WRONG_EXCEPTION_CASE_MESSAGE =
      "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony";

  /**
   * General constructor to be used by extended class.
   *
   * @param message is string message to print with the exception
   */
  protected BridgeException(final String message) {
    super(message);
  }

  /**
   * General bridge exception. No special cases...
   */
  public BridgeException() {
    super(ExceptionMessages.GENERAL_BRIDGE_EXCEPTION_CASE_MESSAGE);
  }


}
