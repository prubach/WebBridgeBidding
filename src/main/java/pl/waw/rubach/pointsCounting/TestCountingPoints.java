package pl.waw.rubach.pointsCounting;

public class TestCountingPoints {

    public static void main(String[] args) {

        ResultsOfOneGame a = new ResultsOfOneGame(24,100,true,true);
        System.out.println(a.getResults());

        ImpTable.printTable();
    }
}
