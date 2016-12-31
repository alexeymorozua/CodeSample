package com.alexeymorozua.codesample.injection.modules;

import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.alexeymorozua.codesample.util.RxBus;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john on 06.12.2016.
 */

@Module public class RxBusModule {

  @Provides @PerApplication public RxBus provideRxBus() {
    return new RxBus();
  }
}
