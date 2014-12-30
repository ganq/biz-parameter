package com.mysoft.b2b.bizsupport.provider;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Think on 2014/5/22.
 */
public class EliminatableListItem implements Serializable {
    private Date expiration;
    private String value;

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
