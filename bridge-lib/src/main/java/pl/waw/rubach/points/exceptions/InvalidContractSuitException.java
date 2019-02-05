package pl.waw.rubach.points.exceptions;

public class InvalidContractSuitException extends BridgeException {

    private String contractSuit;

    public static final String SUITSPADES = "S";
    public static final String SUITSPADES2 = "s";
    public static final String SUITSHEART = "H";
    public static final String SUITSHEART2 = "h";
    public static final String SUITSCLUBS = "C";
    public static final String SUITSCLUBS2 = "c";
    public static final String SUITSDIAMONTS = "D";
    public static final String SUITSDIAMONTS2 = "d";
    public static final String SUITSNOTRUMPH = "NT";
    public static final String SUITSNOTRUMPH2 = "nt";
    public static final String SUITSNOTRUMPH3 = "N";
    public static final String SUITSNOTRUMPH4 = "n";

    public static final String[] SUITS = {"S","s","H","h","D","d","C","c","NT","nt","N","n"};

    public InvalidContractSuitException() {
        super("Żle podany kolor kart - ustal jeszcze raz.");

    }

    public InvalidContractSuitException(String inputValue) {
//        super(inputValue.equals(SUITS) ? "Wyjątek ustawiony w złym przypadku - warunek nie jest spełniony":"Nie ma takiego koloru: podałeś "+ inputValue +" - wpisz jeszcze raz.");
        super("Nie ma takiego koloru: podałeś "+ inputValue +" - wpisz jeszcze raz.");

        this.contractSuit = inputValue;
    }

    public String getContractSuit() {
        return contractSuit;
    }
}

