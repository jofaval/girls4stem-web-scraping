package com.capgemini.catedrauv.girls4stem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.capgemini.catedrauv.girls4stem.application.Girls4STEMService;
import com.capgemini.catedrauv.girls4stem.domain.models.Expert;

/**
 * Aplicación que extrae información de la página web de Girls4STEM
 * 
 * @version 1.0
 * @author <a href="mailto:jose.fabra-valverde@capgemini.com">Pepe Fabra
 *         Valverde</a>
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        Girls4STEMService girls4stemService = new Girls4STEMService();

        List<Expert> experts = girls4stemService.findExperts();
        System.out.println("Lista de expertas: " + experts);

        girls4stemService.saveToCsv(experts);
        girls4stemService.saveToDb(experts);
    }
}
