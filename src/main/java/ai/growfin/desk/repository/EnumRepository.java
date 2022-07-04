package ai.growfin.desk.repository;

import ai.growfin.desk.entity.EnumValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import javax.transaction.Transactional;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EnumRepository extends JpaRepository<EnumValue, String> {

    @Query("SELECT t FROM EnumValue t WHERE t.metadata_id LIKE %?1%")
    List<EnumValue> enumsByFeatureName(String feature);

    @Query(value = "SELECT t FROM EnumValue t WHERE t.id =?1")
    List<EnumValue> find(String id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update EnumValue e set e.display_name=?2 where e.id=?1" )
    void changeDisplayName(String initialId,  String newDisplayName, String finalId,String newIdentifier);

    @Query("select e FROM EnumValue e WHERE e.super_enum_value_id=?1")
    List<EnumValue> getSubStates(String id);
}

