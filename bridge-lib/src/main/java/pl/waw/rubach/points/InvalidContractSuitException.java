package pl.waw.rubach.points;

public class InvalidContractSuitException extends BridgeException {

    private int contractLevel =0;//pyt dlaczego wpisałeś zero -przecież zero nie może być? no i private (bo getter) - a jeszcze mówiłeś że można tu coś zrobić zeby obejść - czyli tak sztuka dla sztuki może np podstawić jakąś przykładową wartość 

    //odp nie - nie ma sensu warunek ma być na zewnątrz tu mogą być jakieś parametry - czy mogłoby tu sprawdzać warunek a nie w kodzie?

    public InvalidContractSuitException(String message) {
        super(message);
    }

    InvalidContractSuitException(int contractLevel) {
        super("Nie ma takiego koloru, podano: " + contractLevel + " - spróbuj jeszcze raz");
        this.contractLevel = contractLevel;
    }

    public int getContractLevel() {
        return contractLevel;
    }
}

