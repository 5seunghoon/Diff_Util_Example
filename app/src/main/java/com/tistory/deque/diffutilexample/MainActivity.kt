package com.tistory.deque.diffutilexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainRecyclerViewAdapter = MainRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_recycler_view.run {
            adapter = mainRecyclerViewAdapter
            layoutManager = GridLayoutManager(applicationContext, 5)
            setHasFixedSize(true)
        }

        shuffle_button.setOnClickListener { mainRecyclerViewAdapter.shuffle() }
        erase_button.setOnClickListener { mainRecyclerViewAdapter.eraseOneTile() }
        add_button.setOnClickListener { mainRecyclerViewAdapter.addOneTile() }
        three_erase_button.setOnClickListener { mainRecyclerViewAdapter.eraseThreeTile() }
        three_add_button.setOnClickListener { mainRecyclerViewAdapter.addThreeTile() }
    }
}
