package com.yjh.project.commitprogress.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omjoonkim.project.interviewtask.model.Person
import com.omjoonkim.project.interviewtask.model.Repo
import com.yjh.project.commitprogress.R
import com.yjh.project.commitprogress.presenter.main.MainContract
import com.yjh.project.commitprogress.presenter.main.MainPresenter
import com.yjh.project.commitprogress.presenter.ownerRepo.OwnerRepoContract
import com.yjh.project.commitprogress.presenter.ownerRepo.OwnerRepoPresenter
import com.yjh.project.commitprogress.ui.activity.OwnerRepositoryDetailActivity
import com.yjh.project.commitprogress.ui.adapter.OwnerRepoRecyclerViewAdapter
import com.yjh.project.commitprogress.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_owner.view.*

class OwnerRepoFragment: BaseFragment<OwnerRepoContract.UserActionsListener>(), OwnerRepoContract.View {

    lateinit var mainPresenter: MainContract.UserActionsListener

    override val presenter: OwnerRepoContract.UserActionsListener by lazy { OwnerRepoPresenter(this) }

    companion object {
        fun newInstance(mainPresenter: MainContract.UserActionsListener) =
                (OwnerRepoFragment()).apply { setMainAction(mainPresenter) }
    }

    private fun setMainAction(mainPresenter: MainContract.UserActionsListener){
        this.mainPresenter=mainPresenter
    }

    private val ownerRepoRecyclerViewAdapter by lazy { OwnerRepoRecyclerViewAdapter(repositoryClick) }

    private val repositoryClick=(object: OwnerRepoContract.OnViewHolderListener{
        override fun onRepositoryClick(repoName: String) {
            presenter.openRepositoriesDetails(repoName)
        }

        override fun onStargazersClick(person: Person) {
            mainPresenter.loadProfile(person.login)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView =inflater.inflate(R.layout.fragment_owner, container, false)

        with(rootView){
            //recyclerView.layoutManager =StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            recyclerView.layoutManager =LinearLayoutManager(context)
            recyclerView.adapter=ownerRepoRecyclerViewAdapter
        }

        presenter.loadRepositories("yjh5424")
        return rootView
    }

    override fun showRepositories(repositories: List<Pair<Repo,List<Person>>>) {
        ownerRepoRecyclerViewAdapter.setList(repositories)
    }

    override fun moveRepositoryDetailUi(repoName: String) {
        Intent(context,OwnerRepositoryDetailActivity::class.java).let {
            startActivity(it)
        }
    }
}
