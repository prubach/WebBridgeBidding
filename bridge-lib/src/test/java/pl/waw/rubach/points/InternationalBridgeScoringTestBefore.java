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
 * 20 test of diferent case of counting points for one deal according to International bridge scorring
 * with assumption unvunerability (befor) - to see afrer - class before :)
 */
public class InternationalBridgeScoringTestBefore {

    //all variable below are protected - possible use only for class which extend thsi (so it is less then package-private -as idea propose!)
    //because we dont want to other test form this package use some of this constants by mistake
    protected boolean beforeAfter = false;
    protected int karaZaKontre = 50;
    protected int premiaZaCzesciowke = 0;//50;
    protected int premiaZaKoncowke = 0; //300;
    protected int premiaZaSzlemika = 500;
    protected int premiaZaSzlema = 1000;
    protected int nadrobkaZKontra = 100;
    protected int wpadkaBezKontry = 50;
    protected int wpadka1Zkontra = 100;
    protected int wpadka2Zkontra = 200;
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
    protected Map<Integer, Integer> testMajorContractPointsTwoMore = new HashMap<>();
    protected Map<Integer, Integer> testMajorContractPointsThreeMore = new HashMap<>();
    protected Map<Integer, Integer> testMajorContractPointsFourMore = new HashMap<>();
    protected Map<Integer, Integer> testMajorContractPointsFiveMore = new HashMap<>();
    protected Map<Integer, Integer> testMajorContractPointsSixMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsTwoMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsThreeMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsFourMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsFiveMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsSixMore = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsThreeMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMajorContractOneMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorContractOneMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsOneMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMajorContractOneMoreReDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForMinorContractOneMoreReDouble = new HashMap<>();
    protected Map<Integer, Integer> testNoTrumphContractPointsOneMoreReDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsContractMinusOne = new HashMap<>();
    protected Map<Integer, Integer> testPointsContractMinusOneDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsContractMinusMore = new HashMap<>();
    protected Map<Integer, Integer> testPointsForContractMinusMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForContractMinusMoreReDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForContractLevel3MinusMore = new HashMap<>();
    protected Map<Integer, Integer> testPointsForContractLevel3MinusMoreDouble = new HashMap<>();
    protected Map<Integer, Integer> testPointsForContractLevel3MinusMoreReDouble = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @Before
    public void fillTestPointsMap() {
        //testPointsMap.put()

        //all equal normal contract
        testPointsForMajorContract.put(0,0);
        testPointsForMajorContract.put(1, 30 + premiaZaCzesciowke);
        testPointsForMajorContract.put(2, 60 + premiaZaCzesciowke);
        testPointsForMajorContract.put(3, 90 + premiaZaCzesciowke);
        testPointsForMajorContract.put(4, 120 + premiaZaKoncowke);
        testPointsForMajorContract.put(5, 150 + premiaZaKoncowke);
        testPointsForMajorContract.put(6, 180 + premiaZaKoncowke + premiaZaSzlemika);
        testPointsForMajorContract.put(7, 210 + premiaZaKoncowke + premiaZaSzlema);

        testPointsForMinorContract.put(0,0);
        testPointsForMinorContract.put(1, 20 + premiaZaCzesciowke);
        testPointsForMinorContract.put(2, 40 + premiaZaCzesciowke);
        testPointsForMinorContract.put(3, 60 + premiaZaCzesciowke);
        testPointsForMinorContract.put(4, 80 + premiaZaCzesciowke);
        testPointsForMinorContract.put(5, 100 + premiaZaKoncowke);
        testPointsForMinorContract.put(6, 120 + premiaZaKoncowke + premiaZaSzlemika);
        testPointsForMinorContract.put(7, 140 + premiaZaKoncowke + premiaZaSzlema);

        testPointsForNoTrumphContract.put(0,0);
        testPointsForNoTrumphContract.put(1, 40 + premiaZaCzesciowke);
        testPointsForNoTrumphContract.put(2, 70 + premiaZaCzesciowke);
        testPointsForNoTrumphContract.put(3, 100 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(4, 130 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(5, 160 + premiaZaKoncowke);
        testPointsForNoTrumphContract.put(6, 190 + premiaZaKoncowke + premiaZaSzlemika);
        testPointsForNoTrumphContract.put(7, 220 + premiaZaKoncowke + premiaZaSzlema);

        //all of double contract

        testPointsForMajorDoubleContract.put(1, 2 * 30 + karaZaKontre + premiaZaCzesciowke);
        testPointsForMajorDoubleContract.put(2, 2 * 60 + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(3, 2 * 90 + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(4, 2 * 120 + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(5, 2 * 150 + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorDoubleContract.put(6, 2 * 180 + karaZaKontre + premiaZaKoncowke + premiaZaSzlemika);
        testPointsForMajorDoubleContract.put(7, 2 * 210 + karaZaKontre + premiaZaKoncowke + premiaZaSzlema);

        testPointsForMinorDoubleContract.put(1, 2 * 20 + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorDoubleContract.put(2, 2 * 40 + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorDoubleContract.put(3, 2 * 60 + karaZaKontre + premiaZaKoncowke);
        testPointsForMinorDoubleContract.put(4, 2 * 80 + karaZaKontre + premiaZaKoncowke);
        testPointsForMinorDoubleContract.put(5, 2 * 100 + karaZaKontre + premiaZaKoncowke);
        testPointsForMinorDoubleContract.put(6, 2 * 120 + karaZaKontre + premiaZaKoncowke + premiaZaSzlemika);
        testPointsForMinorDoubleContract.put(7, 2 * 140 + karaZaKontre + premiaZaKoncowke + premiaZaSzlema);

        testPointsForNoTrumphDoubleContract.put(1, 40 * 2 + premiaZaCzesciowke + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(2, (40 + 30) * 2 + premiaZaKoncowke + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(3, 100 * 2 + premiaZaKoncowke + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(4, 130 * 2 + premiaZaKoncowke + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(5, 160 * 2 + premiaZaKoncowke + karaZaKontre);
        testPointsForNoTrumphDoubleContract.put(6, 190 * 2 + premiaZaKoncowke + karaZaKontre + premiaZaSzlemika);
        testPointsForNoTrumphDoubleContract.put(7, 220 * 2 + premiaZaKoncowke + karaZaKontre + premiaZaSzlema);

        //all of redouble contract

        testPointsForMajorReDoubleContract.put(1, 4 * 30 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMajorReDoubleContract.put(2, 4 * 2 * 30 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMajorReDoubleContract.put(3, 4 * 3 * 30 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMajorReDoubleContract.put(4, 4 * 4 * 30 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMajorReDoubleContract.put(5, 4 * 5 * 30 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMajorReDoubleContract.put(6, 4 * 6 * 30 + premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlemika);
        testPointsForMajorReDoubleContract.put(7, 4 * 7 * 30 + premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlema);

        testPointsForMinorReDoubleContract.put(1, 4 * 20 + premiaZaCzesciowke + 2 * karaZaKontre);
        testPointsForMinorReDoubleContract.put(2, 4 * 2 * 20 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMinorReDoubleContract.put(3, 4 * 3 * 20 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMinorReDoubleContract.put(4, 4 * 4 * 20 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMinorReDoubleContract.put(5, 4 * 5 * 20 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForMinorReDoubleContract.put(6, 4 * 6 * 20 + premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlemika);
        testPointsForMinorReDoubleContract.put(7, 4 * 7 * 20 + premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlema);

        testPointsForNoTrumphReDoubleContract.put(1, 4 * 40 + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(2, 4 * (40 + 30) + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(3, 4 * (40 + 2 * 30) + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(4, 4 * (40 + 3 * 30) + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(5, 4 * (40 + 4 * 30) + premiaZaKoncowke + 2 * karaZaKontre);
        testPointsForNoTrumphReDoubleContract.put(6, 4 * (40 + 5 * 30) + premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlemika);
        testPointsForNoTrumphReDoubleContract.put(7, 4 * (40 + 6 * 30) + premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlema);


        //all of Nadróbki - one more
        testPointsForMajorContractOneMore.put(1, 30 + 30 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(2, 60 + 30 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(3, 90 + 30 + premiaZaCzesciowke);
        testPointsForMajorContractOneMore.put(4, 120 + 30 + premiaZaKoncowke);
        testPointsForMajorContractOneMore.put(5, 150 + 30 + premiaZaKoncowke);
        testPointsForMajorContractOneMore.put(6, 180 + 30 + premiaZaKoncowke + premiaZaSzlemika);
        //  testPointsForMajorContractOneMore.put(7, 240 +300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testPointsForMinorContractOneMore.put(1, 20 + 20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(2, 40 + 20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(3, 60 + 20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(4, 80 + 20 + premiaZaCzesciowke);
        testPointsForMinorContractOneMore.put(5, 100 + 20 + premiaZaKoncowke);
        testPointsForMinorContractOneMore.put(6, 120 + 20 + premiaZaKoncowke + premiaZaSzlemika);
        //  testPointsForMajorContractOneMore.put(7, 240 +300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testNoTrumphContractPointsOneMore.put(1, 40 + 30 + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMore.put(2, 40 + 30 + 30 + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMore.put(3, 40 + 30 * 2 + 30 + premiaZaKoncowke);
        testNoTrumphContractPointsOneMore.put(4, 40 + 30 * 3 + 30 + premiaZaKoncowke);
        testNoTrumphContractPointsOneMore.put(5, 40 + 30 * 4 + 30 + premiaZaKoncowke);
        testNoTrumphContractPointsOneMore.put(6, 40 + 30 * 5 + 30 + premiaZaKoncowke + premiaZaSzlemika);

        testNoTrumphContractPointsOneMoreDouble.put(1, 2 * 40 + nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke);
        testNoTrumphContractPointsOneMoreDouble.put(2, 2 * (40 + 30) + nadrobkaZKontra + +premiaZaKoncowke + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(3, 2 * (40 + 2 * 30) + nadrobkaZKontra + +premiaZaKoncowke + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(4, 2 * (40 + 3 * 30) + nadrobkaZKontra + +premiaZaKoncowke + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(5, 2 * (40 + 4 * 30) + nadrobkaZKontra + +premiaZaKoncowke + karaZaKontre);
        testNoTrumphContractPointsOneMoreDouble.put(6, 2 * (40 + 5 * 30) + nadrobkaZKontra + +premiaZaKoncowke + karaZaKontre + premiaZaSzlemika);

        testPointsForMajorContractOneMoreDouble.put(1, 2 * 30 + nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMajorContractOneMoreDouble.put(2, 2 * 60 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(3, 2 * 90 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(4, 2 * 120 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(5, 2 * 150 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreDouble.put(6, 2 * 180 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke + premiaZaSzlemika);
        // testPointsForMajorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testPointsForMinorContractOneMoreDouble.put(1, 2 * 20 + nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMinorContractOneMoreDouble.put(2, 2 * 40 + nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorContractOneMoreDouble.put(3, 2 * 60 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(4, 2 * 80 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(5, 2 * 100 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(6, 2 * 120 + nadrobkaZKontra + karaZaKontre + premiaZaKoncowke + premiaZaSzlemika);
        // testPointsForMinorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!


        testNoTrumphContractPointsOneMoreReDouble.put(1, 4 * 40 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testNoTrumphContractPointsOneMoreReDouble.put(2, 4 * (40 + 30) + 2 * nadrobkaZKontra + +premiaZaKoncowke + 2 * karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(3, 4 * (40 + 2 * 30) + 2 * nadrobkaZKontra + +premiaZaKoncowke + 2 * karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(4, 4 * (40 + 3 * 30) + 2 * nadrobkaZKontra + +premiaZaKoncowke + 2 * karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(5, 4 * (40 + 4 * 30) + 2 * nadrobkaZKontra + +premiaZaKoncowke + 2 * karaZaKontre);
        testNoTrumphContractPointsOneMoreReDouble.put(6, 4 * (40 + 5 * 30) + 2 * nadrobkaZKontra + +premiaZaKoncowke + 2 * karaZaKontre + premiaZaSzlemika);

        testPointsForMajorContractOneMoreReDouble.put(1, 4 * 30 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMajorContractOneMoreReDouble.put(2, 4 * 60 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(3, 4 * 90 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(4, 4 * 120 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(5, 4 * 150 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMajorContractOneMoreReDouble.put(6, 4 * 180 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke + premiaZaSzlemika);
        // testPointsForMajorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!

        testPointsForMinorContractOneMoreReDouble.put(1, 4 * 20 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMinorContractOneMoreReDouble.put(2, 4 * 40 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(3, 4 * 60 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(4, 4 * 80 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(5, 4 * 100 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreReDouble.put(6, 4 * 120 + 2 * nadrobkaZKontra + 2 * karaZaKontre + premiaZaKoncowke + premiaZaSzlemika);
        // testPointsForMinorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!


        //example of two more
        testNoTrumphContractPointsTwoMore.put(1, 40 + 60 + premiaZaCzesciowke);
        testNoTrumphContractPointsTwoMore.put(2, 40 + 30 + 60 + premiaZaCzesciowke);
        testNoTrumphContractPointsTwoMore.put(3, 40 + 2 * 30 + 60 + premiaZaKoncowke);
        testNoTrumphContractPointsTwoMore.put(4, 40 + 3 * 30 + 60 + premiaZaKoncowke);
        testNoTrumphContractPointsTwoMore.put(5, 40 + 4 * 30 + 60 + premiaZaKoncowke);
        //     testNoTrumphContractPointsTwoMore.put(6, 40+5*30  +60 + premiaZaKoncowke); Nie może być 6 i dwie lepiej

        testMajorContractPointsTwoMore.put(1, 30 + 60 + premiaZaCzesciowke);
        testMajorContractPointsTwoMore.put(2, 2 * 30 + 60 + premiaZaCzesciowke);
        testMajorContractPointsTwoMore.put(3, 3 * 30 + 60 + premiaZaCzesciowke);
        testMajorContractPointsTwoMore.put(4, 4 * 30 + 60 + premiaZaKoncowke);
        testMajorContractPointsTwoMore.put(5, 5 * 30 + 60 + premiaZaKoncowke);
        //     testMajorContractPointsTwoMore.put(6, 6*30  +60 + premiaZaKoncowke); Nie może być 6 i dwie lepiej


//todo add double and redouble example (and minor  ew.)

        //example of nt more more only on nt
        testNoTrumphContractPointsThreeMore.put(1, 40 + 90 + premiaZaCzesciowke);
        testNoTrumphContractPointsThreeMore.put(2, 40 + 30 + 90 + premiaZaCzesciowke);
        testNoTrumphContractPointsThreeMore.put(3, 40 + 2 * 30 + 90 + premiaZaKoncowke);
        testNoTrumphContractPointsThreeMore.put(4, 40 + 3 * 30 + 90 + premiaZaKoncowke);
        //   testNoTrumphContractPointsThreeMore.put(5, 40+4*30  +90 + premiaZaKoncowke); - not possible only 13 trics

        testNoTrumphContractPointsFourMore.put(1, 40 + 120 + premiaZaCzesciowke);
        testNoTrumphContractPointsFourMore.put(2, 40 + 30 + 120 + premiaZaCzesciowke);
        testNoTrumphContractPointsFourMore.put(3, 40 + 2 * 30 + 120 + premiaZaKoncowke);

        testNoTrumphContractPointsFiveMore.put(1, 40 + 150 + premiaZaCzesciowke);
        testNoTrumphContractPointsFiveMore.put(2, 40 + 30 + 150 + premiaZaCzesciowke);

        testNoTrumphContractPointsSixMore.put(1, 40 + 180 + premiaZaCzesciowke);

        //example of nt more more only on nt
        testNoTrumphContractPointsThreeMoreDouble.put(1, 2 * 40 + 3 * nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke);
        testNoTrumphContractPointsThreeMoreDouble.put(2, 2 * (40 + 30) + 3 * nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testNoTrumphContractPointsThreeMoreDouble.put(3, 2 * (40 + 2 * 30) + 3 * nadrobkaZKontra + karaZaKontre + premiaZaKoncowke);
        testNoTrumphContractPointsThreeMoreDouble.put(4, 2 * (40 + 3 * 30) + 3 * nadrobkaZKontra + karaZaKontre + +premiaZaKoncowke);
        //   testNoTrumphContractPointsThreeMore.put(5, 40+4*30  +90 + premiaZaKoncowke); - not possible only 13 trics

        testNoTrumphContractPointsFourMore.put(1, 40 + 120 + premiaZaCzesciowke);
        testNoTrumphContractPointsFourMore.put(2, 40 + 30 + 120 + premiaZaCzesciowke);
        testNoTrumphContractPointsFourMore.put(3, 40 + 2 * 30 + 120 + premiaZaKoncowke);

        testNoTrumphContractPointsFiveMore.put(1, 40 + 150 + premiaZaCzesciowke);
        testNoTrumphContractPointsFiveMore.put(2, 40 + 30 + 150 + premiaZaCzesciowke);

        testNoTrumphContractPointsSixMore.put(1, 40 + 180 + premiaZaCzesciowke);

// major more more
        testMajorContractPointsThreeMore.put(1, 30 + 90 + premiaZaCzesciowke);
        testMajorContractPointsThreeMore.put(2, 2 * 30 + 90 + premiaZaCzesciowke);
        testMajorContractPointsThreeMore.put(3, 3 * 30 + 90 + premiaZaCzesciowke);
        testMajorContractPointsThreeMore.put(4, 4 * 30 + 90 + premiaZaKoncowke);
        //   testNoTrumphContractPointsThreeMore.put(5, 40+4*30  +90 + premiaZaKoncowke); - not possible only 13 trics

        testMajorContractPointsFourMore.put(1, 30 + 120 + premiaZaCzesciowke);
        testMajorContractPointsFourMore.put(2, 2 * 30 + 120 + premiaZaCzesciowke);
        testMajorContractPointsFourMore.put(3, 3 * 30 + 120 + premiaZaCzesciowke);

        testMajorContractPointsFiveMore.put(1, 30 + 150 + premiaZaCzesciowke);
        testMajorContractPointsFiveMore.put(2, 2 * 30 + 150 + premiaZaCzesciowke);

        testMajorContractPointsSixMore.put(1, 30 + 180 + premiaZaCzesciowke);


        //TESTS WPADKI - all possibilitis
        //example test jedna wpadka  (wysokość kontraktu, oczekiwany wynik)
        testPointsContractMinusOne.put(1, -wpadkaBezKontry);
        testPointsContractMinusOne.put(2, -wpadkaBezKontry);
        testPointsContractMinusOne.put(3, -wpadkaBezKontry);
        testPointsContractMinusOne.put(4, -wpadkaBezKontry);
        testPointsContractMinusOne.put(5, -wpadkaBezKontry);
        testPointsContractMinusOne.put(6, -wpadkaBezKontry);

        testPointsContractMinusOneDouble.put(1, -2 * wpadkaBezKontry);
        testPointsContractMinusOneDouble.put(2, -2 * wpadkaBezKontry);
        testPointsContractMinusOneDouble.put(4, -2 * wpadkaBezKontry);
        testPointsContractMinusOneDouble.put(5, -2 * wpadkaBezKontry);
        testPointsContractMinusOneDouble.put(6, -2 * wpadkaBezKontry);
        testPointsContractMinusOneDouble.put(7, -2 * wpadkaBezKontry);

        //test for contract  with missig p tricks - all possibilites

        for (int i = 1; i <= 13; i++) {
            testPointsContractMinusMore.put(i, -i * wpadkaBezKontry);
        }
/*
        testPointsContractMinusMore.put(1, -wpadkaBezKontry);
        testPointsContractMinusMore.put(2, -2 * wpadkaBezKontry);
        testPointsContractMinusMore.put(3, -3 * wpadkaBezKontry);
        testPointsContractMinusMore.put(4, -4 * wpadkaBezKontry);
        testPointsContractMinusMore.put(5, -5 * wpadkaBezKontry);
        testPointsContractMinusMore.put(6, -6 * wpadkaBezKontry);
        testPointsContractMinusMore.put(7, -7 * wpadkaBezKontry);
        testPointsContractMinusMore.put(8, -8 * wpadkaBezKontry);
        testPointsContractMinusMore.put(9, -9 * wpadkaBezKontry);
        testPointsContractMinusMore.put(10, -10 * wpadkaBezKontry);
        testPointsContractMinusMore.put(11, -11 * wpadkaBezKontry);
        testPointsContractMinusMore.put(12, -12 * wpadkaBezKontry);
        testPointsContractMinusMore.put(13, -13 * wpadkaBezKontry);
*/
        testPointsForContractMinusMoreDouble.put(1, -wpadka1Zkontra);
        testPointsForContractMinusMoreDouble.put(2, -wpadka1Zkontra - wpadka2Zkontra);
        testPointsForContractMinusMoreDouble.put(3, -wpadka1Zkontra - 2 * wpadka2Zkontra);

        for (int i = 4; i <= 13; i++) {
            testPointsForContractMinusMoreDouble.put(i, -wpadka1Zkontra - 2 * wpadka2Zkontra - (i - 3) * 300);
        }
        /*
        testPointsForContractMinusMoreDouble.put(4, -wpadka1Zkontra - 2 * wpadka2Zkontra - 300);
        testPointsForContractMinusMoreDouble.put(5, -wpadka1Zkontra - 2 * wpadka2Zkontra - 2 * 300);
        testPointsForContractMinusMoreDouble.put(6, -wpadka1Zkontra - 2 * wpadka2Zkontra - 3 * 300);
        testPointsForContractMinusMoreDouble.put(7, -wpadka1Zkontra - 2 * wpadka2Zkontra - 4 * 300);
        testPointsForContractMinusMoreDouble.put(8, -wpadka1Zkontra - 2 * wpadka2Zkontra - 5 * 300);
        testPointsForContractMinusMoreDouble.put(9, -wpadka1Zkontra - 2 * wpadka2Zkontra - 6 * 300);
        testPointsForContractMinusMoreDouble.put(10, -wpadka1Zkontra - 2 * wpadka2Zkontra - 7 * 300);
        testPointsForContractMinusMoreDouble.put(11, -wpadka1Zkontra - 2 * wpadka2Zkontra - 8 * 300);
        testPointsForContractMinusMoreDouble.put(12, -wpadka1Zkontra - 2 * wpadka2Zkontra - 9 * 300);
        testPointsForContractMinusMoreDouble.put(13, -wpadka1Zkontra - 2 * wpadka2Zkontra - 10 * 300);
*/
        testPointsForContractMinusMoreReDouble.put(1, 2 * (-wpadka1Zkontra));
        testPointsForContractMinusMoreReDouble.put(2, 2 * (-wpadka1Zkontra - wpadka2Zkontra));
        testPointsForContractMinusMoreReDouble.put(3, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra));

        for (int i = 4; i <= 13; i++) {
            testPointsForContractMinusMoreReDouble.put(i, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - (i - 3) * 300));
        }
        /*
        testPointsForContractMinusMoreReDouble.put(4, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 300));
        testPointsForContractMinusMoreReDouble.put(5, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 2 * 300));
        testPointsForContractMinusMoreReDouble.put(6, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 3 * 300));
        testPointsForContractMinusMoreReDouble.put(7, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 4 * 300));
        testPointsForContractMinusMoreReDouble.put(8, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 5 * 300));
        testPointsForContractMinusMoreReDouble.put(9, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 6 * 300));
        testPointsForContractMinusMoreReDouble.put(10, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 7 * 300));
        testPointsForContractMinusMoreReDouble.put(11, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 8 * 300));
        testPointsForContractMinusMoreReDouble.put(12, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 9 * 300));
        testPointsForContractMinusMoreReDouble.put(13, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 10 * 300));
*/
        //test for contract nt on level 3 with missig p - not nessesery (is included above)

        testPointsForContractLevel3MinusMore.put(1, -wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(2, -2 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(3, -3 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(4, -4 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(5, -5 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(6, -6 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(7, -7 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(8, -8 * wpadkaBezKontry);
        testPointsForContractLevel3MinusMore.put(9, -9 * wpadkaBezKontry);

        testPointsForContractLevel3MinusMoreDouble.put(1, -wpadka1Zkontra);
        testPointsForContractLevel3MinusMoreDouble.put(2, -wpadka1Zkontra - wpadka2Zkontra);
        testPointsForContractLevel3MinusMoreDouble.put(3, -wpadka1Zkontra - 2 * wpadka2Zkontra);
        testPointsForContractLevel3MinusMoreDouble.put(4, -wpadka1Zkontra - 2 * wpadka2Zkontra - 300);
        testPointsForContractLevel3MinusMoreDouble.put(5, -wpadka1Zkontra - 2 * wpadka2Zkontra - 2 * 300);
        testPointsForContractLevel3MinusMoreDouble.put(6, -wpadka1Zkontra - 2 * wpadka2Zkontra - 3 * 300);
        testPointsForContractLevel3MinusMoreDouble.put(7, -wpadka1Zkontra - 2 * wpadka2Zkontra - 4 * 300);
        testPointsForContractLevel3MinusMoreDouble.put(8, -wpadka1Zkontra - 2 * wpadka2Zkontra - 5 * 300);
        testPointsForContractLevel3MinusMoreDouble.put(9, -wpadka1Zkontra - 2 * wpadka2Zkontra - 6 * 300);

        testPointsForContractLevel3MinusMoreReDouble.put(1, 2 * (-wpadka1Zkontra));
        testPointsForContractLevel3MinusMoreReDouble.put(2, 2 * (-wpadka1Zkontra - wpadka2Zkontra));
        testPointsForContractLevel3MinusMoreReDouble.put(3, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra));
        testPointsForContractLevel3MinusMoreReDouble.put(4, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 300));
        testPointsForContractLevel3MinusMoreReDouble.put(5, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 2 * 300));
        testPointsForContractLevel3MinusMoreReDouble.put(6, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 3 * 300));
        testPointsForContractLevel3MinusMoreReDouble.put(7, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 4 * 300));
        testPointsForContractLevel3MinusMoreReDouble.put(8, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 5 * 300));
        testPointsForContractLevel3MinusMoreReDouble.put(9, 2 * (-wpadka1Zkontra - 2 * wpadka2Zkontra - 6 * 300));
    }


    @Test
    public void testMajorContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMajorContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            Integer resA = new InternationalBridgeScoring(p, "s", 1, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym wynik jest: " + res + "/" + resA + " punktów.");
            Assert.assertEquals(testPointsForMajorContract.get(p), res);
            Assert.assertEquals(testPointsForMajorContract.get(p), resA);
        }
        //  }
        //  @Test
        //  public void testMajorDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMajorDoubleContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", 2, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorDoubleContract.get(p), res);
        }
        // }
        //  @Test
        //  public void testMajorReDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMajorReDoubleContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", 4, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorReDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMinorContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMinorContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "d", false, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContract.get(p), res);
        }
        //  }
        //   @Test
        //   public void testMinorDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMinorDoubleContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "d", true, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorDoubleContract.get(p), res);
        }
        //   }
        //   @Test
        //   public void testMinorReDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForMinorReDoubleContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "d", false, true, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorReDoubleContract.get(p), res);
        }
    }

    @Test
    public void testNoTrumphContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "n", false, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContract.get(p), res);
        }
//    }
//    @Test
//    public void testNoTrumphDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphDoubleContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "n", true, false, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphDoubleContract.get(p), res);
        }
//    }
//    @Test
//    public void testNoTrumphReDoubleContract() throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphReDoubleContract.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "n", false, true, beforeAfter, p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphReDoubleContract.get(p), res);
        }
    }

    //TESTS NADRÓBKI


    @Test
    public void testMinorContractOneMore() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "d", false, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym  z jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMore.get(p), res);
            //   }
        }
    }

    @Test
    public void testMinorContractOneMoreDouble() throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "d", true, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMoreDouble.get(p), res);
            //  }
        }
    }

    @Test
    public void testNoTrumphContractOneMoreDouble() throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", true, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMoreDouble.get(p), res);
            //  }
        }
    }

    @Test
    public void testMajorContractOneMoreDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMoreDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", true, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z kontrą i jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractOneMoreDouble.get(p), res);
            //   }
        }
    }


    @Test
    public void testMinorContractOneMoreReDouble() throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreReDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "d", false, true, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMoreReDouble.get(p), res);
            //  }
        }
    }

    @Test
    public void testNoTrumphContractOneMoreReDouble() throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreReDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, true, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z rekotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMoreReDouble.get(p), res);
            //  }
        }
    }

    @Test
    public void testMajorContractOneMoreReDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMoreReDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, true, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
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
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractOneMore.get(p), res);
            //   }
        }
        //   }
        //   @Test
        //   public void testMajorContractTwoMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsTwoMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 2 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsTwoMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractThreeMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsThreeMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 3 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsThreeMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractFourMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsFourMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 4 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z czterema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsFourMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractFiveMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsFiveMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 5 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsFiveMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testMajorContractSixMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsSixMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "s", false, false, beforeAfter, p + 6 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy   z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsSixMore.get(p), res);
            //  }
        }
    }

    //
    @Test
    public void testNoTrumphContractOverTricks() throws BridgeException {
        //   public void testNoTrumphContractOneMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, false, beforeAfter, p + 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMore.get(p), res);
            //  }
        }
        //   }
        //   @Test
        //   public void testNoTrumphContractTwoMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsTwoMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, false, beforeAfter, p + 2 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsTwoMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractThreeMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsThreeMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, false, beforeAfter, p + 3 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsThreeMore.get(p), res);
            //  }
        }
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsThreeMoreDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", true, false, beforeAfter, p + 3 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kontrą i  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsThreeMoreDouble.get(p), res);
            //  }
        }
        //    }
//    @Test
//    public void testNoTrumphContractFourMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFourMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, false, beforeAfter, p + 4 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z czterema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsFourMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractFiveMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFiveMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, false, beforeAfter, p + 5 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsFiveMore.get(p), res);
            //  }
        }
//    }
//    @Test
//    public void testNoTrumphContractSixMore()  throws BridgeException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsSixMore.keySet())) {
            Integer res = new InternationalBridgeScoring(p, "nt", false, false, beforeAfter, p + 6 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsSixMore.get(p), res);
            //  }
        }
    }

    // TESTS WPADKI


    @Test
    public void testAllContractMinusOne() throws BridgeException {
        testsContractMinusOne("s", "starszym");
        testsContractMinusOne("c", "młodszym");
        testsContractMinusOne("nt", "bez atu");
    }

    protected void testsContractMinusOne(String suit, String suitDes) throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsContractMinusOne.keySet())) {
            Integer res = new InternationalBridgeScoring(p, suit, false, false, beforeAfter, p - 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w color: " + suitDes + "bez jednej wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsContractMinusOne.get(p), res);
            //   }
        }
    }


    @Test
    public void testAllContractMinusOneDouble() throws BridgeException {
        testsContractMinusOneDouble("s", "starszym");
        testsContractMinusOneDouble("d", "młodszym");
        testsContractMinusOneDouble("nt", "bez atu");
    }

    protected void testsContractMinusOneDouble(String suit, String suitDes) throws BridgeException {
        for (int p : new TreeSet<Integer>(testPointsContractMinusOneDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(p, suit, true, false, beforeAfter, p - 1 + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze: " + suitDes + "  bez jednej z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsContractMinusOneDouble.get(p), res);
        }
    }

    @Test
    public void testContractAllLevelMinusMoreAll() throws BridgeException {

        for (int level = 1; level < 8; level++) {

            testsContractMinusMore(testPointsContractMinusMore, "s", "starszym", level, 1, " ");
            testsContractMinusMore(testPointsContractMinusMore, "h", "starszym", level, 1, " ");
            testsContractMinusMore(testPointsContractMinusMore, "d", "młodszym", level, 1, " ");
            testsContractMinusMore(testPointsContractMinusMore, "nt", "bez atu", level, 1, " ");

            testsContractMinusMore(testPointsForContractMinusMoreDouble, "s", "starszym", level, 2, " z kontrą ");
            testsContractMinusMore(testPointsForContractMinusMoreDouble, "d", "młodszym", level, 2, " z kontrą ");
            testsContractMinusMore(testPointsForContractMinusMoreDouble, "nt", "bez atu", level, 2, " z kontrą ");

            testsContractMinusMore(testPointsForContractMinusMoreReDouble, "s", "starszym", level, 4, " z rekontrą ");
            testsContractMinusMore(testPointsForContractMinusMoreReDouble, "d", "młodszym", level, 4, " z rekontrą ");
            testsContractMinusMore(testPointsForContractMinusMoreReDouble, "nt", "bez atu", level, 4, " z rekontrą ");
        }
    }

    protected void testsContractMinusMore(Map map, String suit, String suitDes, int level, int doubleRe, String doubleReDes) throws BridgeException {
        for (int p : new TreeSet<Integer>(map.keySet())) {
            if (level + 6 - p >= 0) {
                Integer res = new InternationalBridgeScoring(level, suit, doubleRe, beforeAfter, level - p + 6).getDeclarerContractScoringPoints();
                logger.info("Dla kontraktu o wysokości 3 w kolorze: " + suitDes + "  bez " + p + " " + doubleReDes + "wynik jest: " + res + " punktów.");
                Assert.assertEquals(map.get(p), res);
            }
        }
    }


    //old version of this above
    @Test
    public void testMajor3ContractMinusMore() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsContractMinusMore.keySet())) {
            if (3 + 6 - p >= 0) {
                Integer res = new InternationalBridgeScoring(3, "s", false, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
                logger.info("Dla kontraktu o wysokości 3 w starszym  bez  " + p + "   wynik jest: " + res + " punktów.");
                Assert.assertEquals(testPointsContractMinusMore.get(p), res);
            }
        }
    }

    @Test
    public void testMajor3ContractMinusMoreDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForContractMinusMoreDouble.keySet())) {

            if (3 + 6 - p >= 0) {
                Integer res = new InternationalBridgeScoring(3, "s", true, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
                logger.info("Dla kontraktu o wysokości 3 w starszym  bez  " + p + "  z kontą wynik jest: " + res + " punktów.");
                Assert.assertEquals(testPointsForContractMinusMoreDouble.get(p), res);
            }
        }
    }

    @Test
    public void testMajor3ContractMinusMoreReDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForContractMinusMoreReDouble.keySet())) {
            if (3 + 6 - p >= 0) {
                Integer res = new InternationalBridgeScoring(3, "s", true, true, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
                logger.info("Dla kontraktu o wysokości 3 w starszym  bez  " + p + "  z rekontą wynik jest: " + res + " punktów.");
                Assert.assertEquals(testPointsForContractMinusMoreReDouble.get(p), res);
            }
        }
    }

    @Test
    public void testNoTrumph3ContractMinusMore() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForContractLevel3MinusMore.keySet())) {

            Integer res = new InternationalBridgeScoring(3, "nt", false, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez " + p + " wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForContractLevel3MinusMore.get(p), res);

        }
    }

    @Test
    public void testNoTrumph3ContractMinusMoreDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForContractLevel3MinusMoreDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(3, "nt", true, false, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu bez  " + p + "  z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForContractLevel3MinusMoreDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumph3ContractMinusMoreReDouble() throws BridgeException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForContractLevel3MinusMoreReDouble.keySet())) {
            Integer res = new InternationalBridgeScoring(3, "nt", true, true, beforeAfter, 3 - p + 6).getDeclarerContractScoringPoints();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez  " + p + "  z rekontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForContractLevel3MinusMoreReDouble.get(p), res);
            //   }
        }
    }

}
