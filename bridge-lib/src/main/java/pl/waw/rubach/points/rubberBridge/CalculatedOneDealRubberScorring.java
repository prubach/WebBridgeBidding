package pl.waw.rubach.points.rubberBridge;

import pl.waw.rubach.points.InternationalBridgeScoring;
import pl.waw.rubach.points.exceptions.BridgeException;

public class CalculatedOneDealRubberScorring extends InternationalBridgeScoring {


     private int declarerOverPoints =0, declarerUnderPoints =0;


    public CalculatedOneDealRubberScorring(int contractLevel, String contractSuit, int nDRSignature,
                                           boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer) throws BridgeException {
        super(contractLevel, contractSuit, nDRSignature, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);

    setDeclarerUnderPoints();
    setDeclarerOverPoints();

    }

    public CalculatedOneDealRubberScorring(int contractLevel, String contractSuit, boolean isContractDouble, boolean isContractRedouble,
                                           boolean auctionAssumptionDeclarer, int numberOfTrickTakenByDeclarer)  throws  BridgeException {
        this(contractLevel, contractSuit, isContractDouble? IS_DOUBLE: isContractRedouble? IS_REDOUBLE: IS_UNDOUBTED,
                auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);

    }


    public CalculatedOneDealRubberScorring(boolean whoPlay, int contractLevel, String contractSuit, int nDRSignature,
                                           boolean auctionAssumptionWe,boolean auctionAssumptionThey, int numberOfTrickTakenByWe)  throws  BridgeException {
        this(contractLevel, contractSuit, nDRSignature,
                whoPlay? auctionAssumptionWe: auctionAssumptionThey,
                whoPlay? numberOfTrickTakenByWe: NUMBEROFTRICS -numberOfTrickTakenByWe);

        setWePlay(whoPlay);
    }

    public int getDeclarerUnderPoints(){
        return declarerUnderPoints;
    }

    public int getDeclarerOverPoints(){
        return declarerOverPoints;
    }


    private void setDeclarerOverPoints() throws BridgeException {
        this.declarerOverPoints = getDeclarerContractScoringPoints() - getDeclarerUnderPoints();
    }

    private void setDeclarerUnderPoints() throws BridgeException {
        this.declarerUnderPoints = made ? getContractPoints(getContractLevel()) * getNoDoubleReSignature() :0 ;
    }
}
