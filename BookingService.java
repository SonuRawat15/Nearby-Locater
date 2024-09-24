@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusinessRepository businessRepository;

    public boolean createBooking(BookingRequest bookingRequest) {
        // Check if the business offers booking
        Business business = businessRepository.findById(bookingRequest.getBusinessId())
            .orElseThrow(() -> new IllegalArgumentException("Business not found"));

        if (business.isBookingAvailable()) {
            Booking booking = new Booking();
            booking.setUserId(bookingRequest.getUserId());
            booking.setBusinessId(bookingRequest.getBusinessId());
            booking.setBookingDate(bookingRequest.getBookingDate());
            booking.setServiceDetails(bookingRequest.getServiceDetails());
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    public boolean cancelBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            bookingRepository.delete(booking.get());
            return true;
        }
        return false;
    }
}
