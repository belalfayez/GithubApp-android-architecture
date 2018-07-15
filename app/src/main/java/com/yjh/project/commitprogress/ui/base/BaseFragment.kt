package com.yjh.project.commitprogress.ui.base

import android.net.Uri
import android.support.v4.app.Fragment
import com.yjh.project.commitprogress.presenter.base.BasePresenter
import com.yjh.project.commitprogress.presenter.base.BaseView

open class BaseFragment : Fragment(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter=presenter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detachView()
    }


    fun stringToUri(str: String) : Uri = Uri.parse(str)

}