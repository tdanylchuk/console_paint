package com.tdanylchuk.drawmaster.engine;

import org.springframework.stereotype.Component;

@Component
public class SystemExitShutDownHook implements ShutDownHook {

    public void quit() {
        System.exit(0);
    }
}
