package com.cvaccari.commons.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    protected open var module: Module = module {}

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(module)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        unloadKoinModules(module)
        super.onDestroy()
    }
}