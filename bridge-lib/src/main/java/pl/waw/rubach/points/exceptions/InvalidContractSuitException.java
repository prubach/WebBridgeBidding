package pl.waw.rubach.points.exceptions;

public class InvalidContractSuitException extends BridgeException {

    private String contractSuit;//nie da się tego powtórzyć ze stringiem 


    public InvalidContractSuitException(String message) {
        super(message);
    }

  /*  InvalidContractSuitException(String contractSuit) {
        super("Nie ma takiego koloru, podano: " + contractSuit + " - spróbuj jeszcze raz");
        this.contractSuit = contractSuit;
    }

    public int getContractLevel() {
        return contractLevel;
    }*/
}

