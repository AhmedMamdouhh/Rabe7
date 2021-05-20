package com.rabe7.community.di.base;


import com.rabe7.community.di.ui.auth.AuthComponent;
import com.rabe7.community.di.ui.main.MainComponent;

import dagger.Module;

@Module(
        subcomponents = {
                AuthComponent.class,
                MainComponent.class
        }
)
public class SubComponentsModule {
}
