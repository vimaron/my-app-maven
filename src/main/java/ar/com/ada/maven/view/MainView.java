package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.ScannerSingleton;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ar.com.ada.maven.utils.ScannerSingleton.keyboard;

public class MainView {
    public int selectOption(){

        System.out.println("Welcome to My App Maven");
        System.out.println("Selecciones una opcion: \n 1.Continente \n 2.Salir");

        Scanner key = ScannerSingleton.getInstance();


        while (true){
            try {
                int choice = key.nextInt();
                return choice;
            } catch (InputMismatchException e){
                System.out.println("El caracter ingresado es erroneo");
                key.next();
            }
        }
    }

}
