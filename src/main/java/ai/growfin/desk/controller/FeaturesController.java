package ai.growfin.desk.controller;

import ai.growfin.desk.entity.Feature;
import ai.growfin.desk.service.EnumService;
import ai.growfin.desk.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class FeaturesController {
    @Autowired
    private FeaturesService service;

    @GetMapping("/features")
    public List<Feature> getAllFeatures(){
        return service.getAllFeatures();
    }

    @PostMapping("/features")
    public String addNewFeature(@RequestBody Feature feature){
        return service.addNewFeature(feature);
    }
}
