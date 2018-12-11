package pl.waw.rubach.points;

public class InvalidContractLevelException extends BridgeException {

    int contractLevel = 0;

    public InvalidContractLevelException(String message) {
        super(message);
    }

    public InvalidContractLevelException(int contractLevel) {
        super("Nie ma takiego poziomu gry spróbuj jeszcze raz, podałeś: " + contractLevel + " a powinno być...");
        this.contractLevel = contractLevel;
    }

    public int getContractLevel() {
        return contractLevel;
    }
}

