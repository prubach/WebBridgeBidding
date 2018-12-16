package pl.waw.rubach.points;

import pl.waw.rubach.points.bridgeExeption.*;

//This was  PointsForContract(...)
public class DuplicateBridgeScoring {

    public static final int IS_DOUBLE = 2;
    public static final int IS_REDOUBLE = 4;
    public static final int IS_UNDOUBLE = 1;
    //CONTRACT PARAMETER - arguments of constructor
    /**
     * The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     */
    private int contractLevel;
    /**
     * Cards suits [denomination or strain] that denotes the proposed trump suit or notrump.
     * Thus, there are five denominations – notrump, spades, hearts, diamonds and clubs.
     */
    private String contractSuit;
    /**
     * Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     */
    private int nDRSignature;
    /**
     *
     * Auction Assumption for Plaing Pair means if pair is  vulnerable or not vulnerable
     */
    private boolean auctionAssumptionDeclarer;

    /**
     * Number of tricks taken in game  by declarer - the player whose bid establishes the suit of the contract
     * and who must therefore play both their own hand and the exposed hand of the dummy.
     * (opponent takes 13 - numberOfTrickTakenByDeclarer)
     */
    private int numberOfTrickTakenByDeclarer;


    /**
     * The number of tricks above six (the book) that are taken by declarer.
     */
    private int oddTricks;
    /**
     * Indicates if contract is made  which means  take at least as many tricks as a contract calls for.
     */
    private boolean made;

    /**
     * shortDescription -  only with contract main parameter
     */
    private String shortDescription = " Kontrakt jest: ";
    /**
     * description - Long description explain how points are calculating
     */
    private String description = "Punkty za kontrakt: ";

    /**
     * Points for contract calculated with modify bridge rules according to 4 play scoring
     */
    private int calculatedPointsForContract = 0; //pyt tu powinno być zerowanie czy w konstruktorze? jest tu i tu :)


    //This was :  PointsForContract(...)
    public DuplicateBridgeScoring(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble, boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
            throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, InvalidParameterException {
        this(contractLevel, contractSuit, isContractRedouble ? 4 : (isContractDouble ? 2 : 1), auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }

    public DuplicateBridgeScoring(int contractLevel, String contractSuit, int nDRSignature, boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
            throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, InvalidParameterException {
        //this(contractLevel, numberOfTrickTakenByDeclarer, contractSuit, (nDRSignature == 2), nDRSignature == 4, auctionAssumptionDeclarer);

        //checking if contractLevel is correct
        if (contractLevel > 7 || contractLevel < 1)
            throw new InvalidContractLevelException(contractLevel);
        this.contractLevel = contractLevel;
        this.contractSuit = contractSuit;

        //checking if number of tricks is corect
        if (numberOfTrickTakenByDeclarer > 13 || numberOfTrickTakenByDeclarer < 0)
            throw new InvalidNumberOfTrickTakenException(numberOfTrickTakenByDeclarer);
        this.numberOfTrickTakenByDeclarer = numberOfTrickTakenByDeclarer;

        //checking if double/ redouble or undouble signature  is corect
        if (!(nDRSignature == 1 || nDRSignature == 2 || nDRSignature == 4))
            throw new InvalidParameterException(nDRSignature);
        this.nDRSignature = nDRSignature;

        this.auctionAssumptionDeclarer = auctionAssumptionDeclarer;
        this.shortDescription = getContractDescription();

        //  Begin of calculation
        this.calculatedPointsForContract = 0;       // points equal zero at the beginging of calculation
        this.oddTricks = numberOfTrickTakenByDeclarer - 6;    //trics above 6
        this.made = oddTricks >= contractLevel;  //condition if game is made or not

        if (made) {
            calculatedPointsForContract = getContractPoints(contractLevel) * nDRSignature;
              description = description + "Za ugraną grę:" + contractLevel + " " + contractSuit + " to: " + calculatedPointsForContract + "pkt. ";
        } else {
            calculatedPointsForContract = getPenaltyPoints();
        }

        calculatedPointsForContract = calculatedPointsForContract
                + getGamePartGameBonus(calculatedPointsForContract)
                + getOvertrickPoints()
                + getDoubleRedoubleBonus()
                + getSlamsBonusPoints();
    }


    /**
     * Contract points, assigned to each odd trick bid and made:
     * Their values depend on the suit (or notrump) and whether the contract is doubled or redoubled; they are not affected by vulnerability.
     * Tricks won beyond that necessary to fulfill the contract are referred to as overtricks and their scoring points are accounted
     * for separately because their values are dependent upon declarer's vulnerability.
     *
     * @param numberOfTricksForWhichArePointsCalculated The number of tricks for which points are calculated
     *                                                  in normal calse that is contract level = (when added to the book of six tricks) a bid or contract states will be taken.
     *                                                  but could be number of overtricks!!!
     * @return Scorring  for this part of contract
     * @throws InvalidContractSuitException if contract suits is not correct
     */
    private int getContractPoints(int numberOfTricksForWhichArePointsCalculated) throws InvalidContractSuitException {

        switch (contractSuit) {
            case "s":
            case "S":
            case "h":
            case "H":
                return numberOfTricksForWhichArePointsCalculated * 30;

            case "d":
            case "D":
            case "c":
            case "C":
                return numberOfTricksForWhichArePointsCalculated * 20;

            case "n":
            case "N":
            case "nt":
            case "NT":
                return numberOfTricksForWhichArePointsCalculated * 30 + 10;

            default:
                throw new InvalidContractSuitException("Nie ma takiego koloru wpisz jeszcze raz.");
        }
    }

    /**
     * When declarer makes overtricks, their score value depends upon the contract denomination, declarer's vulnerability and whether or not the contract is undoubled, doubled or redoubled.
     * In an undoubled contract each overtrick earns the same as in contract points (30 for notrump and major suit contracts, 20 for minor suit contracts);
     * values increase significantly when the contract has been doubled or redoubled, especially when vulnerable.
     * @return scorring for overtricks
     * @throws InvalidContractSuitException if contract suits is not correct
     */

    private int getOvertrickPoints() throws InvalidContractSuitException {
        // getOvertrickPoints(contractLevel,oddTricks,auctionAssumptionDeclarer,s==2,s==4,contractSuit);
        int overtricksPoints = 0;
        int overtricks = oddTricks -contractLevel;
        if (oddTricks > contractLevel) {

            //  if (!isContractDouble && !isContractRedouble) {
            if (nDRSignature == 1) {
                overtricksPoints = getContractPoints(overtricks);  //bez kontry i rekontry - tak samo jak lewa
                if (contractSuit.equals("nt") || contractSuit.equals("n") || contractSuit.equals("N") || contractSuit.equals("NT"))
                    overtricksPoints = overtricksPoints - 10; //przy bez atu pierwsza nadróbka za 30 a nie 40!
            }
            //if (isContractDouble && !auctionAssumptionDeclarer)
            if (nDRSignature == IS_DOUBLE && !auctionAssumptionDeclarer)
                overtricksPoints = overtricks * 100;   //z kontrą przed partią - za 100
            //if (isContractDouble && auctionAssumptionDeclarer)
            if (nDRSignature == IS_DOUBLE && auctionAssumptionDeclarer)
                overtricksPoints = overtricks * 200;    // z kontrą po partii za 200

            //if (isContractRedouble && !auctionAssumptionDeclarer)
            if (nDRSignature == IS_REDOUBLE && !auctionAssumptionDeclarer)
                overtricksPoints = overtricks * 200;     // z rekontrą przed partią za 200
            //if (isContractRedouble && auctionAssumptionDeclarer)
            if (nDRSignature == IS_REDOUBLE && auctionAssumptionDeclarer)
                overtricksPoints = overtricks * 400;     //z rekontrą po partii za 400

            description = description + "+  punkty z " + overtricks + " nadróbek to: " + overtricksPoints + "pkt,";
        }
        return overtricksPoints;
    }

    /**
     * When a contract is defeated, penalty points are awarded to the defending side. The value of the penalty depends on the number of undertricks,
     * whether the declaring side is vulnerable or not vulnerable and whether the contract was undoubled, doubled or redoubled.    *
     * @return scoring for under Tricks
     */
    private int getPenaltyPoints() {
        int underTricks = contractLevel - oddTricks;
        int underTricksPoints = 0;

        if (!auctionAssumptionDeclarer && !(nDRSignature == IS_DOUBLE) && !(nDRSignature == IS_REDOUBLE))
            underTricksPoints = -underTricks * 50;   //bez kontry przed partią 50
        if (!auctionAssumptionDeclarer && ((nDRSignature == IS_DOUBLE) || (nDRSignature == IS_REDOUBLE)) && underTricks == 1)
            underTricksPoints = -underTricks * 100;  //z kontrą przed partią pierwsza za 100
        else if (!auctionAssumptionDeclarer && ((nDRSignature == IS_DOUBLE) || (nDRSignature == IS_REDOUBLE)) && (underTricks == 2 || underTricks ==3))
            underTricksPoints = -underTricks * 200 + 100;  //z kontrą przed partią druga i trzecia za 200
    //    else if (!auctionAssumptionDeclarer && (isContractDouble || isContractRedouble) && underTricks == 3)
     //       underTricksPoints = -underTricks * 200 + 100;
        else if (!auctionAssumptionDeclarer && ((nDRSignature == IS_DOUBLE) || (nDRSignature == IS_REDOUBLE)) && underTricks >= 4)
            underTricksPoints = -underTricks * 300 + 400;    //z kontrą przed partią czwarta i kolejne za 300?

        if (auctionAssumptionDeclarer && !(nDRSignature == IS_DOUBLE) && !(nDRSignature == IS_REDOUBLE))
            underTricksPoints = -underTricks * 100;  //bez kontry po partii 100
        if (auctionAssumptionDeclarer && ((nDRSignature == IS_DOUBLE) || (nDRSignature == IS_REDOUBLE)) && underTricks == 1)
            underTricksPoints = -underTricks * 200;  // z kontrą po partii pierwsza za 200
        else if (auctionAssumptionDeclarer && ((nDRSignature == IS_DOUBLE) || (nDRSignature == IS_REDOUBLE)) && underTricks >= 2)
            underTricksPoints = -underTricks * 300 + 100;  //z kontrą po partii kolejne za 300

        if ((nDRSignature ==IS_REDOUBLE)) underTricksPoints = underTricksPoints * 2;

        if (underTricks == 1) description = description + " Za nieugraną grę: punkty z " + underTricks + " wpadki: "+ underTricksPoints+ ". ";
        else description = description + "Za nieugraną grę: punkty z " + underTricks + " wpadek: "+ underTricksPoints+ ". ";

        return underTricksPoints;
    }


    /**
     * When a doubled or redoubled contract is made, a bonus is awarded to the declaring side. It is colloquially referred to as a bonus for "insult",
     * meaning that the opponents have insulted the pair by suggesting that the declarer will not make the contract.
     * 50 points are awarded for a doubled contract made, and  100 points are awarded for a redoubled contract made.
     * @return  double or redouble bonus
     */
    private int getDoubleRedoubleBonus() {
        if (nDRSignature == IS_DOUBLE && made) {
            description = description + " + 50 pkt. kara za nieudaną kontrę, ";
            return 50;

        } else if (nDRSignature == IS_REDOUBLE && made) {
            description = description + " + 100 pkt. kara za nieudaną rekontrę, ";
            return 100;
        } else return 0;
    }

    /**
     * In duplicate bridge only, game and partial-game bonuses are awarded at the conclusion of each deal as follows:
     * any partial contract, i.e. one scoring less than 100 contract points, scores a bonus of 50 points, and
     * any game contract, i.e. one scoring 100 or more points, scores a game bonus of 300 if not vulnerable and 500 if vulnerable.
     *
     * @param calculatedPointsForContract - are points for Contract - not overtrics included - so to know is is more then 100.
     * @return game/ part game or slam bonnus
     */
    private int getGamePartGameBonus(int calculatedPointsForContract) {

        if (calculatedPointsForContract >= 100 && made) {
            description = description + " + punkty za ugraną końcówkę (zależnie od założeń).";
            return (auctionAssumptionDeclarer) ? 500 : 300;
        } else if (calculatedPointsForContract > 0) {
            description = description + " + 50 pkt za częściówkę.";
            return 50;
        } else
            return 0;
    }

    /**
     * Bonuses are awarded for all slam contracts bid and made:
     * a small slam, or successful contract to win 12 of 13 tricks, earns a bonus of 500 points if not vulnerable and 750 points if vulnerable;
     * a grand slam, or successful contract to win all 13 tricks, earns a bonus of 1000 points if not vulnerable and 1500 points if vulnerable.
     *
     * @return slam and small slam bonus
     */
    private int getSlamsBonusPoints() {

        if (oddTricks >= contractLevel) {
            //description = description + "+ premia szlemowa/szlemikowa (zależnie od założeń).";
            if (contractLevel == 6) return (auctionAssumptionDeclarer) ? 750 : 500;
            else if (contractLevel == 7) return (auctionAssumptionDeclarer) ? 1500 : 1000;
        }
        return 0;
    }

    /**
     * Description of contract
     * @return short description of contract
     */
    private String getContractDescription() {
        if (nDRSignature == IS_DOUBLE)
            return shortDescription + contractLevel + contractSuit + " z kontrą, zebrano " + numberOfTrickTakenByDeclarer + " lew.";
        else if (nDRSignature == IS_REDOUBLE)
            return shortDescription + contractLevel + contractSuit + " z rekontrą, zebrano " + numberOfTrickTakenByDeclarer + " lew.";
        else
            return shortDescription + contractLevel + contractSuit + ", zebrano " + numberOfTrickTakenByDeclarer + " lew.";
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
