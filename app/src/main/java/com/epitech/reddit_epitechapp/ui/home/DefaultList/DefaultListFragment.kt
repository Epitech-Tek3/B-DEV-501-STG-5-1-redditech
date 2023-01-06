package com.epitech.reddit_epitechapp.ui.home.DefaultList

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epitech.reddit_epitechapp.R
import com.epitech.reddit_epitechapp.THIS
import com.epitech.reddit_epitechapp.models.ChildrenPopularModelId
import com.epitech.reddit_epitechapp.repository.Repository
import com.epitech.reddit_epitechapp.viewModel.ViewModels.PopularViewModel
import com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory.PopularViewModelFactory
import kotlinx.android.synthetic.main.fragment_default_list.view.*

class DefaultListFragment : Fragment() {

    private lateinit var itemsCells: ArrayList<ChildrenPopularModelId?>
    private lateinit var dataCells: List<ChildrenPopularModelId?>
    private lateinit var popularViewModel: PopularViewModel
    private lateinit var loadMoreItemsCells: ArrayList<ChildrenPopularModelId?>
    private lateinit var adapterLinear: ItemsLinearRVAdapterPopular
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var viewOfLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    private fun setItemsData() {
        itemsCells = ArrayList()
        dataCells = ArrayList()

        val repository = Repository()
        val popularViewModelFactory = PopularViewModelFactory(repository)
        popularViewModel =
            THIS?.let { ViewModelProvider(it.getAbstract(), popularViewModelFactory).get(
                PopularViewModel::class.java) }!!
        popularViewModel.getDefaultData("10", THIS!!.getAfterDefaultList())
        THIS?.getAbstract()?.let {
            popularViewModel.rep.observe(it, { rep ->
                if (rep.body()?.children?.after != null) {
                    THIS?.setAfterDefaultList(rep.body()?.children?.after!!)
                }
                if (rep.isSuccessful) {
                    dataCells = rep.body()?.children?.children!!
                    for (i in dataCells) {
                        if (i != null) {
                            itemsCells.add(i)
                        }
                    }
                } else {
                    Log.e("Error response", rep.code().toString())
                    Log.e("Error response", rep.message().toString())
                }
            })
        }
    }

    private fun setAdapter() {
        adapterLinear = ItemsLinearRVAdapterPopular(itemsCells)
        adapterLinear.notifyDataSetChanged()
        viewOfLayout.items_linear_rv_default.adapter = adapterLinear
    }

    private fun setRVLayoutManager() {
        mLayoutManager = LinearLayoutManager(this.requireContext())
        viewOfLayout.items_linear_rv_default.layoutManager = mLayoutManager
        viewOfLayout.items_linear_rv_default.setHasFixedSize(true)
    }

    private fun setRVScrollListener() {
        mLayoutManager = LinearLayoutManager(THIS)
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                LoadMoreData()
            }
        })
        viewOfLayout.items_linear_rv_default.addOnScrollListener(scrollListener)
    }

    private fun LoadMoreData() {
        adapterLinear.addLoadingView()
        loadMoreItemsCells = ArrayList()

        Handler().postDelayed({
            val repository = Repository()
            val popularViewModelFactory = PopularViewModelFactory(repository)
            popularViewModel =
                THIS?.let { ViewModelProvider(it.getAbstract(), popularViewModelFactory).get(
                    PopularViewModel::class.java) }!!
            popularViewModel.getDefaultData("10", THIS!!.getAfterDefaultList())
            THIS?.getAbstract()?.let {
                popularViewModel.rep.observe(it, { rep ->
                    if (rep.body()?.children?.after != null) {
                        THIS?.setAfterDefaultList(rep.body()?.children?.after!!)
                    }
                    if (rep.isSuccessful) {
                        for (i in dataCells) {
                            loadMoreItemsCells.add(i)
                        }
                        adapterLinear.removeLoadingView()
                        adapterLinear.addData(loadMoreItemsCells)
                        scrollListener.setLoaded()
                        viewOfLayout.items_linear_rv_default.post {
                            adapterLinear.notifyDataSetChanged()
                        }
                    } else {
                        Log.e("Error response", rep.code().toString())
                        Log.e("Error response", rep.message().toString())
                    }
                })
            }
        }, 3000)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewOfLayout = inflater.inflate(R.layout.fragment_default_list, container, false)

        setItemsData()
        setAdapter()
        setRVLayoutManager()
        setRVScrollListener()
        return viewOfLayout
    }

}