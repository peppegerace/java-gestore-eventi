package org.experis.gestioneEventi;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String titolo = "";
        LocalDate data = null;
        int capienza = 0;

        boolean datiValidi = false;

        // prendo i dati dall'utente per creare un evento
        while (!datiValidi) {
            try {
                System.out.print("Inserire titolo evento: ");
                titolo = scan.nextLine();
                if (titolo.isEmpty()) {
                    throw new IllegalArgumentException("Il titolo non può essere vuoto.");
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

        // creo il nuovo evento con i dati raccolti
        Evento event = new Evento(titolo, data, capienza);
        System.out.println("-----------------------------------");
        System.out.println("Evento Creato!");
        System.out.println("-----------------------------------");
        System.out.println(event);
        System.out.println("-----------------------------------");

        // chiedo all'utente se vuole effettuare una prenotazione
        boolean prenotazione = false;
        int postiPrenotati = 0;
        int postiDisponibili = 0;
        while (!prenotazione) {
            System.out.print("Vuoi effettuare una prenotazione? (sì/no): ");
            String rispostaPrenotazione = scan.nextLine().toLowerCase();

            if (rispostaPrenotazione.equals("si")) {

                boolean inputValido = false;
                while (!inputValido) {
                    System.out.print("Quanti posti vuoi prenotare? ");
                    String postiDaPrenotare = scan.nextLine();
                    try {
                        postiPrenotati = Integer.parseInt(postiDaPrenotare);
                        if (postiPrenotati <= 0) {
                            throw new IllegalArgumentException("Il numero di posti deve essere maggiore di 0");
                        }
                        inputValido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: Formato non valido. Inserisci un numero maggiore di 0");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                try {
                    event.prenota(postiPrenotati);
                    System.out.println(postiPrenotati + " posti prenotati");
                    postiDisponibili = capienza - postiPrenotati;
                    System.out.println("Posti ancora disponibili: " + postiDisponibili);
                    prenotazione = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Errore durante la prenotazione: " + e.getMessage());
                }
            } else if (rispostaPrenotazione.equals("no")) {
                System.out.println("Goodbye!");
                prenotazione = true;
            } else {
                System.out.println("Risposta non valida. Rispondi 'si' o 'no'.");
            }
        }

        // stampo a video il numero di posti prenotati e quelli disponibili
        System.out.println("-----------------------------------");
        System.out.println(event);
        System.out.println("Posti disponibili: " + postiDisponibili);
        System.out.println("-----------------------------------");

        // // chiedo all'utente se vuole disdire una prenotazione effettuata
        boolean disdetta = false;
        while (!disdetta) {
            System.out.println("Vuoi disdire una prenotazione? ");
            String rispostaDisdetta = scan.nextLine().toLowerCase();

            if (rispostaDisdetta.equals("si")) {
                boolean inputValido = false;
                while (!inputValido) {
                    System.out.println("Quanti posti vuoi disdire? ");
                    String postiDaDisdire = scan.nextLine();
                    try {
                        int postiDisdetti = Integer.parseInt(postiDaDisdire);
                        if (postiDisdetti <= 0) {
                            throw new IllegalArgumentException("Il numero di posti deve essere maggiore di 0");
                        }
                        event.disdici(postiDisdetti);
                        System.out.println(postiDisdetti + " posti disdetti");
                        postiDisponibili = capienza - event.getPostiPrenotati();
                        System.out.println("Posti ancora disponibili: " + postiDisponibili);
                        inputValido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: Formato non valido. Inserisci u numero maggiore di 0");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore durante la disdetta: " + e.getMessage());
                    }
                }
            } else if (rispostaDisdetta.equals("no")) {
                System.out.println("Goodbye");
                disdetta = true;
            } else {
                System.out.println("Risposta non valida. Rispondi 'si' o 'no'");
            }

        }

        System.out.println("-----------------------------------");
        System.out.println(event);
        System.out.println("Posti disponibili: " + postiDisponibili);
        System.out.println("-----------------------------------");

        scan.close();
    }

}
