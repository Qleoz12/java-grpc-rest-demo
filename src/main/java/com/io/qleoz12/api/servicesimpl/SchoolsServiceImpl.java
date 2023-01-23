package com.io.qleoz12.api.servicesimpl;

import com.io.qleoz12.api.entities.Schools;
import com.io.qleoz12.api.services.SchoolsService;
import com.io.qleoz12.api.repositories.SchoolsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SchoolsServiceImpl implements SchoolsService {
    @Autowired
    private SchoolsRepository schoolsRepository;

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Schools> save(Schools school) {
        school.setStatus(1);
        return CompletableFuture.completedFuture(schoolsRepository.save(school));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Schools> getOneById(Long id) {
        Optional<Schools> school = schoolsRepository.findById(id);
        return CompletableFuture.completedFuture(school.orElse(null));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<List<Schools>> getAll() {
        return CompletableFuture.completedFuture(schoolsRepository.findAll());
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Schools> update(Schools school, Long id) {
        Schools findStudent = schoolsRepository.getById(id);
        if(findStudent.getId() != null){
            ModelMapper modelMapper = new ModelMapper();
            Schools updateStudent = modelMapper.map(findStudent,Schools.class);
            return CompletableFuture.completedFuture(schoolsRepository.save(updateStudent));
        }else {
            return null;
        }
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Schools> delete(Long id) {
        Schools findStudent = schoolsRepository.getById(id);
        if(findStudent.getId() != null){
            findStudent.setStatus(0);
            return CompletableFuture.completedFuture(schoolsRepository.save(findStudent));
        }else {
            return null;
        }
    }
}
