package cl.cotemustis.rickandmorty.presentation.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.cotemustis.rickandmorty.R

class RmListFragment : Fragment() {

    companion object {
        fun newInstance() = RmListFragment()
    }

    private lateinit var viewModel: RmListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rm_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RmListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}