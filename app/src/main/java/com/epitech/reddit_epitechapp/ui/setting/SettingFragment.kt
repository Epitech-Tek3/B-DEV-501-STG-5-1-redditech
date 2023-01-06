package com.epitech.reddit_epitechapp.ui.setting

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.epitech.reddit_epitechapp.R
import com.epitech.reddit_epitechapp.THIS
import com.epitech.reddit_epitechapp.models.PopularModels
import com.epitech.reddit_epitechapp.models.main.SettingModelId
import com.epitech.reddit_epitechapp.repository.Repository
import com.epitech.reddit_epitechapp.viewModel.ViewModels.SettingViewModel
import com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory.SettingViewModelFactory
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewOfLayout: View
    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOfLayout = inflater.inflate(R.layout.fragment_setting, container, false)

        var navProfilePicture = viewOfLayout.findViewById<View>(R.id.IconPerson) as ImageView
        var navProfilePicture2 = viewOfLayout.findViewById<View>(R.id.BannerPerson) as ImageView
        val repository = Repository()
        val settingViewModelFactory = SettingViewModelFactory(repository)
        settingViewModel = ViewModelProvider(this, settingViewModelFactory).get(SettingViewModel::class.java)
        settingViewModel.getSettingData()
        settingViewModel.rep.observe(viewLifecycleOwner, { rep ->
            if (rep.isSuccessful) {
                viewOfLayout.findViewById<EditText>(R.id.editTextTextPersonName).setText(rep.body()?.subreddit?.title)
                viewOfLayout.findViewById<EditText>(R.id.editTextDescription).setText(rep.body()?.subreddit?.public_description)
                if (rep.body()?.subreddit?.icon_img.toString().isNotEmpty())
                    Picasso.get().load(rep.body()?.subreddit?.icon_img.toString().replaceAfter(".png","")).into(navProfilePicture)
                if (rep.body()?.subreddit?.banner_img.toString().isNotEmpty())
                    Picasso.get().load(rep.body()?.subreddit?.banner_img.toString().replaceAfter(".png","")).into(navProfilePicture2)
                rep.body()?.subreddit?.over_18?.let {
                    viewOfLayout.findViewById<Switch>(R.id.switch1).setChecked(
                        it
                    )
                }
                rep.body()?.subreddit?.accept_followers?.let {
                    viewOfLayout.findViewById<Switch>(R.id.switch2).setChecked(
                        it
                    )
                }
            } else {
                Log.e("Error response", rep.code().toString())
                Log.e("Error response", rep.message().toString())
            }
        })

        return viewOfLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}