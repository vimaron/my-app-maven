package ar.com.ada.maven.view;

import ar.com.ada.maven.model.dto.ContinentDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.ScannerSingleton;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContinentView {
    public int continentMenuSelectOption(){
        System.out.println("Zoo World App :: Modulo Continente ");
        System.out.println("Seleccione una opcion: \n 1.Lista \n 2.Agregar \n 3.Editar \n 4.Eliminar \n 5.Salir");

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
        System.out.println("Listado de continentes");
        continents.forEach(continent -> {
            String contName = continent.getName();
            int contId = continent.getId();
            System.out.println("Continente [id:  " + contId + " y nombre: " + contName + "]");
        });
        ScannerSingleton.pressEnterKeyToContinue();
    }

    public String getNameNewContinent(){
        System.out.println("Este es un formulario para crear un continente");
        System.out.println("Ingresar el nombre \n(si deja el texto vacio, se cancela el proceso de guardado) \n");

        Scanner key = ScannerSingleton.getInstance();
 //       key.nextLine();
        while (true){
            try {
                String name = key.nextLine().trim();
                while (!name.matches("[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()){
                    System.out.println("ERROR :: Debe ingresar un dato valido");
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

    public int continentIdSelected(String action){
        System.out.println("Ingrese el numero de ID del continente para " + action + " o 0 para cancelar: \n");

        Scanner key = ScannerSingleton.getInstance();

        while (true){
            try {
                System.out.println("? ");
                int choice = key.nextInt();
                return choice;
            } catch (InputMismatchException e){
                System.out.println("ERROR :: Debe ingresar un ID valido");
                key.next();
            }
        }
    }

    public static String getNameToUpdate(ContinentDTO continent){
        System.out.print("Se actualizara el nombre del siguiente continente : ");
        System.out.println(Ansi.PURPLE + continent.getId() + "\t" + continent.getName() + Ansi.RESET);

        System.out.println("Ingrese el nombre del continente para actualizar ");
        System.out.println("(para cancelar no ingrese nada y presione ENTER):\n");

        Scanner key = ScannerSingleton.getInstance();
        key.nextLine();

        while (true){
            try {
                System.out.println("? ");
                String name = key.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()) {
                    System.out.println("ERROR :: Debe ingresar un dato valido");
                    name = key.nextLine();
                }
                return name;
            } catch (InputMismatchException e){
                System.out.println("ERROR :: Debe ingresar un dato valido");
                key.next();
            }
        }
    }

    public void continentNotExist(int id){
        System.out.println("No existe el continente con el ID " + id + " asociado");
        System.out.println("Seleccione un ID valido o 0 para cancelar");
    }

    public void updateContinentCancelled(){
        System.out.println("Ha cancelado la actualizacion del continente");
        ScannerSingleton.pressEnterKeyToContinue();
    }

    public void showUpdateContinent(String name){
        System.out.println("El continente " + name + " se ha actualizado exitosamente");
        ScannerSingleton.pressEnterKeyToContinue();
    }
}
