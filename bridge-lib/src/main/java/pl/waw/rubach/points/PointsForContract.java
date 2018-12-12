package pl.waw.rubach.points;

import java.util.NoSuchElementException;

public class PointsForContract {

    private int calculatedPointsForContract = 0;


    private String shortDescription =" Kontrakt jest: ";
    private String description = "Opis punktów: ";
//todo odp tak choć ostrożnie  pomysł - czy zamienić boolean double/ reooube na jedną zmienna int na przykład 0-nic, 1-kontra, 2- rekontra albo lepiej 1-nic, 2- kontra 4 rekontra (będzie mógł być może mnożnik)

    public PointsForContract(int gameLevel, int numberOfTrickTaken, String gameSuit, boolean doubleGame, boolean redoubleGame, boolean asumption)
            throws NoSuchElementException, InvalidContractLevelException {

        if (gameLevel > 7 || gameLevel < 1)
            throw new InvalidContractLevelException(gameLevel); // odp TAK - wyrzuca !!  czy jak jest wyjątek to już nie liczy dalej - tak bym chciała ...

        int numberOfTrickTakenAbove6 = numberOfTrickTaken -6;

        if (redoubleGame) doubleGame = false;

        if(doubleGame) shortDescription = shortDescription + gameLevel + gameSuit + " z kontrą, zebrano "+ numberOfTrickTaken + " lew.";
        else if(redoubleGame) shortDescription = shortDescription + gameLevel + gameSuit + " z rekontrą, zebrano "+ numberOfTrickTaken + " lew.";
        else shortDescription = shortDescription + gameLevel + gameSuit + ", zebrano "+ numberOfTrickTaken + " lew.";


        this.calculatedPointsForContract = 0;
        int aditionalTricksPoints = 0;

        if (numberOfTrickTakenAbove6 >= gameLevel) {
            calculatedPointsForContract = getMainPoints(gameLevel, gameSuit);
            if (doubleGame) calculatedPointsForContract = calculatedPointsForContract * 2;
            if (redoubleGame) calculatedPointsForContract = calculatedPointsForContract * 4;

            description = description + "punkty za ugraną grę:" + gameLevel + " " + gameSuit + " to: "+ calculatedPointsForContract + "pkt. ,  + ";
        }
        if (numberOfTrickTakenAbove6 < gameLevel) {
            //Undertrick points -
            // mniej lew niż zalicytowano -wpadki z rekontrą x2 (sprawdzone)
            int undertricks = gameLevel - numberOfTrickTakenAbove6;
            if (undertricks == 1) description = description + " punkty z " + undertricks + " wpadki. ";
            else description = description + " punkty z " + undertricks + " wpadek. ";

            if (!asumption && !doubleGame && !redoubleGame)
                calculatedPointsForContract = -undertricks * 50;   //bez kontry przed partią 50
            if (!asumption && (doubleGame || redoubleGame) && undertricks == 1)
                calculatedPointsForContract = -undertricks * 100;  //z kontrą przed partią pierwsza za 100
            else if (!asumption && (doubleGame || redoubleGame) && undertricks == 2)
                calculatedPointsForContract = -undertricks * 200 + 100;  //z kontrą przed partią druga i trzecia za 200
            else if (!asumption && (doubleGame || redoubleGame) && undertricks == 3)
                calculatedPointsForContract = -undertricks * 200 + 100;
            else if (!asumption && (doubleGame || redoubleGame) && undertricks >= 4)
                calculatedPointsForContract = -undertricks * 300 + 400;    //z kontrą przed partią czwarta i kolejne za 300?

            if (asumption && !doubleGame && !redoubleGame)
                calculatedPointsForContract = -undertricks * 100;  //bez kontry po partii 100
            if (asumption && (doubleGame || redoubleGame) && undertricks == 1)
                calculatedPointsForContract = -undertricks * 200;  // z kontrą po partii pierwsza za 200
            else if (asumption && (doubleGame || redoubleGame) && undertricks >= 2)
                calculatedPointsForContract = -undertricks * 300 + 100;  //z kontrą po partii kolejne za 300

            if (redoubleGame) calculatedPointsForContract = calculatedPointsForContract * 2;

        }
             calculatedPointsForContract = calculatedPointsForContract
                + getOvertrickPoints(numberOfTrickTakenAbove6,gameLevel,asumption,doubleGame,redoubleGame,gameSuit)
                + getBonusDoubleRedouble(gameLevel, numberOfTrickTakenAbove6, doubleGame, redoubleGame)
                + getGamePartGameBonus(calculatedPointsForContract, gameLevel, numberOfTrickTakenAbove6, asumption)
                + getSlamPremiaPoints(gameLevel, numberOfTrickTakenAbove6, asumption)
                + aditionalTricksPoints;
    }



    private int getMainPoints(int levelOfGame, String gameColor) throws NoSuchElementException{

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
                //pyt tutaj jakiś jeszcze gotowy wyjątek nie wiem skąd i po co ale co dziwniejsze wyżej nie ma catch ani nic?
        }
    }

    private int getOvertrickPoints(int numberOfTrickTakenAbove6,int levelOfGame, boolean asumption, boolean doubleGame, boolean redoubleGame, String gameColor) {
        int aditionalTricksPoints = 0;
        if (numberOfTrickTakenAbove6 > levelOfGame) {


            // Overtrick points
            if (!doubleGame && !redoubleGame) {
                aditionalTricksPoints = getMainPoints(numberOfTrickTakenAbove6 - levelOfGame, gameColor);  //bez kontry i rekontry - tak samo jak lewa
                if (gameColor.equals("nt") || gameColor.equals("n") || gameColor.equals("N") || gameColor.equals("NT"))
                    aditionalTricksPoints = aditionalTricksPoints - 10; //przy bez atu pierwsza nadróbka za 30 a nie 40!
            }
            if (doubleGame && !asumption)
                aditionalTricksPoints = (numberOfTrickTakenAbove6 - levelOfGame) * 100;   //z kontrą przed partią - za 100
            if (doubleGame && asumption)
                aditionalTricksPoints = (numberOfTrickTakenAbove6 - levelOfGame) * 200;    // z kontrą po partii za 200

            if (redoubleGame && !asumption)
                aditionalTricksPoints = (numberOfTrickTakenAbove6 - levelOfGame) * 200;     // z rekontrą przed partią za 200
            if (redoubleGame && asumption)
                aditionalTricksPoints = (numberOfTrickTakenAbove6 - levelOfGame) * 400;     //z rekontrą po partii za 400

            description = description + " punkty z " + (numberOfTrickTakenAbove6 - levelOfGame) + " nadróbek to: " + aditionalTricksPoints + "pkt, + ";
        }
    return aditionalTricksPoints;}

    private int getBonusDoubleRedouble(int levelOfGame, int numberOfTrickTakenAbove6, boolean doubleGame, boolean redoubleGame) {
        if ((doubleGame ) && levelOfGame <= numberOfTrickTakenAbove6) {
            description = description + " kara za nieudaną kontrę, ";
            return 50;

        } else if ((redoubleGame)  && levelOfGame <= numberOfTrickTakenAbove6){
            description = description + " kara za nieudaną rekontrę, ";
            return 100;
        }
        else return 0;
    }

    private int getGamePartGameBonus(int calculatedPointsForContract, int levelOfGame, int numberOfTrickTakenAbove6, boolean assumption) {

        if (calculatedPointsForContract >= 100 && numberOfTrickTakenAbove6 >= levelOfGame) {
            description = description + " punkty za ugraną końcówkę.";
            return (assumption) ? 500 : 300;
        } else if (calculatedPointsForContract > 0) {
            description = description + " 50 pkt za częściówkę.";
            return 50;
        } else return 0;
    }

    private int getSlamPremiaPoints(int levelOfGame, int numberOfTrickTakenAbove6, boolean assumption) {

        if (numberOfTrickTakenAbove6 >= levelOfGame) {
            if (levelOfGame == 6) return (assumption) ? 750 : 500;
            else if (levelOfGame == 7) return (assumption) ? 1500 : 1000;
        }
        return 0;
    }



    //getter
    public int getCalculatedPointsForContract() {
        return calculatedPointsForContract;
    }

    public String getDescription() {
        return description;
    }
    public String getShortDescription() {
        return shortDescription;
    }
}
