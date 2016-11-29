package com.alexeymorozua.codesample.injection.modules;

import com.alexeymorozua.codesample.injection.scopes.PerApplication;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.lang.reflect.Field;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by john on 29.11.2016.
 */

@Module public class RetrofitModule {

  @Provides @PerApplication public Retrofit provideRetrofit(Retrofit.Builder builder) {
    return builder.baseUrl("https://api.github.com").build();
  }

  @Provides @PerApplication
  public Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory,
      OkHttpClient okHttpClient) {
    return new Retrofit.Builder().addCallAdapterFactory(
        RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(converterFactory)
        .client(okHttpClient);
  }

  @Provides @PerApplication public Converter.Factory provideConverterFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides @PerApplication Gson provideGson() {
    return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setFieldNamingStrategy(new CustomFieldNamingPolicy())
        .setPrettyPrinting()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .serializeNulls()
        .create();
  }

  @Provides @PerApplication public OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor logging =
        new HttpLoggingInterceptor(message -> Timber.tag("response").d(message));
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder().addInterceptor(logging).build();
  }

  private static class CustomFieldNamingPolicy implements FieldNamingStrategy {
    @Override public String translateName(Field field) {
      String name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field);
      name = name.substring(2, name.length()).toLowerCase();
      return name;
    }
  }
}
