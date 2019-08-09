package com.news.demo.ui.home

import com.news.demo.ui.news.NewsListFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [NewsListFragmentModule::class])
interface HomeActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}