package pl.waw.rubach.points;

import java.util.TreeSet;

public class ExpectedResultsTable extends AbstractTable{

//only map!

    ExpectedResultsTable(boolean fitInOlderColor, boolean auctionAssumption) {

        //TODO find what to do if there is half point (calculate two and divide etc? not in table?
        // it shoud be in the middle betwen two next to it

        ptsMap.put(20, (fitInOlderColor ? 50 : 0));
        ptsMap.put(21, (fitInOlderColor ? 70 : 50));
        ptsMap.put(22, (fitInOlderColor ? 110 : 70));
        ptsMap.put(23, (fitInOlderColor ? 130 : 110));
        ptsMap.put(24, (fitInOlderColor ? (auctionAssumption ? 440:300) : 130));
        ptsMap.put(25, (fitInOlderColor ? (auctionAssumption ? 520:350) : (auctionAssumption ? 290: 200)));
        ptsMap.put(26, (fitInOlderColor ? (auctionAssumption ? 600:400) : (auctionAssumption ? 440: 300)));
        ptsMap.put(27, (fitInOlderColor ? (auctionAssumption ? 630:430) : (auctionAssumption ? 520: 350)));
        ptsMap.put(28, (fitInOlderColor ? (auctionAssumption ? 660:460) : (auctionAssumption ? 600: 400)));
        ptsMap.put(29, (fitInOlderColor ? (auctionAssumption ? 690:490) : (auctionAssumption ? 630: 430)));
        ptsMap.put(30, (fitInOlderColor ? (auctionAssumption ? 750:550) : (auctionAssumption ? 660: 460)));
        ptsMap.put(31, (fitInOlderColor ? (auctionAssumption ? 800:600) : (auctionAssumption ? 720: 520)));
        ptsMap.put(32, (fitInOlderColor ? (auctionAssumption ? 1050:700) : (auctionAssumption ? 800: 600)));
        ptsMap.put(33, (fitInOlderColor ? (auctionAssumption ? 1350:900) : (auctionAssumption ? 1050: 700)));
        ptsMap.put(34, (fitInOlderColor ? (auctionAssumption ? 1500:1000) : (auctionAssumption ? 1350: 1000)));
        ptsMap.put(35, (fitInOlderColor ? (auctionAssumption ? 1650:1100) : (auctionAssumption ? 1500: 1100)));
        ptsMap.put(36, (fitInOlderColor ? (auctionAssumption ? 1800:1200) : (auctionAssumption ? 1650: 1200)));
        ptsMap.put(37, (fitInOlderColor ? (auctionAssumption ? 2100:1400) : (auctionAssumption ? 1800: 1400)));
        ptsMap.put(38, (fitInOlderColor ? (auctionAssumption ? 2100:1400) : (auctionAssumption ? 2100:1400)));

        ptsSet = new TreeSet<>(ptsMap.keySet());





}
    public int getExpectedPoints(int points) {
        Integer key = ptsSet.higher(points);
        return ptsMap.get(key);
    }
}
