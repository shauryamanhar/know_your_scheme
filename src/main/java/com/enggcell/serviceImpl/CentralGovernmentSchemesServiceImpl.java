package com.enggcell.serviceImpl;

import com.enggcell.entities.CentralGovernmentSchemes;
import com.enggcell.repositories.CentralGovernmentSchemesRepository;
import com.enggcell.services.CentralGovernmentSchemesService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Scope;

@Service
@Transactional
@Scope(value = "request")

public class CentralGovernmentSchemesServiceImpl implements CentralGovernmentSchemesService {

    @Resource
    private CentralGovernmentSchemesRepository centralGovernmentSchemesRepository;

    @Override
    public CentralGovernmentSchemes findOne(Long id) {
        return centralGovernmentSchemesRepository.findOne(id);
    }

    @Override
    public void save(CentralGovernmentSchemes registrations) {
        centralGovernmentSchemesRepository.save(registrations);
    }
    
    @Override
    public List<CentralGovernmentSchemes> findAll(){
    	return centralGovernmentSchemesRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	centralGovernmentSchemesRepository.delete(id);
    }

	@Override
	public List<CentralGovernmentSchemes> findAllBySchemeCategory(
			String schemeCategory) {
		return centralGovernmentSchemesRepository.findAllBySchemeCategory(schemeCategory);
	}

	@Override
	public CentralGovernmentSchemes findAllBySchemeCategoryAndSchemeName(
			String schemeCategory, String schemeName) {
		return centralGovernmentSchemesRepository.findAllBySchemeCategoryAndSchemeName(schemeCategory, schemeName);
	}

	@Override
	public CentralGovernmentSchemes findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(
			String schemeName, String typeOfScheme, String schemeLink) {
		return centralGovernmentSchemesRepository.findAllBySchemeNameAndTypeOfSchemeAndSchemeLink(schemeName, typeOfScheme, schemeLink);
	}
}
