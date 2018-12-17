package pl.waw.rubach.points;

public class PointsForOneDeal {
    /**
     * The number of tricks that (when added to the book of six tricks) a bid or contract states will be taken to win.
     * in future from other part of aplication - results of biding part or user imput
     */
    private int contractLevel;
    /**
     * Cards suits [denomination or strain] that denotes the proposed trump suit or notrump.
     * Thus, there are five denominations – notrump, spades, hearts, diamonds and clubs.
     */
    private String contractSuit;
    /**
     *  Signature that shows is it undouble (=1), double (=2) or redouble (=4) contract.
     */
    private int normalDoubleRedubleSingnature;

    /**
     * Numnber of tricks taken in game
     */
    private int numberOfTrickTakenByDeclarer;

    /**
     * Points  achved by playng contract with some additiona or lacking triks (calculated from duplicate bridge scoring)
     * calculated by DuplicateBridgeScoring
     */
    private int pointsForContract;

    /**
     * number of  Goren (PC) points of both hands
     * (in future could be astimated from biding part not exactly)
     */
    private float pointsInBothHands;

    /**
     * diference betwenn assumpted result from ExpectedResults table and point reach playng contract (pointsForContract)
     */
    private int pointDifferent;

    /**
     * Number of Imp Point  if 0 is equal, if -1 is one less etc ...
     */
    private int results; //= 0;     //pyt dlaczego tak?  że niżej a nie tu zerowanie (to samo pytanie jest w DuplicateBridgeScoring i ono ma dwa razy i działa a tu nie ?
    //odp nie potrzeba zerowania - zmienna typu int ma domyślną wartość 0, inaczej jest z Integer, bo to obiekt, więc byłby null

    //getteres and setteres

    public float getPointsInBothHands() {
        return pointsInBothHands;
    }

    public void setPointsInBothHands(int pointsInBothHands) {
        this.pointsInBothHands = pointsInBothHands;
    }

    public int getContractLevel() {
        return contractLevel;
    }

    public void setContractLevel(int contractLevel) {
        this.contractLevel = contractLevel;
    }

    public int getPointsForContract() {
        return pointsForContract;
    }

    public void setPointsForContract(int pointsForContract) {
        this.pointsForContract = pointsForContract;
    }

    public int getPointDifferent() {
        return pointDifferent;
    }

    public void setPointDifferent(int pointDifferent) {
        this.pointDifferent = pointDifferent;
    }

    public void setPointsInBothHands(float pointsInBothHands) {
        this.pointsInBothHands = pointsInBothHands;
    }

    public String getContractSuit() {
        return contractSuit;
    }

    public void setContractSuit(String contractSuit) {
        this.contractSuit = contractSuit;
    }

    public int getNumberOfTrickTakenByDeclarer() {
        return numberOfTrickTakenByDeclarer;
    }

    public void setNumberOfTrickTakenByDeclarer(int numberOfTrickTakenByDeclarer) {
        this.numberOfTrickTakenByDeclarer = numberOfTrickTakenByDeclarer;
    }

    public int getNormalDoubleRedubleSingnature() {
        return normalDoubleRedubleSingnature;
    }

    public void setNormalDoubleRedubleSingnature(int normalDoubleRedubleSingnature) {
        this.normalDoubleRedubleSingnature = normalDoubleRedubleSingnature;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
