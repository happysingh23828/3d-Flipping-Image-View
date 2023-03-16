package com.example.a3dfliping

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a3dfliping.databinding.LayoutCompeteMilestoneAvatarBinding

class CompeteMilestoneAvatarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    var binding: LayoutCompeteMilestoneAvatarBinding
    var onSlideAnimationEnd: (() -> Unit)? = null

    init {
        binding = LayoutCompeteMilestoneAvatarBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setData(data: Data, onSlideAnimationEnd: (() -> Unit)? = null) {
        this.onSlideAnimationEnd = onSlideAnimationEnd
        binding.leftImage.setImageResource(data.leftImage)
        binding.rightImage.setImageResource(data.rightImage)
        if (data.showTransitionAnimation) {
            slideImageFromLeftAndRight()
        }
    }

    private fun slideImageFromLeftAndRight() {
        with(binding) {
            leftImage.slideFromLeft(200f, 1000, true, onAnimationEnd = {
                onSlideAnimationEnd?.invoke()
            })

            rightImage.slideFromRight(200f, 1000, true)
        }
    }

    override fun onDetachedFromWindow() {
        binding.leftImage.clearAnimation()
        binding.rightImage.clearAnimation()
        onSlideAnimationEnd = null
        super.onDetachedFromWindow()
    }

    data class Data(
        @DrawableRes val leftImage: Int,
        @DrawableRes val rightImage: Int,
        val showTransitionAnimation: Boolean = false
    )

}