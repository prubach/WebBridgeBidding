package pl.waw.rubach;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByParentBid(Bid parentBid);

    List<Bid> findByBidLevel(Integer bidLevel);

    List<Bid> findByBidID(Long bidID);
}
