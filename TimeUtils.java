
public class TimeUtils {

    // Converts time in HH:mm:ss or HH:mm format to total minutes since midnight
    public static int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // Converts minutes since midnight to HH:mm format
    public static String convertMinutesToTime(int minutes) {
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;
        return String.format("%02d:%02d", hours, remainingMinutes);
    }
}

