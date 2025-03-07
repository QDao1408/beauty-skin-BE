package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {



    Form findById(long id);

    List<Form> findByIsDeletedFalse();



}
