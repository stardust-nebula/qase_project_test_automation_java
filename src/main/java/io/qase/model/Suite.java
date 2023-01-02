package io.qase.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Suite {
    @SerializedName("title")
    private String suiteName;
    private String description;
    private String preconditions;

}
