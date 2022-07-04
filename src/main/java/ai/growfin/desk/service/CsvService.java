package ai.growfin.desk.service;

import ai.growfin.desk.entity.NewQuery;
import ai.growfin.desk.repository.NewQueriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvService {
    @Autowired
    private NewQueriesRepository newQueriesRepository;

    public List<NewQuery> NewQueries(){
        System.out.println("t4erfds");
        return newQueriesRepository.findAll();
    }
}
