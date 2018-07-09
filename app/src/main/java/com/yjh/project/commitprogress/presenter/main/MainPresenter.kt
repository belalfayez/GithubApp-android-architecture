package com.yjh.project.commitprogress.presenter.main

import android.app.Fragment
import com.omjoonkim.project.interviewtask.model.Person
import com.yjh.project.commitprogress.di.app.App
import com.yjh.project.commitprogress.domain.Repository.GithubDataRepository
import javax.inject.Inject


class MainPresenter(val view : MainContract.View) : MainContract.UserActionsListener {


    init { App.component.inject(this)}

    @Inject
    lateinit var githubDataRepository: GithubDataRepository

    override fun loadProfile(userName : String) {
        githubDataRepository.getUserProfile(userName)
                .subscribe { response -> view.showProfile(response) }
    }

    override fun openStargazerProfile(person: Person) {
        view.showProfile(person)
    }
}
