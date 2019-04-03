package pl.waw.rubach.points.rubberScorring;

import pl.waw.rubach.points.DuplicateBridgeScoring;
import pl.waw.rubach.points.exceptions.BridgeException;

public class RubberBridgeScorring extends DuplicateBridgeScoring {


    public RubberBridgeScorring(int contractLevel, String contractSuit, int nDRSignature,
                                boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer) throws BridgeException {
        super(contractLevel, contractSuit, nDRSignature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }

    public RubberBridgeScorring(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
                                boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)  throws  BridgeException {
        super(contractLevel, contractSuit, isContractDouble, isContractRedouble, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }
    @Override
    protected int getGamePartGameBonus(int calculatedPointsForContract){
        return 0;
    }

    public int getDeclarerUnderPoints()  throws  BridgeException {
        return made ? getContractPoints(getContractLevel()) * getNoDoubleReSignature() :0 ;
    }

    public int getDeclarerOverPoints() throws BridgeException {
        return getDeclarerContractScoringPoints() - getDeclarerUnderPoints();
    }
}
