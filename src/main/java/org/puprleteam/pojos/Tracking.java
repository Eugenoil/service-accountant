package org.puprleteam.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.puprleteam.interfaces.Trackings;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Builder
public class Tracking extends Trackings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long user_id;

    @Column
    private String task_name;

    @Column
    private Timestamp start_time;

    @Column
    private Timestamp end_time;

    @Column
    private Integer status;


    public Tracking() {
    }
}
