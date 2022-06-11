package com.gsnotes.web.controllers;


import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IEnseignantService;
import com.gsnotes.services.IExportDataService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.services.INiveauService;
import com.gsnotes.utils.export.ExcelExporterByModule;
import com.gsnotes.web.models.ExportAllModel;
import com.gsnotes.web.models.ModuleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/controller")
public class MyController {


    @Autowired
    IModuleService moduleService;

    @Autowired
    IEnseignantService enseignantService;

    @Autowired
    INiveauService niveauService;

    @Autowired
    IExportDataService exportDataService;








    @PostMapping("/exporter")
    public void exportDataByModule(@ModelAttribute("exportModule") ModuleData exportModule, HttpServletResponse response) throws IOException {

       //session
        String session = exportModule.getSession();


        //nom du modele
        String nomModule = exportModule.getNomModule();

       
        Module module = moduleService.getModuleByTitre(nomModule);


        
         //nom de l'enseignant
        String enseignantName = enseignantService.getEnseignantNameById(module.getEnseignant().getIdUtilisateur());

         //annne scolaire
        int currentYear = Year.now().getValue();
        int previousYear = currentYear-1;
        String annee=previousYear+"/"+currentYear;
        //semestre
        String semestre="Printemps";


        //alias of niveau
        String Classe = module.getNiveau().getAlias();


        //nombre des elements
        int numberOfElements = module.getElements().size();


        //liste of etudiants de ce module en cette annne
        List<Etudiant> etudiants = new ArrayList<>();
        if("Normale".equals(session)){
            List<InscriptionAnnuelle> inscriptionAnnuelles = module.getNiveau().getInscriptions();

            for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                if(inscriptionAnnuelle.getAnnee()==currentYear){

                    etudiants.add(inscriptionAnnuelle.getEtudiant());


                }


            }

        }else{
            
            List<Element> elements = module.getElements();

            if (numberOfElements==1){

                List<InscriptionModule> inscriptionModules = module.getInscriptionModules();

                List<InscriptionAnnuelle> inscriptionAnnuelles= new ArrayList<>();



                //les inscriptions annuels pour les etudiant ayant une note de module inferieur à 12
                for (InscriptionModule inscriptionModule:inscriptionModules) {

                    if(inscriptionModule.getNoteSN()<12){
                        inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());

                    }
                }



                for (InscriptionAnnuelle inscriptionAnnuelle:inscriptionAnnuelles) {


                    if(inscriptionAnnuelle.getAnnee()==currentYear){
                        etudiants.add(inscriptionAnnuelle.getEtudiant());

                    }



                }






            }else if(numberOfElements==2){

              
                
                List<InscriptionMatiere> inscriptionMatieresFirstElement = elements.get(0).getInscriptionMatieres();

               
                List<InscriptionMatiere> inscriptionMatieresForSecondeElement = elements.get(1).getInscriptionMatieres();



               
                List<InscriptionAnnuelle> inscriptionAnnuelles = new ArrayList<>();


                for (int i = 0; i < inscriptionMatieresFirstElement.size(); i++) {
                    for (int j = 0; j < inscriptionMatieresForSecondeElement.size(); j++){

                        if(inscriptionMatieresFirstElement.get(i).getInscriptionAnnuelle().getIdInscription()==inscriptionMatieresForSecondeElement.get(j).getInscriptionAnnuelle().getIdInscription()
                                && (inscriptionMatieresFirstElement.get(i).getNoteSN()+inscriptionMatieresForSecondeElement.get(j).getNoteSN()*inscriptionMatieresForSecondeElement.get(j).getCoefficient())<12){

                            inscriptionAnnuelles.add(inscriptionMatieresFirstElement.get(i).getInscriptionAnnuelle());


                        }



                    }




                }

                for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                    if(inscriptionAnnuelle.getAnnee()==currentYear && !etudiants.contains(inscriptionAnnuelle.getEtudiant())){
                        etudiants.add(inscriptionAnnuelle.getEtudiant());
                    }


                }



            }
        }

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelExporterByModule excelExporterByModule = exportDataService.prepareDataExport( session, nomModule, annee, enseignantName, semestre,  Classe, etudiants,numberOfElements);

        excelExporterByModule.export(response);


    }}
    
    



   
