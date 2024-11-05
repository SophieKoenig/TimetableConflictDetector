import java.util.*;

public class RoomConflictDetector {
    private Map<String, List<Lecture.Booking>> schedule = new HashMap<>();

    public void detectRoomConflicts(List<Lecture> lectures) {
        for (Lecture lecture : lectures) {
            for (Lecture.Booking booking : lecture.getBookings()) {
                // Key based on room and day to find potential conflicts only within the same room on the same day
                String key = booking.getRoom() + "-" + booking.getDay();
                List<Lecture.Booking> bookingsInRoom = schedule.getOrDefault(key, new ArrayList<>());

                for (Lecture.Booking existingBooking : bookingsInRoom) {
                    if (isConflict(existingBooking, booking)) {
                        System.out.println("Room Conflict detected in " + booking.getRoom() + " on " + booking.getDay() + ": " +
                                "(" + lecture.getName() + " " + booking + ") overlaps with another booking " +
                                "(" + getLectureName(existingBooking, lectures) + " " + existingBooking + ")");
                    }
                }
                bookingsInRoom.add(booking);
                schedule.put(key, bookingsInRoom);
            }
        }
    }

    private boolean isConflict(Lecture.Booking booking1, Lecture.Booking booking2) {
        return (booking1.getStartTime() < booking2.getEndTime() && booking2.getStartTime() < booking1.getEndTime());
    }

    private String getLectureName(Lecture.Booking booking, List<Lecture> lectures) {
        for (Lecture lecture : lectures) {
            if (lecture.getBookings().contains(booking)) {
                return lecture.getName();
            }
        }
        return "Unknown Lecture";
    }
}
