@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {
        boolean isBooked = bookingService.createBooking(bookingRequest);
        if (isBooked) {
            return ResponseEntity.ok("Booking successful!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking failed. Please try again.");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelBooking(@RequestParam Long bookingId) {
        boolean isCancelled = bookingService.cancelBooking(bookingId);
        if (isCancelled) {
            return ResponseEntity.ok("Booking cancelled successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel the booking.");
    }
}
