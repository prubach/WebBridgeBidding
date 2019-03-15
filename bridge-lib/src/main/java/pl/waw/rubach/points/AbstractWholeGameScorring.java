package pl.waw.rubach.points;

public abstract class AbstractWholeGameScorring {

    /**
     * game (for contract) ID - not sure if needed but probably for something?  - could be added in future:
     * date and time
     * place
     * names of players
     * photos etc ...
     * should be done automaticly incriasing of 1 - but how????- gdzieś tak widziałam...
     */
    private int gameID;

    /**
     * Wchich typ of scoriing trhe game is
     */
    private String gameType;
    /**
     * rubberSpecialDescription describe parameter of game - if accesible and results describe results of rubber
     */
    private String rubberSpecialDescription, resultsDescription;


    /**
     * Result of 4 games - imp points
     */
    private int summ;



    protected AbstractWholeGameScorring(int gameID, String des) {
        setGameID(gameID);
        setRubberSpecialDescription(" \n *** Nowa seria gier "+ des +" z numerem:  " + getGameID() + ". ***  \n");
        setResultsDescription(" \nWyniki: \n");
        setSumm(0);

    }




    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getGameID() {
        return gameID;
    }

    protected void setGameID(int gameID) {
        this.gameID = gameID;
    }


    public String getRubberSpecialDescription() {
        return rubberSpecialDescription;
    }

    protected void setRubberSpecialDescription(String rubberSpecialDescription) {
        this.rubberSpecialDescription = rubberSpecialDescription;
    }

    public String getResultsDescription() {
        return resultsDescription;
    }

    protected void setResultsDescription(String resultsDescription) {
        this.resultsDescription = resultsDescription;
    }

     public int getSumm() {
        return this.summ;
    }

    protected void setSumm(int summ) {
        this.summ = summ;
    }

}
