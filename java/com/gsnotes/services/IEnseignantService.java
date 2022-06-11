package com.gsnotes.services;

import com.gsnotes.bo.Enseignant;

import java.util.List;
import java.util.Optional;

public interface IEnseignantService {

    List<Enseignant> getAllEnseignant();

    String getEnseignantNameById(Long id);



}
