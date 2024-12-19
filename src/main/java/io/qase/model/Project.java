package io.qase.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @SerializedName("title")
    private String projectName;

    @SerializedName("code")
    private String projectCode;
    private String description;

    @SerializedName("access")
    private String accessType;

    @SerializedName("group")
    private String accessGroup;
// comment from master 4000
    // Test squash 2 - 1
}
