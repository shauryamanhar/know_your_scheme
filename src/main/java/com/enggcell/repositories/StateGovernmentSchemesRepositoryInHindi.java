package com.enggcell.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.StatementGovernmentSchemesInHindi;

@Repository
@Scope(value = "request")

public interface StateGovernmentSchemesRepositoryInHindi extends JpaRepository<StatementGovernmentSchemesInHindi, Long> {
	public StatementGovernmentSchemesInHindi findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);
	public List<StatementGovernmentSchemesInHindi> findAllByTypeOfScheme(String typeOfScheme);
	public List<StatementGovernmentSchemesInHindi> findAllBySchemeNameAndTypeOfScheme(String schemeName, String typeOfScheme);
}
