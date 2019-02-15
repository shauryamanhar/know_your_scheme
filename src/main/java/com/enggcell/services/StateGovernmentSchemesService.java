package com.enggcell.services;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.StatementGovernmentSchemes;

@Scope(value = "request")

public interface StateGovernmentSchemesService {

public StatementGovernmentSchemes findOne(Long id);

public void save(StatementGovernmentSchemes registrations);

public List<StatementGovernmentSchemes> findAll();

public StatementGovernmentSchemes findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);

public List<StatementGovernmentSchemes> findAllByTypeOfScheme(String typeOfScheme);

public List<StatementGovernmentSchemes> findAllBySchemeNameAndTypeOfScheme(String schemeName, String typeOfScheme);

public List<StatementGovernmentSchemes> findAllByOldNew(String oldNew);

public List<StatementGovernmentSchemes> findAllBySearchKeyword(String searchKey);

public void delete(Long id);

}
