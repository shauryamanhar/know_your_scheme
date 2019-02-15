package com.enggcell.services;
import com.enggcell.entities.Registrations;
import java.util.List;
import org.springframework.context.annotation.Scope;

@Scope(value = "request")

public interface RegistrationsService {

public Registrations findOne(Long id);

public void save(Registrations registrations);

List<Registrations> findAll();

public void delete(Long id);

}
