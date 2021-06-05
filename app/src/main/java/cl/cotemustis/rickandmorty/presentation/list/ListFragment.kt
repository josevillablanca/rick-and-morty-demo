package cl.cotemustis.rickandmorty.presentation.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.cotemustis.rickandmorty.R
import cl.cotemustis.rickandmorty.data.model.CharactersResponseData
import cl.cotemustis.rickandmorty.data.utils.Resource
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
        _binding = RmListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.retrieveCharacters()
        setObservers()
        setListeners()
    }

    private fun setListeners() {


    }

    private fun setObservers() {
        viewModel.charactersListStatus.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                when(it.status){
                    Status.LOADING ->{
                        // Show Loading
                        showLoadingState(false)
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
        if(data == null){
            showErrorState("Some problem occurred")
            return
        }

        if( data.characterList.isNullOrEmpty()){
            showEmptyStatus()
            return
        }
        binding.listMainGroup.visible = true
        binding.listErrorGroup.visible = false
        binding.listEmptyGroup.visible = false
        binding.listProgressGroup.visible = false
        adapter.characters = data.characterList
        binding.charactersRecyclerView.adapter = adapter
    }

    private fun showErrorState(message: String?) {
        binding.listEmptyGroup.visible = false
        binding.listErrorGroup.visible = true
        binding.listMainGroup.visible = false
        binding.listProgressGroup.visible = false
    }

    private fun showLoadingState(isRefreshing: Boolean) {
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