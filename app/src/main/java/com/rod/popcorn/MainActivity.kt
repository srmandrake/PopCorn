package com.rod.popcorn

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

@SuppressLint("RestrictedApi")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        buttonSearch.setOnClickListener {
            val movieName = editTextMovieName.text.toString()

            if (movieName.isEmpty() or movieName.isBlank()) {
                Toast.makeText(this, "Movie Name is Terribly wrong ¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.viewContainer, MovieInfoFragment.newInstance(movieName.trim()))
                    .addToBackStack(null)
                    .commit()

                buttonSearch.visibility = View.GONE
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        buttonSearch.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
