package ru.medyannikov.kotlintest

import android.content.Context
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MainApplication) {

  @Provides @Singleton fun provideContext() : Context = app.applicationContext

  @Provides @Singleton fun provideApplication() : MainApplication = app

  @Provides @Singleton fun provideMapper(): ObjectMapper {
    val mapper = ObjectMapper()
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
    return mapper
  }
}