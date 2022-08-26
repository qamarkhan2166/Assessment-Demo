package com.example.mvvmdemoproject.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.model.TransformResponse
import com.example.mvvmdemoproject.databinding.FragmentDetailBinding
import com.example.mvvmdemoproject.presentation.HomeFragment
import com.example.mvvmdemoproject.utils.viewBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding<FragmentDetailBinding>()
    private lateinit var transformResponse: TransformResponse

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (it.containsKey(HomeFragment.TRANSFORM_ARGUMENT)) {
                transformResponse = it.getParcelable(HomeFragment.TRANSFORM_ARGUMENT)!!
            }
        }
        initViews()
    }

    private fun initViews() = with(binding) {
        layoutToolbar.title.text = toolbarTitle
        layoutToolbar.backImg.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24)
        layoutToolbar.backImg.setOnClickListener { findNavController().navigateUp() }
        tvSource.text = """$ratingText${transformResponse.rating}"""
        Glide.with(requireContext())
            .load(transformResponse.background_image)
            .into(ivNewsCover)
        tvDate.text = transformResponse.released
        tvNewsHeadline.text = transformResponse.name
        tvNewsContent.text = transformResponse.description
    }

    companion object {
        private const val toolbarTitle = "Detail"
        private const val ratingText = "Rating: "
    }
}
