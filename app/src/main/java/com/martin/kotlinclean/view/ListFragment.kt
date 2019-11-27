package com.martin.kotlinclean.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.martin.kotlinclean.R
import com.martin.kotlinclean.model.Animal
import com.martin.kotlinclean.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val listAdapter = AnimalListAdapter(arrayListOf())
    private val animalListDataObserver = Observer<List<Animal>> { list ->

        list?.let {
            animalList.visibility = View.VISIBLE
            listAdapter.updateAnimalList(it)
        }
    }
    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading){
            listError.visibility= View.GONE
            animalList.visibility=View.GONE
        }
    }
    private val onErrorLiveDataObserver = Observer<Boolean> { error ->
        listError.visibility= if (error)View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.animals.observe(this, animalListDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, onErrorLiveDataObserver)
        viewModel.refresh()


        animalList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        refresh_layout.setOnRefreshListener {
            animalList.visibility= View.GONE

            listError.visibility= View.GONE
            loadingView.visibility= View.VISIBLE
            viewModel.hardRefresh()

            refresh_layout.isRefreshing= false
        }
    }

}
