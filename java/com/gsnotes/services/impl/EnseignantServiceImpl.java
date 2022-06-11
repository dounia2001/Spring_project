package com.gsnotes.services.impl;

import com.gsnotes.bo.Enseignant;
import com.gsnotes.dao.IEnseignantDao;
import com.gsnotes.services.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EnseignantServiceImpl implements IEnseignantService {


    @Autowired
    IEnseignantDao enseignantDao;


    @Override
    public List<Enseignant> getAllEnseignant() {
        return enseignantDao.findAll();
    }

    @Override
    public String getEnseignantNameById(Long id) {
        Enseignant enseignant = enseignantDao.getById(id);


        return enseignant.getNom();

    }


}
