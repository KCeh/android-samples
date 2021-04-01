package com.example.circulardialrecyclerview

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.circulardialrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val names = listOf("Number 1", "Number 2", "One two three", "Test 1",
            "Test 2", "2", "3","4", "5", "6", "7",
            "8", "9", "10")
        val adapter = Adapter(names)

        //just to scale view, don't use in production
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val screenWidth = size.x
        binding.recyclerView.layoutParams.height = screenWidth / 2

        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=CustomLayoutManager(resources = resources, screenWidth = screenWidth)
    }
}