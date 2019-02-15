package com.enggcell.services;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.CentralGovernmentSchemes;

@Scope(value = "request")

public interface CentralGovernmentSchemesService {

public CentralGovernmentSchemes findOne(Long id);

public void save(CentralGovernmentSchemes registrations);

public List<CentralGovernmentSchemes> findAll();

public List<CentralGovernmentSchemes> findAllBySchemeCategory(String schemeCategory);

public CentralGovernmentSchemes findAllBySchemeCategoryAndSchemeName(String schemeCategory, String schemeName);

public CentralGovernmentSchemes findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);

public void delete(Long id);

}
