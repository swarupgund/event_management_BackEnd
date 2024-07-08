package com.swarup.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swarup.entity.EventsEntity;
import com.swarup.entity.Form;
import com.swarup.serviceInterface.EventServiceInterface;
import com.swarup.serviceInterface.FormService;

import jakarta.servlet.http.HttpSession;
@CrossOrigin(value="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FormController {

  @Autowired
  FormService service;
  @Autowired
  EventServiceInterface eService;

  @GetMapping("/Form/BookingForm")
  public ResponseEntity<EventsEntity> getFinalForm(@RequestParam String eventCategory, HttpSession session) {
    EventsEntity ee = eService.loadEventData(eventCategory);
    if (ee != null) {
      return ResponseEntity.ok(ee);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }
  
  @PostMapping("/Form/saveForm")
  public ResponseEntity<?> saveForm(@RequestBody Form form) {
      service.saveForm(form);
      return ResponseEntity.status(HttpStatus.CREATED).build();
  }
    
  
  //main saveform
//  @PostMapping("/Form/saveForm")
//  public ResponseEntity<String> saveForm(@RequestBody Form form) {
//      try {
//          // Validate form inputs (e.g., check for nulls or empty strings)
//          if (form.getName() == null || form.getName().isEmpty()) {
//              return ResponseEntity.badRequest().body("Username is required");
//          }
//          
//          int ref = service.save(form);
//          if (ref > 0) {
//              // Log success
//              System.out.println("Booking saved successfully: " + form.toString());
//              return ResponseEntity.status(HttpStatus.CREATED).body("Booking successful");
//          } else {
//              // Log failure
//              System.err.println("Failed to save booking: " + form.toString());
//              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking failed");
//          }
//      } catch (Exception e) {
//          // Log exception
//          System.err.println("Exception occurred while saving booking: " + e.getMessage());
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//      }
//  }

}
