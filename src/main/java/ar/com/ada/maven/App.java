package ar.com.ada.maven;

import ar.com.ada.maven.controller.MainController;
import ar.com.ada.maven.model.dao.ContinentDAO;
import ar.com.ada.maven.model.dto.ContinentDTO;
import ar.com.ada.maven.statements.CountryStatements;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        MainController.run();

    }
     //   ContinentDAO dao = new ContinentDAO();

    //    ContinentDTO continent = dao.findById(2);
     //   if (continent != null)
      //      System.out.println(continent.toString());

   //   ContinentDTO continentUpdate = new ContinentDTO("America");
   //   Integer idUpdate = 2;
   //   Boolean hasUpdate = dao.update(continentUpdate, idUpdate);
   //   if (hasUpdate)
   //       System.out.println("Se actualizo el registro " + idUpdate);
   //   else
   //       System.out.println("NO se pudo realizar la actualizacion");
    }

