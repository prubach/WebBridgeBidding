package pl.waw.rubach.points;

public class PointsForContract {

    /**
     * Points for contract calculated with modyfy bridge rulles according to 4 play scoring
     */
    private int calculatedPointsForContract = 0;

    /**
     * Short descirption only with contract main parameter
     */
    private String shortDescription = " Kontrakt jest: ";
    /**
     * Long description explain how points are calulating
     */
    private String description = "Opis punktów: ";
//todo odp tak choć ostrożnie  pomysł - czy zamienić boolean double/ reooube na jedną zmienna int na przykład 0-nic, 1-kontra, 2- rekontra albo lepiej 1-nic, 2- kontra 4 rekontra (będzie mógł być może mnożnik)


    public PointsForContract(int contractLevel, int numberOfTrickTaken, String contractSuit, boolean isContractDouble, boolean isContractRedouble, boolean auctionAssumptionPlaingPair)
            throws InvalidContractLevelException, InvalidContractSuitException {

        if (contractLevel > 7 || contractLevel < 1)
            throw new InvalidContractLevelException(contractLevel); // odp TAK - wyrzuca !!  czy jak jest wyjątek to już nie liczy dalej - tak bym chciała ...

        int numberOfTrickTakenAbove6 = numberOfTrickTaken - 6;

        if (isContractRedouble) isContractDouble = false;

        if (isContractDouble)
            shortDescription = shortDescription + contractLevel + contractSuit + " z kontrą, zebrano " + numberOfTrickTaken + " lew.";
        else if (isContractRedouble)
            shortDescription = shortDescription + contractLevel + contractSuit + " z rekontrą, zebrano " + numberOfTrickTaken + " lew.";
        else shortDescription = shortDescription + contractLevel + contractSuit + ", zebrano " + numberOfTrickTaken + " lew.";


        this.calculatedPointsForContract = 0;
        /**
         * overtricks points dependfing of if is double/redouble and v
         */
        int aditionalTricksPoints = 0;

        if (numberOfTrickTakenAbove6 >= contractLevel) {
            calculatedPointsForContract = getMainPoints(contractLevel, contractSuit);
            if (isContractDouble) calculatedPointsForContract = calculatedPointsForContract * 2;
            if (isContractRedouble) calculatedPointsForContract = calculatedPointsForContract * 4;

            description = description + "punkty za ugraną grę:" + contractLevel + " " + contractSuit + " to: " + calculatedPointsForContract + "pkt.   + ";
        }
        if (numberOfTrickTakenAbove6 < contractLevel) {
            //Undertrick points -
            // mniej lew niż zalicytowano -wpadki z rekontrą x2 (sprawdzone)
            int undertricks = contractLevel - numberOfTrickTakenAbove6;
            if (undertricks == 1) description = description + " punkty z " + undertricks + " wpadki. ";
            else description = description + " punkty z " + undertricks + " wpadek. ";

            if (!auctionAssumptionPlaingPair && !isContractDouble && !isContractRedouble)
                calculatedPointsForContract = -undertricks * 50;   //bez kontry przed partią 50
            if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && undertricks == 1)
                calculatedPointsForContract = -undertricks * 100;  //z kontrą przed partią pierwsza za 100
            else if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && undertricks == 2)
                calculatedPointsForContract = -undertricks * 200 + 100;  //z kontrą przed partią druga i trzecia za 200
            else if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && undertricks == 3)
                calculatedPointsForContract = -undertricks * 200 + 100;
            else if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && undertricks >= 4)
                calculatedPointsForContract = -undertricks * 300 + 400;    //z kontrą przed partią czwarta i kolejne za 300?

            if (auctionAssumptionPlaingPair && !isContractDouble && !isContractRedouble)
                calculatedPointsForContract = -undertricks * 100;  //bez kontry po partii 100
            if (auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && undertricks == 1)
                calculatedPointsForContract = -undertricks * 200;  // z kontrą po partii pierwsza za 200
            else if (auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && undertricks >= 2)
                calculatedPointsForContract = -undertricks * 300 + 100;  //z kontrą po partii kolejne za 300

            if (isContractRedouble) calculatedPointsForContract = calculatedPointsForContract * 2;

        }
        calculatedPointsForContract = calculatedPointsForContract
                + getOvertrickPoints(numberOfTrickTakenAbove6, contractLevel, auctionAssumptionPlaingPair, isContractDouble, isContractRedouble, contractSuit)
                + getBonusDoubleRedouble(contractLevel, numberOfTrickTakenAbove6, isContractDouble, isContractRedouble)
                + getGamePartGameBonus(calculatedPointsForContract, contractLevel, numberOfTrickTakenAbove6, auctionAssumptionPlaingPair)
                + getSlamPremiaPoints(contractLevel, numberOfTrickTakenAbove6, auctionAssumptionPlaingPair)
                + aditionalTricksPoints;
    }

    /**
     * function calculate points for declared tricks which are taken (and also overtricks without double/ redoubld
     * @param contractLevel
     * @param contractSuit
     * @return number of points
     * @throws InvalidContractSuitException
     */
    private int getMainPoints(int contractLevel, String contractSuit) throws InvalidContractSuitException {

        switch (contractSuit) {
            case "s":
            case "S":
            case "h":
            case "H":
                return contractLevel * 30;

            case "d":
            case "D":
            case "c":
            case "C":
                return contractLevel * 20;

            case "n":
            case "N":
            case "nt":
            case "NT":
                return contractLevel * 30 + 10;

            default:
                throw new InvalidContractSuitException("Nie ma takiego koloru wpisz jeszcze raz.");
        }
    }

    private int getOvertrickPoints(int numberOfTrickTakenAbove6, int levelOfGame, boolean asumption, boolean doubleGame, boolean redoubleGame, String gameColor) throws InvalidContractSuitException {
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
        return aditionalTricksPoints;
    }

    private int getBonusDoubleRedouble(int levelOfGame, int numberOfTrickTakenAbove6, boolean doubleGame, boolean redoubleGame) {
        if ((doubleGame) && levelOfGame <= numberOfTrickTakenAbove6) {
            description = description + " kara za nieudaną kontrę, ";
            return 50;

        } else if ((redoubleGame) && levelOfGame <= numberOfTrickTakenAbove6) {
            description = description + " kara za nieudaną rekontrę, ";
            return 100;
        } else return 0;
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
