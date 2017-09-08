package com.justo.mutant.log;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

public class LogManager {

    private static final String STAMP_LABEL = "stamp";

    public static String getStamp() {
        return MDC.get(STAMP_LABEL);
    }

    public static void stamp() {
        stamp(null);
    }

    public static void stamp(String stamp) {
        if (StringUtils.isBlank(stamp)) {
            stamp = UUID.randomUUID().toString();
        }

        MDC.put(STAMP_LABEL, stamp.replaceAll("[^0-9a-zA-Z]", ""));
    }

    public static void stampNull() {
        stampNull(null);
    }

    public static void stampNull(String stamp) {
        if (StringUtils.isNotBlank(MDC.get(STAMP_LABEL))) {
            return;
        }

        stamp(stamp);
    }

    /**
     * Clear the current stamp value assigned to the logger.
     */
    public static void clear() {
        MDC.clear();
    }
    
}
