/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.Hub;

import com.phil.hubtelpaymentapi.interfaces.IAuth;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Philip
 */
public final class BasicAuth implements IAuth {

    private String username;
    private String password;

    /**
     *
     * @param username
     * @param password
     */
    public BasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        String credentials = username.trim() + ":" + password.trim();
        String encodedAuthorization
                = DatatypeConverter.printBase64Binary(credentials.getBytes());
        return String.format("Basic %s", encodedAuthorization);
    }

    @Override
    public String getCredentials() {
        return this.toString();
    }

}
