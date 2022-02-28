package com.cvaccari.commons.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cvaccari.commons.anim.TransitionAnimation

fun FragmentManager.replace(
    id: Int,
    fragment: Fragment,
    tag: String? = null,
    transitionAnim: TransitionAnimation = TransitionAnimation.NoAnim
) {
    val fragmentTransaction = beginTransaction()

    fragmentTransaction.setCustomAnimations(
        transitionAnim.enterIn,
        transitionAnim.exitOut,
        transitionAnim.enterOut,
        transitionAnim.exitIn
    )

    fragmentTransaction.replace(id, fragment, tag)
    fragmentTransaction.commit()
}