package com.example.a3dfliping

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.view.View
import android.view.animation.Animation.AnimationListener


fun moveView(viewToBeMoved: View, targetView: View) {
    val targetX: Float =
        targetView.x + targetView.width / 2 - viewToBeMoved.width / 2
    val targetY: Float =
        targetView.y + targetView.height / 2 - viewToBeMoved.height / 2

    viewToBeMoved.animate()
        .x(targetX)
        .y(targetY)
        .setDuration(2000)
        .start()
}

fun View.slideFromLeft(
    pxToSlide: Float,
    duration: Long = 200,
    hideInStart: Boolean = true,
    pxAtEnd: Float = 0f,
    onAnimationEnd: (() -> Unit)? = null
) {
    if (hideInStart) {
        hide()
    }
    translationX = -pxToSlide
    show()
    val animation = animate().translationX(0f - pxAtEnd).setDuration(duration)
    animation.setListener(null)
    onAnimationEnd?.let {
        animation.setListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }
    animation.start()
}

fun View.slideFromRight(
    pxToSlide: Float,
    duration: Long = 200,
    hideInStart: Boolean = true,
    pxAtEnd: Float = 0f,
    onAnimationEnd: (() -> Unit)? = null
) {
    if (hideInStart) {
        hide()
    }
    translationX = pxToSlide
    show()
    val animation = animate().translationX(0f + pxAtEnd).setDuration(duration)
    animation.setListener(null)
    onAnimationEnd?.let {
        animation.setListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }
    animation.start()
}

fun View.slideToBottom(
    pxToSlide: Float,
    duration: Long = 200,
    hideInStart: Boolean = true,
    pxAtEnd: Float = 0f,
    onAnimationEnd: (() -> Unit)? = null
) {
    if (hideInStart) {
        hide()
    }
    show()
    val animation = animate().translationY(pxToSlide + pxAtEnd).setDuration(duration)
    animation.setListener(null)
    onAnimationEnd?.let {
        animation.setListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }
    animation.start()
}

fun View.slideToTop(
    pxToSlide: Float,
    duration: Long = 200,
    hideInStart: Boolean = true,
    pxAtEnd: Float = 0f,
    onAnimationEnd: (() -> Unit)? = null
) {
    if (hideInStart) {
        hide()
    }
    show()
    val animation = animate().translationY(-pxToSlide - pxAtEnd).setDuration(duration)
    animation.setListener(null)
    onAnimationEnd?.let {
        animation.setListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }
    animation.start()
}


/**
 * show
 *
 * Sets visibility of the current view to View.VISIBLE
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * showIf
 *
 * Sets visibility of the current to VISIBLE if given condition is satisfied.
 * Otherwise sets the visibility to Gone or specified notVisibleType.
 */
fun View.showIf(value: Boolean, notVisibleType: Int = View.GONE) {
    visibility = if (value) View.VISIBLE else notVisibleType
}

/**
 * hide
 *
 * Sets visibility of the current view to View.GONE
 */
fun View.hide() {
    visibility = View.GONE
}
