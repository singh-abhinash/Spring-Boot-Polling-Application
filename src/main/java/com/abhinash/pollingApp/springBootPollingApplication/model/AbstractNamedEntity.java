package com.abhinash.pollingApp.springBootPollingApplication.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

/**
 * Created by Abhinash Singh - 2024
 */

@MappedSuperclass
public class AbstractNamedEntity extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
