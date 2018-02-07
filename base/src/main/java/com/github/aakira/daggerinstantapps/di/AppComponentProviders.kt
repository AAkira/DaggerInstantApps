package com.github.aakira.daggerinstantapps.di

import com.github.aakira.daggerinstantapps.data.api.GitHubService
import com.github.aakira.daggerinstantapps.data.repository.GithubRepository

interface AppComponentProviders {

    // Services

    fun githubService(): GitHubService

    // Repositories

    fun githubRepository(): GithubRepository
}