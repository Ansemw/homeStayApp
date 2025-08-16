package com.eazybytes.homeStayApp.config;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component("testSchoolProps")
@Data
@ConfigurationProperties(prefix = "test-school")
@Validated
public class TestSchoolProps {

    @Min(value = 5,message = "should be between 5 and 25")
    @Max(value = 25, message = "Should be between 5 and 25")
    private int pageSize;

    private Map<String, String> contact;
    private List<String> branches;

}
