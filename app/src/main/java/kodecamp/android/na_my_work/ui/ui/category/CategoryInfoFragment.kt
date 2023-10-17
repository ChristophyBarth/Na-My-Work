package kodecamp.android.na_my_work.ui.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentCategoryInfoBinding
import kodecamp.android.na_my_work.ui.adapter.UserAdapter
import kodecamp.android.na_my_work.ui.data.DummyData

class CategoryInfoFragment : Fragment() {
    private var _binding: FragmentCategoryInfoBinding? = null
    private val binding  get() = _binding!!
    private lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = arguments?.getString("category")
        binding.title.text = category
        setUpRecyclerView(category)
    }

    private fun setUpRecyclerView(category: String?) {
        val data = DummyData().fakeProfiles().filter {
            it.category == category
        }.shuffled()

        val recyclerView = binding.usersRecyclerview
        userAdapter = UserAdapter(requireContext(), data, UserAdapter.VIEW_TYPE_TYPE2, R.id.action_categoryInfoFragment_to_userProfileFragment)

        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}