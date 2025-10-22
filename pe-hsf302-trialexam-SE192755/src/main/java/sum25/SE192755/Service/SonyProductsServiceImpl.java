package sum25.SE192755.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.SE192755.Entity.SonyProducts;
import sum25.SE192755.Repository.SonyProductsRepository;

import java.util.List;

@Service
public class SonyProductsServiceImpl implements SonyProductsService {

    @Autowired
    private SonyProductsRepository sonyProductsRepository;

    @Override
    public SonyProducts findByProductID(Long productID) {
        return sonyProductsRepository.findByProductID(productID);
    }

    @Override
    public void addProduct(SonyProducts product) {
        sonyProductsRepository.save(product);
    }

    @Override
    public List<SonyProducts> findAll() {
        return sonyProductsRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productID) {
        sonyProductsRepository.deleteById(productID);
    }
}
