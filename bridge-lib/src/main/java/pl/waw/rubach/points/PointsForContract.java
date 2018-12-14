package pl.waw.rubach.points;

public class PointsForContract {

    /**
     * Points for contract calculated with modyfy bridge rulles according to 4 play scoring
     */
    private int calculatedPointsForContract = 0; //pyt tu powinno być zerowanie czy w konstruktorze?

    /**
     * The number of tricks above six (the book) that are taken by declarer.
     */
   private int oddTricks;
    /**
     * Indicates if contract is made  which means  take at least as many tricks as a contract calls for.
     */
    private boolean made;

    /**
     * Short descirption only with contract main parameter
     */
    private String shortDescription = " Kontrakt jest: ";
    /**
     * Long description explain how points are calulating
     */
    private String description = "Opis punktów: ";
//todo odp tak choć ostrożnie  pomysł - czy zamienić boolean double/ reooube na jedną zmienna int na przykład 0-nic, 1-kontra, 2- rekontra albo lepiej 1-nic, 2- kontra 4 rekontra (będzie mógł być może mnożnik)
//pyt czy to tak? Bo jakoś mam wrażenie że ta pierwsza wersja była dużo czytelniejsza a oszczędność dwóch liniek (tam gdzie było mnożenie)...  No i nie chce mi się zmieniać testów wiec ten drugi konstruktor i tak musi zostać ...
// kombinowałam w testach z mnożnikami żeby np samo się generowało z kontrą i rekontrką ale nie da się chyba bo za dużo się zmienia...

    //todo zmienić nazwę może na  DuplicateBridgeScorring (albo DealScorring??? ale jakoś mi się nie podoba)  -a drugi będzie RubberScorring (i będzie się niewiele różnił w bonusach!) albo tu będą dwie opcje - bo różnice są małe)
    //po polsku było by po prostu zapis międzynarodowy (w odróżnieniu od zapisu polskiego

    public PointsForContract(int contractLevel, int numberOfTrickTaken, String contractSuit, boolean isContractDouble, boolean isContractRedouble, boolean auctionAssumptionPlaingPair)
            throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException {
        this(contractLevel,numberOfTrickTaken,contractSuit,isContractRedouble ? 4 : (isContractDouble ? 2 :1),auctionAssumptionPlaingPair);
    }


    public PointsForContract(int contractLevel, int numberOfTrickTaken, String contractSuit, int normalDoubleRedubleSingnature, boolean auctionAssumptionPlaingPair)
            throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException {
        //this(contractLevel, numberOfTrickTaken, contractSuit, (normalDoubleRedubleSingnature == 2), normalDoubleRedubleSingnature == 4, auctionAssumptionPlaingPair);

        boolean isContractDouble = normalDoubleRedubleSingnature==2;
        boolean isContractRedouble = normalDoubleRedubleSingnature==4;

        //checking if contractLevel is correct
        if (contractLevel > 7 || contractLevel < 1)
            throw new InvalidContractLevelException(contractLevel);
        //checking if number of tricks is corect
        if (numberOfTrickTaken > 13 || numberOfTrickTaken < 0)
            throw new InvalidNumberOfTrickTakenException(numberOfTrickTaken);
        //checking if double is false when redouble - not important because it could not be both
        //  if (isContractRedouble) isContractDouble = false;
        this.shortDescription = getContractDescription(contractLevel,numberOfTrickTaken,contractSuit,normalDoubleRedubleSingnature);


        //  Begin of calculation
        this.calculatedPointsForContract = 0;
        this.oddTricks = numberOfTrickTaken - 6;
        this.made = oddTricks >=contractLevel;


        if (made) {
            calculatedPointsForContract = getContractPoints(contractLevel, contractSuit)*normalDoubleRedubleSingnature;
        //    if (isContractDouble) calculatedPointsForContract = calculatedPointsForContract * 2;
        //    if (isContractRedouble) calculatedPointsForContract = calculatedPointsForContract * 4;

            description = description + "punkty za ugraną grę:" + contractLevel + " " + contractSuit + " to: " + calculatedPointsForContract + "pkt.   + ";
        }
        if (!made) {
            calculatedPointsForContract = getUnderTricksPoints(contractLevel,oddTricks,auctionAssumptionPlaingPair,isContractDouble,isContractRedouble);

        }
        calculatedPointsForContract = calculatedPointsForContract
                + getOvertrickPoints(contractLevel, oddTricks, auctionAssumptionPlaingPair, normalDoubleRedubleSingnature, contractSuit)
                + getBonusDoubleRedouble(contractLevel, oddTricks, isContractDouble, isContractRedouble)
                + getGamePartGameBonus(calculatedPointsForContract, contractLevel, oddTricks, auctionAssumptionPlaingPair)
                + getSlamsBonusPoints(contractLevel, oddTricks, auctionAssumptionPlaingPair);
    }




    /**
     * Contract points, assigned to each odd trick bid and made:
     * Their values depend on the suit (or notrump) and whether the contract is doubled or redoubled; they are not affected by vulnerability.
     * Tricks won beyond that necessary to fulfill the contract are referred to as overtricks and their scoring points are accounted
     * for separately because their values are dependent upon declarer's vulnerability.
     *
     * @param contractLevel  The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
     * @param contractSuit  Cards suits [denomination or strain] that denotes the proposed trump suit or notrump. Thus, there are five denominations – notrump, spades, hearts, diamonds and clubs.
     * @return Scorring  for this part of contract
     * @throws InvalidContractSuitException if contract suits is not correct
     */
    private int getContractPoints(int contractLevel, String contractSuit) throws InvalidContractSuitException {

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

    /**
     * When declarer makes overtricks, their score value depends upon the contract denomination, declarer's vulnerability and whether or not the contract is undoubled, doubled or redoubled.
     * In an undoubled contract each overtrick earns the same as in contract points (30 for notrump and major suit contracts, 20 for minor suit contracts);
     * values increase significantly when the contract has been doubled or redoubled, especially when vulnerable.
     *
     * @param contractLevel  The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
     * @param oddTricks   The number of tricks above six (the book) that are taken by declarer.
     * @param auctionAssumptionPlaingPair  is vulnerable or not vulnerable
     * @param isContractDouble   is contract double
     * @param isContractRedouble is conract redouble
     * @param gameSuit  Cards suits [denomination or strain] that denotes the proposed trump suit or notrump. Thus, there are five denominations – notrump, spades, hearts, diamonds and clubs.
     * @return scorring for overtricks
     * @throws InvalidContractSuitException if contract suits is not correct
     */
    private int getOvertrickPoints(int contractLevel, int oddTricks,  boolean auctionAssumptionPlaingPair, int s, String gameSuit) throws InvalidContractSuitException {
        int aditionalTricksPoints = 0;
        if (oddTricks > contractLevel) {


            // Overtrick points
            if(s==1) {
          //  if (!isContractDouble && !isContractRedouble) {
                aditionalTricksPoints = getContractPoints(oddTricks - contractLevel, gameSuit);  //bez kontry i rekontry - tak samo jak lewa
                if (gameSuit.equals("nt") || gameSuit.equals("n") || gameSuit.equals("N") || gameSuit.equals("NT"))
                    aditionalTricksPoints = aditionalTricksPoints - 10; //przy bez atu pierwsza nadróbka za 30 a nie 40!
            }
            //if (isContractDouble && !auctionAssumptionPlaingPair)
            if(s==2 && !auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 100;   //z kontrą przed partią - za 100
            //if (isContractDouble && auctionAssumptionPlaingPair)
            if(s==2 && auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 200;    // z kontrą po partii za 200

            //if (isContractRedouble && !auctionAssumptionPlaingPair)
            if(s==4 && !auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 200;     // z rekontrą przed partią za 200
            //if (isContractRedouble && auctionAssumptionPlaingPair)
            if(s==4 && auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 400;     //z rekontrą po partii za 400

            description = description + " punkty z " + (oddTricks - contractLevel) + " nadróbek to: " + aditionalTricksPoints + "pkt, + ";
        }
        return aditionalTricksPoints;
    }

    /**
     * When a contract is defeated, penalty points are awarded to the defending side. The value of the penalty depends on the number of undertricks,
     * whether the declaring side is vulnerable or not vulnerable and whether the contract was undoubled, doubled or redoubled.    *
     * @param contractLevel  The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
     * @param oddTricks   The number of tricks above six (the book) that are taken by declarer.
     * @param auctionAssumptionPlaingPair  is vulnerable or not vulnerable
     * @param isContractDouble   is contract double
     * @param isContractRedouble is conract redouble
     * @return scorring for under Tricks
     */
    private int getUnderTricksPoints(int contractLevel, int oddTricks, boolean auctionAssumptionPlaingPair, boolean isContractDouble, boolean isContractRedouble){
        int underTricks = contractLevel - oddTricks;
        int underTricskPoints =0;
        if (underTricks == 1) description = description + " punkty z " + underTricks + " wpadki. ";
        else description = description + " punkty z " + underTricks + " wpadek. ";

        if (!auctionAssumptionPlaingPair && !isContractDouble && !isContractRedouble)
            underTricskPoints = -underTricks * 50;   //bez kontry przed partią 50
        if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && underTricks == 1)
            underTricskPoints = -underTricks * 100;  //z kontrą przed partią pierwsza za 100
        else if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && underTricks == 2)
            underTricskPoints = -underTricks * 200 + 100;  //z kontrą przed partią druga i trzecia za 200
        else if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && underTricks == 3)
            underTricskPoints = -underTricks * 200 + 100;
        else if (!auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && underTricks >= 4)
            underTricskPoints = -underTricks * 300 + 400;    //z kontrą przed partią czwarta i kolejne za 300?

        if (auctionAssumptionPlaingPair && !isContractDouble && !isContractRedouble)
            underTricskPoints = -underTricks * 100;  //bez kontry po partii 100
        if (auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && underTricks == 1)
            underTricskPoints = -underTricks * 200;  // z kontrą po partii pierwsza za 200
        else if (auctionAssumptionPlaingPair && (isContractDouble || isContractRedouble) && underTricks >= 2)
            underTricskPoints = -underTricks * 300 + 100;  //z kontrą po partii kolejne za 300

        if (isContractRedouble) underTricskPoints = underTricskPoints * 2;

        return underTricskPoints;
    }


    /**
     * When a doubled or redoubled contract is made, a bonus is awarded to the declaring side. It is colloquially referred to as a bonus for "insult",
     * meaning that the opponents have insulted the pair by suggesting that the declarer will not make the contract.
     * 50 points are awarded for a doubled contract made, and  100 points are awarded for a redoubled contract made.
     * @param contractLevel  The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
     * @param oddTricks   The number of tricks above six (the book) that are taken by declarer.
     * @param isContractDouble   is contract double
     * @param isContractRedouble is conract redouble
     * @return bonus scorring
     */
    private int getBonusDoubleRedouble(int contractLevel, int oddTricks, boolean isContractDouble, boolean isContractRedouble) {
        if ((isContractDouble) && contractLevel <= oddTricks) {
            description = description + " kara za nieudaną kontrę, ";
            return 50;

        } else if ((isContractRedouble) && contractLevel <= oddTricks) {
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

    /**
     * Bonuses are awarded for all slam contracts bid and made:
     * a small slam, or successful contract to win 12 of 13 tricks, earns a bonus of 500 points if not vulnerable and 750 points if vulnerable;
     * a grand slam, or successful contract to win all 13 tricks, earns a bonus of 1000 points if not vulnerable and 1500 points if vulnerable.
     *
     * @param contractLevel  The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
     * @param oddTricks   The number of tricks above six (the book) that are taken by declarer.
     * @param auctionAssumptionPlaingPair  is vulnerable or not vulnerable
     * @return slam and small slam bonus
     */
    private int getSlamsBonusPoints(int contractLevel, int oddTricks, boolean auctionAssumptionPlaingPair) {

        if (oddTricks >= contractLevel) {
            if (contractLevel == 6) return (auctionAssumptionPlaingPair) ? 750 : 500;
            else if (contractLevel == 7) return (auctionAssumptionPlaingPair) ? 1500 : 1000;
        }
        return 0;
    }

    /**
     * Description of cotracts
     * @param contractLevel  The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
     * @param numberOfTrickTaken   The whole number of tricks  (not only above six) that are taken by declarer.
     * @param isContractDouble   is contract double
     * @param isContractRedouble is conract redouble
     * @return short description of contract
     */
    private String getContractDescription(int contractLevel, int numberOfTrickTaken, String contractSuit, boolean isContractDouble, boolean isContractRedouble) {
       return getContractDescription(contractLevel,numberOfTrickTaken,contractSuit,isContractRedouble ? 4 : (isContractDouble ? 2 :1));
    }
    private String getContractDescription(int contractLevel, int numberOfTrickTaken, String contractSuit, int s) {
        if (s==2)
            return shortDescription + contractLevel + contractSuit + " z kontrą, zebrano " + numberOfTrickTaken + " lew.";
        else if (s==4)
            return shortDescription + contractLevel + contractSuit + " z rekontrą, zebrano " + numberOfTrickTaken + " lew.";
        else
            return shortDescription + contractLevel + contractSuit + ", zebrano " + numberOfTrickTaken + " lew.";
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
