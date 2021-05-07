package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.MAXCONTRACTLEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.MINCONTRACTLEVEL;

/**
 * Exception of invalid contract level.
 */
public class InvalidContractLevelException extends BridgeException {

  /**
   * Contract level which trigger this exception.
   */
  private final int contractLevel;

  /**
   * Exception of invalid contract level.
   *
   * @param level is  contract level
   */
  public InvalidContractLevelException(final int level) {
    super(level > MAXCONTRACTLEVEL | level < MINCONTRACTLEVEL
        ? String.format(ExceptionMessages.INVALID_CONTRACT_LEVEL_MESSAGE,
        level)
        : WRONG_EXCEPTION_CASE_MESSAGE);
    this.contractLevel = level;
  }

  /**
   * Getter to have parameter trigger this exception.
   *
   * @return contract level
   */
  public int getContractLevel() {
    return contractLevel;
  }
}

