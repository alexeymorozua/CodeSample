package com.alexeymorozua.codesample.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by john on 25.11.2016.
 */

@Scope @Retention(RetentionPolicy.RUNTIME) public @interface PerApplication {
}
