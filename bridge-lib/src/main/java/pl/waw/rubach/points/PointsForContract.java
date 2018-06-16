package pl.waw.rubach.points;

public class PointsForContract {

    int levelOfGame;  //6 + level of game = number of trics should take
    int numberOfTrickTaken;
    String gameColor;   // S - spaces, H heart, c clubes, Diamonsd N -notrumph
    boolean doubleGame;
    boolean redoubleGame;
            int calculatedPointsForContract;
            int aditionalTricsPoints;

    PointsForContract(int levelOfGame, int numberOfTricksTaken, String gameColor, boolean doubleGame, boolean redoubleGame) {
    this.gameColor = gameColor;
    this.levelOfGame = levelOfGame;
    this.numberOfTrickTaken = numberOfTricksTaken;
    this.doubleGame = doubleGame;
    this.redoubleGame = redoubleGame;
    this.calculatedPointsForContract=0;


        switch (gameColor) {
            case "s":
            case "h":
                calculatedPointsForContract = levelOfGame * 30;
                aditionalTricsPoints = (numberOfTricksTaken-levelOfGame) *30;
                break;
            case "d":
            case "c":
                calculatedPointsForContract = levelOfGame * 20;
                aditionalTricsPoints = (numberOfTricksTaken-levelOfGame) *20;
                break;
            case "n":
                calculatedPointsForContract = levelOfGame * 30 + 10;
                aditionalTricsPoints = (numberOfTricksTaken-levelOfGame) *30;
                break;
            //  else  throw exeption error invalid color of game

        }

    if (numberOfTricksTaken==levelOfGame){


        if(doubleGame) calculatedPointsForContract = calculatedPointsForContract*2;
        if(redoubleGame) calculatedPointsForContract = calculatedPointsForContract*4;
    }
    else if(numberOfTricksTaken>levelOfGame) {


        if(!doubleGame && ! redoubleGame) calculatedPointsForContract = calculatedPointsForContract+aditionalTricsPoints;
        if(doubleGame) calculatedPointsForContract = calculatedPointsForContract+ (numberOfTricksTaken-levelOfGame)*100;
        if(redoubleGame) calculatedPointsForContract = calculatedPointsForContract + (numberOfTricksTaken-levelOfGame)*200;
    }
    }

    public int getCalculatedPointsForContract() {
        return calculatedPointsForContract;
    }

}
