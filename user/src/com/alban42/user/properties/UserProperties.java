package com.alban42.user.properties;

import com.alban42.properties.IProperties;

/**
 * Created by alban on 03/08/2016.
 *
 * @author alban
 */
public enum UserProperties implements IProperties {

    SERVER_HOST("localhost", "Server host"), SERVER_PORT("27960", "Server port"), SERVER_PORT_UDP("27961", "Server udp port");

    private String defaultValue;
    private String comment;

    UserProperties(final String defaultValue, final String comment) {
        this.defaultValue = defaultValue;
        this.comment = comment;
    }

    @Override
    public String defaultValue() {
        return defaultValue;
    }

    @Override
    public String getComment() {
        return comment;
    }
}
