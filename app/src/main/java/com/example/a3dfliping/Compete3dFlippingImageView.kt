package com.example.a3dfliping

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.a3dfliping.databinding.LayoutCompete3dFlippingBinding

class Compete3dFlippingImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), Animation.AnimationListener {

    private var toMiddleAnimation: Animation
    private var fromMiddleAnimation: Animation

    var binding: LayoutCompete3dFlippingBinding

    init {
        binding = LayoutCompete3dFlippingBinding.inflate(LayoutInflater.from(context), this)
        toMiddleAnimation =
            AnimationUtils.loadAnimation(context, R.anim.to_middle) //apply animation from to_middle
        toMiddleAnimation.setAnimationListener(this)
        fromMiddleAnimation =
            AnimationUtils.loadAnimation(
                context,
                R.anim.from_middle
            )  //apply animation from to_middle
        fromMiddleAnimation.setAnimationListener(this)
    }

    private var flipAnimationListener: FlipAnimationListener? = null

    private var data: Data? = null

    fun setData(data: Data) {
        this.data = data
        this.data?.let {
            binding.imageView.setImageResource(it.initialFlippingImage)
        }
    }

    fun startAnimation() {
        isAnimationEnded = false
        toMiddleAnimation.duration = getHalfFlipDuration()
        toMiddleAnimation.interpolator = LinearInterpolator()
        this.data?.let {
            binding.imageView.setImageResource(it.initialFlippingImage)
        }
        binding.imageView.clearAnimation()
        binding.imageView.animation = toMiddleAnimation
        binding.imageView.startAnimation(toMiddleAnimation)
    }

    fun setFlipAnimationListener(flipAnimationListener: FlipAnimationListener) {
        this.flipAnimationListener = flipAnimationListener
    }


    data class Data(
        @DrawableRes val initialFlippingImage: Int,
        @DrawableRes val endFlippingImage: Int,
        val flippingCount: Int = DEFAULT_FLIP_COUNT,
        val durationPerFlip: Long = DEFAULT_FLIP_DURATION
    )


    interface FlipAnimationListener {
        fun onAnimationStart()
        fun onAnimationEnd()
    }

    private fun getHalfFlipDuration() = (data?.durationPerFlip ?: DEFAULT_FLIP_DURATION).div(2)

    private fun getFlipCount() = data?.flippingCount ?: DEFAULT_FLIP_COUNT

    companion object {
        private const val DEFAULT_FLIP_DURATION = 300L
        private const val DEFAULT_FLIP_COUNT = 2
    }

    private var loadingCount = 0
    private var isAnimationEnded = false

    override fun onAnimationEnd(animation: Animation) {
        data?.let { flipData ->
            if (isAnimationEnded.not()) {
                when {
                    loadingCount > getFlipCount().plus(1) -> {
                        with(binding) {
                            fromMiddleAnimation.duration = getHalfFlipDuration()
                            fromMiddleAnimation.interpolator = LinearInterpolator()
                            imageView.setImageResource(flipData.endFlippingImage)
                            imageView.clearAnimation()
                            imageView.animation = fromMiddleAnimation
                            imageView.startAnimation(fromMiddleAnimation)
                            isAnimationEnded = true
                        }
                    }

                    loadingCount.rem(2) != 0 -> {
                        with(binding) {
                            toMiddleAnimation.duration = getHalfFlipDuration()
                            toMiddleAnimation.interpolator = LinearInterpolator()
                            loadingCount += 1
                            imageView.setImageResource(flipData.initialFlippingImage)
                            imageView.clearAnimation()
                            imageView.animation = toMiddleAnimation
                            imageView.startAnimation(toMiddleAnimation)
                        }
                    }

                    loadingCount.rem(2) == 0 -> {
                        with(binding) {
                            fromMiddleAnimation.duration = getHalfFlipDuration()
                            fromMiddleAnimation.interpolator = LinearInterpolator()
                            loadingCount += 1
                            imageView.setImageResource(flipData.initialFlippingImage)
                            imageView.clearAnimation()
                            imageView.animation = fromMiddleAnimation
                            imageView.startAnimation(fromMiddleAnimation)
                        }
                    }
                }
            } else {
                isAnimationEnded = false
                loadingCount = 0
            }
        }
    }

    override fun onDetachedFromWindow() {
        flipAnimationListener = null
        binding.imageView.clearAnimation()
        super.onDetachedFromWindow()
    }

    override fun onAnimationRepeat(animation: Animation?) {}
    override fun onAnimationStart(animation: Animation?) {}
}