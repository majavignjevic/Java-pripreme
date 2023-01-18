package hr.java.vjezbe.entitet;

import java.util.Scanner;

public sealed interface Online permits Ispit{
    Scanner unos = new Scanner(System.in);//definiramo ulaznu klasu scanner
        default void unesiProgram(){
            System.out.print("Unesite naziv programa: ");
            String nazivPrograma = unos.nextLine();
            System.out.println("Na online ispitu se koristi program: '"+ nazivPrograma + "'!");
        }
}
