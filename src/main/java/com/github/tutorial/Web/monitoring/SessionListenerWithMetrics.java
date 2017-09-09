package com.github.tutorial.Web.monitoring;

import com.codahale.metrics.Counter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;
import java.util.concurrent.atomic.AtomicInteger;


public class SessionListenerWithMetrics extends HttpSessionEventPublisher {

    private final AtomicInteger activeSessions;
    private final AtomicInteger timeoutSessions;
    private final Counter counterOfActiveSessions;
    private int SessionTimeout = 1 * 50;

    public SessionListenerWithMetrics() {
        super();
        activeSessions = new AtomicInteger();
        timeoutSessions = new AtomicInteger();
        counterOfActiveSessions = MetricRegistrySingleton.metrics.counter("web.sessions.active.count");
//      context.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

    // API

    @Override
    public final void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("Session Created: ");
        event.getSession().setMaxInactiveInterval(SessionTimeout);
        timeoutSessions.set(event.getSession().getMaxInactiveInterval());
        counterOfActiveSessions.inc();
    }

    @Override
    public final void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("Session Destroyed: ");
        counterOfActiveSessions.dec();
    }

    public final int getTotalActiveSession() {
        return activeSessions.get();
    }

    public final int getTotalTimeoutSession() {
        return timeoutSessions.get();
    }

}
