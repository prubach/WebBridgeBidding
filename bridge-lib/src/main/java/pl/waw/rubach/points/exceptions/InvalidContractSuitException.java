package pl.waw.rubach.points.exceptions;

public class InvalidContractSuitException extends BridgeException {

    private String contractSuit;


    public InvalidContractSuitException() {
        super("Żle podany kolor kart - ustal jeszcze raz.");

    }

    public InvalidContractSuitException(String inputValue) {
        super("Nie ma takiego koloru: podałeś "+ inputValue +" - wpisz jeszcze raz.");

        this.contractSuit = inputValue;
    }

    public String getContractSuit() {
        return contractSuit;
    }
}

