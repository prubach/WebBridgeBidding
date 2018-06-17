package pl.waw.rubach.points;

public class PointsForContract {

    int levelOfGame;  //6 + level of game = number of trics should take
    int numberOfTrickTaken;
    String gameColor;   // S - spaces, H heart, c clubes, Diamonsd N -notrumph
    boolean doubleGame;
    boolean redoubleGame;
            int calculatedPointsForContract;
            int aditionalTricsPoints;


   public PointsForContract(int levelOfGame, int numberOfTricksTaken, String gameColor, boolean doubleGame, boolean redoubleGame, boolean asumption) {
    this.gameColor = gameColor;
    this.levelOfGame = levelOfGame;
    this.numberOfTrickTaken = numberOfTricksTaken;
    this.doubleGame = doubleGame;
    this.redoubleGame = redoubleGame;
    this.calculatedPointsForContract=0;
    this.aditionalTricsPoints=0;


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

        if(calculatedPointsForContract>=100 && asumption) calculatedPointsForContract = calculatedPointsForContract+500;
        else if (calculatedPointsForContract>=100 ) calculatedPointsForContract = calculatedPointsForContract +300;


        }
    else if(numberOfTricksTaken>levelOfGame) {


        if(!doubleGame && ! redoubleGame) calculatedPointsForContract = calculatedPointsForContract+aditionalTricsPoints;
        if(doubleGame && !asumption) calculatedPointsForContract = calculatedPointsForContract+ (numberOfTricksTaken-levelOfGame)*100 +50;
        if(doubleGame && asumption) calculatedPointsForContract = calculatedPointsForContract+ (numberOfTricksTaken-levelOfGame)*200 +50;

        if(redoubleGame && !asumption) calculatedPointsForContract = calculatedPointsForContract + (numberOfTricksTaken-levelOfGame)*200 +50;
        if(redoubleGame && asumption) calculatedPointsForContract = calculatedPointsForContract + (numberOfTricksTaken-levelOfGame)*400 +50;

        if(calculatedPointsForContract>=100 && asumption) calculatedPointsForContract = calculatedPointsForContract+500;
        else if (calculatedPointsForContract>=100 ) calculatedPointsForContract = calculatedPointsForContract +300;



       }

    else if(numberOfTricksTaken<levelOfGame) {
       int wpadki = levelOfGame - numberOfTricksTaken;

       if (!asumption && !doubleGame) calculatedPointsForContract = - wpadki*50;
       if (!asumption &&  doubleGame && wpadki==1) calculatedPointsForContract = -wpadki*100; // TODO nie uwzględnia że druga za 200 itp...
        else if (!asumption &&  doubleGame && wpadki==2) calculatedPointsForContract = -wpadki*200 +100;
        else if (!asumption &&  doubleGame && wpadki==3) calculatedPointsForContract = -wpadki*200 +100;
        else if (!asumption &&  doubleGame && wpadki>=4) calculatedPointsForContract = -wpadki*300 +400;

        if (asumption && !doubleGame) calculatedPointsForContract = - wpadki*100;
        if (asumption &&  doubleGame  && wpadki==1) calculatedPointsForContract = -wpadki*200;  //TODO nie uwzględnia że druga i dalsze wiecej
        else if (asumption &&  doubleGame && wpadki>=2) calculatedPointsForContract = -wpadki*300 +100;

    }

    calculatedPointsForContract = calculatedPointsForContract + getPremiaPoints(levelOfGame,numberOfTrickTaken,asumption);
    }

    public int getCalculatedPointsForContract() {
        return calculatedPointsForContract;
    }


    private int getPremiaPoints(int levelOfGame, int numberOfTrickTaken, boolean assumption){
        int result=0;
        if (levelOfGame==6 && !assumption && numberOfTrickTaken>=levelOfGame) result =500;
        if (levelOfGame==6 && assumption  && numberOfTrickTaken>=levelOfGame) result =750;
        if (levelOfGame==7 && !assumption && numberOfTrickTaken>=levelOfGame) result =1000;
        if (levelOfGame==7 && assumption && numberOfTrickTaken>=levelOfGame) result =1500;
   return result;
    }
}
