// java
package sum25.SE192755.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import sum25.SE192755.Entity.SonyAccounts;
import sum25.SE192755.Entity.SonyCategories;
import sum25.SE192755.Entity.SonyProducts;
import sum25.SE192755.Service.SonyAccountsService;
import sum25.SE192755.Service.SonyCategoriesService;
import sum25.SE192755.Service.SonyProductsService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Data implements CommandLineRunner {

    private final SonyAccountsService sonyAccountsService;
    private final SonyCategoriesService sonyCategoriesService;
    private final SonyProductsService sonyProductsService;

    @Override
    public void run(String... args) throws Exception {

        // Accounts (existing logic)
        if (!sonyAccountsService.isPhoneExists("0905111111")) {
            SonyAccounts sonyAccounts = new SonyAccounts();
            sonyAccounts.setPhone("0905111111");
            sonyAccounts.setPassword("123123");
            sonyAccounts.setRoleId(1);
            sonyAccountsService.addSonyAccounts(sonyAccounts);
        }

        if (!sonyAccountsService.isPhoneExists("0905222222")) {
            SonyAccounts sonyAccounts2 = new SonyAccounts();
            sonyAccounts2.setPhone("0905222222");
            sonyAccounts2.setPassword("123123");
            sonyAccounts2.setRoleId(2);
            sonyAccountsService.addSonyAccounts(sonyAccounts2);
        }

        if (!sonyAccountsService.isPhoneExists("0905333333")) {
            SonyAccounts sonyAccounts3 = new SonyAccounts();
            sonyAccounts3.setPhone("0905333333");
            sonyAccounts3.setPassword("123123");
            sonyAccounts3.setRoleId(3);
            sonyAccountsService.addSonyAccounts(sonyAccounts3);
        }

        // Categories: only seed if findByCateID(1L) returns null
        if (sonyCategoriesService.findByCateID(1L) == null) {
            SonyCategories c1 = new SonyCategories();
            c1.setCateName("Headphones");
            c1.setStatus("active");
            sonyCategoriesService.save(c1);

            SonyCategories c2 = new SonyCategories();
            c2.setCateName("Television");
            c2.setStatus("active");
            sonyCategoriesService.save(c2);

            SonyCategories c3 = new SonyCategories();
            c3.setCateName("Camera");
            c3.setStatus("active");
            sonyCategoriesService.save(c3);
        }

        // Products: only seed when there are no products
        if (sonyProductsService.findAll().isEmpty()) {
            List<SonyCategories> categories = sonyCategoriesService.findAll();
            if (!categories.isEmpty()) {
                SonyProducts p1 = new SonyProducts();
                p1.setProductName("WH-1000XM5");
                p1.setPrice(399);
                p1.setStock(50);
                p1.setSonyCategories(categories.get(0));
                sonyProductsService.addProduct(p1);

                SonyProducts p2 = new SonyProducts();
                p2.setProductName("BRAVIA XR A80K");
                p2.setPrice(1499);
                p2.setStock(20);
                p2.setSonyCategories(categories.size() > 1 ? categories.get(1) : categories.get(0));
                sonyProductsService.addProduct(p2);

                SonyProducts p3 = new SonyProducts();
                p3.setProductName("Alpha a7 IV");
                p3.setPrice(2499);
                p3.setStock(10);
                p3.setSonyCategories(categories.size() > 2 ? categories.get(2) : categories.get(0));
                sonyProductsService.addProduct(p3);
            }
        }

        System.out.println("✅ Default accounts/categories/products seeded successfully (no duplicates)");
    }
}
