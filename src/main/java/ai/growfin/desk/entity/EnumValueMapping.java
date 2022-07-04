package ai.growfin.desk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enum_value_mapping")
public class EnumValueMapping {
    @Id
    @GeneratedValue
    private long id;
    private String cur_enum_value;
    private String next_enum_value;
    private int config;
}
