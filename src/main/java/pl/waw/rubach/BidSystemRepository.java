package pl.waw.rubach;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidSystemRepository extends JpaRepository<BidSystem, Long> {
    List<BidSystem> findByName(String bidSystemName);
}
