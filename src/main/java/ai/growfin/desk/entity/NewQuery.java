package ai.growfin.desk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="QueryLog")
public class NewQuery {
    @Id
    @GeneratedValue
    private Long id;
    private String newQuery;
    @Column(name="timestamp", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date timestamp = new Date();
}
