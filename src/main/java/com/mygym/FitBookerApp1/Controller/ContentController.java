package com.mygym.FitBookerApp1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    @GetMapping("/bookings")
    public String bookings() {
        return "redirect:/view-bookings";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        // This will trigger the Spring Security logout
        return "redirect:/login"; // Redirect to login page after logout
    }
}
