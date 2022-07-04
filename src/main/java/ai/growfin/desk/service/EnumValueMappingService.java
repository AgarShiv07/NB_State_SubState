package ai.growfin.desk.service;

import ai.growfin.desk.entity.EnumValue;
import ai.growfin.desk.entity.EnumValueMapping;
import ai.growfin.desk.entity.NewQuery;
import ai.growfin.desk.repository.EnumMappingRepository;
import ai.growfin.desk.repository.NewQueriesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnumValueMappingService {

    @Autowired
    private NewQueriesRepository queryRepository;
    @Autowired
    private EnumMappingRepository repository;

    public String addTransition(EnumValueMapping enumValueMapping){
        Boolean alreadyExists= repository.alreadyExists(enumValueMapping.getCur_enum_value(), enumValueMapping.getNext_enum_value()).size()>0?true:false;
        if(alreadyExists==true){
            System.out.println("check");
            return "Transition Already Exists";
        }
        repository.save(enumValueMapping);
        List<String> list=new ArrayList<>();
        long id = repository.getIdOfNewlyAddedEdge(enumValueMapping.getCur_enum_value(), enumValueMapping.getNext_enum_value());
        System.out.println(id);
        list.add(String.valueOf(id));
        list.add(enumValueMapping.getCur_enum_value());
        list.add(enumValueMapping.getNext_enum_value());
        list.add(String.valueOf(enumValueMapping.getConfig()));
        String query = MessageFormat.format("insert into enum_value_mapping (id, cur_enum_value, next_enum_value, config) VALUES ({0}, ''{1}'', ''{2}'', {3});",list.toArray());
        NewQuery obj= new NewQuery();
        obj.setNewQuery(query);
        queryRepository.save(obj);
        return "Transition Added";
    }


    public List<EnumValueMapping> transitions() {
        return repository.findAll();
    }

    public String delete(long id) {
        String idWithoutCommas = String.valueOf(id).replaceAll(",","");
        System.out.println(id);
        repository.deleteById(id);
        String query = MessageFormat.format("delete from enum_value_mapping where id={0};",idWithoutCommas);
        NewQuery obj= new NewQuery();
        obj.setNewQuery(query);
        System.out.println(obj.getNewQuery());
        queryRepository.save(obj);
        return "Transition with id = "+id+" deleted successfully";
    }
}
