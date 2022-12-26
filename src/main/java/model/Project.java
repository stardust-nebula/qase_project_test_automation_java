package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    private String projectName;
    private String projectCode;
    private String description;
    private String accessType;

}
