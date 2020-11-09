package com.example.infinitescroll.data.mapper;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApodMapper_Factory implements Factory<ApodMapper> {
  @Override
  public ApodMapper get() {
    return newInstance();
  }

  public static ApodMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ApodMapper newInstance() {
    return new ApodMapper();
  }

  private static final class InstanceHolder {
    private static final ApodMapper_Factory INSTANCE = new ApodMapper_Factory();
  }
}
