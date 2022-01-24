package org.puprleteam.dto;

import lombok.Builder;
import lombok.Value;
import org.puprleteam.interfaces.Trackings;

@Value
@Builder
public class NewTracking extends Trackings {
    String task_name;
    Long user_id;
    Integer status;
}
