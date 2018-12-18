package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.waw.rubach.points.exceptions.BridgeException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 20 test of diferent case of counting points for one deal according to duplicate bridge scorring
 * with assumption unvunerability (befor) - to see afrer - class before :)
 */
public class DuplicateBridgeScoringTestBefore {

    //czemu to wszystko jest protected jak idea proponuje żeby było bez niczego (package-private) - niby tylko ta druga klasa z pakietu z tego korzysta? Może Idea ma rację? Próbowałam i było ok.
    //odp protected to dostęp dla wszystkich klas dziedziczących bez względu na to w jakim są pakiecie, więc de facto to jeszcze węższy poziom niż package-private
    //pyt czyli zostaje protected? Bo to TYLKO klasy dziedziczące wiec żeby inny test z tego pakietu czegoś nie poużywał sobie ?
    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    protected boolean beforeAfter = false;
    protected int karaZaKontre = 50;
    protected int premiaZaCzesciowke = 50;
    protected int premiaZaKoncowke = 300;
    protected int premiaZaSzlemika = 500;
    protected int premiaZaSzlema = 1000;
    protected int nadrobkaZKontra = 100;
    protected int wpadkaBezKontry =50;
    protected int wpadka1Zkontra=100;
    protected int wpadka2Zkontra=200;

    protected Map<Integer, Integer> testPointsForMajorContract = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorContract = new HashMap<>();
    protected Map<Integer, Integer> testPointsForNoTrumphContract = new HashMap<>();

    protected Map<Integer, Integer> testPointsForMajorDoubleContract = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorDoubleContract = new HashMap<>();
    protected Map<Integer, Integer> testPointsForNoTrumphDoubleContract = new HashMap<>();

    protected Map<Integer, Integer> testPointsForMajorReDoubleContract = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorReDoubleContract = new HashMap<>();
    protected Map<Integer, Integer> testPointsForNoTrumphReDoubleContract = new HashMap<>();

    protected Map<Integer, Integer> testPointsForMajorContractOneMore = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorContractOneMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsOneMore = new HashMap<>();


    protected Map<Integer, Integer>  testMajorContractPointsTwoMore = new HashMap<>();
    protected Map<Integer, Integer>  testMajorContractPointsThreeMore = new HashMap<>();
    protected Map<Integer, Integer>  testMajorContractPointsFourMore = new HashMap<>();
    protected Map<Integer, Integer>  testMajorContractPointsFiveMore = new HashMap<>();
    protected Map<Integer, Integer>  testMajorContractPointsSixMore = new HashMap<>();

    protected Map<Integer, Integer>  testNoTrumphContractPointsTwoMore = new HashMap<>();
    protected Map<Integer, Integer>  testNoTrumphContractPointsThreeMore = new HashMap<>();
    protected Map<Integer, Integer>  testNoTrumphContractPointsFourMore = new HashMap<>();
    protected Map<Integer, Integer>  testNoTrumphContractPointsFiveMore = new HashMap<>();
    protected Map<Integer, Integer>  testNoTrumphContractPointsSixMore = new HashMap<>();

    protected Map<Integer, Integer> testPointsForMajorContractOneMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorContractOneMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsOneMoreDouble = new HashMap<>();

    protected Map<Integer, Integer> testPointsForMajorContractOneMoreReDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorContractOneMoreReDouble = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsOneMoreReDouble = new HashMap<>();


    protected Map<Integer, Integer> testPointsForMajorContractMinusOne = new HashMap<>();
    protected Map<Integer, Integer> testPointsForNoTrumphContractMinusOneDouble = new HashMap<>();

    protected Map<Integer, Integer> testPointsForMajor3ContractMinusMore = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMajor3ContractMinusMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMajor3ContractMinusMoreReDouble = new HashMap<>();

    protected Map<Integer, Integer> testPointsFor3NTContractMinusMore = new HashMap<>();
    protected Map<Integer, Integer> testPointsFor3NTContractMinusMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsFor3NTContractMinusMoreReDouble = new HashMap<>();

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()

        //all equal normal contract
        testPointsForMajorContract.put(1, 30+premiaZaCzesciowke);
        testPointsForMajorContract.put(2, 60 +premiaZaCzesciowke);
        testPointsForMajorContract.put(3, 90 +premiaZaCzesciowke);
        testPointsForMajorContract.put(4, 120 +premiaZaKoncowke);
        testPointsForMajorContract.put(5, 150 +premiaZaKoncowke);
        testPointsForMajorContract.put(6, 180 +premiaZaKoncowke +premiaZaSzlemika);
        testPointsForMajorContract.put(7, 210+ premiaZaKoncowke +premiaZaSzlema);

        testPointsForMinorContract.put(1, 20 + premiaZaCzesciowke);
        testPointsForMinorContract.put(2, 40 + premiaZaCzesciowke);
        testPointsForMinorContract.put(3, 60 + premiaZaCzesciowke);
        testPointsForMinorContract.put(4, 80 + premiaZaCzesciowke);
        testPointsForMinorContract.put(5, 100 +premiaZaKoncowke);
        testPointsForMinorContract.put(6, 120 +premiaZaKoncowke + premiaZaSzlemika);
        testPointsForMinorContract.put(7, 140 +premiaZaKoncowke +premiaZaSzlema);

        testPointsForNoTrumphContract.put(1, 40 + premiaZaCzesciowke);
        testPointsForNoTrumphContract.put(2, 70 + premiaZaCzesciowke);
        testPointsForNoTrumphContract.put(3, 100 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(4, 130 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(5, 160 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(6, 190 + premiaZaKoncowke +premiaZaSzlemika);
        testPointsForNoTrumphContract.put(7, 220 + premiaZaKoncowke +premiaZaSzlema);

        //all of double contract

        testPointsForMajorDoubleContract.put(1, 2*30 +karaZaKontre  +premiaZaCzesciowke);
        testPointsForMajorDoubleContract.put(2, 2*60 +karaZaKontre  +premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(3, 2*90 +karaZaKontre  +premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(4, 2*120 +karaZaKontre  +premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(5, 2*150 +karaZaKontre  +premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(6, 2*180 +karaZaKontre  +premiaZaKoncowke+premiaZaSzlemika);
        testPointsForMajorDoubleContract.put(7, 2*210 +karaZaKontre  +premiaZaKoncowke+premiaZaSzlema);

        testPointsForMinorDoubleContract.put(1, 2*20   +karaZaKontre  +premiaZaCzesciowke);
        testPointsForMinorDoubleContract.put(2, 2*40   +karaZaKontre  +premiaZaCzesciowke);
        testPointsForMinorDoubleContract.put(3, 2*60   +karaZaKontre  +premiaZaKoncowke);
        testPointsForMinorDoubleContract.put(4, 2*80   +karaZaKontre  +premiaZaKoncowke);
        testPointsForMinorDoubleContract.put(5, 2*100  +karaZaKontre  +premiaZaKoncowke);
        testPointsForMinorDoubleContract.put(6, 2*120  +karaZaKontre  +premiaZaKoncowke+premiaZaSzlemika);
        testPointsForMinorDoubleContract.put(7, 2*140  +karaZaKontre  +premiaZaKoncowke+premiaZaSzlema);

        testPointsForNoTrumphDoubleContract.put(1, 40*2   +premiaZaCzesciowke +karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(2, (40+30)*2 +premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphDoubleContract.put(3, 100*2 +premiaZaKoncowke +karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(4, 130*2 +premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphDoubleContract.put(5, 160*2 +premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphDoubleContract.put(6, 190*2 +premiaZaKoncowke +karaZaKontre +premiaZaSzlemika );
        testPointsForNoTrumphDoubleContract.put(7, 220*2 +premiaZaKoncowke +karaZaKontre +premiaZaSzlema );

           //all of redouble contract

        testPointsForMajorReDoubleContract.put(1, 4*30 + premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMajorReDoubleContract.put(2, 4*2*30 + premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMajorReDoubleContract.put(3, 4*3*30 + premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMajorReDoubleContract.put(4, 4*4*30 + premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMajorReDoubleContract.put(5, 4*5*30 + premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMajorReDoubleContract.put(6, 4*6*30 + premiaZaKoncowke + 2*karaZaKontre+premiaZaSzlemika );
        testPointsForMajorReDoubleContract.put(7, 4*7*30 + premiaZaKoncowke + 2*karaZaKontre+premiaZaSzlema );

        testPointsForMinorReDoubleContract.put(1, 4*20  + premiaZaCzesciowke  +2*karaZaKontre );
        testPointsForMinorReDoubleContract.put(2, 4*2*20+ premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMinorReDoubleContract.put(3, 4*3*20+ premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMinorReDoubleContract.put(4, 4*4*20+ premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMinorReDoubleContract.put(5, 4*5*20+ premiaZaKoncowke + 2*karaZaKontre );
        testPointsForMinorReDoubleContract.put(6, 4*6*20+ premiaZaKoncowke + 2*karaZaKontre +premiaZaSzlemika);
        testPointsForMinorReDoubleContract.put(7, 4*7*20+ premiaZaKoncowke + 2*karaZaKontre +premiaZaSzlema );

        testPointsForNoTrumphReDoubleContract.put(1, 4*40 + premiaZaKoncowke +2*karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(2, 4*(40+30) + premiaZaKoncowke +2*karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(3, 4*(40+2*30) + premiaZaKoncowke +2*karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(4, 4*(40+3*30) + premiaZaKoncowke +2*karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(5, 4*(40+4*30) + premiaZaKoncowke +2*karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(6, 4*(40+5*30) + premiaZaKoncowke +2*karaZaKontre +premiaZaSzlemika);
        testPointsForNoTrumphReDoubleContract.put(7, 4*(40+6*30) + premiaZaKoncowke +2*karaZaKontre +premiaZaSzlema );


        //all of Nadróbki - one more
        testPointsForMajorContractOneMore.put(1, 30  +30 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(2, 60  +30 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(3, 90  +30 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(4, 120 +30 + premiaZaKoncowke);
        testPointsForMajorContractOneMore.put(5, 150 +30 + premiaZaKoncowke);
        testPointsForMajorContractOneMore.put(6, 180 +30 + premiaZaKoncowke +premiaZaSzlemika);
      //  testPointsForMajorContractOneMore.put(7, 240 +300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testPointsForMinorContractOneMore.put(1, 20  +20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(2, 40  +20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(3, 60  +20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(4, 80  +20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(5, 100 +20 + premiaZaKoncowke);
        testPointsForMinorContractOneMore.put(6, 120 +20 + premiaZaKoncowke +premiaZaSzlemika);
        //  testPointsForMajorContractOneMore.put(7, 240 +300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testNoTrumphContractPointsOneMore.put(1, 40       +30 + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMore.put(2, 40+30    +30 + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMore.put(3, 40+30*2  +30 + premiaZaKoncowke);
        testNoTrumphContractPointsOneMore.put(4, 40+30*3  +30 + premiaZaKoncowke);
        testNoTrumphContractPointsOneMore.put(5, 40+30*4  +30 + premiaZaKoncowke);
        testNoTrumphContractPointsOneMore.put(6, 40+30*5  +30 + premiaZaKoncowke+premiaZaSzlemika);

        testNoTrumphContractPointsOneMoreDouble.put(1, 2*40 + nadrobkaZKontra+  karaZaKontre + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMoreDouble.put(2, 2*(40+30)  + nadrobkaZKontra+  +premiaZaKoncowke  + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(3, 2*(40+2*30) + nadrobkaZKontra+ +premiaZaKoncowke  + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(4, 2*(40+3*30) + nadrobkaZKontra+ +premiaZaKoncowke  + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(5, 2*(40+4*30) + nadrobkaZKontra+ +premiaZaKoncowke  + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(6, 2*(40+5*30) + nadrobkaZKontra+ +premiaZaKoncowke  + karaZaKontre + premiaZaSzlemika);

        testPointsForMajorContractOneMoreDouble.put(1, 2*30+ nadrobkaZKontra + karaZaKontre +premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMajorContractOneMoreDouble.put(2, 2*60+ nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(3, 2*90+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(4, 2*120+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(5, 2* 150+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(6, 2*180+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke +premiaZaSzlemika);
        // testPointsForMajorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testPointsForMinorContractOneMoreDouble.put(1, 2*20+ nadrobkaZKontra + karaZaKontre +premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMinorContractOneMoreDouble.put(2, 2*40+ nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorContractOneMoreDouble.put(3, 2*60+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(4, 2*80+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(5, 2* 100+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(6, 2*120+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke +premiaZaSzlemika);
       // testPointsForMinorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!


        testNoTrumphContractPointsOneMoreReDouble.put(1, 4*40 + 2*nadrobkaZKontra+  2*karaZaKontre + premiaZaKoncowke);
        testNoTrumphContractPointsOneMoreReDouble.put(2, 4*(40+30)  + 2*nadrobkaZKontra+  +premiaZaKoncowke  + 2*karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(3, 4*(40+2*30) + 2*nadrobkaZKontra+ +premiaZaKoncowke  + 2*karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(4, 4*(40+3*30) + 2*nadrobkaZKontra+ +premiaZaKoncowke  + 2*karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(5, 4*(40+4*30) + 2*nadrobkaZKontra+ +premiaZaKoncowke  + 2*karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(6, 4*(40+5*30) + 2*nadrobkaZKontra+ +premiaZaKoncowke  + 2*karaZaKontre + premiaZaSzlemika);

        testPointsForMajorContractOneMoreReDouble.put(1, 4*30+ 2*nadrobkaZKontra + 2*karaZaKontre +premiaZaKoncowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMajorContractOneMoreReDouble.put(2, 4*60+ 2*nadrobkaZKontra + 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(3, 4*90+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(4, 4*120+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(5, 4* 150+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(6, 4*180+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke +premiaZaSzlemika);
        // testPointsForMajorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testPointsForMinorContractOneMoreReDouble.put(1, 4*20+ 2*nadrobkaZKontra + 2*karaZaKontre +premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMinorContractOneMoreReDouble.put(2, 4*40+ 2*nadrobkaZKontra + 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(3, 4*60+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(4, 4*80+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(5, 4* 100+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(6, 4*120+ 2*nadrobkaZKontra+ 2*karaZaKontre + premiaZaKoncowke +premiaZaSzlemika);
        // testPointsForMinorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!


        //example of two more
        testNoTrumphContractPointsTwoMore.put(1, 40       +60 + premiaZaCzesciowke);
        testNoTrumphContractPointsTwoMore.put(2, 40+30    +60 + premiaZaCzesciowke);
        testNoTrumphContractPointsTwoMore.put(3, 40+2*30  +60 + premiaZaKoncowke);
        testNoTrumphContractPointsTwoMore.put(4, 40+3*30  +60 + premiaZaKoncowke);
        testNoTrumphContractPointsTwoMore.put(5, 40+4*30  +60 + premiaZaKoncowke);
        //     testNoTrumphContractPointsTwoMore.put(6, 40+5*30  +60 + premiaZaKoncowke); Nie może być 6 i dwie lepiej

        testMajorContractPointsTwoMore.put(1, 30    +60 + premiaZaCzesciowke);
        testMajorContractPointsTwoMore.put(2, 2*30  +60 + premiaZaCzesciowke);
        testMajorContractPointsTwoMore.put(3, 3*30  +60 + premiaZaCzesciowke);
        testMajorContractPointsTwoMore.put(4, 4*30  +60 + premiaZaKoncowke);
        testMajorContractPointsTwoMore.put(5, 5*30  +60 + premiaZaKoncowke);
        //     testMajorContractPointsTwoMore.put(6, 6*30  +60 + premiaZaKoncowke); Nie może być 6 i dwie lepiej




//todo add double and redouble example (and minor  ew.)

        //example of nt more more only on nt
        testNoTrumphContractPointsThreeMore.put(1, 40       +90 + premiaZaCzesciowke);
        testNoTrumphContractPointsThreeMore.put(2, 40+30    +90 + premiaZaCzesciowke);
        testNoTrumphContractPointsThreeMore.put(3, 40+2*30  +90 + premiaZaKoncowke);
        testNoTrumphContractPointsThreeMore.put(4, 40+3*30  +90 + premiaZaKoncowke);
       //   testNoTrumphContractPointsThreeMore.put(5, 40+4*30  +90 + premiaZaKoncowke); - not possible only 13 trics

        testNoTrumphContractPointsFourMore.put(1, 40       +120 + premiaZaCzesciowke);
        testNoTrumphContractPointsFourMore.put(2, 40+30    +120 + premiaZaCzesciowke);
        testNoTrumphContractPointsFourMore.put(3, 40+2*30  +120 + premiaZaKoncowke);

        testNoTrumphContractPointsFiveMore.put(1, 40       +150 + premiaZaCzesciowke);
        testNoTrumphContractPointsFiveMore.put(2, 40+30    +150 + premiaZaCzesciowke);

        testNoTrumphContractPointsSixMore.put(1, 40        +180 + premiaZaCzesciowke);

// major more more
        testMajorContractPointsThreeMore.put(1, 30    +90 + premiaZaCzesciowke);
        testMajorContractPointsThreeMore.put(2, 2*30  +90 + premiaZaCzesciowke);
        testMajorContractPointsThreeMore.put(3, 3*30  +90 + premiaZaCzesciowke);
        testMajorContractPointsThreeMore.put(4, 4*30  +90 + premiaZaKoncowke);
        //   testNoTrumphContractPointsThreeMore.put(5, 40+4*30  +90 + premiaZaKoncowke); - not possible only 13 trics

        testMajorContractPointsFourMore.put(1, 30    +120 + premiaZaCzesciowke);
        testMajorContractPointsFourMore.put(2, 2*30  +120 + premiaZaCzesciowke);
        testMajorContractPointsFourMore.put(3, 3*30  +120 + premiaZaCzesciowke);

        testMajorContractPointsFiveMore.put(1, 30       +150 + premiaZaCzesciowke);
        testMajorContractPointsFiveMore.put(2, 2*30    +150 + premiaZaCzesciowke);

        testMajorContractPointsSixMore.put(1, 30        +180 + premiaZaCzesciowke);
//todo minor more more

        //TESTS WPADKI
        //example test jedna wpadka
        testPointsForMajorContractMinusOne.put(1, -wpadkaBezKontry);
        testPointsForMajorContractMinusOne.put(2, -wpadkaBezKontry);
        testPointsForMajorContractMinusOne.put(3, -wpadkaBezKontry);
        testPointsForMajorContractMinusOne.put(4, -wpadkaBezKontry);
        testPointsForMajorContractMinusOne.put(5, -wpadkaBezKontry);
        testPointsForMajorContractMinusOne.put(6, -wpadkaBezKontry);

        testPointsForNoTrumphContractMinusOneDouble.put(1, -2*wpadkaBezKontry);
        testPointsForNoTrumphContractMinusOneDouble.put(2, -2*wpadkaBezKontry);
        testPointsForNoTrumphContractMinusOneDouble.put(4, -2*wpadkaBezKontry);
        testPointsForNoTrumphContractMinusOneDouble.put(5, -2*wpadkaBezKontry);
        testPointsForNoTrumphContractMinusOneDouble.put(6, -2*wpadkaBezKontry);
        testPointsForNoTrumphContractMinusOneDouble.put(7, -2*wpadkaBezKontry);

//test for contract major on level 3 with missig p

        testPointsForMajor3ContractMinusMore.put(1, -wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(2, -2*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(3, -3*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(4, -4*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(5, -5*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(6, -6*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(7, -7*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(8, -8*wpadkaBezKontry);
        testPointsForMajor3ContractMinusMore.put(9, -9*wpadkaBezKontry);

        testPointsForMajor3ContractMinusMoreDouble.put(1, -wpadka1Zkontra);
        testPointsForMajor3ContractMinusMoreDouble.put(2, -wpadka1Zkontra-wpadka2Zkontra);
        testPointsForMajor3ContractMinusMoreDouble.put(3, -wpadka1Zkontra-2*wpadka2Zkontra);
        testPointsForMajor3ContractMinusMoreDouble.put(4, -wpadka1Zkontra-2*wpadka2Zkontra-300);
        testPointsForMajor3ContractMinusMoreDouble.put(5, -wpadka1Zkontra-2*wpadka2Zkontra-2*300);
        testPointsForMajor3ContractMinusMoreDouble.put(6, -wpadka1Zkontra-2*wpadka2Zkontra-3*300);
        testPointsForMajor3ContractMinusMoreDouble.put(7, -wpadka1Zkontra-2*wpadka2Zkontra-4*300);
        testPointsForMajor3ContractMinusMoreDouble.put(8, -wpadka1Zkontra-2*wpadka2Zkontra-5*300);
        testPointsForMajor3ContractMinusMoreDouble.put(9, -wpadka1Zkontra-2*wpadka2Zkontra-6*300);

       testPointsForMajor3ContractMinusMoreReDouble.put(1, 2*(-wpadka1Zkontra));
       testPointsForMajor3ContractMinusMoreReDouble.put(2, 2*(-wpadka1Zkontra-wpadka2Zkontra));
       testPointsForMajor3ContractMinusMoreReDouble.put(3, 2*(-wpadka1Zkontra-2*wpadka2Zkontra));
       testPointsForMajor3ContractMinusMoreReDouble.put(4, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-300));
       testPointsForMajor3ContractMinusMoreReDouble.put(5, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-2*300));
       testPointsForMajor3ContractMinusMoreReDouble.put(6, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-3*300));
       testPointsForMajor3ContractMinusMoreReDouble.put(7, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-4*300));
       testPointsForMajor3ContractMinusMoreReDouble.put(8, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-5*300));
       testPointsForMajor3ContractMinusMoreReDouble.put(9, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-6*300));

        //test for contract nt on level 3 with missig p

        testPointsFor3NTContractMinusMore.put(1, -wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(2, -2*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(3, -3*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(4, -4*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(5, -5*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(6, -6*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(7, -7*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(8, -8*wpadkaBezKontry);
        testPointsFor3NTContractMinusMore.put(9, -9*wpadkaBezKontry);

        testPointsFor3NTContractMinusMoreDouble.put(1, -wpadka1Zkontra);
        testPointsFor3NTContractMinusMoreDouble.put(2, -wpadka1Zkontra-wpadka2Zkontra);
        testPointsFor3NTContractMinusMoreDouble.put(3, -wpadka1Zkontra-2*wpadka2Zkontra);
        testPointsFor3NTContractMinusMoreDouble.put(4, -wpadka1Zkontra-2*wpadka2Zkontra-300);
        testPointsFor3NTContractMinusMoreDouble.put(5, -wpadka1Zkontra-2*wpadka2Zkontra-2*300);
        testPointsFor3NTContractMinusMoreDouble.put(6, -wpadka1Zkontra-2*wpadka2Zkontra-3*300);
        testPointsFor3NTContractMinusMoreDouble.put(7, -wpadka1Zkontra-2*wpadka2Zkontra-4*300);
        testPointsFor3NTContractMinusMoreDouble.put(8, -wpadka1Zkontra-2*wpadka2Zkontra-5*300);
        testPointsFor3NTContractMinusMoreDouble.put(9, -wpadka1Zkontra-2*wpadka2Zkontra-6*300);

        testPointsFor3NTContractMinusMoreReDouble.put(1, 2*(-wpadka1Zkontra));
        testPointsFor3NTContractMinusMoreReDouble.put(2, 2*(-wpadka1Zkontra-wpadka2Zkontra));
        testPointsFor3NTContractMinusMoreReDouble.put(3, 2*(-wpadka1Zkontra-2*wpadka2Zkontra));
        testPointsFor3NTContractMinusMoreReDouble.put(4, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-300));
        testPointsFor3NTContractMinusMoreReDouble.put(5, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-2*300));
        testPointsFor3NTContractMinusMoreReDouble.put(6, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-3*300));
        testPointsFor3NTContractMinusMoreReDouble.put(7, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-4*300));
        testPointsFor3NTContractMinusMoreReDouble.put(8, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-5*300));
        testPointsFor3NTContractMinusMoreReDouble.put(9, 2*(-wpadka1Zkontra-2*wpadka2Zkontra-6*300));
    }


    @Test
    public void testMajorContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMajorContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p+6).getContractScoringPoints();
            Integer resA = new DuplicateBridgeScoring(p, "s", 1, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym wynik jest: " + res +"/" + resA +" punktów.");
            Assert.assertEquals(testPointsForMajorContract.get(p), res);
            Assert.assertEquals(testPointsForMajorContract.get(p), resA);
        }
  //  }
  //  @Test
  //  public void testMajorDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMajorDoubleContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", 2, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorDoubleContract.get(p), res);
        }
   // }
  //  @Test
  //  public void testMajorReDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMajorReDoubleContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", 4, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorReDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMinorContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMinorContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "d", false, false, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContract.get(p), res);
        }
 //  }
 //   @Test
 //   public void testMinorDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMinorDoubleContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "d", true, false, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorDoubleContract.get(p), res);
        }
 //   }
 //   @Test
 //   public void testMinorReDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMinorReDoubleContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "d", false, true, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorReDoubleContract.get(p), res);
        }
    }

    @Test
    public void testNoTrumphContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "n", false, false, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContract.get(p), res);
        }
//    }
//    @Test
//    public void testNoTrumphDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphDoubleContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "n", true, false, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphDoubleContract.get(p), res);
        }
//    }
//    @Test
//    public void testNoTrumphReDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphReDoubleContract.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "n", false, true, beforeAfter, p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphReDoubleContract.get(p), res);
        }
    }

    //TESTS NADRÓBKI


    @Test
    public void testMinorContractOneMore() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "d", false, false, beforeAfter, p + 1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym  z jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMore.get(p), res);
            //   }
        }
    }

    @Test
    public void testMinorContractOneMoreDouble() throws BridgeException {
    //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "d", true, false, beforeAfter, p+1+6).getContractScoringPoints();
                 logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMoreDouble.get(p), res);
      //  }
    }}
    @Test
    public void testNoTrumphContractOneMoreDouble()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", true, false, beforeAfter, p+1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMoreDouble.get(p), res);
            //  }
        }}
    @Test
    public void testMajorContractOneMoreDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMoreDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", true, false, beforeAfter, p + 1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z kontrą i jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractOneMoreDouble.get(p), res);
            //   }
        }
           }



    @Test
    public void testMinorContractOneMoreReDouble() throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreReDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "d", false, true, beforeAfter, p+1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMoreReDouble.get(p), res);
            //  }
        }}
    @Test
    public void testNoTrumphContractOneMoreReDouble()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreReDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, true, beforeAfter, p+1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z rekotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMoreReDouble.get(p), res);
            //  }
        }}
    @Test
    public void testMajorContractOneMoreReDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMoreReDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, true, beforeAfter, p + 1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z rekontrą i jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractOneMoreReDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testMajorContractOverTricks() throws BridgeException {
  //  public void testMajorContractOneMore() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p + 1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractOneMore.get(p), res);
            //   }
        }
 //   }
 //   @Test
 //   public void testMajorContractTwoMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsTwoMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p+2 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsTwoMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractThreeMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsThreeMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p+3+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsThreeMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractFourMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsFourMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p+4 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z czterema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsFourMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractFiveMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsFiveMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p+5 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsFiveMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractSixMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsSixMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p+6 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy   z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsSixMore.get(p), res);
            //  }
        }
    }

    //
    @Test
    public void testNoTrumphContractOverTricks()  throws BridgeException {
 //   public void testNoTrumphContractOneMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p+1 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMore.get(p), res);
            //  }
        }
 //   }
 //   @Test
 //   public void testNoTrumphContractTwoMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsTwoMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p+2 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsTwoMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractThreeMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsThreeMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p+3+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsThreeMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractFourMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFourMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p+4+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z czterema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsFourMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractFiveMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFiveMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p+5+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsFiveMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractSixMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsSixMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "nt", false, false, beforeAfter, p+6 +6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsSixMore.get(p), res);
            //  }
        }
    }

    // TESTS WPADKI


    @Test
    public void testMajorContractMinusOne() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractMinusOne.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "s", false, false, beforeAfter, p - 1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  bez jednej wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractMinusOne.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumphContractMinusOneDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContractMinusOneDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(p, "n", true, false, beforeAfter, p - 1+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu  bez jednej z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContractMinusOneDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testMajor3ContractMinusMore() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajor3ContractMinusMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(3, "s", false, false, beforeAfter, 3 - p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w kolorze starszym  bez " + p +" wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajor3ContractMinusMore.get(p), res);
            //   }
        }
    }
    @Test
    public void testMajor3ContractMinusMoreDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajor3ContractMinusMoreDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(3, "nt", true, false, beforeAfter, 3 - p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w starszym  bez  "+ p+ "  z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajor3ContractMinusMoreDouble.get(p), res);
            //   }
        }
    }
    @Test
    public void testMajor3ContractMinusMoreReDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajor3ContractMinusMoreReDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(3, "nt", true, true, beforeAfter, 3 - p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w starszym  bez  "+ p+ "  z rekontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajor3ContractMinusMoreReDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumph3ContractMinusMore() throws  BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsFor3NTContractMinusMore.keySet())) {
            Integer res = new DuplicateBridgeScoring(3, "s", false, false, beforeAfter, 3 - p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez " + p +" wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsFor3NTContractMinusMore.get(p), res);
            //   }
        }
    }
    @Test
    public void testNoTrumph3ContractMinusMoreDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsFor3NTContractMinusMoreDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(3, "nt", true, false, beforeAfter, 3 - p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu bez  "+ p+ "  z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsFor3NTContractMinusMoreDouble.get(p), res);
            //   }
        }
    }
    @Test
    public void testNoTrumph3ContractMinusMoreReDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsFor3NTContractMinusMoreReDouble.keySet())) {
            Integer res = new DuplicateBridgeScoring(3, "nt", true, true, beforeAfter, 3 - p+6).getContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez  "+ p+ "  z rekontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsFor3NTContractMinusMoreReDouble.get(p), res);
            //   }
        }
    }

}
