package com.dk.pagingv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * MainActivity -> VM -> PagingSource -> Repo (네트워크 통신)
 * 이런 플로우
 */
class MainActivity : AppCompatActivity() {

    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[MainViewModel::class.java]

        val rv = findViewById<RecyclerView>(R.id.rv)
        val myAdapter = MyAdapter()

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = myAdapter

        lifecycleScope.launch {
            vm.items.collect {
                myAdapter.submitData(it)
            }
        }
    }
}