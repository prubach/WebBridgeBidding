package pl.waw.rubach;

import java.util.HashMap;
import java.util.Map;

public class BidHelper {


    private static final Map<String , String> bidSymbols = new HashMap<String , String>() {{
        put("C","\u2663");
        put("D","\u2666");
        put("H","\u2665");
        put("S", "\u2660");
        put("NT", "BA");
    }};


    private static final Map<String , String> bidColors = new HashMap<String , String>() {{
        put("C","black");
        put("D","red");
        put("H","red");
        put("S", "black");
        put("NT", "black");
    }};

    private static final Map<String , String> bidDescs = new HashMap<String , String>() {{
        put("kier","H");
        put("kiery","H");
        put("kierów","H");
        put("karo", "D");
        put("kara", "D");
        put("trefl", "C");
        put("trefle", "C");
        put("trefli", "C");
        put("pik", "S");
        put("piki", "S");
        put("pików", "S");
    }};

    /**
     * Automatically replace the text that means Suits in the descriptions with Suit symbols and color them
     *
     * @param desc
     * @return
     */
    public static String replaceSuitsInDesc(String desc) {
        if (desc == null) return null;
        for (String bidSuit : bidDescs.keySet()) {
            desc = desc.replaceAll(bidSuit, " " + getBidSuit(bidDescs.get(bidSuit)));
        }
        return desc;
    }

    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bidSuit
     * @return
     */
    public static String getBidSuit(String bidSuit) {
        return "<font color=\"" + bidColors.get(bidSuit.toUpperCase()) + "\">" + getBidSuitSymbol(bidSuit) + "</font>";
    }

    /**
     * Use the Suit symbols instead of letters
     *
     * @param bidSuit
     * @return
     */
    public static String getBidSuitSymbol(String bidSuit) {
        return bidSymbols.get(bidSuit.toUpperCase());
    }

    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bidSuit
     * @return
     */
    public static String getBidSuitAndroid(String bidSuit) {
        return "<font color=\"" + getBidSuitColorAndroid(bidSuit) + "\">" + getBidSuitSymbol(bidSuit) + "</font>";
    }


    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bidSuit
     * @return
     */
    public static String getBidSuitColorAndroid(String bidSuit) {
        switch (bidSuit) {
            case "D":
            case "H":
                return "#FF0000";
            default:
                return "#000000";
        }
    }

}
