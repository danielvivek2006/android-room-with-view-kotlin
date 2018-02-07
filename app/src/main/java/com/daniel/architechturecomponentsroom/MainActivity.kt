package com.daniel.architechturecomponentsroom

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.daniel.architechturecomponentsroom.Adapter.WordListAdapter
import com.daniel.architechturecomponentsroom.Factory.WordViewModelFactory
import com.daniel.architechturecomponentsroom.Model.Word
import com.daniel.architechturecomponentsroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
  override fun onRefresh() {
    if(binding?.swipeLayout?.isRefreshing == true) {
      fetchData()
    }
  }

  var binding: ActivityMainBinding? = null
  var viewModel: WordViewModel? = null
  var adapter: WordListAdapter? = null
  var requestCode = 12389
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    viewModel = ViewModelProviders.of(this, WordViewModelFactory(application)).get(
        WordViewModel::class.java)
    setupUI()
    fetchData()
  }

  private fun setupUI() {
    binding?.title?.text = getString(R.string.welcome_text)
    binding?.addWord?.setOnClickListener {
      startActivityForResult(Intent(this, AddWordActivity::class.java), requestCode)
    }
    adapter = WordListAdapter()
    binding?.wordsList?.adapter = adapter
    binding?.wordsList?.layoutManager = LinearLayoutManager(this)
    binding?.swipeLayout?.setOnRefreshListener(this)
  }

  fun fetchData() {
    viewModel?.getAllWords()?.observe(this, Observer<List<Word>> { words: List<Word>? ->
      words.let { adapter?.setData(words!!) }
      binding?.swipeLayout?.isRefreshing = false
    })
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == this.requestCode) {
        val wordString = data?.getStringExtra(EXTRA_REPLY) as String
        viewModel?.insert(Word(wordString))
      }
    }
  }

  companion object {
    var EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
  }
}
