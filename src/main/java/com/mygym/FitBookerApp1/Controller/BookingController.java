package com.mygym.FitBookerApp1.Controller;

import com.mygym.FitBookerApp1.Model.Booking;
import com.mygym.FitBookerApp1.Model.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // Endpoint to book a class
    @PostMapping
    public ResponseEntity<String> bookClass(@RequestParam String name, @RequestParam String email, @RequestParam String classTime) {
        long count = bookingRepository.countByClassTime(classTime);
        if (count >= 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Class is full");
        }

        Booking newBooking = new Booking(name, email, classTime);
        bookingRepository.save(newBooking);

        return ResponseEntity.ok("Booking successful");
    }

    // Endpoint to get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();  // Return all bookings in the database
    }

    // Endpoint to delete a booking
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteBooking(@PathVariable("id") Long bookingId) {
    // Check if the booking exists in the database
    if (bookingRepository.existsById(bookingId)) {
        bookingRepository.deleteById(bookingId); // Delete the booking
        return ResponseEntity.noContent().build(); // Return 204 No Content on success
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if booking doesn't exist
    }
}
}
