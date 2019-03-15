package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;
import pl.waw.rubach.points.exceptions.InvalidContractLevelException;
import pl.waw.rubach.points.exceptions.InvalidContractSuitException;
import pl.waw.rubach.points.exceptions.InvalidNumberOfTrickTakenException;

public class RubberPoints extends DuplicateBridgeScoring {
    public RubberPoints(int contractLevel, String contractSuit, int nDRSignature, boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer) throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, BridgeException {
        super(contractLevel, contractSuit, nDRSignature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }

    public RubberPoints(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble, boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer) throws InvalidContractLevelException, InvalidContractSuitException, InvalidNumberOfTrickTakenException, BridgeException {
        super(contractLevel, contractSuit, isContractDouble, isContractRedouble, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);
    }
    @Override
    protected int getGamePartGameBonus(int calculatedPointsForContract){
        return 0;
    }

}
