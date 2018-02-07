package com.github.aakira.featuretwo.ui.repolist

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.aakira.daggerinstantapps.ui.AbstractActivity
import com.github.aakira.daggerinstantapps.ui.startRepoActivity
import com.github.aakira.daggerinstantapps.util.ext.showEmptywToast
import com.github.aakira.featuretwo.R
import com.github.aakira.featuretwo.di.FeatureTwoModuleInjector
import com.github.aakira.featuretwo.ui.repolist.flux.RepoListAction
import com.github.aakira.featuretwo.ui.repolist.flux.RepoListStore
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RepositoryListActivity : AbstractActivity() {

    @Inject lateinit var repoListAction: RepoListAction
    @Inject lateinit var repoListStore: RepoListStore

    override fun onCreate(savedInstanceState: Bundle?) {
        FeatureTwoModuleInjector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)

        repoListStore.githubRepoList()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe {
                    if (it.isEmpty()) showEmptywToast()

                    recyclerView.adapter = RepoRecyclerAdapter(it) { startRepoActivity(it) }
                }

        repoListAction.getRepoList("aakira")
    }
}