package com.swarup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swarup.entity.Form;
import com.swarup.entity.RegisterEntity;
import com.swarup.serviceInterface.FormService;
import com.swarup.serviceInterface.RegisterService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(value="http://localhost:3000")
@RestController
//@RequestMapping("/EventManagement")
@RequestMapping("/api")
public class RegisterController {

	@Autowired
	private RegisterService service;
	
	

	// Register a new user
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterEntity user) {
		if (service.checkUser(user.getUserEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		Integer uid = service.saveUser(user);
		if (uid > 0) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(user.getUserName() + " Registered Successfully with id: " + uid);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration Unsuccessful");
		}
	}

	// User login
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody RegisterEntity entity, HttpSession session) {
		String status = service.loginUser(entity.getUserEmail(), entity.getUserPassword(), session);
		if ("success".equals(status)) {
			if ("admin@gmail.com".equals(entity.getUserEmail()) && "88888888".equals(entity.getUserPassword())) {
				return ResponseEntity.ok("Admin login successful");
			} else {
				return ResponseEntity.ok("User login successful");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}
	
	//user  details
	  @GetMapping("/user")
	    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
		  System.out.println(email);
	        RegisterEntity user = service.findByEmail(email);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.ok("user not found");
	        }
	    }

	// User logout
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Logged out");
	}

	// Forgot password
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody RegisterEntity entity) {
		String result = service.forgotPassword(entity.getUserEmail(), entity.getUserPassword());
		if ("success".equals(result)) {
			return ResponseEntity.ok("Password changed successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user email");
		}
	}

	// Check email availability
	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
		boolean isEmailAvailable = service.isCodeAvailable(email);
		return ResponseEntity.ok(	isEmailAvailable);
	}

	// Admin: Get all users
//	@GetMapping("/users")
//	public ResponseEntity<List<RegisterEntity>> getAllUsers() {
//		List<RegisterEntity> users = service.data();
//		return ResponseEntity.ok(users);
//	}

	// Admin: Delete user booking
	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<String> deleteUserBooking(@PathVariable Integer id) {
		service.deleteUserBookingByAdmin(id);
		return ResponseEntity.ok("Event with id: " + id + " cancelled successfully");
	}

	// Admin: Get all bookings
	@GetMapping("/bookings")
	public ResponseEntity<List<Form>> getAllBookings() {
		List<Form> bookings = service.getAllBookings();
		return ResponseEntity.ok(bookings);
	}

	// Admin: Delete user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return ResponseEntity.ok("User with id: " + id + " deleted successfully");
	}
}
