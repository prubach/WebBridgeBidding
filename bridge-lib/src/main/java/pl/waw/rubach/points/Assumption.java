package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

public enum Assumption {
  NOR(1),WE(2),THEY(3),BOTH(4);

 /*   NOR(false,false),
    WE(true,false),
    THEY(false,true),
    BOTH(true,true);
*/
    private  boolean we;
    private  boolean they;
    private  int contractNumber;

    Assumption(boolean we,boolean they) {
        this.we = we;
        this.they = they;
       if(!we && !they) this.contractNumber = 1;
       else if(we && !they) this.contractNumber =2;
       else if(!we) this.contractNumber=3;
       else this.contractNumber=4;
    }
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
        //    throw new BridgeException(contractNumber);)
    }

   public boolean findAssumption(int contractNumber,boolean who) throws BridgeException
   {

      return who ? we : they;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public boolean areWeVunerable() {
        return we;
    }

    public boolean areTheyVunerable() {
        return they;
    }
}
