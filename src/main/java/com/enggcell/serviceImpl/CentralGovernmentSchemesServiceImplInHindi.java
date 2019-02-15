package com.enggcell.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Scope;

import com.enggcell.entities.CentralGovernmentSchemesInHindi;
import com.enggcell.repositories.CentralGovernmentSchemesRepositoryInHindi;
import com.enggcell.services.CentralGovernmentSchemesServiceInHindi;

@Service
@Transactional
@Scope(value = "request")

public class CentralGovernmentSchemesServiceImplInHindi implements CentralGovernmentSchemesServiceInHindi {

    @Resource
    private CentralGovernmentSchemesRepositoryInHindi centralGovernmentSchemesRepositoryInHindi;

    @Override
    public CentralGovernmentSchemesInHindi findOne(Long id) {
        return centralGovernmentSchemesRepositoryInHindi.findOne(id);
    }

    @Override
    public void save(CentralGovernmentSchemesInHindi registrations) {
    	centralGovernmentSchemesRepositoryInHindi.save(registrations);
    }
    
    @Override
    public List<CentralGovernmentSchemesInHindi> findAll(){
    	return centralGovernmentSchemesRepositoryInHindi.findAll();
    }
    
    @Override
    public void delete(Long id){
    	centralGovernmentSchemesRepositoryInHindi.delete(id);
    }

	@Override
	public List<CentralGovernmentSchemesInHindi> findAllBySchemeCategory(
			String schemeCategory) {
		return centralGovernmentSchemesRepositoryInHindi.findAllBySchemeCategory(schemeCategory);
	}

	@Override
	public CentralGovernmentSchemesInHindi findAllBySchemeCategoryAndSchemeName(
			String schemeCategory, String schemeName) {
		return centralGovernmentSchemesRepositoryInHindi.findAllBySchemeCategoryAndSchemeName(schemeCategory, schemeName);
	}

	@Override
	public CentralGovernmentSchemesInHindi findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(
			String schemeName, String typeOfScheme, String schemeLink) {
		return centralGovernmentSchemesRepositoryInHindi.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName, typeOfScheme, schemeLink);
	}
}
