# ⚡ Android Instant Apps ⚡

Instant apps sample using [dagger2](https://github.com/google/dagger) android support.  
This sample uses Github API.  
This application is [flux](https://github.com/facebook/flux)-like architecture.

# Structure

```
                         --------------
                        | base module  |
                         --------------
                            /       \
                           /         \
                          /           \
            ------------------   ------------------  
           | feature module 1 |  | feature module 2 |
            ------------------   ------------------ 
                      |     \                |
                      |      \____________   |
                      |                   \  |   
            ----------------------     -------------
            | Instant Apps module |    | App module |
            ----------------------     -------------

```

## Module

### Base module (app, instant apps)

* Define models
* API connection
* Common utilities

### Feature one module (app, instant apps)

* Repository screen

### Feature two module (app)

* Repository List screen 
* Pull Request List screen
* Issue List screen

## Dagger scope

![DaggerScope][scope]

# Screen shot

![RepoListActivity][screenshot-repolist]
![RepoActivity][screenshot-repo]  
![InstallInstantApps][install-instant-apps]

# Setup environment

## Start instant apps ⚡

* Start github repository 
  - [owner]
  - [repository name]
 
`https://aakira.github.com/[owner]/[repository name]`

* e.g.
`https://aakira.github.com/aakira/DaggerInstantApps`

## Github access token

You should set your access token into `/gradle.properties`.  
You don't have to set it but github api has limitations of the number of times.

```
# Add your github token
gitHubToken = [YOUR ACCESS TOKEN]
```

1. Access this link [Github settings](https://github.com/settings/tokens)
2. Generate new token
3. Input new personal access token
4. Enter your token into `gradle.properties`.

# Dagger injector

## BaseModuleInjector

This class refers to DaggerApplication class.  
You can use dagger android support even if each module is separated.

## Hot to inject 

* Base/AppComponent

```kotlin
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<App>, AppComponentProviders {

    override fun inject(instance: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
```

* Feature/ModuleComponent

```kotlin
@PerModuleScope
@Component(
        dependencies = [AppComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,
            FeatureOneUiBuilder::class,
            FeatureOneModule::class
        ]
)
interface FeatureOneComponent : AndroidInjector<FeatureOneModuleInjector>
```

* Feature/ModuleInjector

```kotlin
@Module
abstract class FeatureOneUiBuilder {

    @PerUiScope
    @ContributesAndroidInjector(modules = [RepoModule::class])
    internal abstract fun bindRepoActivity(): RepoActivity
}
```

* Feature/ModuleUiBuilder

```kotlin
@Module
abstract class FeatureOneUiBuilder {

    @PerUiScope
    @ContributesAndroidInjector(modules = [RepoModule::class])
    internal abstract fun bindRepoActivity(): RepoActivity
}
```

* View

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    FeatureOneModuleInjector.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_repo)
}
```

## Author

### Akira Aratani

* Twitter
  - [@_a_akira](https://twitter.com/_a_akira)
* Mail
  - developer.a.akira@gmail.com

## License

```
Copyright (C) 2018 A.Akira

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[scope]: /art/scope.jpeg
[screenshot-repo]: /art/screenshot-repo.png
[screenshot-repolist]: /art/screenshot-repolist.png
[install-instant-apps]: /art/instant-install.gif