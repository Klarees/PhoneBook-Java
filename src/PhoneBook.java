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
    private static void loadContacts(Map<String, List<String>> contacts) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH))) {

            Pattern pattern = Pattern.compile("^([^,\"]{2,50}),\"([0-9+, ]+)\"$");

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String[] numbers = matcher.group(2).split(",\\s*");
                    contacts.put(matcher.group(1), Arrays.asList(numbers));
                }
            }

        } catch (IOException ioex) {
            System.err.println("Could not load contacts, phone book is empty!");
        }
    }
}