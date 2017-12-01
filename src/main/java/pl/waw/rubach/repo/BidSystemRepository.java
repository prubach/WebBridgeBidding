package pl.waw.rubach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.waw.rubach.model.BidSystem;

import java.util.List;

public interface BidSystemRepository extends JpaRepository<BidSystem, Long> {
    List<BidSystem> findByName(String bidSystemName);
}
