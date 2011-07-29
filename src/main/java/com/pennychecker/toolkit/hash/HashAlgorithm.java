/*
 *   File:       HashAlgorithm.java
 *   Created:    17.07.2009, 11:23:34
 *   Author:     Frank Stahl
 *   mailto:     fs@babeng.com
 *   Copyright Babendererde Engineers GmbH
 *   Markt 2
 *   D-23611 Bad Schwartau
 *   Germany
 * 
 *   The copyright to the computer program(s) herein
 *   is the property of Babendererde Ingenieure GmbH, Germany.
 *   The program(s) may be used and/or copied  only with
 *   the written permission of  Babendererde Engineers GmbH
 *   or in accordance with the terms and conditions
 *   stipulated  in the agreement/contract under which
 *   the  program(s) have been supplied.
 * 
 *   Copyright 2008 Version 1.1
 */
package com.pennychecker.toolkit.hash;

/**
 *
 * @author Steffen Kämpke mailto:steffen.kaempke@stud4u.de
 */
public enum HashAlgorithm {

    /**
     * 
     */
    SHA1("SHA-1"),
    /**
     * Only to to bild a HashValue of an file. @Deprecated for using to crypt passwords
     */
    MD5("MD5");

    HashAlgorithm(String key) {
        this.key = key;
    }

    /**
     *
     * @return
     */
    public String getKey() {
        return key;
    }
    //
    private String key;
}
