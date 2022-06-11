package com.gsnotes.services;

import com.gsnotes.bo.Niveau;

import java.util.List;

public interface INiveauService {

       // String getNiveauAliasById(Long id);

        List<Niveau> getAllNiveaux();
        Niveau getNiveauByAlias(String nomNiveau);

}
