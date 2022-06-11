package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.services.IExportDataService;
import com.gsnotes.utils.export.ExcelExporterByModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class ExportDataServiceImpl implements IExportDataService {


    @Override
    public ExcelExporterByModule prepareDataExport(String session, String nomModule, String annee, String enseignantName, String semestre, String Classe, List<Etudiant> etudiants, int numberOfElements) {


        //the seconde Table:


        // List<String> columns = List.of(new String[]{"id", "cne", "Nom", "Prénom"});
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("CNE");
        columnNames.add("Nom");
        columnNames.add("Prénom");


        //elements


        if(numberOfElements==1){
            columnNames.add("La Note Du Module");
        }else{

            for (int j = 1; j <= numberOfElements; j++) {
                columnNames.add("Element " + j);
            }

        }



        //moyenne

        columnNames.add("Moyenne");
        //validation

        columnNames.add("Validation");

        String[][] data = new String[etudiants.size()][4];

        int i = 0;
        for (Etudiant e : etudiants) {
            data[i][0] = String.valueOf(e.getIdUtilisateur());
            data[i][1] = e.getCne();
            data[i][2] = e.getNom();
            data[i][3] = e.getPrenom();

            i++;
        }


        //String columnNames[] = columns.toArray(new String[columns.size()]);


        return new ExcelExporterByModule(columnNames, data, "Etudiants", session, nomModule, annee, enseignantName, semestre, Classe, numberOfElements);
    }
}
