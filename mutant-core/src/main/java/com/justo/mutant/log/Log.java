package com.justo.mutant.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Log {

    Logger CORE = LoggerFactory.getLogger("core");

    Logger EXCEPTION = LoggerFactory.getLogger("exceptions");
    
    Logger DATA_ACCESS = LoggerFactory.getLogger("data_access");

    Logger SYSTEM = LoggerFactory.getLogger("system");
    
}
