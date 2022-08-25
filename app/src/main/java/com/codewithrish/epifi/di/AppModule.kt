package com.codewithrish.epifi.di

import com.codewithrish.epifi.data.local.repository.FormValidationRepositoryImpl
import com.codewithrish.epifi.domain.repository.FormValidationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    // Create singleton(Only one instance in defined scope) instance of form validation repository
    @Provides
    @Singleton
    fun providesValidationRepository(): FormValidationRepository {
        return FormValidationRepositoryImpl()
    }
}