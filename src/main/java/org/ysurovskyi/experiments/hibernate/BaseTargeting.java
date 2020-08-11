package org.ysurovskyi.experiments.hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO: write javadoc
 *
 * @author Evgeniy Surovskiy
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseTargeting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long campaignId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "campaign_states",
            joinColumns = @JoinColumn(name = "campaign_id", referencedColumnName = "campaignId"))
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private Set<State> states = new HashSet<>();
}
