package sum25.SE192755.Service;

import org.springframework.stereotype.Service;
import sum25.SE192755.Entity.SonyCategories;

import java.util.List;

@Service
public interface SonyCategoriesService {
    List<SonyCategories> findAll();
    SonyCategories findByCateID(Long id);
    void save(SonyCategories category);
    void deleteById(Long id);
}
