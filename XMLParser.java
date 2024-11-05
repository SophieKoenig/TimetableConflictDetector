import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMLParser {

    public List<Lecture> parseLectures(String xmlFilePath) throws Exception {
        List<Lecture> lectures = new ArrayList<>();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFilePath));

        NodeList lectureNodes = doc.getElementsByTagName("lecture");

        for (int i = 0; i < lectureNodes.getLength(); i++) {
            Element lectureElement = (Element) lectureNodes.item(i);
            String id = getTagValue(lectureElement, "id");
            String name = getTagValue(lectureElement, "name");

            Lecture lecture = new Lecture(id, name);

            NodeList bookings = lectureElement.getElementsByTagName("booking");
            for (int j = 0; j < bookings.getLength(); j++) {
                Element bookingElement = (Element) bookings.item(j);
                String room = getTagValue(bookingElement, "room");
                String day = getTagValue(bookingElement, "weekday");
                int startTime = TimeUtils.convertTimeToMinutes(getTagValue(bookingElement, "startTime"));
                int endTime = TimeUtils.convertTimeToMinutes(getTagValue(bookingElement, "endTime"));

                lecture.addBooking(room, day, startTime, endTime);
            }

            lectures.add(lecture);
        }
        return lectures;
    }

    public Map<String, List<String>> parseCurricula(String xmlFilePath) throws Exception {
        Map<String, List<String>> curricula = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFilePath));

        NodeList curriculumNodes = doc.getElementsByTagName("curriculum");

        for (int i = 0; i < curriculumNodes.getLength(); i++) {
            Element curriculumElement = (Element) curriculumNodes.item(i);
            String curriculumName = getTagValue(curriculumElement, "name");

            NodeList lectureIds = curriculumElement.getElementsByTagName("lecture");
            List<String> lectureList = new ArrayList<>();
            for (int j = 0; j < lectureIds.getLength(); j++) {
                lectureList.add(lectureIds.item(j).getTextContent().trim());
            }

            curricula.put(curriculumName, lectureList);
        }
        return curricula;
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }
}

