/*
 *
 *  *                     GNU GENERAL PUBLIC LICENSE
 *  *                        Version 0.8 ,  8. 05. 2021
 *  *
 *  *                   "Bridge-lib"  application as help
 *  *                  for bidding, and points counting
 *   *     Copyright (C) {2017}  {Paweł Rubach, Magdalena Wilska}
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *      any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *
 *
 */

package pl.waw.rubach.points.duplicateBridgeImps;

import pl.waw.rubach.points.exceptions.BridgeException;

import static pl.waw.rubach.points.duplicateBridgeImps.AbstractImpTable.impDeclination;

/**
 * class to calculate which contract (in future) should be playde with assumption, poinst on Hands fit to reach imp points
 */
public class Prediction extends OneDealImp {

  /**
   * description
   */
  private String des;


  /**
   * expected Points number acording assumption and fit of those who have more poinst. If the same those who have fit in major suit. If both those who have fit in spades.
   */
  private int expectedPoints;

  public Prediction(int imps, float pointsInBothHandsWe,
                    boolean assumptionWe, boolean assumptionThey, boolean fitWe, boolean fitThey,
                    boolean whoPlay) throws BridgeException {
    super(whoPlay, pointsInBothHandsWe, 0, assumptionWe, assumptionThey, fitWe, fitThey);

    setResultsWe(whoPlay, imps);

    setExpectedPoints(ExpectedResultsTable.getInstance().getPoints(getPointsInBothDeclarerHands(),
        isFitWe(), isFitThey(), areWeVulnerable(whoPlay), areTheyVulnerable(whoPlay)));

    int resultMax = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[1];
    int resultMin = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[0];

    int wynikUjemnyMax = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[0];
    int wynikUjemnyMin = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[1];

    if (imps > 0)
      setDeclarerContractScoringPoints((resultMax + resultMin) / 2);

    else {
      imps = -imps;
      resultMax = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[1];
      resultMin = getExpectedPoints() + ImpTable.findingDifferenceFromImp(imps)[0];

      wynikUjemnyMax = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[0];
      wynikUjemnyMin = getExpectedPoints() - ImpTable.findingDifferenceFromImp(imps)[1];
      setDeclarerContractScoringPoints(-(wynikUjemnyMax + wynikUjemnyMin) / 2);

    }

    setDes(" \n Oczekiwane wyniki przy " + getPointsInBothDeclarerHands() + " to " + getExpectedPoints() + " punktów za kontrakt .  "
        + "</B>  <BR> Aby uzyskać " + imps + impDeclination(imps) + " różnica punktów musi być między: " + ImpTable.findingDifferenceFromImp(imps)[0] + " a " + ImpTable.findingDifferenceFromImp(imps)[1]
        + "</B>  <BR> Aby uzyskać wynik " + imps + impDeclination(imps) + " , przy " + getPointsInBothDeclarerHands() + " na ręku, musisz ugrać (zdobyć) pomiędzy " + resultMin + " a " + resultMax + "."
        + " czyli średnio: " + getContractScoringPointsWe(whoPlay) + " pkt."
        + "</B>  <BR>Przeciwnicy uzyskają wynik " + imps + impDeclination(imps) + " , gdy ty masz  " + getPointsInBothDeclarerHands() + " na ręku (czyli oni mają : " + (40 - getPointsInBothDeclarerHands()) + "),"
        + "</B>  <BR> a ty ugrasz (zdobędziesz) pomiędzy " + wynikUjemnyMin + " a " + wynikUjemnyMax + "punktów. ");
  }

  //getters and setters


  public int getExpectedPoints() {
    return expectedPoints;
  }

  public void setExpectedPoints(int expectedPoints) {
    this.expectedPoints = expectedPoints;
  }

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }
}
