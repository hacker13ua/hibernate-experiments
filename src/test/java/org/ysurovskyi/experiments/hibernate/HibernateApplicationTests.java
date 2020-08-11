package org.ysurovskyi.experiments.hibernate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.util.Set;

import static org.ysurovskyi.experiments.hibernate.State.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class HibernateApplicationTests {
    @Autowired
    private BaseTargetingRepository baseTargetingRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void test() {
        final BaseTargeting build = BaseTargeting.builder()
                .campaignId(42L)
                .states(Set.of(S1, S2))
                .build();
        baseTargetingRepository.save(build);
        final Long result = jdbcTemplate.queryForObject("SELECT count(*) from campaign_states where campaign_id=42", Long.class);
        Assert.assertEquals(2L, result.longValue());
    }

}
