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
import pl.waw.rubach.points.exceptions.InvalidNumberOfGames;

public enum Assumption {
  NOR(1),WE(2),THEY(3),BOTH(4);

 /*
    NOR(false,false),
    WE(true,false),
    THEY(false,true),
    BOTH(true,true);
 Assumption(boolean we,boolean they) {
        this.we = we;
        this.they = they;
       if(!we && !they) this.contractNumber = 1;
       else if(we && !they) this.contractNumber =2;
       else if(!we) this.contractNumber=3;
       else this.contractNumber=4;
    }

*/
    private final boolean we;
    private final boolean they;
    private final int contractNumber;


    Assumption(int contractNumber) {
        this.contractNumber = contractNumber;
        if (contractNumber == 1) {
            this.we = false;
            this.they = false;
        } else if (contractNumber == 2) {
            this.we = true;
            this.they = false;
        } else if (contractNumber == 3) {
            this.we = false;
            this.they = true;
        } else if (contractNumber == 4) {
            this.we = true;
            this.they = true;
        } else {
            we = false;
            they = false;
        }

    }

    public static boolean fillAssumptionWe(int contractNumber) throws BridgeException
    {
        checkIfCorrectContractNumber(contractNumber);
        for (Assumption a : Assumption.values()) {
            if (contractNumber == a.getContractNumber()) return a.areWeVulnerable();
            }
        return false;

    }
    public static boolean fillAssumptionThey(int contractNumber) throws BridgeException
    {
        checkIfCorrectContractNumber(contractNumber);
        for (Assumption a : Assumption.values()) {
            if (contractNumber == a.getContractNumber()) return a.areTheyVulnerable();
            }
        return false;

    }

   public static boolean fillAssumption(int contractNumber, boolean who) throws BridgeException
   {  checkIfCorrectContractNumber(contractNumber);
       for (Assumption a : Assumption.values()) {
           if (contractNumber == a.getContractNumber()) return who ? a.areWeVulnerable() : a.areTheyVulnerable();
    }
       return false;
   }

   private static void checkIfCorrectContractNumber(int contractNumber) throws BridgeException {
        if(contractNumber>4 || contractNumber<=0) throw new InvalidNumberOfGames(contractNumber);
   }

    public int getContractNumber() {
        return contractNumber;
    }

    public boolean areWeVulnerable() {
        return we;
    }

    public boolean areTheyVulnerable() {
        return they;
    }
}
