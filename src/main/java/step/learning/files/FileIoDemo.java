package step.learning.files;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileIoDemo {
    /**
     * Демонастрация работы с файлами для сохранения данных
     */
    public void run1() {
        String fileName = "test.txt";
        try (OutputStream writer = new FileOutputStream(fileName, false)) {
            writer.write("Hello, world!".getBytes());
            System.out.printf("File '%s' created%n", fileName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("\r\nHello, world again!");
            System.out.printf("File '%s' updated%n", fileName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        try (InputStream reader = new FileInputStream(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("-------------------------------------");

        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileName))) {
            int cnt;
            while ((cnt = reader.read(buffer)) > 0) {
                byteBuilder.write(buffer, 0, cnt);
            }

            System.out.println(byteBuilder.toString(StandardCharsets.UTF_16.name()));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("-------------------------------------");

        try (InputStream stream = new FileInputStream(fileName); Scanner scanner = new Scanner(stream)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("-------------------------------------");

        // Задача: создать файл с случайным количеством (от 20 до 100) строк, каждая с которых содержит случайное количество (от 10 до 100) случайных символов
        // сами символы тоже случайны (коды от 20 до 127) - см. таблицу ASCII
        String file_pract = "pract.txt";
        int minLines = 20;
        int maxLines = 100;
        int minChars = 10;
        int maxChars = 100;
        int minCode = 20;
        int maxCode = 127;
        int lines = (int) (Math.random() * (maxLines - minLines)) + minLines;
        StringBuilder strBuilder = new StringBuilder();

        try (OutputStream writer = new FileOutputStream(file_pract, false)) {
            for (int i = 0; i < lines; i++) {
                strBuilder.setLength(0);
                int chars = (int) (Math.random() * (maxChars - minChars)) + minChars;

                for (int j = 0; j < chars; j++) {
                    int code = (int) (Math.random() * (maxCode - minCode)) + minCode;
                    strBuilder.append((char) code);
                }

                writer.write(strBuilder.toString().getBytes());
                writer.write('\n');
            }
            System.out.printf("File '%s' updated%n", file_pract);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        //  Д.З.
        //  1. Реалізувати виведення вмісту директорії у стилі команди "dir"("ls")
        //  Mode          LastWriteTime     Length   Name
        //  ----          -------------     ------   ----
        //  d-----     21.09.2023  14:43             .idea
        //  d-----     20.09.2023  12:32             src
        //  d-----     20.09.2023  12:42             target
        //  d-----     21.09.2023  14:12             upload
        //  -a----     20.09.2023  12:32       490   .gitignore
        //  -a----     20.09.2023  15:43      1093   pom.xml
        //
        //  2. Визначити у файлі "lines.txt" найдовший рядок
        //  (вивести його номер, довжину та контент)
    }

    public void run() {
        displayDirectoryContents("./");
        System.out.println("------------------------------------------------------------");
        findLongestLine("pract.txt");
    }

    public static void displayDirectoryContents(String directoryPath) {
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            System.err.println("Invalid directory path: " + directoryPath);
            return;
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            System.out.println("Mode          LastWriteTime     Length   Name");
            System.out.println("----          -------------     ------   ----");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm");

            for (Path entry : stream) {
                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);

                String type = (attributes.isDirectory()) ? "d-----" : "f-----";
                String lastWriteTime = dateFormat.format(new Date(attributes.lastModifiedTime().toMillis()));
                long length = attributes.size();
                String name = entry.getFileName().toString();

                System.out.printf("%s  %s  %12d   %s%n", type, lastWriteTime, length, name);
            }
        } catch (IOException e) {
            System.err.println("Error listing directory: " + e.getMessage());
        }
    }

    public static void findLongestLine(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String longestLine = "";
            int longestLineLength = 0;
            int lineNumber = 0;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;
                int currentLineLength = currentLine.length();

                if (currentLineLength > longestLineLength) {
                    longestLine = currentLine;
                    longestLineLength = currentLineLength;
                }
            }

            if (longestLineLength > 0) {
                System.out.println("Longest line in '" + filePath + "':");
                System.out.println("Line number: " + lineNumber);
                System.out.println("Length: " + longestLineLength);
                System.out.println("Content: " + longestLine);
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
