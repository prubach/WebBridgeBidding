package pl.waw.rubach.points.bridgeExeption;

public class InvalidContractLevelException extends BridgeException {

  //  private int mincontractLevel = 1;  //chciałam żeby mogło stąd brać co ma sprawdzać ale chyba to nie tak...
  //  private int maxcontractlevel = 7;
    private int contractLevel =0;//pyt dlaczego wpisałeś zero -przecież zero nie może być? no i private (bo getter) - a jeszcze mówiłeś że można tu coś zrobić zeby obejść - czyli tak sztuka dla sztuki może np podstawić jakąś przykładową wartość 

    //odp nie - nie ma sensu warunek ma być na zewnątrz tu mogą być jakieś parametry - czy mogłoby tu sprawdzać warunek a nie w kodzie?

    public InvalidContractLevelException(String message) {
        super(message);
        //"Nieprawidłowo podana poziom kontraktu - spróbuj jeszcze raz!"
    }

    public InvalidContractLevelException(int contractLevel) {
        super("Nie ma takiego poziomu gry, podano: " + contractLevel + " a powinno być między 1 a 7 (szlem) - spróbuj jeszcze raz");
        this.contractLevel = contractLevel;
    }

    public int getContractLevel() {
        return contractLevel;
    }
}

