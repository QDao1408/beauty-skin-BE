package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.StaffTask;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.exception.NotFoundException;
import online.beautyskin.beauty.repository.StaffTaskRepository;
import online.beautyskin.beauty.repository.UserRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffTaskService {

    @Autowired
    private StaffTaskRepository staffTaskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    public List<StaffTask> getAll() {
        return staffTaskRepository.findAll();
    }

    public List<StaffTask> getByStaffId(long id) {
        return staffTaskRepository.findByStaff(userRepository.findById(id));
    }

    public List<StaffTask> getByCurrentStaff() {
        return staffTaskRepository.findByStaff(userUtils.getCurrentUser());
    }

    public StaffTask updateTaskForStaff(long taskId, long staffId) {
        StaffTask task = staffTaskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found"));
        User staff = userRepository.findById(staffId);
        task.setStaff(staff);
        return staffTaskRepository.save(task);
    }


}
