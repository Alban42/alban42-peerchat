package com.alban42.common;

import com.alban42.security.Key;

/**
 * Created by alban on 02/08/2016.
 *
 * @author alban
 */
public class User {

    /**
     * the pseudo of the user.
     */
    public String pseudo;

    /**
     * the host (aka: IP) where the user is reachable.
     */
    public String host;

    /**
     * the port where the user is reachable.
     */
    public int tcpPort;

    /**
     * The public key of the user for encryption.
     */
    public Key publicKey;

}
