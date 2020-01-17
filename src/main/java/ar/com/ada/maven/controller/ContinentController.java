package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.dao.ContinentDAO;
import ar.com.ada.maven.model.dto.ContinentDTO;
import ar.com.ada.maven.view.ContinentView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContinentController {
    private static ContinentView view = new ContinentView();
    private static ContinentDAO continentDAO = new ContinentDAO();

    private static void continentList() {
        List<ContinentDTO> contDAOFindAll = continentDAO.findAll();
//        view.printAllContinents(contDAOFindAll);
    }

    public static void init() {
        boolean bool = false;
        while (!bool) {
            int varWhile = view.continentMenuSelectOption();
            switch (varWhile) {
                case 1:
                    continentList();
                    break;
                case 2:
                    createNewContinent();
                    break;
                case 3:
                    editContinent();
                    break;
                default:
                    System.out.println("Se debe seleccionar una opcion valida");
            }
        }
    }

    public static void createNewContinent() {
        String newContinentName = view.getNameNewContinent();
        if (!newContinentName.isEmpty()) {
            ContinentDTO newContinent = new ContinentDTO(newContinentName);
            ContinentDTO byName = continentDAO.findByName(newContinentName);
            if (byName != null && newContinent.getName().toLowerCase().equals(byName.getName().toLowerCase())) {
                view.continentAlreadyExists(newContinent.getName());
            } else {
                Boolean var = continentDAO.save(newContinent);
                if (var) {
                    view.showNewContinent(newContinent.getName());
                }
            }
        } else {
            view.newContinentCanceled();
        }

    }

    private static List<String> buildPaginator(int currentPage, int totalPages){
        List<String> pages = new ArrayList<>();
        pages.add("[Inicio]");
        pages.add("[Anterior]");

        for (int i = 1; i <= totalPages; i++){
            if (i == currentPage + 1)
                pages.add("[>" + i + "<]");
            else
                pages.add("[" + i + "]");
        }
        pages.add("[Siguiente]");
        pages.add("[Ultimo]");

        return pages;
    }

    private static int continentListPerPage(){
        int limit = 3, currentPage = 0;
        List<ContinentDTO> continents;
        int numberContinents;
        int totalPages;
        List<String> paginator;
        boolean shouldGetOut = false;
        while (!shouldGetOut){
            continents = continentDAO.findAll(limit, currentPage * limit);
            numberContinents = continentDAO.getTotalContinents();
            System.out.println("Cantidad de registros " + numberContinents);
            totalPages = (int) Math.ceil((double) numberContinents / limit);
            paginator = buildPaginator(currentPage, totalPages);
            String choice = view.printContinentsPerPage(continents, paginator);
            switch (choice){
                case "i": case "I":
                    currentPage = 0;
                    break;
                case "a": case "A":
                    if (currentPage > 0) currentPage--;
                    break;
                case "s": case "S":
                    if (currentPage + 1 < totalPages)
                        currentPage++;
                    break;
                case "u": case "U":
                    currentPage = totalPages - 1;
                    break;
                case "e": case "E":
                    return view.continentIdSelected("Editar");
                    break;
                case "q": case "Q":
                    shouldGetOut = true;
                    break;
                default:
                    if (choice.matches("-?\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else System.out.println("ERROR :: Debe ingresar una opcion valida del paginador");
            }
        }
    }

    private static void editContinent(){
        int continentIdToEdith = continentListPerPage();
        if (continentIdToEdith != 0)
            editSelectedContinent(continentIdToEdith);
        else
            view.updateContinentCancelled();
    }

    private static void editSelectedContinent(int id){
        ContinentDTO continent = continentDAO.findById(id);
        if (continent != null) {
            String nameToUpdate = view.getNameToUpdate(continent);
            if (!nameToUpdate.isEmpty()){
                continentDAO.findByName(nameToUpdate);
                continent.setName(nameToUpdate);

                Boolean isSaved = continentDAO.update(continent, id);
                if (isSaved)
                    view.showUpdateContinent(continent.getName());

            } else
                view.updateContinentCancelled();
        } else {
            view.continentNotExist(id);
            int continentIdSelected = view.continentIdSelected("Editar");
            if (continentIdSelected != 0)
                editSelectedContinent(continentIdSelected);
            else
                view.updateContinentCancelled();
        }
    }
}

