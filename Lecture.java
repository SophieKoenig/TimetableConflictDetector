
import java.util.ArrayList;
import java.util.List;

public class Lecture {
    String id;
    String name;
    List<Booking> bookings; // List to store multiple bookings

    public Lecture(String id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    // Method to add a booking to the lecture
    public void addBooking(String room, String day, int startTime, int endTime) {
        this.bookings.add(new Booking(room, day, startTime, endTime));
    }

    // Inner class for Booking
    public class Booking {
        String room;
        String day;
        int startTime; // Store time in integer format, e.g., 1000 for 10:00
        int endTime;

        public Booking(String room, String day, int startTime, int endTime) {
            this.room = room;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            String formattedStartTime = TimeUtils.convertMinutesToTime(startTime);
            String formattedEndTime = TimeUtils.convertMinutesToTime(endTime);
            return "Booking{" +
                   "room='" + room + '\'' +
                   ", day='" + day + '\'' +
                   ", startTime=" + formattedStartTime +
                   ", endTime=" + formattedEndTime +
                   '}';
        }

        // Getters
        public String getRoom() { return room; }
        public String getDay() { return day; }
        public int getStartTime() { return startTime; }
        public int getEndTime() { return endTime; }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lecture{" +
                                             "id='" + id + '\'' +
                                             ", name='" + name + '\'' +
                                             ", bookings=");
        for (Booking booking : bookings) {
            sb.append("\n\t").append(booking.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    // Getters for Lecture class fields
    public String getId() { return id; }
    public String getName() { return name; }
    public List<Booking> getBookings() { return bookings; }
}

