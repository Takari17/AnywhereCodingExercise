package com.takari.anywherecodingexercise.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.takari.anywherecodingexercise.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A fragment containing detailed information on a Character.
 */
class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterImage = view.findViewById<ImageView>(R.id.characterImage)
        val characterTitle = view.findViewById<TextView>(R.id.characterTitle)
        val characterDescription = view.findViewById<TextView>(R.id.characterDescription)

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                Log.d("takari", "UI State: $state")

                val imageUrl = "https://duckduckgo.com${state.clickedCharacter?.Icon?.URL}"

                if (state.clickedCharacter?.Icon?.URL.isNullOrEmpty() and !state.apiCallFailed) {
                    characterImage.load(R.drawable.default_character_icon) {
                        crossfade(true)
                    }
                } else {
                    characterImage.load(imageUrl) {
                        crossfade(true)
                        placeholder(R.drawable.default_character_icon)
                    }
                }

                val nameAndDescription = state.clickedCharacter?.Text

                characterTitle.text = viewModel.getCharacterName(nameAndDescription)
                characterDescription.text = viewModel.getCharacterDescription(nameAndDescription)
            }
        }
    }

}