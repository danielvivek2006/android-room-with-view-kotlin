package com.daniel.architechturecomponentsroom.Adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.daniel.architechturecomponentsroom.BR
import com.daniel.architechturecomponentsroom.Model.Word
import com.daniel.architechturecomponentsroom.R
import com.daniel.architechturecomponentsroom.databinding.LayoutItemBinding

class WordListAdapter() : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
  private var words: List<Word>? = null

  fun setData(words: List<Word>) {
    this.words = words
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WordViewHolder {
    val layoutInflater = LayoutInflater.from(parent?.context)
    val binding = DataBindingUtil.inflate<LayoutItemBinding>(layoutInflater, R.layout.layout_item,
        parent, false)
    return WordViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return words?.let { words?.size } ?: 0
  }

  override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
    words?.let { holder.bind(words!![position]) } ?: return
  }

  class WordViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Any) {
      if (data !is Word) return
      binding.setVariable(BR.word, data)
      binding.executePendingBindings()
    }
  }
}