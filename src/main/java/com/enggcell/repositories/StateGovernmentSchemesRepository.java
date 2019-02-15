package com.enggcell.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.StatementGovernmentSchemes;

@Repository
@Scope(value = "request")

public interface StateGovernmentSchemesRepository extends JpaRepository<StatementGovernmentSchemes, Long> {
	public StatementGovernmentSchemes findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);
	public List<StatementGovernmentSchemes> findAllByTypeOfScheme(String typeOfScheme);
	public List<StatementGovernmentSchemes> findAllBySchemeNameAndTypeOfScheme(String schemeName, String typeOfScheme);
	public List<StatementGovernmentSchemes> findAllByOldNew(String oldNew);
}
