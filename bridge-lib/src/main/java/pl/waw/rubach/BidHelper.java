package pl.waw.rubach;

public class BidHelper {


    /**
     * Automatically replace the text that means Suits in the descriptions with Suit symbols and color them
     *
     * @param desc
     * @return
     */
    public static String replaceSuitsInDesc(String desc) {
        if (desc == null) return null;
        desc = desc.replaceAll("kier", " <font color=\"red\">\u2665</font> ");
        desc = desc.replaceAll("karo", " <font color=\"red\">\u2666</font> ");
        desc = desc.replaceAll("trefl", " <font color=\"black\">\u2663</font> ");
        desc = desc.replaceAll("pik", " <font color=\"black\">\u2660</font> ");

        desc = desc.replaceAll("kiery", " <font color=\"red\">\u2665</font> ");
        desc = desc.replaceAll("kara", " <font color=\"red\">\u2666</font> ");
        desc = desc.replaceAll("trefle", " <font color=\"black\">\u2663</font> ");
        desc = desc.replaceAll("piki", " <font color=\"black\">\u2660</font> ");

        desc = desc.replaceAll("kierów", " <font color=\"red\">\u2665</font> ");
        //  desc = desc.replaceAll("kar", " <font color=\"red\">\u2666</font> ");
        desc = desc.replaceAll("trefli", " <font color=\"black\">\u2663</font> ");
        desc = desc.replaceAll("pików", " <font color=\"black\">\u2660</font> ");

        //desc = desc.replaceAll("SK", "<font color=\"black\">\u2660</font>");

        return desc;
    }

    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bidSuit
     * @return
     */
    public static String getBidSuit(String bidSuit) {
        switch (bidSuit) {
            case "C":
                return "<font color=\"black\">\u2663</font>";
            case "D":
                return "<font color=\"red\">\u2666</font>";
            case "H":
                return "<font color=\"red\">\u2665</font>";
            case "S":
                return "<font color=\"black\">\u2660</font>";
            case "NT":
                return "BA";
        }
        return "";
    }

    /**
     * Use the Suit symbols instead of letters, add red color to hearts and diamonds
     *
     * @param bidSuit
     * @return
     */
    public static String getBidSuitAndroid(String bidSuit) {
        switch (bidSuit) {
            case "D":
                return "<font color=\"#FF0000\">\u2662</font>";
            case "H":
                return "<font color=\"#FF0000\">\u2661</font>";
            default:
                return getBidSuit(bidSuit);
        }
    }
}
