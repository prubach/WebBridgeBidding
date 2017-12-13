package pl.waw.rubach.points;

public class ExpectedResults {

    int getResults() {
        return results;
    }

    void setResults(int results) {
        this.results = results;
    }

    private int results;

    /**
     * todo function to print tabel with points
     */
    public void printTable(){}
    /**
     * Calculate expected result depending of
     * @param poinst in both hand which have more or older fit (or if both spades fit)
     * @param auctionAssumption1 which is true if is after game and false if before
     * @param fitInOlderColor   which is true when older is fit and false if not
     */
    public ExpectedResults(int poinst, boolean auctionAssumption1, boolean fitInOlderColor) {

        boolean auctionAssumption = !auctionAssumption1;
        if (poinst==20 && !fitInOlderColor) results=0;
        else if ((poinst==20 && fitInOlderColor) || poinst==21 && !fitInOlderColor)results =50;
        else if ((poinst==21 && fitInOlderColor) || poinst==22 && !fitInOlderColor)results =70;
        else if ((poinst==22 && fitInOlderColor) || poinst==23 && !fitInOlderColor)results =110;
        else if ((poinst==23 && fitInOlderColor) || poinst==24 && !fitInOlderColor)results =130;
        else if (poinst==25 && !fitInOlderColor)
            { if(auctionAssumption) results=200; else  results =290;}
        else if ((poinst==24 && fitInOlderColor) || poinst==26 && !fitInOlderColor)
        { if(auctionAssumption) results=300; else  results =440;}
        else if ((poinst==25 && fitInOlderColor) || poinst==27 && !fitInOlderColor)
        { if(auctionAssumption) results=350; else  results =520;}
        else if ((poinst==26 && fitInOlderColor) || poinst==28 && !fitInOlderColor)
        { if(auctionAssumption) results=400; else  results =600;}
        else if ((poinst==27 && fitInOlderColor) || poinst==29 && !fitInOlderColor)
        { if(auctionAssumption) results=430; else  results =630;}
        else if ((poinst==28 && fitInOlderColor) || poinst==30 && !fitInOlderColor)
        { if(auctionAssumption) results=460; else  results =660;}
        else if ((poinst==29 && fitInOlderColor) )
        { if(auctionAssumption) results=490; else  results =690;}
        else if ((poinst==30 && fitInOlderColor) )
        { if(auctionAssumption) results=550; else  results =750;}
//*******************************************************************************************
        else if ( poinst==31 && !fitInOlderColor)
        { if(auctionAssumption) results=520; else  results =720;}
        else if ((poinst==31 && fitInOlderColor) || poinst==32 && !fitInOlderColor)
        { if(auctionAssumption) results=600; else  results =800;}
        else if ((poinst==32 && fitInOlderColor) || poinst==33 && !fitInOlderColor)
        { if(auctionAssumption) results=700; else  results =1050;}
        else if ((poinst==33 && fitInOlderColor) || poinst==34 && !fitInOlderColor)
        { if(auctionAssumption) results=900; else  results =1350;}
        else if ((poinst==34 && fitInOlderColor) || poinst==35 && !fitInOlderColor)
        { if(auctionAssumption) results=1000; else  results =1500;}
        else if ((poinst==35 && fitInOlderColor) || poinst==36 && !fitInOlderColor)
        { if(auctionAssumption) results=1100; else  results =1650;}
        else if ((poinst==36 && fitInOlderColor) || poinst==37 && !fitInOlderColor)
        { if(auctionAssumption) results=1200; else  results =1800;}
        else if ((poinst==37 && fitInOlderColor) || poinst==38 && !fitInOlderColor)
        { if(auctionAssumption) results=1400; else  results =2100;}

        else results=0;  //TODO should print error message

        //find what to do if there is half point (calculate two and divide etc? not in table?
    }


}
