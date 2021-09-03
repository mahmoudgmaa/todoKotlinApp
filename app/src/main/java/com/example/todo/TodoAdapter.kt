package com.example.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            todoText.text = currentTodo.title
            todoCheckBox.isChecked = currentTodo.isChecked
            toggleStrikeThrough(todoText, currentTodo.isChecked)
            todoCheckBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoText, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    private fun toggleStrikeThrough(todoText: TextView, isChecked: Boolean) {
        if (isChecked) {
            todoText.paintFlags = todoText.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            todoText.paintFlags = todoText.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}