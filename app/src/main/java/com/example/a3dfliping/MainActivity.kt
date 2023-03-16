package com.example.a3dfliping

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.a3dfliping.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val avatarViewData = CompeteMilestoneAvatarView.Data(
            leftImage = R.drawable.ic_compete_beginner,
            rightImage = R.drawable.ic_compete_advanced
        )

        binding.cardMileStoneAvatarView.setData(avatarViewData.copy(showTransitionAnimation = false))

        binding.mileStoneFullView.setData(
            data = CompeteMieStoneFullView.Data(
                leftImage = avatarViewData.leftImage,
                rightImage = avatarViewData.rightImage
            ),
            onSlideAnimationEnd = {
                binding.mileStoneFullView.slideAvatarViewToTargetView(
                    targetView = binding.cardMileStoneAvatarView,
                    onSlideAnimationEnd = {
                        binding.mileStoneFullView.hide()
                    })
            }
        )
    }
}