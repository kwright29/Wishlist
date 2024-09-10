package com.example.wishlist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val items: MutableList<Item> = mutableListOf()
    private lateinit var nameEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var urlEditText: EditText
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        itemRecyclerView = findViewById(R.id.wishlistRv)
        val placeholder = Item("Mug", "15", "www.yeah.com" )
        items.add(placeholder)
        val adapter = ItemAdapter(items)
        itemRecyclerView.adapter = adapter
        itemRecyclerView.layoutManager = LinearLayoutManager(this)

        checkIfListIsEmpty()

        nameEditText = findViewById(R.id.inputName)
        priceEditText = findViewById(R.id.inputPrice)
        urlEditText = findViewById(R.id.inputUrl)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            it.hideKeyboard()

            val name = nameEditText.text.toString()
            val price = priceEditText.text.toString()
            val url = urlEditText.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                val newItem = Item(name, price, url)
                adapter.addItem(newItem)

                // Clear input fields after adding
                nameEditText.text.clear()
                priceEditText.text.clear()
                urlEditText.text.clear()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    // Method to check if the list is empty
    private fun checkIfListIsEmpty() {
        if (items.isEmpty()) {
            itemRecyclerView.visibility = View.GONE
        } else {
            itemRecyclerView.visibility = View.VISIBLE
        }
    }
}