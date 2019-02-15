package com.enggcell.serviceImpl;

import com.enggcell.entities.Registrations;
import com.enggcell.repositories.RegistrationsRepository;
import com.enggcell.services.RegistrationsService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Scope;

@Service
@Transactional
@Scope(value = "request")

public class RegistrationsServiceImpl implements RegistrationsService {

    @Resource
    private RegistrationsRepository registrationsRepository;

    @Override
    public Registrations findOne(Long id) {
        return registrationsRepository.findOne(id);
    }

    @Override
    public void save(Registrations registrations) {
        registrationsRepository.save(registrations);
    }
    
    @Override
    public List<Registrations> findAll(){
    	return registrationsRepository.findAll();
    }
    
    @Override
    public void delete(Long id){
    	registrationsRepository.delete(id);
    }
}
