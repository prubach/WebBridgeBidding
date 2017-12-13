package pl.waw.rubach.points;

public class TestCountingPoints {

    public static void main(String[] args) {

     //   ResultsOfOneGame a = new ResultsOfOneGame(24,100,true,true);
      //  System.out.println(a.getResults());

       ExpectedResultsTable aa = new ExpectedResultsTable(true,true);
    System.out.println(aa.getExpectedPoints(30));




        int[] testPoints = new int[]{50, 120, 450, 1090, 1200};
        for (int p : testPoints) {
      //      System.out.println("Dla " + p + " pkt: " + ImpTable.getInstance().getImpPoints(p));
        }
       // ImpTable.printTable();


    }
}
