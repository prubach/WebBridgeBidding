package pl.waw.rubach;

import javax.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long bidID;

    private Integer bidLevel;

    private Integer level;

    private String suit;

    private Integer pointsMin;

    private Integer pointsMax;

    private Integer suitLength;

    private String bidType;

    private String bidClass;

    private boolean afterInterven;

    private String description;

    @ManyToOne
    private Bid parentBid;

    public Bid() {
    }

    public Bid(Integer bidLevel, Integer level, String suit, Integer pointsMin, Integer pointsMax, Integer suitLength, String bidType, String bidClass, boolean afterInterven, String description, Bid parentBid) {
        this.bidLevel = bidLevel;
        this.level = level;
        this.suit = suit;
        this.pointsMin = pointsMin;
        this.pointsMax = pointsMax;
        this.suitLength = suitLength;
        this.bidType = bidType;
        this.bidClass = bidClass;
        this.afterInterven = afterInterven;
        this.description = description;
        this.parentBid = parentBid;
    }

    public Long getBidID() {
        return bidID;
    }

    public void setBidID(Long bidID) {
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

    public Integer getSuitLength() {
        return suitLength;
    }

    public void setSuitLength(Integer suitLength) {
        this.suitLength = suitLength;
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

    @Override
    public String toString() {
        return "Bid{" +
                "bidID=" + bidID +
                ", bidLevel=" + bidLevel +
                ", level=" + level +
                ", suit='" + suit + '\'' +
                ", pointsMin=" + pointsMin +
                ", pointsMax=" + pointsMax +
                ", suitLength=" + suitLength +
                ", bidType='" + bidType + '\'' +
                ", bidClass='" + bidClass + '\'' +
                ", afterInterven=" + afterInterven +
                ", description='" + description + '\'' +
                ", parentBid=" + parentBid +
                '}';
    }
}
