package ai.growfin.desk.repository;

import ai.growfin.desk.entity.EnumValue;
import ai.growfin.desk.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends JpaRepository<Feature, String> {

}
