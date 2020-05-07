package com.davidups.starwars.core.platform.viewBinding.internal

import android.view.View
import androidx.annotation.RestrictTo
import androidx.viewbinding.ViewBinding
import com.davidups.starwars.core.platform.viewBinding.ViewBinder

@RestrictTo(RestrictTo.Scope.LIBRARY)
@PublishedApi
internal class DefaultViewBinder<T : ViewBinding>(
    private val viewBindingClass: Class<T>
) : ViewBinder<T> {

    /**
     * Cache static method `ViewBinding.bind(View)`
     */
    private val bindViewMethod by lazy(LazyThreadSafetyMode.NONE) {
        viewBindingClass.getMethod("bind", View::class.java)
    }

    /**
     * Create new [ViewBinding] instance
     */
    @Suppress("UNCHECKED_CAST")
    override fun bind(view: View): T {
        return bindViewMethod(null, view) as T
    }
}