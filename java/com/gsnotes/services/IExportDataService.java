package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.utils.export.ExcelExporterByModule;

import java.util.List;

public interface IExportDataService {

    public ExcelExporterByModule prepareDataExport(String session, String nomModule, String annee, String enseignantName, String semestre, String Classe, List<Etudiant> etudiants, int numberOfElements);

}
