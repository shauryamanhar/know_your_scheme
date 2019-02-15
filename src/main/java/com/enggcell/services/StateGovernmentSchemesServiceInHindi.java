package com.enggcell.services;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.enggcell.entities.StatementGovernmentSchemesInHindi;

@Scope(value = "request")

public interface StateGovernmentSchemesServiceInHindi {

public StatementGovernmentSchemesInHindi findOne(Long id);

public void save(StatementGovernmentSchemesInHindi registrations);

public List<StatementGovernmentSchemesInHindi> findAll();

public StatementGovernmentSchemesInHindi findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);

public List<StatementGovernmentSchemesInHindi> findAllByTypeOfScheme(String typeOfScheme);

public List<StatementGovernmentSchemesInHindi> findAllBySchemeNameAndTypeOfScheme(String schemeName, String typeOfScheme);

public void delete(Long id);

}
