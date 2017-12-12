package pl.waw.rubach.pointsCounting;

class ImpTable {

    private int results;
    private static int[] table = new int[25];
    private static int[] tableMin = new int[25];
    private static int[] tableMax = new int[25];

    ImpTable(int poinst) {

        if (poinst < 20) results = 0;
        else if (poinst < 40) results = 1;
        else if (poinst < 80) results = 2;
        else if (poinst < 120) results = 3;
        else if (poinst < 160) results = 4;
        else if (poinst < 210) results = 5;
        else if (poinst < 260) results = 6;
        else if (poinst < 310) results = 7;
        else if (poinst < 360) results = 8;
        else if (poinst < 420) results = 9;
        else if (poinst < 490) results = 10;
        else if (poinst < 590) results = 11;
        else if (poinst < 740) results = 12;
        else if (poinst < 890) results = 13;
        else if (poinst < 1090) results = 14;
        else if (poinst < 1220) results = 15;
        else if (poinst < 1490) results = 16;
        else if (poinst < 1740) results = 17;
        else if (poinst < 1990) results = 18;
        else if (poinst < 2240) results = 19;
        else if (poinst < 2490) results = 20;
        else if (poinst < 2990) results = 21;
        else if (poinst < 3490) results = 22;
        else if (poinst < 3990) results = 23;
        else results = 24;

    }

    int getResults() {
        return results;
    }

    void setResults(int results) {
        this.results = results;
    }

    /**
     * todo function to print tabel with points
     */
    public static void printTable() {
        int res1 = 0, res2, j = 0;
        for (int i = 0; i < 4000; i = i + 10) {
           // System.out.println(i);
            res2 = new ImpTable(i).getResults();
            if (res1 < res2) {
                table[j] = i;
                tableMax[j]= i;

               // System.out.println("Wynik "+j+ " jest dla punktów poniżej "+i+".");
                j++;
                res1=res2;
            }
        }
        tableMin[0]=0;
        tableMin[1]=20;
        for(int k=2;k<25;k++) tableMin[k]=tableMax[k-1]+10;

        //System.out.println(0 + " imp  dla <"+ table[0]);
        //System.out.println((1) + " imp dla "+ table[0]+ "-"+ table[1]);
        //for(int k=1; k<23;k++) System.out.println((k+1) + " imp "+ (table[k]+10)+ "-"+ table[k+1]);
        //System.out.println(24 + " imp  dla >"+ (table[23]+10));


        for(int k=0; k<25;k++) System.out.println(k + " imp "+ tableMin[k]+ "-"+ tableMax[k]);

    }


}
