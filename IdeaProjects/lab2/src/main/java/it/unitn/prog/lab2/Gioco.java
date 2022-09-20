package it.unitn.prog.lab2;

import java.util.Scanner;

public class Gioco {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exCnd = false;
        Mazzo m = new Mazzo();

        while(!exCnd){

            System.out.print("Scegli un numero naturale: ");

            try{
                m.stampa(Integer.parseInt(scanner.nextLine()));
            }
            catch (NumberFormatException e){
                m.stampa();
            }

            System.out.print("\n\n\n");
            System.out.println("Vuoi ancora giocare? Y/N");
            if(!scanner.nextLine().equals("Y"))
                exCnd = true;

            m.shuffle();
        }

    }
}