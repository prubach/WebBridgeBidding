package pl.waw.rubach.points;

public class TestCountingPoints {

    public static void main(String[] args) {

        ResultsOfOneGame a = new ResultsOfOneGame(24,100,true,true);
        System.out.println(a.getResults());

         System.out.println(ExpectedResultsTable.getInstance(true,true).getPoints(30));




        int[] testPoints = new int[]{50, 120, 450, 1090, 1200};
        for (int p : testPoints) {
            System.out.println("Dla " + p + " pkt: " + ImpTable.getInstance().getPoints(p)+ "impów.");
        }


        int[] testPoints1 = new int[]{20, 25, 30, 35};
        for (int p : testPoints1) {
            System.out.println("Dla " + p + " pkt: " + ExpectedResultsTable.getInstance(true,true).getPoints(p)+" oczekiwane.");
        }
     //  ImpTable.printTable();

       ExpectedResultsTable.printTable(false, false);


    }
}
