package pl.waw.rubach.points.exceptions;

import static pl.waw.rubach.points.OneDeal.MAXCONTRACTLEVEL;
import static pl.waw.rubach.points.OneDeal.MINCONTRACTLEVEL;

public class InvalidContractLevelException extends BridgeException {


    private int contractLevel;


    public InvalidContractLevelException(int contractLevel) {
        super( contractLevel >MAXCONTRACTLEVEL | contractLevel <MAXCONTRACTLEVEL ?  "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony":
                "Nie ma takiego poziomu gry, podano: " + contractLevel + " a powinno być między "+ MINCONTRACTLEVEL + " a "+ MAXCONTRACTLEVEL +" (szlem) - spróbuj jeszcze raz");
        this.contractLevel = contractLevel;
    }

    public int getContractLevel() {
        return contractLevel;
    }
}

