package com.mygym.FitBookerApp1.Controller;

import com.mygym.FitBookerApp1.Model.Booking;
import com.mygym.FitBookerApp1.Model.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingViewController {

    @Autowired
    private BookingRepository bookingRepository;

    // Endpoint to view all bookings
    @GetMapping("/view-bookings")
    public String viewBookings(Model model) {
        List<Booking> bookings = bookingRepository.findAll(); // Fetch all bookings
        model.addAttribute("bookings", bookings); // Add the bookings list to the model
        return "bookings"; // This will render bookings.html template
    }
}

