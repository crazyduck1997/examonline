package com.qf.examonline.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource(value = {"classpath:codemsg/codemsg.properties"},
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "codemsg.properties")
public class CodeMsg {
    @Value("${code.1001}")
    private String passwordEmpty;


}
