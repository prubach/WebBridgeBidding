package pl.waw.rubach.pointsCounting;

class ImpTable {

    int getResults() {
        return results;
    }

    void setResults(int results) {
        this.results = results;
    }

   private int results;

    ImpTable(int poinst){

    //   if (pointDifferent>=4000) result = 24;
     //  else if (pointDifferent>=3500) result = 23;
      // else if (pointDifferent>=3000) result = 22;
      // else result=0;
        if (poinst<20) results=0;
        else if (poinst<40) results =1;
        else if (poinst<80) results =2;
        else if (poinst<120) results =3;
        else if (poinst<160) results =4;
        else if (poinst<210) results =5;
        else if (poinst<260) results =6;
        else if (poinst<310) results =7;
        else if (poinst<360) results =8;
        else if (poinst<420) results =9;
        else if (poinst<490) results =10;
            //TODO uzupełnić :)
        else results=11;

   }

}
