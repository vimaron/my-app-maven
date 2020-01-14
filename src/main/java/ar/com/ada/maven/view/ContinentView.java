package ar.com.ada.maven.view;

import ar.com.ada.maven.model.dto.ContinentDTO;
import ar.com.ada.maven.utils.ScannerSingleton;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContinentView {
    public int continentMenuSelectOption(){
        System.out.println("Modulo Continente ");
        System.out.println("Seleccione una opcion: \n 1.lista \n 2.agregar \n 3.editar \n 4.eliminar \n 5.salir");

        Scanner key = ScannerSingleton.getInstance();

        while (true){
            try {
                int selectOpcion = key.nextInt();
                key.nextLine();
                return selectOpcion;
            } catch (InputMismatchException e){
                System.out.println("El caracter ingresado es erroneo");
                key.next();
            }
        }
    }
    public void printAllContinents(List<ContinentDTO> continents){
        System.out.println("Listado de continente");
        continents.forEach(continent -> {
            String contName = continent.getName();
            int contId = continent.getId();
            System.out.println("Continente [id:  " + contId + " y nombre: " + contName + "]");
        });
        ScannerSingleton.pressEnterKeyToContinue();
    }

    public String getNameNewContinent(){
        System.out.println("Este es un formulario para crear un continente");
        System.out.println("Ingresar el nombre (si deja el texto vacio, se cancela el proceso de guardado): ");

        Scanner key = ScannerSingleton.getInstance();
 //       key.nextLine();
        while (true){
            try {
                String name = key.nextLine().trim();
                while (!name.matches("[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()){
                    System.out.println("ERRO :: Debe ingresar un dato valido");
                    name = key.nextLine();
                }
                return name;
            } catch (InputMismatchException e){
                System.out.println("ERROR :: Debe ingresar un dato valido");
                key.next();
            }
        }
    }

    public void showNewContinent(String continentName){
        System.out.println("El nuevo continente " + continentName + " se guardo correctamente");
        ScannerSingleton.pressEnterKeyToContinue();
    }

    public void newContinentCanceled(){
        System.out.println("El continente no ha sido guardado");
        ScannerSingleton.pressEnterKeyToContinue();
    }

    public void continentAlreadyExists(String continentExist){
        System.out.println("El nombre ingresado ya existe");
        ScannerSingleton.pressEnterKeyToContinue();
    }

    public String printContinentsPerPage(List<ContinentDTO> continents, List<String> paginator){
        System.out.println("\n+-------------------------------------------------------------------+");
        System.out.println("\n Zoo World App :: Modulo Continente :: Lista Continente");
        System.out.println("\n+-------------------------------------------------------------------+");

        System.out.println("ID\t|\tCONTINENTE");
        continents.forEach(continent ->{
            System.out.println(continent.getId() + "\t|\t" + continent.getName());
        });
        System.out.println("\n+-------------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.print("[Editar]");
        System.out.println("\n+-------------------------------------------------------------------+");

        Scanner key = ScannerSingleton.getInstance();

        while (true){
            try {
                System.out.print("? ");
                String name = key.nextLine().trim();
                while (!name.matches("^[0-9IiAaSsUuEe]+$") && !name.isEmpty()) {
                    System.out.println("ERROR :: Debe ingresar una opcion valida");
                    name = key.nextLine();
                }
                return name;
            } catch (InputMismatchException e){
                System.out.println("ERROR :: ");
                key.next();
            }
        }
    }

}
