package ar.com.ada.maven.controller;

import ar.com.ada.maven.view.MainView;

public class MainController {
    private static MainView view = new MainView();
    public static void run(){
        boolean bool = false;
        while (!bool){
            int varWhile = view.selectOption();
            switch (varWhile){
                case 1: ContinentController.init(); break;
                case 2: bool = true; break;
                default:
                    System.out.println("Seleccionar una opcion valida");
            }
        }
    };

}
