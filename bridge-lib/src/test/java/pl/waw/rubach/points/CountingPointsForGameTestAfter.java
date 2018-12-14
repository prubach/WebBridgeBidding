package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountingPointsForGameTestAfter extends CountingPointsForGameTestBefore {

    @Before
    public void fillTestPointsMap() {
        beforeAfter = true;
        karaZaKontre = 50;
        premiaZaCzesciowke = 50;
        premiaZaKoncowke = 500;
        premiaZaSzlemika = 750;
        premiaZaSzlema = 1500;
        nadrobkaZKontra = 200;
        wpadkaBezKontry = 100;
        wpadka1Zkontra = 200;
        wpadka2Zkontra = 300;
        super.fillTestPointsMap();
    }
}
