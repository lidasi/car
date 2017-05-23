package com.comm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rule implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 角色id
    private String rule;
    // 角色名称
    private String ruleName;

    @Id
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
