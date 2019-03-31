package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidContractLevelException;
import pl.waw.rubach.points.exceptions.InvalidContractSuitException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfTrickTakenException;

public class CalculatedRubberPoints extends DuplicateBridgeScoring {


    public CalculatedRubberPoints(int contractLevel, String contractSuit, int nDRSignature, boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer) throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, BridgeException {
        super(contractLevel, contractSuit, nDRSignature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }

    public CalculatedRubberPoints(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
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
