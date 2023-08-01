package com.example.test.sub1.Common;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;

public class Hibernate5ModuleBean {
    // hibernateLazyInitializer 제거용 라이브러리
    @Bean
    Hibernate5Module hibernate5Module(){
        return new Hibernate5Module();
    }

}
