package by.kulikovski.rest.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {

        // ObjectMapper uses getter/setter methods. Just need them and no-args constructor
        // also we can use special method to make Jackson ignore some unknown properties (see Student class)
        // if a new property will be included to a JSON file (see student_full.json "company" property needs to be ignored)
        // is this case an addition of new fields/properties wouldn't cause an exception

        // @JsonIgnoreProperties(ignoreUnknown = true) on class to ignore any unknown property

        try {
            // create mapper
            ObjectMapper mapper = new ObjectMapper();

            // read JSON and convert to POJO
            Student student = mapper.readValue(new File("src/main/resources/student_simple.json"), Student.class);

            System.out.println("First name: " + student.getFirstName());
            System.out.println("Last name: " + student.getLastName());

            // show null cause student_simple doesn't include those fields
            System.out.println("Address: " + student.getAddress());
            System.out.println("Languages: " + Arrays.toString(student.getLanguages()));

            System.out.println("*************");

            Student student2 = mapper.readValue(new File("src/main/resources/student_full.json"), Student.class);
            System.out.println("First name: " + student2.getFirstName());
            System.out.println("Last name: " + student2.getLastName());
            System.out.println("Address: " + student2.getAddress());
            System.out.println("Languages: " + Arrays.toString(student2.getLanguages()));

            System.out.println("*************");

            // also an information from nested JSONs and arrays can be displayed separately
            System.out.printf("Student's %s city is %s", student2.getLastName(), student2.getAddress().getCity());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
