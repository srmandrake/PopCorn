package com.rod.popcorn

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.fragment_movie_info.*

private const val ARG_MOVIE_NAME = "arg_movie_name"

class MovieInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var movieName: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieName = it.getString(ARG_MOVIE_NAME)
        }
    }

    private fun loadData(movieName: String?) {
        val url = "https://www.omdbapi.com/"

        val movieInfo = Ion.with(context)
            .load(url)
            .addQuery("apiKey", "3f9c2cd0")
            .addQuery("t", movieName)
            .asJsonObject()
            .get()

        movieInfo?.let {
            if (it.get("Error") != null) {
                Toast.makeText(context, "Not found!", Toast.LENGTH_SHORT).show()
                return
            }

            displayInfo(it)
        }
    }

    private fun displayInfo(movieInfo: JsonObject) {
        textViewTitle.text = movieInfo.get("Title").asString
        textViewYear.text = movieInfo.get("Year").asString
        textViewReleaseDate.text = movieInfo.get("Released").asString
        textViewPlot.text = movieInfo.get("Plot").asString

        Ion.with(imageViewPoster).load(movieInfo.get("Poster").asString)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(movieName)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
//        else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(movieName: String) =
            MovieInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MOVIE_NAME, movieName)
                }
            }
    }
}
