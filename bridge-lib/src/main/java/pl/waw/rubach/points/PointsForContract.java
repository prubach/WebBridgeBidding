package pl.waw.rubach.points;

public class PointsForContract {

    //CONTRACT PARAMETER - arguments of constructor
    /**
     * The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken.
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
    private int normalDoubleRedubleSingnature;
    /**
     * Indicates if  contract is  double
     */
    private boolean isContractDouble;
    /**
     * Indicates if  contract is redouble
     */
    private boolean isContractRedouble;
    /**
     * Auction Assumption for PlaingPair means if pair is  vulnerable or not vulnerable
     */
    private boolean auctionAssumptionPlaingPair;



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
     * Points for contract calculated with modyfy bridge rulles according to 4 play scoring
     */
    private int calculatedPointsForContract = 0; //pyt tu powinno być zerowanie czy w konstruktorze? jest tu i tu :)


//odp tak choć ostrożnie  pomysł - czy zamienić boolean double/ reooube na jedną zmienna int na przykład 0-nic, 1-kontra, 2- rekontra albo lepiej 1-nic, 2- kontra 4 rekontra (będzie mógł być może mnożnik)
//pyt czy to tak? Bo jakoś mam wrażenie że ta pierwsza wersja była dużo czytelniejsza a oszczędność dwóch liniek (tam gdzie było mnożenie)...  i mniej czytelnie - na razie zostawiam zmienne a nie s?
// No i nie chce mi się zmieniać testów wiec ten drugi konstruktor i tak musi zostać ...
// kombinowałam w testach z mnożnikami żeby np samo się generowało z kontrą i rekontrką ale nie da się chyba bo za dużo się zmienia...


    //    * @param contractSuit  Cards suits [denomination or strain] that denotes the proposed trump suit or notrump. Thus, there are five denominations – notrump, spades, hearts, diamonds and clubs.

    //todo zmienić nazwę może na  DuplicateBridgeScorring (albo DealScorring??? ale jakoś mi się nie podoba)  -a drugi będzie RubberScorring (i będzie się niewiele różnił w bonusach!) albo tu będą dwie opcje i wtedy ta ogólniejsza nazwa- bo różnice są małe)
    //po polsku było by po prostu zapis międzynarodowy (w odróżnieniu od zapisu polskiego

    public PointsForContract(int contractLevel, int numberOfTrickTaken, String contractSuit, boolean isContractDouble, boolean isContractRedouble, boolean auctionAssumptionPlaingPair)
            throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, InvalidNormalDoubleRedoubleSignature {
        this(contractLevel, numberOfTrickTaken, contractSuit, isContractRedouble ? 4 : (isContractDouble ? 2 : 1), auctionAssumptionPlaingPair);
    }

    public PointsForContract(int contractLevel, int numberOfTrickTaken, String contractSuit, int normalDoubleRedubleSingnature, boolean auctionAssumptionPlaingPair)
            throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, InvalidNormalDoubleRedoubleSignature {
        //this(contractLevel, numberOfTrickTaken, contractSuit, (normalDoubleRedubleSingnature == 2), normalDoubleRedubleSingnature == 4, auctionAssumptionPlaingPair);

        //checking if contractLevel is correct
        if (contractLevel > 7 || contractLevel < 1) throw new InvalidContractLevelException(contractLevel);
        this.contractLevel = contractLevel;
        this.contractSuit = contractSuit;

        //checking if number of tricks is corect
        if (numberOfTrickTaken > 13 || numberOfTrickTaken < 0)
            throw new InvalidNumberOfTrickTakenException(numberOfTrickTaken);


        if (!(normalDoubleRedubleSingnature == 1 || normalDoubleRedubleSingnature == 2 || normalDoubleRedubleSingnature == 4))
            throw new InvalidNormalDoubleRedoubleSignature(normalDoubleRedubleSingnature);
        this.normalDoubleRedubleSingnature = normalDoubleRedubleSingnature;
        this.isContractDouble = normalDoubleRedubleSingnature == 2;
        this.isContractRedouble = normalDoubleRedubleSingnature == 4;

        //checking if double is false when redouble - not important because it could not be both
        //  if (isContractRedouble) isContractDouble = false;

        this.auctionAssumptionPlaingPair = auctionAssumptionPlaingPair;
        this.shortDescription = getContractDescription(numberOfTrickTaken);

        //  Begin of calculation
        this.calculatedPointsForContract = 0;       // zerowanie?
        this.oddTricks = numberOfTrickTaken - 6;    //trics above 6
        this.made = oddTricks >= contractLevel;  //condition if game is made or not


        if (made) {
            calculatedPointsForContract = getContractPoints(contractLevel) * normalDoubleRedubleSingnature;
            //    if (isContractDouble) calculatedPointsForContract = calculatedPointsForContract * 2;
            //    if (isContractRedouble) calculatedPointsForContract = calculatedPointsForContract * 4;
            description = description + "Za ugraną grę:" + contractLevel + " " + contractSuit + " to: " + calculatedPointsForContract + "pkt. ";
        }
        if (!made) calculatedPointsForContract = getPenaltyPoints();


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
        // getOvertrickPoints(contractLevel,oddTricks,auctionAssumptionPlaingPair,s==2,s==4,contractSuit);
        int aditionalTricksPoints = 0;
        if (oddTricks > contractLevel) {


            // Overtrick points
            if (normalDoubleRedubleSingnature == 1) {
                //  if (!isContractDouble && !isContractRedouble) {
                aditionalTricksPoints = getContractPoints(oddTricks - contractLevel);  //bez kontry i rekontry - tak samo jak lewa
                if (contractSuit.equals("nt") || contractSuit.equals("n") || contractSuit.equals("N") || contractSuit.equals("NT"))
                    aditionalTricksPoints = aditionalTricksPoints - 10; //przy bez atu pierwsza nadróbka za 30 a nie 40!
            }
            //if (isContractDouble && !auctionAssumptionPlaingPair)
            if (normalDoubleRedubleSingnature == 2 && !auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 100;   //z kontrą przed partią - za 100
            //if (isContractDouble && auctionAssumptionPlaingPair)
            if (normalDoubleRedubleSingnature == 2 && auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 200;    // z kontrą po partii za 200

            //if (isContractRedouble && !auctionAssumptionPlaingPair)
            if (normalDoubleRedubleSingnature == 4 && !auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 200;     // z rekontrą przed partią za 200
            //if (isContractRedouble && auctionAssumptionPlaingPair)
            if (normalDoubleRedubleSingnature == 4 && auctionAssumptionPlaingPair)
                aditionalTricksPoints = (oddTricks - contractLevel) * 400;     //z rekontrą po partii za 400

            description = description + "+  punkty z " + (oddTricks - contractLevel) + " nadróbek to: " + aditionalTricksPoints + "pkt,";
        }
        return aditionalTricksPoints;
    }

    /**
     * When a contract is defeated, penalty points are awarded to the defending side. The value of the penalty depends on the number of undertricks,
     * whether the declaring side is vulnerable or not vulnerable and whether the contract was undoubled, doubled or redoubled.    *
     * @return scorring for under Tricks
     */
    private int getPenaltyPoints() {
        int underTricks = contractLevel - oddTricks;
        int underTricskPoints = 0;
        if (underTricks == 1) description = description + " punkty z " + underTricks + " wpadki. ";
        else description = description + "Za nieugraną grę: punkty z " + underTricks + " wpadek. ";

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
     * @return  double or redouble bonus
     */
    private int getDoubleRedoubleBonus() {
        if (isContractDouble && made) {
            description = description + " + kara za nieudaną kontrę, ";
            return 50;

        } else if (isContractRedouble && made) {
            description = description + " + kara za nieudaną rekontrę, ";
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

        if (calculatedPointsForContract >= 100 && oddTricks >= contractLevel) {
            description = description + " + punkty za ugraną końcówkę (zależnie od założeń).";
            return (auctionAssumptionPlaingPair) ? 500 : 300;
        } else if (calculatedPointsForContract > 0) {
            description = description + " + 50 pkt za częściówkę.";
            return 50;
        } else return 0;
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
            description = description + "+ premia szlemowa/szlemikowa (zależnie od założeń).";
            if (contractLevel == 6) return (auctionAssumptionPlaingPair) ? 750 : 500;
            else if (contractLevel == 7) return (auctionAssumptionPlaingPair) ? 1500 : 1000;
        }
        return 0;
    }

    /**
     * Description of cotracts
     * @param numberOfTrickTaken The whole number of tricks  (not only above six) that are taken by declarer.
     * @return short description of contract
     */
    private String getContractDescription(int numberOfTrickTaken) {
        if (isContractDouble)
            return shortDescription + contractLevel + contractSuit + " z kontrą, zebrano " + numberOfTrickTaken + " lew.";
        else if (isContractRedouble)
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
