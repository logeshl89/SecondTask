import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "phonebook.txt";
    private static Map<String, String> phonebook = new HashMap<>();

    public static void main(String[] args) {
        loadPhonebook();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (add, search, list, quit): ");
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "add" -> addContact(scanner);
                case "search" -> searchContact(scanner);
                case "list" -> listContacts();
                case "quit" -> {
                    savePhonebook();
                    return;
                }
                default -> System.out.println("Invalid command");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.println("Enter a name: ");
        String name = scanner.nextLine();
        System.out.println("Enter a phone number: ");
        String phone = scanner.nextLine();
        phonebook.put(name, phone);
        System.out.println("Contact added");
    }

    private static void searchContact(Scanner scanner) {
        System.out.println("Enter a name: ");
        String name = scanner.nextLine();
        String phone = phonebook.get(name);
        if (phone == null) {
            System.out.println("Contact not found");
        } else {
            System.out.println(name + ": " + phone);
        }
    }

    private static void listContacts() {
        for (String name : phonebook.keySet()) {
            String phone = phonebook.get(name);
            System.out.println(name + ": " + phone);
        }
    }

    private static void loadPhonebook() {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0];
                        String phone = parts[1];
                        phonebook.put(name, phone);
                    }
                }
                scanner.close();
                System.out.println("Phonebook loaded");
            } else {
                System.out.println("Phonebook not found");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void savePhonebook() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            for (String name : phonebook.keySet()) {
                String phone = phonebook.get(name);
                writer.write(name + "," + phone + "\n");
            }
            writer.close();
            System.out.println("Phonebook saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
