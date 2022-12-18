package com.geektcp.common.client.service;

import com.geektcp.common.client.SomeClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class ClientService {

    private SomeClass thy;

    @Autowired
    private void init(SomeClass thy){
        this.thy = thy;
    }

    @PostConstruct
    public void thy() {
        try {
            if (this.thy.thy()) {
                log.info(SomeClass.thy("CKlGaQj\u0002lG}VfDfAnVfMa\u0002\u007fC|QjF."));
            } else {
                log.error(SomeClass.thy("CKlGaQj\u0002lG}VfDfAnVfMa\u0002iCfNjF."));
                System.exit(-10);
            }
        } catch (Exception var2) {
            log.error(SomeClass.thy("CKlGaQj\u0002lG}VfDfAnVfMa\u0002xK{J/GwAjR{K`L."), var2);
            System.exit(-11);
        }
    }
}
