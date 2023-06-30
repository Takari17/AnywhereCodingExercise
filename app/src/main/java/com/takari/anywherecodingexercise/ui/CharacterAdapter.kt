package com.takari.anywherecodingexercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.takari.anywherecodingexercise.R
import com.takari.anywherecodingexercise.data.characterResponse.RelatedTopic


class CharacterAdapter(
    private val viewModel: CharacterViewModel,
    private val onItemClicked: (RelatedTopic) -> Unit,
) : ListAdapter<RelatedTopic, CharacterAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(
            R.layout.character_item, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val nameAndDescription = getItem(position).Text
        viewHolder.characterName.text = viewModel.getCharacterName(nameAndDescription)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterLayout: CardView
        val characterName: TextView

        init {
            characterLayout = view.findViewById(R.id.characterLayout)
            characterName = view.findViewById(R.id.characterName)

            characterLayout.setOnClickListener { onItemClicked(getItem(absoluteAdapterPosition)) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RelatedTopic>() {
            override fun areItemsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
                return (oldItem.Text == newItem.Text || oldItem.FirstURL == newItem.FirstURL ||
                        oldItem.Icon == newItem.Icon || oldItem.Result == newItem.Result)
            }

            override fun areContentsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
                return oldItem == newItem
            }
        }
    }
}
