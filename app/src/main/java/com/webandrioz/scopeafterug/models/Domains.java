package com.webandrioz.scopeafterug.models;

/**
 * Created by root on 26/2/17.
 */

public class Domains {
    String domainName;

    public Domains(String domainName, String domaainId) {
        this.domainName = domainName;
        this.domaainId = domaainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomaainId() {
        return domaainId;
    }

    public void setDomaainId(String domaainId) {
        this.domaainId = domaainId;
    }

    String domaainId;
}
