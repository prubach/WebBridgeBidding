package pl.waw.rubach.points.exceptions;

public class InvalidContractLevelException extends BridgeException {

  //  private int mincontractLevel = 1;  //chciałam żeby mogło stąd brać co ma sprawdzać ale chyba to nie tak...
  //  private int maxcontractlevel = 7;
    private int contractLevel; //pyt dlaczego wpisałeś zero -przecież zero nie może być? no i private (bo getter) - a jeszcze mówiłeś że można tu coś zrobić zeby obejść - czyli tak sztuka dla sztuki może np podstawić jakąś przykładową wartość 
    //odp nie ma znaczenia czy jest zero czy nie, bo int ma i tak domyślną wartość 0 jeśli się nic nie ustawi.
    //nie wiem o jakie obejście chodzi. Getter ew potrzebny, żeby z samego wyjątku odczytać jaki poziom gry był podany.

    //odp nie - nie ma sensu warunek ma być na zewnątrz tu mogą być jakieś parametry - czy mogłoby tu sprawdzać warunek a nie w kodzie?



    public InvalidContractLevelException(int contractLevel) {
        super("Nie ma takiego poziomu gry, podano: " + contractLevel + " a powinno być między 1 a 7 (szlem) - spróbuj jeszcze raz");
        this.contractLevel = contractLevel;
    }

    public int getContractLevel() {
        return contractLevel;
    }
}

