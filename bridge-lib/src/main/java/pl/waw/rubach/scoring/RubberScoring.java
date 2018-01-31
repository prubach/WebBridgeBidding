package pl.waw.rubach.scoring;

import pl.waw.rubach.points.InvalidNumberOfPointsException;
import pl.waw.rubach.points.PointsDiferentLessThenZeroException;

public class RubberScoring {

    /**
     * game (for contract) ID - not sure if needed but probably for something?  - could be added in future:
     * date and time
     * place
     * names of players
     * photos etc ...
     * should be done automaticly? - gdzieś tak widziałam...
     */
    private int gameID;



    RubberScoring(int pointsInBothHands, int pointsForContract, boolean fitInOlderColorWe, boolean fitInOlderColorThey)
            throws InvalidNumberOfPointsException, PointsDiferentLessThenZeroException {

    this.gameID = 1;


for(int contractNumber =1;contractNumber<5;contractNumber++) {
     new Scoring(contractNumber,pointsInBothHands,pointsForContract,fitInOlderColorWe,fitInOlderColorThey);
    }}




}
