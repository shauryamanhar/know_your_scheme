package com.enggcell.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.CentralGovernmentSchemes;

@Repository
@Scope(value = "request")

public interface CentralGovernmentSchemesRepository extends JpaRepository<CentralGovernmentSchemes, Long> {
	public List<CentralGovernmentSchemes> findAllBySchemeCategory(String schemeCategory);
	public CentralGovernmentSchemes findAllBySchemeCategoryAndSchemeName(String schemeCategory, String schemeName);
	public CentralGovernmentSchemes findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);
}
