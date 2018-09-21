package pl.waw.rubach.points;

import java.util.NoSuchElementException;

public class PointsForContract {

    private int calculatedPointsForContract = 0;


    public PointsForContract(int levelOfGame, int numberOfTrickAboveLevel, String gameColor, boolean doubleGame, boolean redoubleGame, boolean asumption)
            throws NoSuchElementException, InvalidContractLevelException {

        if (redoubleGame) doubleGame = false;

        this.calculatedPointsForContract = 0;
        int aditionalTricksPoints = 0;

        calculatedPointsForContract = getMainPoints(levelOfGame, gameColor);
        if (doubleGame) calculatedPointsForContract = calculatedPointsForContract * 2;
        if (redoubleGame) calculatedPointsForContract = calculatedPointsForContract * 4;

        if (numberOfTrickAboveLevel > levelOfGame) {
            // nadróbki
            if (!doubleGame && !redoubleGame)
                aditionalTricksPoints =  getMainPoints(numberOfTrickAboveLevel-levelOfGame, gameColor);  //bez kontry i rekontry - tak samo jak lewa
            if (doubleGame && !asumption)
                aditionalTricksPoints =(numberOfTrickAboveLevel - levelOfGame) * 100;   //z kontrą przed partią - za 100
            if (doubleGame && asumption)
                aditionalTricksPoints =(numberOfTrickAboveLevel - levelOfGame) * 200;    // z kontrą po partii za 200

            if (redoubleGame && !asumption)
                aditionalTricksPoints = (numberOfTrickAboveLevel - levelOfGame) * 200;     // z rekontrą przed partią za 200
            if (redoubleGame && asumption)
                aditionalTricksPoints =(numberOfTrickAboveLevel - levelOfGame) * 400;     //z rekontrą po partii za 400



        } else if (numberOfTrickAboveLevel < levelOfGame) {   //wpadki - mniej lew niż zalicytowano - nie ma wpadek z rekontrą bo nie wiem jak się liczą????
            int wpadki = levelOfGame - numberOfTrickAboveLevel;

            if (!asumption && !doubleGame) calculatedPointsForContract = -wpadki * 50;   //bez kontry przed partią 50
            if (!asumption && doubleGame && wpadki == 1) calculatedPointsForContract = -wpadki * 100;  //z kontrą przed partią pierwsza za 100
            else if (!asumption && doubleGame && wpadki == 2) calculatedPointsForContract = -wpadki * 200 + 100;  //z kontrą przed partią druga i trzecia za 200
            else if (!asumption && doubleGame && wpadki == 3) calculatedPointsForContract = -wpadki * 200 + 100;
            else if (!asumption && doubleGame && wpadki >= 4) calculatedPointsForContract = -wpadki * 300 + 400;    //z kontrą przed partią czwarta i kolejne za 300?

            if (asumption && !doubleGame) calculatedPointsForContract = -wpadki * 100;  //bez kontry po partii 100
            if (asumption && doubleGame && wpadki == 1) calculatedPointsForContract = -wpadki * 200;  // z kontrą po partii pierwsza za 200
            else if (asumption && doubleGame && wpadki >= 2) calculatedPointsForContract = -wpadki * 300 + 100;  //z kontrą po partii kolejne za 300

            if(redoubleGame) calculatedPointsForContract = calculatedPointsForContract*2;
        }

        calculatedPointsForContract = calculatedPointsForContract
                                + getKaraZaNieudanaKontre(levelOfGame,numberOfTrickAboveLevel,doubleGame, redoubleGame)
                                + getVunerablePoints(calculatedPointsForContract, levelOfGame, numberOfTrickAboveLevel, asumption)
                                + getSlamPremiaPoints(levelOfGame, numberOfTrickAboveLevel, asumption)
                                + aditionalTricksPoints;
    }
    private int getKaraZaNieudanaKontre(int levelOfGame, int numberOfTrickTaken, boolean doubleGame, boolean redoubleGame){
        if ((doubleGame || redoubleGame) && levelOfGame <=numberOfTrickTaken) return 50;
        else return 0;
    }

    private int getMainPoints(int levelOfGame, String gameColor) throws InvalidContractLevelException {

        if(levelOfGame>7 || levelOfGame <1)
            throw new InvalidContractLevelException("Nie ma takiego poziomu gry spróbuj jeszcze raz");

        switch (gameColor) {
            case "s":
            case "S":
            case "h":
            case "H":
                return levelOfGame * 30;

            case "d":
            case "D":
            case "c":
            case "C":
                return levelOfGame * 20;

            case "n":
            case "N":
            case "nt":
            case "NT":
                return levelOfGame * 30 + 10;

            default:
                throw new NoSuchElementException("Nie ma takiego koloru wpisz jeszcze raz.");
        }
    }

    private int getVunerablePoints(int calculatedPointsForContract, int levelOfGame, int numberOfTrickTaken, boolean assumption) {

        if (calculatedPointsForContract >= 100 && numberOfTrickTaken >= levelOfGame) {
            return (assumption) ? 500 : 300;
        } else if(calculatedPointsForContract >0) return  50; //premia za częsciówkę o ile ugrane?
        else return 0;
    }

    private int getSlamPremiaPoints(int levelOfGame, int numberOfTrickTaken, boolean assumption) {

        if (numberOfTrickTaken >= levelOfGame) {
            if (levelOfGame == 6) return (assumption) ? 750 : 500;
            else if (levelOfGame == 7) return (assumption) ? 1500 : 1000;
        }
        return 0;
    }

    //getter
    public int getCalculatedPointsForContract() {
        return calculatedPointsForContract;
    }
}
