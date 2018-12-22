package pl.waw.rubach.points.exceptions;

public class InvalidContractSuitException extends BridgeException {

    private String contractSuit;//nie da się tego powtórzyć ze stringiem 


    public InvalidContractSuitException() {
        super("Nie ma takiego koloru - wpisz jeszcze raz.");
    }


}

