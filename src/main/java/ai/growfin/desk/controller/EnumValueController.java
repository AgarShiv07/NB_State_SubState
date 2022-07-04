package ai.growfin.desk.controller;

import ai.growfin.desk.entity.EnumValue;
import ai.growfin.desk.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class EnumValueController {
    @Autowired
    private EnumService service;

    @DeleteMapping("/enums/{id}")
    public String deleteById(@PathVariable(value = "id") String id){
        return service.delete(id);
    }

    @GetMapping("/enums")
    public List<EnumValue> enums(){
        return service.enums();
    }

    @GetMapping("/enums/{feature}")
    public List<EnumValue> enumsByFeatureName(@PathVariable(value="feature") String feature){
        return service.enumsByFeatureName(feature);
    }

    @PostMapping("/enums")
    public EnumValue addNewEnum(@RequestBody EnumValue enumValue){
        return service.addNewEnum(enumValue);
    }

    @PutMapping("enums")
    public String changeDisplayName(@RequestBody List<EnumValue> list)
    {
        return service.changeDisplayName(list);
    }

}
