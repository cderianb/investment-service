package com.investment.investmentservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;


@Getter
public abstract class BaseEntity implements Serializable {
    @Id
    protected Long id;

    @CreatedDate
    @Column(value = "created_date")
    @JsonIgnore
    protected Long createdDate;

    @CreatedBy
    @Column(value = "created_by")
    @JsonIgnore
    protected String createdBy;

    @LastModifiedDate
    @Column(value = "last_modified_date")
    @JsonIgnore
    protected Long lastModifiedDate;

    @Column(value = "last_modified_by")
    @JsonIgnore
    protected String lastModifiedBy;

    @Version
    @JsonIgnore
    protected Long version;
}
