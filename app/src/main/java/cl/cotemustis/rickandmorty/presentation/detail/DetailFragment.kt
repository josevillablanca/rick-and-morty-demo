package cl.cotemustis.rickandmorty.presentation.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cl.cotemustis.rickandmorty.R
import cl.cotemustis.rickandmorty.data.model.CharacterData
import cl.cotemustis.rickandmorty.data.utils.Status
import cl.cotemustis.rickandmorty.databinding.DetailFragmentBinding
import cl.cotemustis.rickandmorty.utils.visible
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.id = arguments?.getInt("characterId") ?: 0
        viewModel.name = arguments?.getString("characterName") ?: ""
        binding.detailToolbar.title = viewModel.name

        viewModel.getCharacterDetail()

        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.detailToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setObservers() {
        viewModel.detailStatus.observe(viewLifecycleOwner, { event ->
            event.peekContent().let {
                when (it.status) {
                    Status.LOADING -> {
                        binding.detailProgressBar.visible = true
                    }
                    Status.ERROR -> {
                        binding.detailProgressBar.visible = false
                        binding.detailErrorContainer.visible = true
                    }
                    Status.SUCCESS -> {
                        binding.detailProgressBar.visible = false
                        setData(it.data)
                    }
                }
            }
        })

    }

    private fun setData(data: CharacterData?) {
        if (data == null) {
            binding.detailErrorContainer.visible = true
            return
        }
        binding.detailScrollView.visible = true

        binding.apply {
            glide.load(data.image).into(detailImageView)
            detailNameTextView.text = data.name
            detailStatusTextView.text = getString(R.string.detail_status_placeholder, data.status)
            detailSpeciesTextView.text = getString(R.string.detail_species_placeholder, data.species)
            detailGenderTextView.text = getString(R.string.detail_gender_placeholder, data.gender)
            detailOriginTextView.text = getString(R.string.detail_origin_placeholder,
                data.characterOrigin?.name ?: ""
            )
            detailLocationTextView.text = getString(R.string.detail_location_placeholder,
                data.location?.name ?: "")
        }

    }

}