package com.alban42.common;

import com.alban42.network.register.ClassRegister;
import com.alban42.security.Key;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alban on 17/07/2016.
 *
 * @author alban
 */
public class PeerRegister extends ClassRegister {


    @Override
    public List<Class> registerSpecificClasses() {
        final List<Class> result = new ArrayList<>(0);
        // Adding new classes.

        result.add(Key.class);
        result.add(User.class);
        result.add(BigInteger.class);
        result.add(StatusPacket.class);

        return result;
    }
}
