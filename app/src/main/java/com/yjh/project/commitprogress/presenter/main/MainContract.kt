package com.yjh.project.commitprogress.presenter.main

import android.app.Fragment
import com.omjoonkim.project.interviewtask.model.Person
import com.yjh.project.commitprogress.presenter.base.BaseUserActionsListener
import com.yjh.project.commitprogress.presenter.base.BaseView

interface MainContract {
    interface View : BaseView {
        fun showProfile(person: Person)
    }


    interface UserActionsListener : BaseUserActionsListener {
        fun loadProfile(userName : String)
        fun openStargazerProfile(person: Person)
    }
}