package com.news.demo.ui.newsdetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.news.demo.domain.model.Article
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.news_detail_fragment.*

class WebViewDetailFragment : Fragment() {

    companion object {
        fun newInstance(articleObj: Article): WebViewDetailFragment {
            val assetTransactionFragment = WebViewDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable("articleObj", articleObj)
            assetTransactionFragment.arguments = bundle
            return assetTransactionFragment
        }
    }

    private lateinit var viewModel: NewsDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.news.demo.R.layout.news_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.txtTitleHeading?.text = "News Detail"
        arguments?.let {
            val articleObj = arguments?.getSerializable("articleObj") as Article
            initWebView(articleObj)
        }
    }

    private fun initWebView(articleObj: Article) {
        val webSettings = webview.settings
        webSettings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return false
            }
        }
        webview.loadUrl(articleObj.url)
    }
}
