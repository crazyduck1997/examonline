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

    @Value("${code.2000}")
    private String execteSuccess;
    @Value("${code.2001}")
    private String execteFaile;
    @Value("${code.2002}")
    private String nameRepeat;
    @Value("${code.2003}")
    private String typeNameEmpyy;

    @Value("${tempUrl}")
    private String tempUrl;

    @Value("${isEmpty}")
    private String isEmpty;
}
