package com.mygym.FitBookerApp1.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Method to book a class
    public String bookClass(String name, String email, String classTime) {
        // Determine the maximum capacity based on the class type
        int maxCapacity = getMaxCapacityForClass(classTime); // Dynamic capacity for each class type
        if (maxCapacity == -1) {
            return "Invalid class time";  // Return if the class type is invalid
        }

        // Check how many bookings exist for the class
        long count = bookingRepository.countByClassTime(classTime);

        if (count >= maxCapacity) {
            return "Class is full";  // Return message if class is full
        }

        // Create a new booking
        Booking newBooking = new Booking(name, email, classTime);
        bookingRepository.save(newBooking);

        return "Booking successful";  // Return success message
    }

    // Helper method to get max capacity based on the class time
    private int getMaxCapacityForClass(String classTime) {
        switch (classTime.toLowerCase()) {
            case "zumba":
                return 10; // Max capacity for Zumba
            case "hiit":
                return 10; // Max capacity for HIIT
            case "yoga":
                return 10; // Max capacity for Yoga
            default:
                return -1; // Invalid class type
        }
    }
}
