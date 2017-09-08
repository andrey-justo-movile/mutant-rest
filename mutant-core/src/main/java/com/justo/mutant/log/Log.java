package com.justo.mutant.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Log {

    Logger CORE = LoggerFactory.getLogger("core");

    Logger EXCEPTION = LoggerFactory.getLogger("exceptions");

    Logger SYSTEM = LoggerFactory.getLogger("system");
    
}
