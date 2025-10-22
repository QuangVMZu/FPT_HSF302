package sum25.SE192755.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sum25.SE192755.Entity.SonyAccounts;
import sum25.SE192755.Service.SonyAccountsService;

@Controller
@RequiredArgsConstructor
public class SonyAccountsController {

    private final SonyAccountsService sonyAccountsService;

    // ✅ Dùng chung 1 key session để interceptor, login, logout hiểu nhau
    private static final String SESSION_KEY = "LoggedInAccount";

    // 🟢 Điều hướng root → login (nếu chưa đăng nhập)
    @GetMapping("/")
    public String root(HttpSession session) {
        SonyAccounts acc = (SonyAccounts) session.getAttribute(SESSION_KEY);
        if (acc != null) {
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    // 🟢 Trang đăng nhập
    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        SonyAccounts acc = (SonyAccounts) session.getAttribute(SESSION_KEY);
        if (acc != null) {
            return "redirect:/home";
        }
        return "login"; // templates/login.html
    }

    // 🟢 Xử lý đăng nhập
    @PostMapping("/login")
    public String processLogin(HttpSession session,
                               @RequestParam String phone,
                               @RequestParam String password) {
        SonyAccounts acc = sonyAccountsService.findByPhoneAndPassword(phone, password);

        if (acc == null) {
            return "redirect:/login?error"; // Sai thông tin đăng nhập
        }

        // Ghi session
        session.setAttribute(SESSION_KEY, acc);

        return "redirect:/home"; // admin

    }

    // 🟢 Trang home
    @GetMapping("/home")
    public String home(HttpSession session) {
        SonyAccounts acc = (SonyAccounts) session.getAttribute(SESSION_KEY);
        if (acc == null) {
            return "redirect:/login";
        }
        return "home";   // ai đăng nhập cũng vào được home
    }

    // 🟢 Trang lỗi phân quyền
    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }

    // 🟢 Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

}
