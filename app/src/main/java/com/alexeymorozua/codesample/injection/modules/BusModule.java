package com.alexeymorozua.codesample.injection.modules;

import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 06.12.2016.
 */

@Module public class BusModule {

  @Provides @PerApplication public Bus provideBus() {
    return new Bus(ThreadEnforcer.ANY);
  }
}
