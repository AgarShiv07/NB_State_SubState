package ai.growfin.desk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enum_value")
public class EnumValue {

    @Id
    private String id;
    private String display_name;
    private String identifier;
    private String metadata_id;
    private String super_enum_value_id;
}
