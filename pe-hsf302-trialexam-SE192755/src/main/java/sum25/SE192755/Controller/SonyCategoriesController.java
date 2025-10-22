package sum25.SE192755.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sum25.SE192755.Entity.SonyAccounts;
import sum25.SE192755.Entity.SonyCategories;
import sum25.SE192755.Service.SonyCategoriesService;

import java.util.List;

@Controller
public class SonyCategoriesController {

    @Autowired
    private SonyCategoriesService sonyCategoriesService;

    private boolean canManage(HttpSession session) {
        Object o = session.getAttribute("LoggedInAccount");
        if (o instanceof SonyAccounts acc) {
            return acc.getRoleId() == 1 || acc.getRoleId() == 2;
        }
        return false;
    }

    // Xem danh sách: ai đăng nhập cũng xem được (1/2/3)
    @GetMapping("/categories")
    public ModelAndView listCategories() {
        ModelAndView modelAndView = new ModelAndView("categories_list");
        List<SonyCategories> categories = sonyCategoriesService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    // ====== CÁC API CẦN QUYỀN 1/2 ======

    @GetMapping("/categories/create")
    public ModelAndView createCategoryForm(HttpSession session) {
        if (!canManage(session)) return new ModelAndView("redirect:/403");

        ModelAndView modelAndView = new ModelAndView("create_category");
        modelAndView.addObject("sonyCategory", new SonyCategories());
        modelAndView.addObject("isEdit", false);
        return modelAndView;
    }

    @PostMapping("/categories/create")
    public String createCategorySubmit(@ModelAttribute("sonyCategory") SonyCategories sonyCategory,
                                       HttpSession session) {
        if (!canManage(session)) return "redirect:/403";

        sonyCategoriesService.save(sonyCategory);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public ModelAndView editCategoryForm(@PathVariable("id") Long id, HttpSession session) {
        if (!canManage(session)) return new ModelAndView("redirect:/403");

        SonyCategories category = sonyCategoriesService.findByCateID(id);
        ModelAndView modelAndView = new ModelAndView("create_category");
        modelAndView.addObject("sonyCategory", category);
        modelAndView.addObject("isEdit", true);
        return modelAndView;
    }

    @PostMapping("/categories/edit/{id}")
    public String editCategorySubmit(@PathVariable("id") Long id,
                                     @ModelAttribute("sonyCategory") SonyCategories sonyCategory,
                                     HttpSession session) {
        if (!canManage(session)) return "redirect:/403";

        sonyCategory.setCateID(id);
        sonyCategoriesService.save(sonyCategory);
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, HttpSession session) {
        if (!canManage(session)) return "redirect:/403";

        sonyCategoriesService.deleteById(id);
        return "redirect:/categories";
    }
}
