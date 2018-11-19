package org.om.karate.sample.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Emp {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String nickName;

    protected Emp() {
    }

    public Emp(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
