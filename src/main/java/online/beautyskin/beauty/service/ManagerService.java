package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Manager;
import online.beautyskin.beauty.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    //CREATE
    public Manager createManager(Manager manager){
        return managerRepository.save(manager);
    }
    //REMOVE
    public Manager deleteManager(long id){
        Manager manager = managerRepository.findById(id);
        manager.setDelete(true);
        return managerRepository.save(manager);
    }
    //UPDATE
    public Manager updateManager(Manager manager){
        return managerRepository.save(manager);
    }
    //Display
    public List<Manager> getAllManager(){
        return managerRepository.findByIsDeleteFalse();
    }
}
