package com.yjh.project.commitprogress.presenter.ownerRepo

import android.app.Activity
import android.app.Application
import android.content.Context
import com.omjoonkim.project.interviewtask.model.Person
import com.omjoonkim.project.interviewtask.model.Repo
import com.yjh.project.commitprogress.presenter.base.BaseUserActionsListener
import com.yjh.project.commitprogress.presenter.base.BaseView
import com.yjh.project.commitprogress.presenter.main.MainContract

interface OwnerRepoContract{

    interface View : BaseView {


        // UserActionsListener 안의 loadRepositories 함수로 어댑터에 세팅
        fun showRepositories(repositories: List<Pair<Repo,List<Person>>>)

        fun moveRepositoryDetailUi(repoName: String)
    }

    interface OnViewHolderListener{
        fun onRepositoryClick(repoName: String)
        fun onStargazersClick(person : Person)
    }

    interface UserActionsListener : BaseUserActionsListener {
        //프레전터에서 상속받아서 구현
        fun loadRepositories(userName : String)

        fun openRepositoriesDetails(repoName : String)
    }
}