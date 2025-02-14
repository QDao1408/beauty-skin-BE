package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.SkinQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinQuestionRepository extends JpaRepository<SkinQuestion,Long> {
    SkinQuestion findSkinQuestionById(long id);
    List<SkinQuestion> findByIsDeleteFalse();
}
