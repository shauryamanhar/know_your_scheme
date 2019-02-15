package com.enggcell.services;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.CentralGovernmentSchemesInHindi;

@Scope(value = "request")

public interface CentralGovernmentSchemesServiceInHindi {

public CentralGovernmentSchemesInHindi findOne(Long id);

public void save(CentralGovernmentSchemesInHindi registrations);

public List<CentralGovernmentSchemesInHindi> findAll();

public List<CentralGovernmentSchemesInHindi> findAllBySchemeCategory(String schemeCategory);

public CentralGovernmentSchemesInHindi findAllBySchemeCategoryAndSchemeName(String schemeCategory, String schemeName);

public CentralGovernmentSchemesInHindi findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);

public void delete(Long id);

}
