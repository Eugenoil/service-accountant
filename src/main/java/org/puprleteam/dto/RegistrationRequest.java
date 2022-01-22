package org.puprleteam.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class RegistrationRequest {
    String task_name;
    Long user_id;
    Integer status;

}
