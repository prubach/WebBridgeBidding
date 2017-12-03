package pl.waw.rubach.pointsCounting;

class ExpectedResults {

    int getResults() {
        return results;
    }

    void setResults(int results) {
        this.results = results;
    }

    private int results;

    ExpectedResults(int poinst){
       int  results =0;
        if (poinst<20) results=0;
        else if (poinst<21) results =1;

       this.results = results;
    }


}
