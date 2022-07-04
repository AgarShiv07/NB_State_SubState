package ai.growfin.desk.service;

import ai.growfin.desk.entity.EnumValue;
import ai.growfin.desk.entity.NewQuery;
import ai.growfin.desk.repository.EnumRepository;
import ai.growfin.desk.repository.NewQueriesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EnumService {

    @Autowired
    private EnumRepository repository;
    @Autowired
    private NewQueriesRepository queryRepository;
    public List<EnumValue> enums(){
        return repository.findAll();
    }

    public List<EnumValue> enumsByFeatureName(String feature){
        System.out.println(repository.enumsByFeatureName(feature));
        return repository.enumsByFeatureName(feature);
    }

    public EnumValue addNewEnum(EnumValue enumValue) {
        System.out.println(enumValue.toString());
        ObjectMapper mapObject = new ObjectMapper();
        Map<String, Object> mapObj = mapObject.convertValue(enumValue, Map.class);
        String query="";
        if(mapObj.containsValue(null)) query = MessageFormat.format("insert into enum_value (id, display_name, identifier, metadata_id, super_enum_value_id) values (''{0}'', ''{1}'', ''{2}'', ''{3}'', {4});",mapObj.values().toArray());
        else
            query = MessageFormat.format("insert into enum_value (id, display_name, identifier, metadata_id, super_enum_value_id) values (''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'');",mapObj.values().toArray());

        NewQuery obj = new NewQuery();

        obj.setNewQuery(query);
        queryRepository.save(obj);
//        System.out.println(repository.save(enumValue));
        return repository.save(enumValue);
    }

    public String delete(String id) {
        List<EnumValue> enumToBeDeleted = repository.find(id);
        repository.delete(enumToBeDeleted.get(0));
        List<String> queries = new ArrayList<>();
        queries.add(MessageFormat.format("delete from enum_value where id=''{0}'';",id));

        if(enumToBeDeleted.get(0).getSuper_enum_value_id()==null){
            List<EnumValue> subStates = repository.getSubStates(id);
            for(EnumValue x:subStates){
                queries.add(MessageFormat.format("delete from enum_value where id=''{0}'';",x.getId()));
                repository.delete(x);
            }
        }

        for(String query:queries){
            NewQuery obj = new NewQuery();
            obj.setNewQuery(query);
            queryRepository.save(obj);
        }
        return "EnumValue with id = "+id+" and its subStates deleted successfully";
    }

    public String changeDisplayName(List<EnumValue> list) {
        String initialId = list.get(0).getId(),finalId = list.get(1).getId();
        String newDisplayName = list.get(1).getDisplay_name(),newIdentifier = list.get(1).getIdentifier();
        repository.changeDisplayName(initialId,newDisplayName,finalId,newIdentifier);

        List<String> updated = new ArrayList<>();
        updated.add(finalId);
        updated.add(newDisplayName);
        updated.add(newIdentifier);
        updated.add(initialId);
        String  query = MessageFormat.format("update enum_value SET id=''{0}'', display_name=''{1}'', identifier=''{2}'' WHERE id=''{3}'';",updated.toArray());
        NewQuery obj = new NewQuery();

        obj.setNewQuery(query);
        queryRepository.save(obj);
        return "Display Name of node with id="+initialId+" Changed Successfully to "+newDisplayName;
    }
}
