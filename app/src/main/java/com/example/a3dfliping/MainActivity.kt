package com.example.a3dfliping

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator


class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private lateinit var toMiddleAnimation: Animation
    private lateinit var fromMiddleAnimation: Animation
    private var isFrontOfCardShowing = true
    private lateinit var image: ImageView
    private lateinit var clickBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toMiddleAnimation =
            AnimationUtils.loadAnimation(this, R.anim.to_middle) //apply animation from to_middle
        toMiddleAnimation.setAnimationListener(this)
        fromMiddleAnimation =
            AnimationUtils.loadAnimation(this, R.anim.from_middle)  //apply animation from to_middle
        fromMiddleAnimation.setAnimationListener(this)
        clickBtn = findViewById(R.id.button1)
        image = findViewById(R.id.imageView1)


        clickBtn.setOnClickListener {
            toMiddleAnimation.duration = 250
            toMiddleAnimation.interpolator = FastOutSlowInInterpolator()
            image.setImageResource(R.drawable.ic_compete_beginner) //set image from card_front to card_back
            it.isEnabled = false
            image.clearAnimation() //stop animation
            image.animation = toMiddleAnimation
            image.startAnimation(toMiddleAnimation) //start the animation
        }
    }


    private var loadingCount = 0
    private var isAnimationEnded = false
    override fun onAnimationEnd(animation: Animation) {
        //check whether the front of the card is showing
        if (isAnimationEnded.not()) {
            when {

                loadingCount > 1 -> {
                    fromMiddleAnimation.duration = 800
                    fromMiddleAnimation.interpolator = FastOutSlowInInterpolator()
                    image.setImageResource(R.drawable.ic_compete_advanced) //set image from card_front to card_back
                    image.clearAnimation()//stop the animation of the ImageView
                    image.animation = fromMiddleAnimation
                    image.startAnimation(fromMiddleAnimation)
                    isAnimationEnded = true
                }

                loadingCount.rem(2) != 0 -> {
                    toMiddleAnimation.duration = 150
                    toMiddleAnimation.interpolator = LinearInterpolator()
                    loadingCount += 1
                    image.setImageResource(R.drawable.ic_compete_beginner) //set image from card_front to card_back
                    image.clearAnimation()//stop the animation of the ImageView
                    image.animation = toMiddleAnimation
                    image.startAnimation(toMiddleAnimation)
                }

                loadingCount.rem(2) == 0 -> {
                    fromMiddleAnimation.duration = 150
                    fromMiddleAnimation.interpolator = LinearInterpolator()
                    loadingCount += 1
                    image.setImageResource(R.drawable.ic_compete_beginner) //set image from card_front to card_back
                    image.clearAnimation()//stop the animation of the ImageView
                    image.animation = fromMiddleAnimation
                    image.startAnimation(fromMiddleAnimation)
                }
            }
        } else {
            isAnimationEnded = false
            loadingCount = 0
            clickBtn.isEnabled = true
        }
    }

    override fun onAnimationRepeat(animation: Animation?) {
        // TODO Auto-generated method stub
    }

    override fun onAnimationStart(animation: Animation?) {
        // TODO Auto-generated method stub
    }

}