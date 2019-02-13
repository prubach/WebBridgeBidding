package pl.waw.rubach.points;

import pl.waw.rubach.points.exceptions.BridgeException;

public enum Assumption {
    NOR(false,false),
    WE(true,false),
    THEY(false,true),
    BOTH(true,true);

    private final boolean we;
    private final boolean they;
    private final int contractNumber;

    Assumption(boolean we,boolean they) {
        this.we = we;
        this.they = they;
       if(!we && !they) this.contractNumber = 1;
       else if(we && !they) this.contractNumber =2;
       else if(!we) this.contractNumber=3;
       else this.contractNumber=4;
    }


   Assumption(int contractNumber) throws BridgeException {
        this.contractNumber = contractNumber;
      if(contractNumber==1) {this.we = false; this.they =false;}
      else if(contractNumber==2) {this.we = true; this.they =false;}
      else if(contractNumber==3) {this.we = false; this.they = true;}
      else if(contractNumber==4){this.we=true; this.they = true;}
      else throw new BridgeException(contractNumber);

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
