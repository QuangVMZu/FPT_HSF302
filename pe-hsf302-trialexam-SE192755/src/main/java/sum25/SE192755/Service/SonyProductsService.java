package sum25.SE192755.Service;

import org.springframework.stereotype.Service;
import sum25.SE192755.Entity.SonyProducts;

import java.util.List;

@Service
public interface SonyProductsService {
    public SonyProducts findByProductID(Long productID);
    public void addProduct(SonyProducts product);
    public List<SonyProducts> findAll();
    public void deleteProduct(Long productID);
}
