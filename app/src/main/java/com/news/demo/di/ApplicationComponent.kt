package com.news.demo.di

import android.app.Application
import com.news.demo.AndroidNewsApp
import com.news.demo.ui.home.HomeActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    ViewModelFactoryModule::class, NetworkModule::class, RepositoryModule::class,
    ViewModelModule::class, HomeActivityModule::class])

interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: AndroidNewsApp)
}