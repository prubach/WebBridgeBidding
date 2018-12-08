package pl.waw.rubach.points;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountingPointsForGameTestBefore {

    private static Logger logger = LoggerFactory.getLogger(CountingPointsForGameTestBefore.class);
    private boolean beforeAfter = false;
    private int karaZaKontre = 50;
    private int premiaZaCzesciowke = 50;
    private int premiaZaKoncowke = 300;
    private int premiaZaSzlemika = 500;
    private int premiaZaSzlema = 1000;
    private int nadrobkaZKontra = 100;
    private int wpadkaBezKontry =50;
    private int wpadka1Zkontra=100;
    private int wpadka2Zkontra=200;

    //pyt jak zrobić żeby nie powtarzać (przepisywać tych testów do drugiej klasy After) -jeżeli rózni się tylko to  co jest powyżej - czy zrobić testy z parametrem czy jak?

    private Map<Integer, Integer> testPointsForMajorContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphDoubleContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorReDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorReDoubleContract = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphReDoubleContract = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorContractOneMore = new HashMap<>();
    private Map<Integer, Integer> testPointsForMinorContractOneMore = new HashMap<>();
    private Map<Integer, Integer> testNoTrumphContractPointsOneMore = new HashMap<>();


    private Map<Integer, Integer>  testMajorContractPointsTwoMore = new HashMap<>();
    private Map<Integer, Integer>  testMajorContractPointsThreeMore = new HashMap<>();
    private Map<Integer, Integer>  testMajorContractPointsFourMore = new HashMap<>();
    private Map<Integer, Integer>  testMajorContractPointsFiveMore = new HashMap<>();
    private Map<Integer, Integer>  testMajorContractPointsSixMore = new HashMap<>();

    private Map<Integer, Integer>  testNoTrumphContractPointsTwoMore = new HashMap<>();
    private Map<Integer, Integer>  testNoTrumphContractPointsThreeMore = new HashMap<>();
    private Map<Integer, Integer>  testNoTrumphContractPointsFourMore = new HashMap<>();
    private Map<Integer, Integer>  testNoTrumphContractPointsFiveMore = new HashMap<>();
    private Map<Integer, Integer>  testNoTrumphContractPointsSixMore = new HashMap<>();

    private Map<Integer, Integer> testPointsForMinorContractOneMoreDouble = new HashMap<>();
    private Map<Integer, Integer> testNoTrumphContractPointsOneMoreDouble = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajorContractMinusOne = new HashMap<>();
    private Map<Integer, Integer> testPointsForNoTrumphContractMinusOneDouble = new HashMap<>();

    private Map<Integer, Integer> testPointsForMajor3ContractMinusMore = new HashMap<>();
    private Map<Integer, Integer> testPointsForMajor3ContractMinusMoreDouble = new HashMap<>();
    private Map<Integer, Integer> testPointsForMajor3ContractMinusMoreReDouble = new HashMap<>();

    private Map<Integer, Integer> testPointsFor3NTContractMinusMore = new HashMap<>();
    private Map<Integer, Integer> testPointsFor3NTContractMinusMoreDouble = new HashMap<>();
    private Map<Integer, Integer> testPointsFor3NTContractMinusMoreReDouble = new HashMap<>();

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

        testPointsForNoTrumphDoubleContract.put(1, 40*2+karaZaKontre  +premiaZaCzesciowke);
        testPointsForNoTrumphDoubleContract.put(2, (40+30)*2 +premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphDoubleContract.put(3, 100*2 + karaZaKontre  +premiaZaKoncowke);
        testPointsForNoTrumphDoubleContract.put(4, 130*2 +premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphDoubleContract.put(5, 160*2 +premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphDoubleContract.put(6, 190*2 +premiaZaKoncowke +karaZaKontre +premiaZaSzlemika );
        testPointsForNoTrumphDoubleContract.put(7, 220*2 +premiaZaKoncowke +karaZaKontre +premiaZaSzlema );

           //all of redouble contract

        testPointsForMajorReDoubleContract.put(1, 4*30 + premiaZaKoncowke + karaZaKontre );
        testPointsForMajorReDoubleContract.put(2, 4*2*30 + premiaZaKoncowke + karaZaKontre );
        testPointsForMajorReDoubleContract.put(3, 4*3*30 + premiaZaKoncowke + karaZaKontre );
        testPointsForMajorReDoubleContract.put(4, 4*4*30 + premiaZaKoncowke + karaZaKontre );
        testPointsForMajorReDoubleContract.put(5, 4*5*30 + premiaZaKoncowke + karaZaKontre );
        testPointsForMajorReDoubleContract.put(6, 4*6*30 + premiaZaKoncowke + karaZaKontre+premiaZaSzlemika );
        testPointsForMajorReDoubleContract.put(7, 4*7*30 + premiaZaKoncowke + karaZaKontre+premiaZaSzlema );

        testPointsForMinorReDoubleContract.put(1, 4*20  + premiaZaCzesciowke  +karaZaKontre );
        testPointsForMinorReDoubleContract.put(2, 4*2*20+ premiaZaKoncowke + karaZaKontre );
        testPointsForMinorReDoubleContract.put(3, 4*3*20+ premiaZaKoncowke + karaZaKontre );
        testPointsForMinorReDoubleContract.put(4, 4*4*20+ premiaZaKoncowke + karaZaKontre );
        testPointsForMinorReDoubleContract.put(5, 4*5*20+ premiaZaKoncowke + karaZaKontre );
        testPointsForMinorReDoubleContract.put(6, 4*6*20+ premiaZaKoncowke + karaZaKontre +premiaZaSzlemika);
        testPointsForMinorReDoubleContract.put(7, 4*7*20+ premiaZaKoncowke + karaZaKontre +premiaZaSzlema );

        testPointsForNoTrumphReDoubleContract.put(1, 4*40 + premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(2, 4*(40+30) + premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(3, 4*(40+2*30) + premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(4, 4*(40+3*30) + premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(5, 4*(40+4*30) + premiaZaKoncowke +karaZaKontre );
        testPointsForNoTrumphReDoubleContract.put(6, 4*(40+5*30) + premiaZaKoncowke +karaZaKontre +premiaZaSzlemika);
        testPointsForNoTrumphReDoubleContract.put(7, 4*(40+6*30) + premiaZaKoncowke +karaZaKontre +premiaZaSzlema );


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

        testPointsForMinorContractOneMoreDouble.put(1, 2*20+ nadrobkaZKontra + karaZaKontre +premiaZaCzesciowke); //kontrakt np 1karo + 1 lewa z kontrą (20)*2 +100 za nadróbkę + 50 nieudaną kontrę + 300 premi za końcówkę
        testPointsForMinorContractOneMoreDouble.put(2, 2*40+ nadrobkaZKontra + karaZaKontre + premiaZaCzesciowke);
        testPointsForMinorContractOneMoreDouble.put(3, 2*60+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(4, 2*80+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(5, 2* 100+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke);
        testPointsForMinorContractOneMoreDouble.put(6, 2*120+ nadrobkaZKontra+ karaZaKontre + premiaZaKoncowke +premiaZaSzlemika);
       // testPointsForMinorContractOneMoreDouble.put(7, 240+50+300); nie ma sensu - nie może być 7 i jedna lepiej!!!
//todo add redouble example (and nt/major double ew.

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




//todo add double and redouble example (and minor/major  ew.

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
    public void testMajorContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMajorContract.keySet())) {
            Integer res = new PointsForContract(p,p+6,"s",false,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContract.get(p), res);
        }
    }
    @Test
    public void testMinorContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMinorContract.keySet())) {
            Integer res = new PointsForContract(p,p+6,"d",false,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContract.get(p), res);
        }
    }
    @Test
    public void testNoTrumphContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContract.keySet())) {
            Integer res = new PointsForContract(p,p+6,"n",false,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContract.get(p), res);
        }
    }

    @Test
    public void testMajorDoubleContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMajorDoubleContract.keySet())) {
            Integer res = new PointsForContract(p,p+6,"s",true,false,beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorDoubleContract.get(p), res);
        }
    }
    @Test
    public void testMinorDoubleContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMinorDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p+6,"d",true,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorDoubleContract.get(p), res);
        }
    }
    @Test
    public void testNoTrumphDoubleContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p+6,"n",true,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z kontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphDoubleContract.get(p), res);
        }
    }

    @Test
    public void testMajorReDoubleContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMajorReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p+6,"s",false,true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorReDoubleContract.get(p), res);
        }
    }
    @Test
    public void testMinorReDoubleContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForMinorReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p+6,"d",false,true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z rekotrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorReDoubleContract.get(p), res);
        }
    }
    @Test
    public void testNoTrumphReDoubleContract() throws InvalidContractLevelException {
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphReDoubleContract.keySet())) {
            Integer res = new PointsForContract(p, p+6,"n",false,true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu z rekontrą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphReDoubleContract.get(p), res);
        }
    }

    //TESTS NADRÓBKI

    @Test
    public void testMajorContractOneMore() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
            for (int p : new TreeSet<Integer>(testPointsForMajorContractOneMore.keySet())) {
                Integer res = new PointsForContract(p, p + 1+6, "s", false, false, beforeAfter).getCalculatedPointsForContract();
                logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  z jedną nadróbką wynik jest: " + res + " punktów.");
                Assert.assertEquals(testPointsForMajorContractOneMore.get(p), res);
         //   }
        }
    }
    @Test
    public void testMinorContractOneMore() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMore.keySet())) {
            Integer res = new PointsForContract(p, p + 1+6, "d", false, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym  z jedną nadróbką wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMore.get(p), res);
            //   }
        }
    }
    //missin major double one more
    @Test
    public void testMinorContractOneMoreDouble() throws InvalidContractLevelException {
    //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMinorContractOneMoreDouble.keySet())) {
            Integer res = new PointsForContract(p, p+1+6 ,"d",true,false, beforeAfter).getCalculatedPointsForContract();
                 logger.info("Dla kontraktu o wysokości " + p + " w kolorze młodszym z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMinorContractOneMoreDouble.get(p), res);
      //  }
    }}
    @Test
    public void testNoTrumphContractOneMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMore.keySet())) {
            Integer res = new PointsForContract(p, p+1 +6,"nt",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMore.get(p), res);
            //  }
        }}
    @Test
    public void testNoTrumphContractOneMoreDouble()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsOneMoreDouble.keySet())) {
            Integer res = new PointsForContract(p, p+1+6 ,"nt",true,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu z kotrą  z jedną nadróbką  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsOneMoreDouble.get(p), res);
            //  }
        }}


    @Test
    public void testMajorContractTwoMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsTwoMore.keySet())) {
            Integer res = new PointsForContract(p, p+2 +6,"s",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsTwoMore.get(p), res);
            //  }
        }}
    @Test
    public void testMajorContractThreeMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsThreeMore.keySet())) {
            Integer res = new PointsForContract(p, p+3+6 ,"s",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsThreeMore.get(p), res);
            //  }
        }}
    @Test
    public void testMajorContractFourMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsFourMore.keySet())) {
            Integer res = new PointsForContract(p, p+4 +6,"s",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy  z czterema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsFourMore.get(p), res);
            //  }
        }}
    @Test
    public void testMajorContractFiveMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsFiveMore.keySet())) {
            Integer res = new PointsForContract(p, p+5 +6,"s",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsFiveMore.get(p), res);
            //  }
        }}
    @Test
    public void testMajorContractSixMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testMajorContractPointsSixMore.keySet())) {
            Integer res = new PointsForContract(p, p+6 +6,"s",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w starszy   z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testMajorContractPointsSixMore.get(p), res);
            //  }
        }}


    //
    @Test
    public void testNoTrumphContractTwoMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsTwoMore.keySet())) {
            Integer res = new PointsForContract(p, p+2 +6,"nt",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z dwiema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsTwoMore.get(p), res);
            //  }
        }}
    @Test
    public void testNoTrumphContractThreeMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsThreeMore.keySet())) {
            Integer res = new PointsForContract(p, p+3+6 ,"nt",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z trzema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsThreeMore.get(p), res);
            //  }
        }}
    @Test
    public void testNoTrumphContractFourMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFourMore.keySet())) {
            Integer res = new PointsForContract(p, p+4+6 ,"nt",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z czterema nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsFourMore.get(p), res);
            //  }
        }}
    @Test
    public void testNoTrumphContractFiveMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsFiveMore.keySet())) {
            Integer res = new PointsForContract(p, p+5+6 ,"nt",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z pięcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsFiveMore.get(p), res);
            //  }
        }}
    @Test
    public void testNoTrumphContractSixMore()  throws InvalidContractLevelException {
        //    for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testNoTrumphContractPointsSixMore.keySet())) {
            Integer res = new PointsForContract(p, p+6 +6,"nt",false,false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bezatu  z sześcioma nadróbkami  wynik jest: " + res + " punktów.");
            Assert.assertEquals(testNoTrumphContractPointsSixMore.get(p), res);
            //  }
        }}

    // TESTS WPADKI


    @Test
    public void testMajorContractMinusOne() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajorContractMinusOne.keySet())) {
            Integer res = new PointsForContract(p, p - 1+6, "s", false, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w kolorze starszym  bez jednej wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajorContractMinusOne.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumphContractMinusOneDouble() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForNoTrumphContractMinusOneDouble.keySet())) {
            Integer res = new PointsForContract(p, p - 1+6, "n", true, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości " + p + " w bez atu  bez jednej z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForNoTrumphContractMinusOneDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testMajor3ContractMinusMore() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajor3ContractMinusMore.keySet())) {
            Integer res = new PointsForContract(3, 3 - p+6, "s", false, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w kolorze starszym  bez " + p +" wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajor3ContractMinusMore.get(p), res);
            //   }
        }
    }
    @Test
    public void testMajor3ContractMinusMoreDouble() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajor3ContractMinusMoreDouble.keySet())) {
            Integer res = new PointsForContract(3, 3 - p+6, "nt", true, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w starszym  bez  "+ p+ "  z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajor3ContractMinusMoreDouble.get(p), res);
            //   }
        }
    }
    @Test
    public void testMajor3ContractMinusMoreReDouble() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsForMajor3ContractMinusMoreReDouble.keySet())) {
            Integer res = new PointsForContract(3, 3 - p+6, "nt", true, true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w starszym  bez  "+ p+ "  z rekontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsForMajor3ContractMinusMoreReDouble.get(p), res);
            //   }
        }
    }

    @Test
    public void testNoTrumph3ContractMinusMore() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsFor3NTContractMinusMore.keySet())) {
            Integer res = new PointsForContract(3, 3 - p+6, "s", false, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez " + p +" wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsFor3NTContractMinusMore.get(p), res);
            //   }
        }
    }
    @Test
    public void testNoTrumph3ContractMinusMoreDouble() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsFor3NTContractMinusMoreDouble.keySet())) {
            Integer res = new PointsForContract(3, 3 - p+6, "nt", true, false, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu bez  "+ p+ "  z kontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsFor3NTContractMinusMoreDouble.get(p), res);
            //   }
        }
    }
    @Test
    public void testNoTrumph3ContractMinusMoreReDouble() throws InvalidContractLevelException {
        //for(int i=1; i<2; i++){
        for (int p : new TreeSet<Integer>(testPointsFor3NTContractMinusMoreReDouble.keySet())) {
            Integer res = new PointsForContract(3, 3 - p+6, "nt", true, true, beforeAfter).getCalculatedPointsForContract();
            logger.info("Dla kontraktu o wysokości 3 w bez Atu  bez  "+ p+ "  z rekontą wynik jest: " + res + " punktów.");
            Assert.assertEquals(testPointsFor3NTContractMinusMoreReDouble.get(p), res);
            //   }
        }
    }

}
