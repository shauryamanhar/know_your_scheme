package com.enggcell.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enggcell.entities.CentralGovernmentSchemesInHindi;

@Repository
@Scope(value = "request")

public interface CentralGovernmentSchemesRepositoryInHindi extends JpaRepository<CentralGovernmentSchemesInHindi, Long> {
	public List<CentralGovernmentSchemesInHindi> findAllBySchemeCategory(String schemeCategory);
	public CentralGovernmentSchemesInHindi findAllBySchemeCategoryAndSchemeName(String schemeCategory, String schemeName);
	public CentralGovernmentSchemesInHindi findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(String schemeName, String typeOfScheme, String schemeLink);
}
