package step.learning.basics;

import java.sql.SQLOutput;
import java.util.*;

public class BasicsDemo {
    public void run1() {
        System.out.println("Java basics demo");
        // типы данных и переменные - все типы данных являются ссылками (Reference), за исключением примитивов
        byte  b = 10;                      //  Данные целого типа - только знаковые
        short s = 1000;                    //  беззнаковых вариаций - нет
        int   i = 10000000;                //
        long  l = 1000000000000000000L;    //
        // существуют Reference аналоги для этих типов
        Byte rb = 10;
        Short rs = 1000;
        Integer ri = 10000000;
        Long rl = 1000000000000000000L;

        float f = 1e-3f;
        Double d = 3.3e3;
        Float rf = f;
        Double rd = d;
        System.out.printf(  "d = %f (%d), rd = %f (%d)\n", d, d.hashCode(), rd, rd.hashCode()  );
        d*=2;
        System.out.printf(  "d = %f (%d), rd = %f (%d)\n", d, d.hashCode(), rd, rd.hashCode()  );
        rd += 2;
        System.out.printf(  "d = %f (%d), rd = %f (%d)\n", d, d.hashCode(), rd, rd.hashCode()  );

        char c = 'c';
        Character rc = c;

        boolean bool = true;
        Boolean rBool = bool;


        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        System.out.printf(  "str1 = %s (%d), str2 = %s (%d), str3 = %s (%d)\n", str1, str1.hashCode(), str2, str2.hashCode(), str3, str3.hashCode()  );
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str1.equals(str3): " + str1.equals(str3));

    }

    public void run() {
        System.out.println("Java arrays");
        int[] arr1 = { 5, 4, 3, 2, 1};
        for (int i =0; i< arr1.length; i++) {
            System.out.printf("arr1[%d] = %d\n", i, arr1[i]);
        }

        for(int x : arr1) {
            System.out.printf("x = %d\n", x);
        }

        int[][] arr2 = { {1, 2, 3}, {4, 5, 6} };
        for (int i =0; i< arr2.length; i++) {
            for (int j =0; j< arr2[i].length; j++) {
                System.out.printf("arr2[%d][%d] = %d\n", i, j, arr2[i][j]);
            }
        }

        int[][] arr3 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        // вывод как матрица
        for (int[] row : arr3) {
            for (int x : row) {
                System.out.printf("%d ", x);
            }
        }
        System.out.println();

        // Collections
        List<Integer> list = new ArrayList<>(); // List - Interface, ArrayList - implementation
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        for(int x : list) {
            System.out.printf("%d ", x);
        }
        System.out.println();

        // Assotiative arrays
        // Map - Interface, HashMap - implementation
        // Dictionary - Interface, Hashtable - implementation
        Map<String, String> headers = new LinkedHashMap<>(); // Hashmap - не гарантирует порядок элементов, LinkedHashMap - гарантирует
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        for(String key : headers.keySet()) {
            System.out.printf("%s: %s\n", key, headers.get(key));
        }

        // read from console
        Scanner kbScanner = new Scanner(System.in);
        String word = kbScanner.next();
        System.out.println("word = " + word);
    }
}
