package com.gsnotes.dao;

import com.gsnotes.bo.Element;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IElementDoa extends JpaRepository<Element, Long> {

    List<Element> getElementByIdMatiere(Long idMatiere);

}
