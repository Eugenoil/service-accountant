package org.puprleteam.pojos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.*;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.puprleteam.interfaces.Trackings;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "user_id", "task_name", "start_name", "end_time", "status"})
@Entity
@Table
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
    @JsonDeserialize(using = DateDeserializers.CalendarDeserializer.class)
    private Calendar start_time;

    @Column
    private Calendar end_time;

    @Column
    private Integer status;

    @Column
    private String duration;

}
