package com.enggcell.serviceImpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Scope;

import com.enggcell.entities.StatementGovernmentSchemesInHindi;
import com.enggcell.repositories.StateGovernmentSchemesRepositoryInHindi;
import com.enggcell.services.StateGovernmentSchemesServiceInHindi;

@Service
@Transactional
@Scope(value = "request")

public class StateGovernmentSchemesServiceImplInHindi implements StateGovernmentSchemesServiceInHindi {

    @Resource
    private StateGovernmentSchemesRepositoryInHindi stateGovernmentSchemesRepositoryInHindi;

    @Override
    public StatementGovernmentSchemesInHindi findOne(Long id) {
        return stateGovernmentSchemesRepositoryInHindi.findOne(id);
    }

    @Override
    public void save(StatementGovernmentSchemesInHindi registrations) {
    	stateGovernmentSchemesRepositoryInHindi.save(registrations);
    }
    
    @Override
    public List<StatementGovernmentSchemesInHindi> findAll(){
    	return stateGovernmentSchemesRepositoryInHindi.findAll();
    }
    
    @Override
    public void delete(Long id){
    	stateGovernmentSchemesRepositoryInHindi.delete(id);
    }

	@Override
	public StatementGovernmentSchemesInHindi findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(
			String schemeName, String typeOfScheme, String schemeLink) {
		return stateGovernmentSchemesRepositoryInHindi.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName, typeOfScheme, schemeLink);
	}

	@Override
	public List<StatementGovernmentSchemesInHindi> findAllByTypeOfScheme(
			String typeOfScheme) {
		return stateGovernmentSchemesRepositoryInHindi.findAllByTypeOfScheme(typeOfScheme);
	}

	@Override
	public List<StatementGovernmentSchemesInHindi> findAllBySchemeNameAndTypeOfScheme(
			String schemeName, String typeOfScheme) {
		return stateGovernmentSchemesRepositoryInHindi.findAllBySchemeNameAndTypeOfScheme(schemeName, typeOfScheme);
	}
}
