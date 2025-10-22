package sum25.SE192755.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sum25.SE192755.Entity.SonyAccounts;
import sum25.SE192755.Entity.SonyCategories;
import sum25.SE192755.Entity.SonyProducts;
import sum25.SE192755.Service.SonyCategoriesService;
import sum25.SE192755.Service.SonyProductsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SonyProductsController {

    private final SonyProductsService sonyProductsService;
    private final SonyCategoriesService sonyCategoriesService;

    private boolean canManage(HttpSession session) {
        Object o = session.getAttribute("LoggedInAccount");
        if (o instanceof SonyAccounts acc) {
            return acc.getRoleId() == 1 || acc.getRoleId() == 2;
        }
        return false;
    }

    // Xem danh sách: ai đăng nhập cũng xem được (1/2/3)
    @GetMapping("/products")
    public ModelAndView getProductsPage() {
        ModelAndView mv = new ModelAndView("sonyProducts");
        mv.addObject("sonyProducts", sonyProductsService.findAll());
        mv.addObject("isEdit", false);
        return mv;
    }

    // ====== CÁC API CẦN QUYỀN 1/2 ======

    @GetMapping("/products/create")
    public ModelAndView createProduct(HttpSession session) {
        if (!canManage(session)) return new ModelAndView("redirect:/403");

        ModelAndView mv = new ModelAndView("create_product");
        mv.addObject("sonyProducts", new SonyProducts());
        mv.addObject("categories", sonyCategoriesService.findAll());
        mv.addObject("isEdit", false);
        return mv;
    }

    @PostMapping("/products/create")
    public String createProductSubmit(@ModelAttribute("sonyProducts") SonyProducts sonyProducts,
                                      HttpSession session) {
        if (!canManage(session)) return "redirect:/403";

        Long cateId = sonyProducts.getSonyCategories() != null
                ? sonyProducts.getSonyCategories().getCateID() : null;
        sonyProducts.setSonyCategories(cateId != null ? sonyCategoriesService.findByCateID(cateId) : null);
        sonyProductsService.addProduct(sonyProducts);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id, HttpSession session) {
        if (!canManage(session)) return new ModelAndView("redirect:/403");

        SonyProducts product = sonyProductsService.findByProductID(id);
        ModelAndView mv = new ModelAndView("create_product");
        mv.addObject("sonyProducts", product);
        mv.addObject("categories", sonyCategoriesService.findAll());
        mv.addObject("isEdit", true);
        return mv;
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("sonyProducts") SonyProducts sonyProducts,
                                HttpSession session) {
        if (!canManage(session)) return "redirect:/403";

        Long cateId = sonyProducts.getSonyCategories() != null
                ? sonyProducts.getSonyCategories().getCateID() : null;
        sonyProducts.setSonyCategories(cateId != null ? sonyCategoriesService.findByCateID(cateId) : null);
        sonyProducts.setProductID(id);
        sonyProductsService.addProduct(sonyProducts);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteSonyProducts(@PathVariable Long id, HttpSession session) {
        if (!canManage(session)) return "redirect:/403";

        sonyProductsService.deleteProduct(id);
        return "redirect:/products";
    }
}
