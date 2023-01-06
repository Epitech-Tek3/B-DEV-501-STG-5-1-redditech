package com.epitech.reddit_epitechapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.epitech.reddit_epitechapp.R
import com.epitech.reddit_epitechapp.repository.Repository
import com.epitech.reddit_epitechapp.viewModel.ViewModels.MyProfileViewModel
import com.epitech.reddit_epitechapp.viewModel.ViewModels.SettingViewModel
import com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory.MyProfileViewModelFactory
import com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory.SettingViewModelFactory
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewOfLayout: View
    private lateinit var myProfileViewModel: MyProfileViewModel

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
        viewOfLayout = inflater.inflate(R.layout.fragment_my_profile, container, false)

        // Inflate the layout for this fragment
        var navProfilePicture = viewOfLayout.findViewById<View>(R.id._profilePicture) as ImageView
        var navProfilePicture2 = viewOfLayout.findViewById<View>(R.id._bannerProfile) as ImageView

        val repository = Repository()
        val myProfileViewModelFactory = MyProfileViewModelFactory(repository)
        myProfileViewModel = ViewModelProvider(this, myProfileViewModelFactory).get(
            MyProfileViewModel::class.java)
        myProfileViewModel.getMyProfileData()
        myProfileViewModel.rep.observe(viewLifecycleOwner, { rep ->
            if (rep.isSuccessful) {
                viewOfLayout.findViewById<TextView>(R.id.TextTextPersonName).text = rep.body()?.subreddit?.display_name_prefixed
                viewOfLayout.findViewById<TextView>(R.id.TextDescription).text = rep.body()?.subreddit?.public_description
                if (rep.body()?.subreddit?.icon_img.toString().isNotEmpty())
                    Picasso.get().load(rep.body()?.subreddit?.icon_img.toString().replaceAfter(".png","")).into(navProfilePicture)
                if (rep.body()?.subreddit?.banner_img.toString().isNotEmpty())
                    Picasso.get().load(rep.body()?.subreddit?.banner_img.toString().replaceAfter(".png","")).into(navProfilePicture2)
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
         * @return A new instance of fragment MyProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}