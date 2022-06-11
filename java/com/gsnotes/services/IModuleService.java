package com.gsnotes.services;

import com.gsnotes.bo.Module;

import java.util.List;

public interface IModuleService {

    List<Module> getAllModules();

    Module getModuleByTitre(String titre);



}
