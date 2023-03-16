package com.example.a3dfliping

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
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
        slideImageFromLeftAndRight()
    }

    fun listeners() {
        binding.lottieView.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                binding.imageView1.slideToTop(200f, 1000, false)
                binding.imageView2.slideToTop(200f, 1000, false)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }

    private fun slideImageFromLeftAndRight() {
        with(binding) {
            imageView1.slideFromLeft(200f, 1000, true, onAnimationEnd = {
                binding.lottieView.playAnimation()
            })

            imageView2.slideFromRight(200f, 1000, true)
        }
    }


    override fun onDetachedFromWindow() {
        binding.lottieView.cancelAnimation()
        binding.imageView1.clearAnimation()
        binding.imageView2.clearAnimation()
        super.onDetachedFromWindow()
    }
}