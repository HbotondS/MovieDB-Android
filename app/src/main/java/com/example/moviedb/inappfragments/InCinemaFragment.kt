package com.example.moviedb.inappfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.adapters.MoviesAdapter
import com.example.moviedb.api.Client
import com.example.moviedb.api.Service
import com.example.moviedb.models.Movie

class InCinemaFragment : MovieFragment(), IViewType {

    private val TAG = "InCinemaFragment"

    override val type = ViewType.InCinema

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.incinema_layout, container, false)

        init()

        return myView
    }

    private fun init() {
        Log.d(TAG, "Init RecyclerView list")
        recyclerView = myView.findViewById(R.id.airingMovies)
        recyclerView.layoutManager = GridLayoutManager(myView.context, 3)
        val movies = ArrayList<Movie>()
        val adapter = MoviesAdapter(context!!, movies, activity, emptyList())
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        loadData()
    }

    override fun loadJSON() {
        val service = Client.getInstance().create(Service::class.java)
        val call = service.getNowPlayingMovies(BuildConfig.API_KEY)
        loadMovies(call)
    }
}