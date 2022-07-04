package ai.growfin.desk.controller;

import ai.growfin.desk.entity.NewQuery;
import ai.growfin.desk.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class NewQueryController {
    @Autowired
    private CsvService csvService;

    @GetMapping("/download")
    public List<NewQuery> getAll(){
        System.out.println(csvService.NewQueries());
        return csvService.NewQueries();
    }
}
