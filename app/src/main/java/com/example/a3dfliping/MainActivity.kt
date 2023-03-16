package com.example.a3dfliping

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var image: Compete3dFlippingImageView
    private lateinit var clickBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickBtn = findViewById(R.id.button1)
        image = findViewById(R.id.imageView1)

        image.setData(
            Compete3dFlippingImageView.Data(
                initialFlippingImage = R.drawable.ic_compete_beginner,
                endFlippingImage = R.drawable.ic_compete_advanced,
                durationPerFlip = 800,
                flippingCount = 6
            )
        )

        clickBtn.setOnClickListener {

        }
    }
}