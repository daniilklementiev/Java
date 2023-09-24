package step.learning.files;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Демонстрация работы с файлами и папками как с объектами файловой системы
 */
public class DirDemo {
    public void run() {
        System.out.println("Directories demo");
        String path = "./";
        File dir = new File(path);
        if (dir.exists()) {
            System.out.printf("Directory %s exists @ real path '%s' and it is %s%n", path, dir.getAbsolutePath(), dir.isDirectory() ? "directory" : "file");
            // вывод содержимого папки
            try {
                for (File file : dir.listFiles()) {
                    System.out.printf("File: %s, isDir: %s%n", file.getName(), file.isDirectory());
                }
            }
            catch(NullPointerException ignored){
                System.err.println("Iteration exception: Directory is empty");
            }
        } else {
            System.out.printf("Directory %s does not exist%n", path);
        }
        String subPath = "./upload";
        File subDir = new File(subPath);
        boolean needCreate = true;
        if (subDir.exists() && subDir.isDirectory()) {
            System.out.printf("Dir '%s' exists%n", subPath);
            needCreate = false;
        } else {
            if (subDir.exists()) {
                System.out.printf("Dir '%s' exists but it is not a directory%n", subPath);

                System.out.print("Delete object? ");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                switch (answer) {
                    case "y": {
                        System.out.println("Deleting...");
                        if (subDir.delete()) {
                            System.out.printf("Dir '%s' deleted%n", subPath);
                            needCreate = true;
                        } else {
                            System.out.printf("Dir '%s' was not deleted%n", subPath);
                            needCreate = false;
                        }
                        break;
                    }
                    case "n": {
                        System.out.println("Exiting...");
                        break;
                    }
                    default: {
                        System.out.println("Invalid input");
                        break;
                    }
                }
            } else {
                System.out.printf("Dir '%s' does not exist, creating....%n", subPath);
                if (subDir.mkdir()) {
                    System.out.printf("Dir '%s' created%n", subPath);
                    needCreate = false;
                } else {
                    System.out.printf("Dir '%s' was not created%n", subPath);
                }
            }
            if (needCreate) {
                if (subDir.mkdirs()) {
                    System.out.printf("Dir '%s' created%n", subPath);
                } else {
                    System.out.printf("Dir '%s' was not created%n", subPath);
                }
            }
        }
        String logPath = subPath + File.separator + "actions.log";
        File logFile = new File(logPath);
        if (logFile.isFile()) {
            System.out.printf("File '%s' exists%n", logPath);
        } else {
            if (logFile.exists()) {
                System.out.printf("Object '%s' exists but it is not a file%n", logPath);

                System.out.print("Delete object? ");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if(answer.equals("y") || answer.equals("Y")) {
                    System.out.println("Deleting...");
                    if (logFile.delete()) {
                        System.out.printf("Object '%s' deleted%n", logPath);
                    } else {
                        System.out.printf("Object '%s' was not deleted%n", logPath);
                    }
                } else {
                    System.out.println("Exiting...");
                }
            } else {
                try {
                    if (logFile.createNewFile()) {
                        System.out.printf("File '%s' created%n", logPath);
                    } else {
                        System.out.printf("File '%s' was not created%n", logPath);
                    }
                } catch (Exception e) {
                    System.out.printf("File '%s' was not created%n", logPath);
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

/*
Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
d-----        21.09.2023     14:43                .idea
d-----        20.09.2023     13:12                src
d-----        20.09.2023     13:13                target
d-----        21.09.2023     14:36                upload
-a----        20.09.2023     13:12            490 .gitignore
-a----        20.09.2023     13:12            784 pom.xml
 */
