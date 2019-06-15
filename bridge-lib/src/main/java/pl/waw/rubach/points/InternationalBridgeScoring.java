package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidContractSuitException;

//This was  PointsForContract(...)
public class InternationalBridgeScoring extends OneDeal {

    /**
     * The number of tricks above six (the book) that are taken by declarer.
     */
    private int oddTricks;
    /**
     * Indicates if contract is made  which means  take at least as many tricks as a contract calls for.
     */
    protected boolean made;

    /**
     * Long description explain how points are calculating - is setting in time of calculation, so no setter need!
     */
    protected String description = "Punkty za kontrakt: ";


    //This was :  PointsForContract(...)

    /**
     * Constructor of scoring for duplicate Bridge game for Declarer hand  -  setting contract parameter and calculated scoring points for Contract
     * extended DealerPointsForOneDeal class where are field for all parameter for declarer.
     * Contract parameter:
     *
     * @param contractLevel                The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     *                                     in future from other part of application - results of biding part or user input
     * @param contractSuit                 Cards suits [denomination or strain] that denotes the proposed trump suit or no trump.
     *                                     Thus, there are five denominations – no trump, spades, hearts, diamonds and clubs.
     * @param nDRSignature                 Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     * @param auctionAssumptionDeclarer    ATTENTION Auction assumption declarer not we!!!
     *                                     Auction Assumption for playing Pair means if pair is  vulnerable or unvulnerable
     * @param numberOfTrickTakenByDeclarer ATTENTION : Number of tricks taken in game by we !!!(Pair who make scoring not always playing pair)
     *                                     <p>
     *                                     Number of tricks taken in game  by declarer - the player whose bid establishes the suit of the contract
     *                                     and who must therefore play both their own hand and the exposed hand of the dummy.
     *                                     (opponent takes 13 - numberOfTrickTakenByDeclarer)
     * @throws BridgeException                  if some bridge parameter have  incorrect value:
     * InvalidNumberOfTrickTakenException if number of tricks is less then 0 or more then 13 (52:4)
     * InvalidContractSuitException       if suit is no one of 5 bridge strain (colors + nt)
     * InvalidContractLevelException      if level is not between 1 to 7 (according bridge rules etc
     */
    public InternationalBridgeScoring(int contractLevel, String contractSuit, int nDRSignature,
                                      boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
            throws  BridgeException {

        super(contractLevel,contractSuit,nDRSignature,numberOfTrickTakenByDeclarer,auctionAssumptionDeclarer);

        //helping variable
        setShortDescription(getContractDescription());
        this.oddTricks = numberOfTrickTakenByDeclarer - 6;    //tricks above 6
        this.made = oddTricks >= contractLevel;  //condition if game is made or not

        //  Begin of calculation
        setDeclarerContractScoringPoints(0);       // points equal zero at the beginning of calculation

        if (made) {
            setDeclarerContractScoringPoints(getContractPoints(contractLevel) * nDRSignature);
            description = description + "Za ugraną grę:" + contractLevel + " " + contractSuit + " to: " + getDeclarerContractScoringPoints() + "pkt. ";
        } else {
            setDeclarerContractScoringPoints(getPenaltyPoints());
        }
        if(contractLevel ==0) setDeclarerContractScoringPoints(0);
        else
        setDeclarerContractScoringPoints(getDeclarerContractScoringPoints()
                + getGamePartGameBonus(getDeclarerContractScoringPoints())
                + getOvertrickPoints()
                + getDoubleRedoubleBonus()
                + getSlamsBonusPoints());
    }

    //OLD constructor with boolean double redouble - exist in all test so cant be delayed
    public InternationalBridgeScoring(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
                                      boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)
            throws BridgeException {
        this(contractLevel, contractSuit, isContractRedouble ? 4 : (isContractDouble ? 2 : 1), auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }


    /**
     * Contract points, assigned to each odd trick bid and made:
     * Their values depend on the suit (or no trump) and whether the contract is doubled or redoubled; they are not affected by vulnerability.
     * Tricks won beyond that necessary to fulfill the contract are referred to as overtrick and their scoring points are accounted
     * for separately because their values are dependent upon declarer's vulnerability.
     *
     * @param numberOfTricksForWhichArePointsCalculated The number of tricks for which points are calculated
     *                                                  in normal case that is contract level = (when added to the book of six tricks) a bid or contract states will be taken.
     *                                                  but could be number of overtrick!!!
     * @return Scoring  for this part of contract
     * @throws InvalidContractSuitException if contract suits is not correct
     */
    protected int getContractPoints(int numberOfTricksForWhichArePointsCalculated) throws InvalidContractSuitException {

        switch (getContractSuit().toUpperCase()) {
            case "S":
            case "H":
                return numberOfTricksForWhichArePointsCalculated * 30;

            case "D":
            case "C":
                return numberOfTricksForWhichArePointsCalculated * 20;

            case "N":
            case "NT":
                return numberOfTricksForWhichArePointsCalculated * 30 + 10;

            default:
                throw new InvalidContractSuitException(getContractSuit());
        }
    }

    /**
     * "NADRÓBKI"
     * When declarer makes overtrick, their score value depends upon the contract denomination, declarer's vulnerability and whether or not the contract is undouble, doubled or redoubled.
     * In an undouble contract each overtrick earns the same as in contract points (30 for no trump and major suit contracts, 20 for minor suit contracts);
     * values increase significantly when the contract has been doubled or redoubled, especially when vulnerable.
     *
     * @return scoring for overtricks
     * @throws InvalidContractSuitException if contract suits is not correct
     */
    private int getOvertrickPoints() throws InvalidContractSuitException {
        // getOvertrickPoints(contractLevel,oddTricks,auctionAssumptionDeclarer,s==2,s==4,contractSuit);
        int overtricksPoints = 0;
        int overtricks = oddTricks - getContractLevel();

        // if (oddTricks > getContractLevel()) {
        if (made && overtricks > 0) {

            if (getNoDoubleReSignature() == IS_UNDOUBLE) {
                overtricksPoints = getContractPoints(overtricks);  //bez kontry i rekontry - tak samo jak lewa
                if (getContractSuit().equals("nt") || getContractSuit().equals("n") || getContractSuit().equals("N") || getContractSuit().equals("NT"))
                    overtricksPoints = overtricksPoints - 10; //przy bez atu pierwsza nadróbka za 30 a nie 40!
            }
            if (getNoDoubleReSignature() == IS_DOUBLE && !isDeclarerVulnerable())
                overtricksPoints = overtricks * 100;   //doubled, not vulnerable - each for 100
            if (getNoDoubleReSignature() == IS_DOUBLE && isDeclarerVulnerable())
                overtricksPoints = overtricks * 200;    // doubled, vulnerable - each for 200

            if (getNoDoubleReSignature() == IS_REDOUBLE && !isDeclarerVulnerable())
                overtricksPoints = overtricks * 200;     //  redoubled, not vulnerable - each for 200
            if (getNoDoubleReSignature() == IS_REDOUBLE && isDeclarerVulnerable())
                overtricksPoints = overtricks * 400;     //redoubled, vulnerable - each for 400

            description = description + "+  punkty z " + overtricks + " nadróbek to: " + overtricksPoints + "pkt,";
        }
        return overtricksPoints;
    }

    /**
     * "WPADKI"
     * When a contract is defeated, penalty points are awarded to the defending side. The value of the penalty depends on the number of undertricks,
     * whether the declaring side is vulnerable or not vulnerable and whether the contract was undoubled, doubled or redoubled.    *
     *
     * @return scoring for under Tricks
     */
    private int getPenaltyPoints() {
        int underTricks = getContractLevel() - oddTricks;
        int underTricksPoints = 0;

        if (!isDeclarerVulnerable() && !(getNoDoubleReSignature() == IS_DOUBLE) && !(getNoDoubleReSignature() == IS_REDOUBLE))
            underTricksPoints = -underTricks * 50;   //bez kontry przed partią 50
        if (!isDeclarerVulnerable() && ((getNoDoubleReSignature() == IS_DOUBLE) || (getNoDoubleReSignature() == IS_REDOUBLE)) && underTricks == 1)
            underTricksPoints = -underTricks * 100;  //z kontrą przed partią pierwsza za 100
        else if (!isDeclarerVulnerable() && ((getNoDoubleReSignature() == IS_DOUBLE) || (getNoDoubleReSignature() == IS_REDOUBLE)) && (underTricks == 2 || underTricks == 3))
            underTricksPoints = -underTricks * 200 + 100;  //z kontrą przed partią druga i trzecia za 200
            //    else if (!auctionAssumptionDeclarer && (isContractDouble || isContractRedouble) && underTricks == 3)
            //       underTricksPoints = -underTricks * 200 + 100;
        else if (!isDeclarerVulnerable() && ((getNoDoubleReSignature() == IS_DOUBLE) || (getNoDoubleReSignature() == IS_REDOUBLE)) && underTricks >= 4)
            underTricksPoints = -underTricks * 300 + 400;    //z kontrą przed partią czwarta i kolejne za 300?

        if (isDeclarerVulnerable() && !(getNoDoubleReSignature() == IS_DOUBLE) && !(getNoDoubleReSignature() == IS_REDOUBLE))
            underTricksPoints = -underTricks * 100;  //bez kontry po partii 100
        if (isDeclarerVulnerable() && ((getNoDoubleReSignature() == IS_DOUBLE) || (getNoDoubleReSignature() == IS_REDOUBLE)) && underTricks == 1)
            underTricksPoints = -underTricks * 200;  // z kontrą po partii pierwsza za 200
        else if (isDeclarerVulnerable() && ((getNoDoubleReSignature() == IS_DOUBLE) || (getNoDoubleReSignature() == IS_REDOUBLE)) && underTricks >= 2)
            underTricksPoints = -underTricks * 300 + 100;  //z kontrą po partii kolejne za 300

        if ((getNoDoubleReSignature() == IS_REDOUBLE)) underTricksPoints = underTricksPoints * 2;

        if (underTricks == 1)
            description = description + " Za nieugraną grę: punkty z " + underTricks + " wpadki: " + underTricksPoints + ". ";
        else
            description = description + "Za nieugraną grę: punkty z " + underTricks + " wpadek: " + underTricksPoints + ". ";

        return underTricksPoints;
    }

    /**
     * When a doubled or redoubled contract is made, a bonus is awarded to the declaring side. It is colloquially referred to as a bonus for "insult",
     * meaning that the opponents have insulted the pair by suggesting that the declarer will not make the contract.
     * 50 points are awarded for a doubled contract made, and  100 points are awarded for a redoubled contract made.
     *
     * @return double or redouble bonus
     */
    private int getDoubleRedoubleBonus() {
        if (getNoDoubleReSignature() == IS_DOUBLE && made) {
            description = description + " + 50 pkt. kara za nieudaną kontrę, ";
            return 50;

        } else if (getNoDoubleReSignature() == IS_REDOUBLE && made) {
            description = description + " + 100 pkt. kara za nieudaną rekontrę, ";
            return 100;
        } else return 0;
    }

    /**
     * In duplicate bridge only, game and partial-game bonuses are awarded at the conclusion of each deal as follows:
     * any partial contract, i.e. one scoring less than 100 contract points, scores a bonus of 50 points, and
     * any game contract, i.e. one scoring 100 or more points, scores a game bonus of 300 if not vulnerable and 500 if vulnerable.
     *
     * @param calculatedPointsForContract - are points for Contract - not overtricks included - so to know is is more then 100.
     * @return game/ part game or slam bonnus
     */
    protected int getGamePartGameBonus(int calculatedPointsForContract) {
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

        if (oddTricks >= getContractLevel()) {

            if (getContractLevel() == 6) {description = description + "+ premia szlemikowa: %. ";  return (isDeclarerVulnerable()) ? 750 : 500;}
            else if (getContractLevel() == 7) {description = description + "+ premia za szlema: %"; return (isDeclarerVulnerable()) ? 1500 : 1000;}
        }
        return 0;
    }


    //getter
    public String getDescription() {
        if(contractLevel==0) description = "nie było gry";
        description =description.replace("%", Integer.toString(getSlamsBonusPoints()));
        return description;
    }


}
