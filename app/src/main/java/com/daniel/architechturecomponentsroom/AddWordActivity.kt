package com.daniel.architechturecomponentsroom

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.daniel.architechturecomponentsroom.databinding.ActivityAddWordBinding

class AddWordActivity : AppCompatActivity() {
  var binding: ActivityAddWordBinding? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_add_word)
    setupUI()
  }

  private fun setupUI() {
    supportActionBar?.title = getString(R.string.add_word)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeButtonEnabled(true)
    binding?.done?.setOnClickListener {
      moveToMainActivity()
    }
  }

  fun moveToMainActivity() {
    var intent = Intent()
    if (binding?.textInput?.text!!.isNotEmpty()) {
      intent.putExtra(EXTRA_REPLY, binding?.textInput?.text.toString())
      setResult(Activity.RESULT_OK, intent)
    } else {
      setResult(Activity.RESULT_CANCELED, intent)
    }
    finish()
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    super.onOptionsItemSelected(item)
    when (item?.itemId) {
      android.R.id.home -> {
        moveToMainActivity()
      }
    }
    return true
  }

  companion object {
    var EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
  }
}
