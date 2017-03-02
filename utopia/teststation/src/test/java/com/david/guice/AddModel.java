package com.david.guice;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Created by David
 * on 2017/2/13
 */
public class AddModel implements Module {
    public void configure(Binder binder) {
        binder.bind(Add.class).to(SimpleAdd.class);
    }
}
