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
        super(contractLevel, contractSuit, isContractDouble, isContractRedouble, auctionAssumptionDeclarer, numberOfTrickTakenByDeclarer);


        setDeclarerUnderPoints();
        setDeclarerOverPoints();
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
