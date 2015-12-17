package org.soluvas.benchmarkemail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * Requires {@link Environment} property {@code benchmarkemail.access-token}.
 * Created by ceefour on 12/17/15.
 */
@Configuration
public class BenchmarkEmailConfig {
    private static final Logger log = LoggerFactory.getLogger(BenchmarkEmailConfig.class);

    @Autowired
    private Environment env;

    private String accessToken;

    @PostConstruct
    public void init() {
        accessToken = env.getRequiredProperty("benchmarkemail.access-token");
    }

    @Bean
    public InterfaceBMEApi bmeServices() {
        final InterfaceBMEApi bmeServices = BMEApi.getBMEServices();
        return bmeServices;
    }

    @Bean
    public BenchmarkEmail benchmarkEmail() {
        final BenchmarkEmail benchmarkEmail = new BenchmarkEmail();
        benchmarkEmail.setBmeServices(bmeServices());
        benchmarkEmail.setAccessToken(getAccessToken());
        return benchmarkEmail;
    }

    /**
     * Usable for all {@link InterfaceBMEApi} methods.
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }
}
