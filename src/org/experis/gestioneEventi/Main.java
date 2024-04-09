package org.experis.gestioneEventi;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        System.out.print("Inserire titolo evento: ");
//        String titolo = scan.nextLine();
//
//        System.out.print("Inserire data evento (formato yyyy-mm-dd): ");
//        LocalDate data = LocalDate.parse(scan.nextLine());
//
//        System.out.print("Inserire la capienza: ");
//        int capienza = Integer.parseInt(scan.nextLine());
//
//        Evento concerto = new Evento(titolo, data, capienza);
//        System.out.println(concerto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String titolo = "";
        LocalDate data = null;
        int capienza = 0;

        boolean datiValidi = false;

        while (!datiValidi) {
            try {
                System.out.print("Inserire titolo evento: ");
                titolo = scan.nextLine();
                if (titolo.isEmpty()) {
                    throw new IllegalArgumentException("Il titolo non può essere vuoto");
                }

                System.out.print("Inserire data evento (formato dd-mm-yyyy): ");
                String inputData = scan.nextLine();
                data = LocalDate.parse(inputData, formatter);
                if (data.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("La data dell'evento è già passata");
                }

                System.out.print("Inserire la capienza: ");
                capienza = Integer.parseInt(scan.nextLine());
                if (capienza <= 0) {
                    throw new IllegalArgumentException("La capienza deve essere superiore a 0");
                }
                datiValidi = true;
            } catch (DateTimeParseException e) {
                System.out.println("Errore: " + e.getMessage() + " Inserisci una data valida nel formato yyyy-mm-dd");
            } catch (NumberFormatException e) {
                System.out.println("Errore: Formato non valido. Inserire un numero intero superiore a 0");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage() + " Riprova.");
            }
        }

        Evento event = new Evento(titolo, data, capienza);
        System.out.println("-----------------------------------");
        System.out.println("Evento Creato!");
        System.out.println("-----------------------------------");
        System.out.println(event);
        System.out.println("-----------------------------------");

        scan.close();
    }
}
