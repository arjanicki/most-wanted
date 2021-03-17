package com.example.mostwanted.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mostwanted.R
import com.example.mostwanted.databinding.FragmentMostWantedDetailBinding
import com.example.mostwanted.models.ImageUnionType
import com.example.mostwanted.viewmodels.MostWantedDetailViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MostWantedDetailFragment : Fragment(), AndroidScopeComponent {
    override val scope by fragmentScope()

    private val args: MostWantedDetailFragmentArgs by navArgs()

    private val viewModel: MostWantedDetailViewModel by stateViewModel(state = { args.toBundle() })

    private var _binding: FragmentMostWantedDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_most_wanted_detail,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageSrc")
        fun loadImage(view: ImageView, image: ImageUnionType) {
            Glide.with(view.context)
                .load(
                    when (image) {
                        is ImageUnionType.Drawable -> image.resourceId
                        is ImageUnionType.Url -> image.url
                    }
                )
                .centerInside()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }


}