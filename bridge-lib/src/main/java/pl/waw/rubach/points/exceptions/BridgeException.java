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
   * General constructor.
   * @param message is string message to print with the exception
   */
  public BridgeException(String message) {
    super(message);
  }

  /**
   * General bridge exception. No special cases...
   */
  public BridgeException() {
    super("Niezgodność z zasadami brydża. ");
  }


}
