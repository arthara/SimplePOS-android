package com.simple.pos.base.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class UtilProvider {
    private final static HashMap<String, UtilInterface> utils = new HashMap<>();
    private static final String TAG = UtilProvider.class.getSimpleName();

    @SafeVarargs
    public static void initialize(Context context, Class<? extends UtilInterface>... utilInterfacesClass) {
        for (Class<? extends UtilInterface> utilInterfaceClass : utilInterfacesClass)
            utils.put(utilInterfaceClass.getCanonicalName(), Factory.create(context, utilInterfaceClass));
    }

    public static UtilInterface getUtil(Class<? extends UtilInterface> utilClass) {
        UtilInterface utilInterface = utils.get(utilClass.getCanonicalName());

        if (utilClass.isInstance(utilInterface)) {
            return utilInterface;
        } else {
            if (utilInterface != null) {
                Log.d(TAG, "getUtil: UtilInterface put into wrong key");
            }
        }
        throw new RuntimeException(utilClass + " not initialized");
    }

    private static class Factory {
        static UtilInterface create(@NonNull Context context, @NonNull Class<? extends UtilInterface> modelClass) {
            try {
                UtilInterface utilInterface = modelClass.newInstance();
                return utilInterface.create(context);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }
}
