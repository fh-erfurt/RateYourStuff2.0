package de.fourzerofournotfound.rateyourstuff.rays.core;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import java.io.ObjectInputFilter;

public class SessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionApplicationInitializer() {
        super(ObjectInputFilter.Config.class);
    }
}
