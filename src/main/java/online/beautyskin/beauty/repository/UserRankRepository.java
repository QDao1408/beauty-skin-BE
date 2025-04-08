package online.beautyskin.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.beautyskin.beauty.entity.UserRank;

import java.util.Optional;


public interface UserRankRepository extends JpaRepository<UserRank, Long>{
    @Override
    Optional<UserRank> findById(Long aLong);

    Double findAmountLevelByRankName(String rankName);
}
