package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        addButton.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                val todo = Todo(text)
                todoAdapter.addTodo(todo)
                editText.text.clear()
            }
        }
        deleteButton.setOnClickListener {
            todoAdapter.deleteTodos()
        }
    }
}