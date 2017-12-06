package pl.waw.rubach.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BidSystem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer bidSystemID;

    private String name;

    public BidSystem() {}

    public BidSystem(String name) {
        this.name = name;
    }

    public Integer getBidSystemID() {
        return bidSystemID;
    }

    public void setBidSystemID(Integer bidSystemID) {
        this.bidSystemID = bidSystemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BidSystem{" +
                "bidSystemID=" + bidSystemID +
                ", name='" + name + '\'' +
                '}';
    }
}
