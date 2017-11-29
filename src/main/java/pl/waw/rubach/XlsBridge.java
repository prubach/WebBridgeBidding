package pl.waw.rubach;

public abstract class XlsBridge {

    protected static final String FILE_NAME_IN = "bridgeIn.xlsx";

    protected static final String FILE_NAME_OUT = "bridgeOut.xlsx";

    protected static final String[] header = new String[] { "bidId", "parentBid", "bidLevel","level", "suit","pointsMin", "pointsMax", "suitLength",
            "afterInterven", "shortDesc", "description", "bidType", "bidClass" };

}
