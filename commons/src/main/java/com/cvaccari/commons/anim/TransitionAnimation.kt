package com.cvaccari.commons.anim

import com.cvaccari.commons.R

sealed class TransitionAnimation(
    val enterIn: Int, val exitOut: Int, val enterOut: Int, val exitIn: Int
) {

    object RightEnterLeftOutAnim : TransitionAnimation(
        R.anim.enter_from_right,
        R.anim.exit_to_left,
        R.anim.enter_from_left,
        R.anim.exit_to_right
    )

    object FadeAnim : TransitionAnimation(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        0,
        0
    )

    object NoAnim : TransitionAnimation(0, 0, 0, 0)

}
