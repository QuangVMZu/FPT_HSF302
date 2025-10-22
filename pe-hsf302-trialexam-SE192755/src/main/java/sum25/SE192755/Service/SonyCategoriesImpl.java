package sum25.SE192755.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.SE192755.Entity.SonyCategories;
import sum25.SE192755.Repository.SonyCategoriesRepository;

import java.util.List;

@Service
public class SonyCategoriesImpl implements SonyCategoriesService {

    @Autowired
    private SonyCategoriesRepository sonyCategoriesRepository;


    @Override
    public List<SonyCategories> findAll() {
        return sonyCategoriesRepository.findAll();
    }

    @Override
    public SonyCategories findByCateID(Long id) {
        return sonyCategoriesRepository.findByCateID(id);
    }

    @Override
    public void save(SonyCategories category) {
        sonyCategoriesRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        sonyCategoriesRepository.deleteById(id);
    }
}
