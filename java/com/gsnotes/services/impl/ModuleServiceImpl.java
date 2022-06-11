package com.gsnotes.services.impl;

import com.gsnotes.bo.Module;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.services.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    IModuleDao moduleDao;

    //@Override
    public List<Module> getAllModules() {

        return moduleDao.findAll();

    }



    @Override
    public Module getModuleByTitre(String titre) {



        return moduleDao.findModuleByTitre(titre);



    }


}
