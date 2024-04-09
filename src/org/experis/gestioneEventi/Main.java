package org.experis.gestioneEventi;

import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        LocalDate dataEvento = LocalDate.of(2025,1,9);
        Evento concerto = new Evento("Depeche Mode", dataEvento, 10000);
        System.out.println(concerto);
    }
}
