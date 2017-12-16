package pl.waw.rubach.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bidID;

    private Integer bidLevel;

    private Integer level;

    private String suit;

    private Integer pointsMin;

    private Integer pointsMax;

    private String suitLength;

    private String bidType;

    private String bidClass;

    private boolean afterInterven;

    private String shortDesc;

    private String description;

    private Integer assumption;

    @ManyToOne
    private Bid parentBid;

    @ManyToOne
    private BidSystem bidSystem;

    public Bid() {
    }

    public Bid(Integer bidLevel, Integer level, String suit, Integer pointsMin, Integer pointsMax, String suitLength, String bidType, String bidClass, boolean afterInterven, String shortDesc, String description, Integer assumption, Bid parentBid, BidSystem bidSystem) {
        this.bidLevel = bidLevel;
        this.level = level;
        this.suit = suit;
        this.pointsMin = pointsMin;
        this.pointsMax = pointsMax;
        this.suitLength = suitLength;
        this.bidType = bidType;
        this.bidClass = bidClass;
        this.afterInterven = afterInterven;
        this.shortDesc = shortDesc;
        this.description = description;
        this.assumption = assumption;
        this.parentBid = parentBid;
        this.bidSystem = bidSystem;
    }

    //**********************Function to change bid to different auction type - to have only one in table? *************
    /**
     * Function to move bid to some number of biding level (adding bothSide auction)
     * (or 1?)
     */
    public void addBidingLevel(int number) {
        this.bidLevel = bidLevel + number;
    }
    public void addBidingLevel(){
        addBidingLevel(1);
    }

    /**
     * Function to change suit for similar bids
     * */
    public void changeSuitOfBid(String previusSuit, String newSuit){
        if(this.suit.equals(previusSuit)) this.suit = newSuit;
    }

    //************************ Getters and setters
    public String getSuitLength() {
        return suitLength;
    }

    public void setSuitLength(String suitLength) {
        this.suitLength = suitLength;
    }

    public Integer getBidID() {
        return bidID;
    }

    public void setBidID(Integer bidID) {
        this.bidID = bidID;
    }

    public Integer getBidLevel() {
        return bidLevel;
    }

    public void setBidLevel(Integer bidLevel) {
        this.bidLevel = bidLevel;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Integer getPointsMin() {
        return pointsMin;
    }

    public void setPointsMin(Integer pointsMin) {
        this.pointsMin = pointsMin;
    }

    public Integer getPointsMax() {
        return pointsMax;
    }

    public void setPointsMax(Integer pointsMax) {
        this.pointsMax = pointsMax;
    }

    public String getBidType() {
        return bidType;
    }

    public void setBidType(String bidType) {
        this.bidType = bidType;
    }

    public String getBidClass() {
        return bidClass;
    }

    public void setBidClass(String bidClass) {
        this.bidClass = bidClass;
    }

    public boolean isAfterInterven() {
        return afterInterven;
    }

    public void setAfterInterven(boolean afterInterven) {
        this.afterInterven = afterInterven;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bid getParentBid() {
        return parentBid;
    }

    public void setParentBid(Bid parentBid) {
        this.parentBid = parentBid;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public BidSystem getBidSystem() {
        return bidSystem;
    }

    public void setBidSystem(BidSystem bidSystem) {
        this.bidSystem = bidSystem;
    }

    public Integer getAssumption() {
        return assumption;
    }

    public void setAssumption(Integer assumption) {
        this.assumption = assumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return afterInterven == bid.afterInterven &&
                Objects.equals(bidID, bid.bidID) &&
                Objects.equals(bidLevel, bid.bidLevel) &&
                Objects.equals(level, bid.level) &&
                Objects.equals(suit, bid.suit) &&
                Objects.equals(pointsMin, bid.pointsMin) &&
                Objects.equals(pointsMax, bid.pointsMax) &&
                Objects.equals(suitLength, bid.suitLength) &&
                Objects.equals(bidType, bid.bidType) &&
                Objects.equals(bidClass, bid.bidClass) &&
                Objects.equals(shortDesc, bid.shortDesc) &&
                Objects.equals(description, bid.description) &&
                Objects.equals(assumption, bid.assumption) &&
                Objects.equals(parentBid, bid.parentBid) &&
                Objects.equals(bidSystem, bid.bidSystem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bidID, bidLevel, level, suit, pointsMin, pointsMax, suitLength, bidType, bidClass, afterInterven, shortDesc, description, assumption, parentBid, bidSystem);
    }

    @Override
    public String toString() {
        return "Bid{" + level + suit + " " + "id=" + bidID +
                ", lev=" + bidLevel +
                ", pts=" + pointsMin +"-" + pointsMax +
                ", desc='" + shortDesc + '\'' +
                ", parent=" + (parentBid!=null ? parentBid.getLevel() + parentBid.getSuit() + " " + parentBid.getBidID() : "") +
                "}\n";
    }

/*    @Override
    public String toString() {
        return "Bid{" +
                "bidID=" + bidID +
                ", bidLevel=" + bidLevel +
                ", level=" + level +
                ", suit='" + suit + '\'' +
                ", pointsMin=" + pointsMin +
                ", pointsMax=" + pointsMax +
                ", suitLength='" + suitLength + '\'' +
                ", bidType='" + bidType + '\'' +
                ", bidClass='" + bidClass + '\'' +
                ", afterInterven=" + afterInterven +
                ", shortDesc='" + shortDesc + '\'' +
                ", description='" + description + '\'' +
                ", assumption=" + assumption +
                ", parentBid=" + parentBid +
                ", bidSystem=" + bidSystem +
                '}';
    }*/


}
