package cl.cotemustis.rickandmorty.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData
import cl.cotemustis.rickandmorty.data.utils.Status
import cl.cotemustis.rickandmorty.databinding.RmListFragmentBinding
import cl.cotemustis.rickandmorty.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private var _binding: RmListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModels()

    @Inject
    lateinit var adapter: RmAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RmListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
    }

    private fun setListeners() {

        binding.listSwipeRefreshLayout.setOnRefreshListener {
            viewModel.retrieveCharacters(true)
        }

        binding.listErrorRetryButton.setOnClickListener {
            viewModel.retrieveCharacters(false)
        }

        adapter.setOnItemClickListener { id, name ->
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(id, name))
        }

    }

    private fun setObservers() {
        viewModel.charactersListStatus.observe(viewLifecycleOwner, { event ->
            event.peekContent().let {
                when (it.status) {
                    Status.LOADING -> {
                        // Show Loading
                        showLoadingState()
                    }
                    Status.ERROR -> {
                        //Show error message
                        showErrorState(it.message)
                    }
                    Status.SUCCESS -> {
                        // Show data and validate empty status
                        setData(it.data)
                    }

                }
            }
        })

    }

    private fun setData(data: CharactersResponseData?) {
        if (data == null) {
            showErrorState("Some problem occurred")
            return
        }

        if (data.characterList.isNullOrEmpty()) {
            showEmptyStatus()
            return
        }
        binding.listSwipeRefreshLayout.isRefreshing = false
        showMainContainer()
        adapter.characters = data.characterList
        binding.charactersRecyclerView.adapter = adapter
    }

    private fun showMainContainer(){
        binding.listMainGroup.visible = true
        binding.listErrorGroup.visible = false
        binding.listEmptyGroup.visible = false
        binding.listProgressGroup.visible = false
    }

    private fun showErrorState(message: String?) {
        binding.listEmptyGroup.visible = false
        binding.listErrorGroup.visible = true
        binding.listMainGroup.visible = false
        binding.listProgressGroup.visible = false
    }

    private fun showLoadingState() {
        binding.listEmptyGroup.visible = false
        binding.listErrorGroup.visible = false
        binding.listMainGroup.visible = false
        binding.listProgressGroup.visible = true
    }

    private fun showEmptyStatus() {
        binding.listEmptyGroup.visible = true
        binding.listErrorGroup.visible = false
        binding.listMainGroup.visible = false
        binding.listProgressGroup.visible = false
    }

}