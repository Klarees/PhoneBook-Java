import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private static final String DATA_PATH = "src/contacts.csv";

    private static void saveContacts(Map<String, List<String>> contacts) {
        try (PrintWriter writer = new PrintWriter(DATA_PATH)) {
            if (!contacts.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
                    String line = String.format("%s,\"%s\"",
                            entry.getKey(), entry.getValue().toString().replaceAll("\\[|]", ""));
                    writer.println(line);
                }
            }

        } catch (IOException ioex) {
            System.err.println(ioex.getMessage());
        }
    }
}