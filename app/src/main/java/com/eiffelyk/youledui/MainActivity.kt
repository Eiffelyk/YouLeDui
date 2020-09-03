package com.eiffelyk.youledui

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import androidx.appcompat.app.AppCompatActivity
import com.eiffelyk.youledui.utils.HeightVisibleChangeListener
import com.eiffelyk.youledui.webview.DefaultHandler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        callJS()
        web_view.loadUrl("https://youledui.com/");
    }
    private fun initView() {
        web_view.setDefaultHandler(DefaultHandler())
        HeightVisibleChangeListener(web_view)
    }
    private fun callJS() {
        web_view.registerHandler("errorHandle") { _, _ -> finish() }
        web_view.registerHandler("closeHandle") { _, _ -> finish() }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KEYCODE_BACK && web_view.canGoBack()) {
            web_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}