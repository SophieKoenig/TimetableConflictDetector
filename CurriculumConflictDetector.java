import java.util.*;

public class CurriculumConflictDetector {

    public void detectCurriculumConflicts(List<Lecture> lectures, Map<String, List<String>> curricula) {
        Map<String, Lecture> lectureMap = new HashMap<>();
        for (Lecture lecture : lectures) {
            lectureMap.put(lecture.id, lecture);
        }

        for (Map.Entry<String, List<String>> entry : curricula.entrySet()) {
            String curriculum = entry.getKey();
            List<String> lectureIds = entry.getValue();

            for (int i = 0; i < lectureIds.size(); i++) {
                Lecture lecture1 = lectureMap.get(lectureIds.get(i));
                for (Lecture.Booking booking1 : lecture1.getBookings()) { // Accessing Booking as Lecture.Booking
                    for (int j = i + 1; j < lectureIds.size(); j++) {
                        Lecture lecture2 = lectureMap.get(lectureIds.get(j));
                        for (Lecture.Booking booking2 : lecture2.getBookings()) { // Accessing Booking as Lecture.Booking
                            if (booking1.day.equals(booking2.day) && isConflict(booking1, booking2)) {
                                System.out.println("Curriculum Conflict detected in " + curriculum + ": " +
                                        lecture1.name + " (" + booking1 + ") overlaps with " +
                                        lecture2.name + " (" + booking2 + ")");
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isConflict(Lecture.Booking booking1, Lecture.Booking booking2) { // Update to use Lecture.Booking
        return (booking1.startTime < booking2.endTime && booking2.startTime < booking1.endTime);
    }
}
