package pl.waw.rubach.pointsCounting;

class ImpTable {

    int getResults() {
        return results;
    }

    void setResults(int results) {
        this.results = results;
    }

   private int results;

    ImpTable(int pointDifferent){
       int result;
       if (pointDifferent>=4000) result = 24;
       else if (pointDifferent>=3500) result = 23;
       else if (pointDifferent>=3000) result = 22;
       else result=0;


       this.results = result;
   }

}
