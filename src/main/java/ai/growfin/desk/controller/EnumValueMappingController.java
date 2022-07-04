package ai.growfin.desk.controller;

import ai.growfin.desk.entity.EnumValueMapping;
import ai.growfin.desk.service.EnumService;
import ai.growfin.desk.service.EnumValueMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class EnumValueMappingController {
    @Autowired
    private EnumValueMappingService service;

    @PostMapping("/transition")
    public String addTransition(@RequestBody EnumValueMapping enumValueMapping){
        return service.addTransition(enumValueMapping);
    }

    @GetMapping("/transition")
    public List<EnumValueMapping> transitions(){
        return service.transitions();
    }

    @DeleteMapping("/transition/{id}")
    public String deleteById(@PathVariable(value = "id") long id){
        return service.delete(id);
    }
}
