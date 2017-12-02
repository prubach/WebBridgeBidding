package pl.waw.rubach.pointsCounting;

public class ResultsOfOneGame {

    // user had to imput
    int pointsInBothHands;
    //biding height (from other part of aplication? )
    int pointsOfContract;

    int pointDifferent;
    // if 0 is equal, if -1 is one less etc ...
    int results;

    ResultsOfOneGame(int pointsInBothHands, int pointsOfContract){
        this.pointsInBothHands=pointsInBothHands;
        this.pointsOfContract = pointsOfContract;
        this.pointDifferent= pointsOfContract - new ExpectedResults(pointsInBothHands).getResults();
        this.results = results;

    }
}
