package ai.growfin.desk.repository;

import ai.growfin.desk.entity.NewQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewQueriesRepository extends JpaRepository<NewQuery, Long> {

    @Query("DELETE from EnumValue WHERE id=id")
    Enum deleteByid(String id);
}
