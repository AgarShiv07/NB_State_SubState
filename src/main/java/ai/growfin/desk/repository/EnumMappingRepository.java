package ai.growfin.desk.repository;

import ai.growfin.desk.entity.EnumValue;
import ai.growfin.desk.entity.EnumValueMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnumMappingRepository extends JpaRepository<EnumValueMapping, Long> {

    @Query("Select MAX(id) from EnumValueMapping")
    long getMaximumId();

    @Query("Select id from EnumValueMapping e WHERE e.cur_enum_value=?1 and e.next_enum_value=?2")
    long getIdOfNewlyAddedEdge(String cur_enum_value, String next_enum_value);

    @Query("Select e from EnumValueMapping e WHERE e.cur_enum_value=?1 and e.next_enum_value=?2")
    List<EnumValueMapping> alreadyExists(String cur_enum_value, String next_enum_value);
}
