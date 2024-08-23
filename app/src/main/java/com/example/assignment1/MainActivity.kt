package com.example.assignment1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var scorenum = 0
        val climb = findViewById<Button>(R.id.climb)
        val fall = findViewById<Button>(R.id.fall)
        val reset = findViewById<Button>(R.id.reset)
        val score = findViewById<TextView>(R.id.score)
        val winner = findViewById<TextView>(R.id.winner)
        val GameOver = findViewById<TextView>(R.id.GameOver)



        fun updateScore(scorenum: Int): Int {
            return when {
                scorenum < 3 -> scorenum + 1
                scorenum > 8  -> scorenum + 3
                else -> scorenum + 2
            }
        }

        climb.setOnClickListener {
            scorenum = updateScore(scorenum)
            if (scorenum >= 19) {
                 climb.isEnabled = false
                fall.isEnabled = false
                winner.visibility = View.VISIBLE
            } else {
                score.text = scorenum.toString()

            }
        }


        fall.setOnClickListener {
            if (scorenum == 0)
                return@setOnClickListener
            scorenum -= 3
            if (scorenum < 0)
                scorenum = 0
            score.text = scorenum.toString()
            GameOver.visibility = View.VISIBLE
            fall.isEnabled = false
            climb.isEnabled = false

        }

        reset.setOnClickListener {
            scorenum = 0
            score.text = scorenum.toString()
            climb.isEnabled = true
            fall.isEnabled = true
            winner.visibility = View.GONE
            GameOver.visibility = View.GONE
        }

        }
    }
