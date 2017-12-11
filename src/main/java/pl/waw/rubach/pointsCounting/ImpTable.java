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
        else if (poinst<590) results =11;
        else if (poinst<740) results =12;
        else if (poinst<890) results =13;
        else if (poinst<1090) results =14;
        else if (poinst<1220) results =15;
        else if (poinst<1490) results =16;
        else if (poinst<1740) results =17;
        else if (poinst<1990) results =18;
        else if (poinst<2240) results =19;
        else if (poinst<2490) results =20;
        else if (poinst<2990) results =21;
        else if (poinst<3490) results =22;
        else if (poinst<3990) results =23;
        else results=24;

   }

}
