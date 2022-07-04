package ai.growfin.desk.service;

import ai.growfin.desk.entity.Feature;
import ai.growfin.desk.repository.FeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturesService {

    @Autowired
    private FeaturesRepository repository;

    public List<Feature> getAllFeatures() {
        return repository.findAll();
    }

    public String addNewFeature(Feature feature) {
        repository.save(feature);
        return "Added";
    }
}
