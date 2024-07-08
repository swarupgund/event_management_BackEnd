package com.swarup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swarup.entity.EventsEntity;
import com.swarup.entity.Form;
import com.swarup.serviceInterface.EventServiceInterface;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/Events")
public class EventsController {
	
    @Autowired
    private EventServiceInterface service;

    @GetMapping("BookingPage")
    public ResponseEntity<?> getEventBookingPage(@RequestParam String eventCategory, HttpSession session) {
        EventsEntity entity = service.loadEventData(eventCategory);
        if (entity != null) {
            return ResponseEntity.ok(entity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(eventCategory + " Under Development");
        }
    }
    
    ////Demo User booking page
    @GetMapping("/booking")
    public ResponseEntity<?> getUserBookings(@RequestParam String email) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        List<Form> list = service.getUserEvents(email);
        return ResponseEntity.ok(list);
    }

//    // User booking page MAIN
//    @GetMapping("/booking")
//    public ResponseEntity<?> getUserBookings(HttpSession session) {
//        String userEmail = (String) session.getAttribute("umail");
//        if (userEmail == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//        }
//
//        List<Form> list = service.getUserEvents(userEmail);
//        return ResponseEntity.ok(list);
//    }

    // User Booking Canceling
    @DeleteMapping("deleteUserBooking")
    public ResponseEntity<String> cancelUserBooking(@RequestParam Integer id) {
        service.deleteUserBooking(id);
        return ResponseEntity.ok("Event of id: " + id + " Cancelled successfully");
    }
}
