package com.kjetland.dropwizard.activemq;

import com.codahale.metrics.health.HealthCheck;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ActiveMQHttpHealthCheckTest {

    final String url = "http://localhost:31219";
    BrokerService broker;

    @Before
    public void setUp() throws Exception {
        broker = new BrokerService();
        // configure the broker
        broker.addConnector(url);
        broker.start();
    }

    @After
    public void tearDown() throws Exception {
        broker.stop();
    }

    @Test
    public void testCheck() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        ActiveMQHealthCheck h = new ActiveMQHealthCheck(connectionFactory, 3000);
        assertTrue( h.check().isHealthy() );
        assertTrue( h.check().isHealthy() );
        assertTrue( h.check().isHealthy() );
    }
}
