package com.example.a3dfliping

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a3dfliping.databinding.LayoutCompeteMilestoneFullViewBinding

class CompeteMieStoneFullView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var binding: LayoutCompeteMilestoneFullViewBinding

    init {
        binding = LayoutCompeteMilestoneFullViewBinding.inflate(LayoutInflater.from(context), this)
        listeners()
    }

    var onSlideAnimationEnd: (() -> Unit)? = null

    private fun listeners() {
        binding.lottieView.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                onSlideAnimationEnd?.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }

    fun setData(data: Data, onSlideAnimationEnd: (() -> Unit)? = null) {
        this.onSlideAnimationEnd = onSlideAnimationEnd
        binding.milestoneAvatarView.setData(
            data = CompeteMilestoneAvatarView.Data(
                leftImage = data.leftImage,
                rightImage = data.rightImage,
                showTransitionAnimation = true
            ),
            onSlideAnimationEnd = {
                binding.lottieView.playAnimation()
            }
        )
    }

    fun slideAvatarViewToTargetView(targetView: View, onSlideAnimationEnd: (() -> Unit)?) {
        moveView(binding.milestoneAvatarView, targetView, 2000L, onSlideAnimationEnd)
    }

    override fun onDetachedFromWindow() {
        onSlideAnimationEnd = null
        binding.lottieView.cancelAnimation()
        super.onDetachedFromWindow()
    }

    data class Data(
        @DrawableRes val leftImage: Int,
        @DrawableRes val rightImage: Int
    )
}