package pl.waw.rubach.points;

import org.junit.Before;

public class InternationalBridgeScoringTestAfter extends InternationalBridgeScoringTestBefore {

    @Before
    public void fillTestPointsMap() {
        beforeAfter = true;
     //   karaZaKontre = 50;
      //  premiaZaCzesciowke = 50;
      //  premiaZaKoncowke = 500;
        premiaZaSzlemika = 750;
        premiaZaSzlema = 1500;
        nadrobkaZKontra = 200;
        wpadkaBezKontry = 100;
        wpadka1Zkontra = 200;
        wpadka2Zkontra = 300;
        super.fillTestPointsMap();
    }
}
