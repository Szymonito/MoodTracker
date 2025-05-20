package com.example.moodtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moodtracker.MainActivity
import com.example.moodtracker.R
import com.example.moodtracker.data.FakeMoodRepository
import org.w3c.dom.Text


class MoodDetailsFragment : Fragment() {

    private var moodDesc: String? = null
    private var moodFeel: String? = null
    private var moodType: String? = null
    private var moodStatements: MutableList<String>? = null
    private var moodImportance: Boolean? = false
    private var moodRating: Float = 0.0f

    private val args: MoodDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moodDescTextView: TextView = view.findViewById(R.id.mood_details_desc)
        val moodFeelTextView: TextView = view.findViewById(R.id.mood_details_feeling)
        val moodTypeTextView: TextView = view.findViewById(R.id.mood_details_type)
        val moodStatementsTextView: TextView = view.findViewById(R.id.mood_details_statements)
        val moodImportanceTextView: TextView = view.findViewById(R.id.mood_details_importance)
        val moodRating: RatingBar = view.findViewById(R.id.mood_details_rating)
        val moodEmojiTextView: TextView = view.findViewById(R.id.mood_details_emoji)
        val moodButton: Button = view.findViewById(R.id.mood_details_delete)
        val moodScheme: LinearLayout = view.findViewById(R.id.mood_details)

        val selectedMood = args.mood

        if(activity is MainActivity)
        {
            (requireActivity() as MainActivity).setToolbarBackgroundColor(selectedMood.feeling)
        }

        moodDescTextView.text = selectedMood.description
        moodFeelTextView.text = selectedMood.feeling
        moodTypeTextView.text = selectedMood.type
        moodTypeTextView.text = selectedMood.type
        moodImportanceTextView.text = if(selectedMood.importance) "To było dla mnie cos waznego" else "To byla blachostka"
        moodStatementsTextView.text = selectedMood.statements.joinToString(",\n")
        moodRating.rating = selectedMood.rating
        moodEmojiTextView.text = when (selectedMood.feeling){
            "Zadowolony" -> ":-D"
            "Moze byc" -> ":/"
            "Smutny" -> ":("
            else -> ":0"
        }

        moodScheme.setBackgroundResource(
            when(selectedMood.feeling){
                "Zadowolony" -> R.color.black
                "Moze byc" -> R.color.white
                "Smutny" -> R.color.white
                else -> R.color.white
            }
        )

        moodButton.setOnClickListener{
            FakeMoodRepository.removeMood(selectedMood)
            findNavController().popBackStack()
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (activity is MainActivity) {
            (requireActivity() as MainActivity).resetToolbarBackgroundColor()
        }

    }
    }




