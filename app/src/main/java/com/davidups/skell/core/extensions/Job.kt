package com.davidups.skell.core.extensions

import kotlinx.coroutines.Job

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}