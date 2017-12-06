package pl.waw.rubach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByBidSystem(BidSystem bidSystem);

    List<Bid> findByBidSystemAndParentBid(BidSystem bidSystem, Bid parentBid);

    List<Bid> findByBidSystemAndBidLevel(BidSystem bidSystem, Integer bidLevel);

    List<Bid> findByBidID(Long bidID);
}
