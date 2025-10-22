package sum25.SE192755.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.SE192755.Entity.SonyProducts;

import java.util.List;

@Repository
public interface SonyProductsRepository extends JpaRepository<SonyProducts, Long> {
    public List<SonyProducts> findAll();
    public SonyProducts findByProductID(Long productID);
}
