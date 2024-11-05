
import java.util.*;

public class Main {
    public static void main(String[] args) {
        XMLParser parser = new XMLParser();

        try {
            List<Lecture> lectures = parser.parseLectures("timetable.xml");
            Map<String, List<String>> curricula = parser.parseCurricula("timetable.xml");

            RoomConflictDetector roomDetector = new RoomConflictDetector();
            roomDetector.detectRoomConflicts(lectures);

            CurriculumConflictDetector curriculumDetector = new CurriculumConflictDetector();
            curriculumDetector.detectCurriculumConflicts(lectures, curricula);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

