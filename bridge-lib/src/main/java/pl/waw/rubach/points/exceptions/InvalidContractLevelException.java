package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.AbstractOneDeal.MAXCONTRACTLEVEL;
import static pl.waw.rubach.points.AbstractOneDeal.MINCONTRACTLEVEL;

/**
 * Exception of invalid contract level.
 */
public class InvalidContractLevelException extends BridgeException {


  private final int contractLevel;

  /**
   * Exception of invalid contract level.
   */
  public InvalidContractLevelException(int contractLevel) {
    super(contractLevel > MAXCONTRACTLEVEL | contractLevel < MINCONTRACTLEVEL
        ? String.format(ExceptionMessages.INVALID_CONTRACT_LEVEL_MESSAGE,contractLevel)
        : WRONG_EXCEPTION_CASE_MESSAGE);
    this.contractLevel = contractLevel;
  }

  public int getContractLevel() {
    return contractLevel;
  }
}

