package online.beautyskin.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import online.beautyskin.beauty.entity.UserRank;

import java.util.Optional;


public interface UserRankRepository extends JpaRepository<UserRank, Long>{


    UserRank findById(long id);

    @Override
    Optional<UserRank> findById(Long id);

    @Query("SELECT ur.amountLevel FROM UserRank ur WHERE ur.rankName = :rankName")
    Double findAmountLevelByRankName(@Param("rankName") String rankName);
}
