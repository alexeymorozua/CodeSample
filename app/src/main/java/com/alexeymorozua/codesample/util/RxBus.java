package com.alexeymorozua.codesample.util;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by john on 30.12.2016.
 */

public class RxBus {

  private final PublishSubject<Object> mBusSubject;

  public RxBus() {
    mBusSubject = PublishSubject.create();
  }

  public void post(Object event) {
    mBusSubject.onNext(event);
  }

  public Observable<Object> observable() {
    return mBusSubject;
  }

  public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
    return mBusSubject.ofType(eventClass);
  }

  public boolean hasObservers() {
    return mBusSubject.hasObservers();
  }
}
