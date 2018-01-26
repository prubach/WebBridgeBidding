package pl.waw.rubach.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BidSystem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidSystem bidSystem = (BidSystem) o;
        return Objects.equals(bidSystemID, bidSystem.bidSystemID) &&
                Objects.equals(name, bidSystem.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bidSystemID, name);
    }

    @Override
    public String toString() {
        return "BidSystem{" +
                "bidSystemID=" + bidSystemID +
                ", name='" + name + '\'' +
                '}';
    }
}
