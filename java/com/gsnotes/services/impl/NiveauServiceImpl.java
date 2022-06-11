package com.gsnotes.services.impl;

import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IEnseignantDao;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.INiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class NiveauServiceImpl implements INiveauService {

   @Autowired
   INiveauDao niveauDao;




   /* @Override
    public String getNiveauAliasById(Long id) {

        Niveau niveau = niveauDao.getById(id);

        return niveau.getAlias();
    }*/

    @Override
    public List<Niveau> getAllNiveaux() {
        return niveauDao.findAll();
    }

    @Override
    public Niveau getNiveauByAlias(String nomNiveau) {
        return niveauDao.getNiveauByAlias(nomNiveau);
    }
}
