package bankaccount.service;

import java.util.ArrayList;

public class ServicePrinter {


    public static void print(ArrayList list) {
        list.forEach(System.out::println);
    }
}
