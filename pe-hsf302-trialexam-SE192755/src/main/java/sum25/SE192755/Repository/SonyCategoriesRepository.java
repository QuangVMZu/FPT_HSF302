package sum25.SE192755.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.SE192755.Entity.SonyCategories;

import java.util.List;

@Repository
public interface SonyCategoriesRepository extends JpaRepository<SonyCategories, Long> {
    SonyCategories findByCateID(Long cateID);

}
