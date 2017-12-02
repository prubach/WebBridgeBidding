package pl.waw.rubach.pointsCounting;

public class ExpectedResults {
    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    int results;

    ExpectedResults(int poinst){
       int  results =0;
        if (poinst<20) results=0;
        else if (poinst<21) results =1;

       this.results = results;
    }


}
