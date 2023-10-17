package kodecamp.android.na_my_work.ui.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kodecamp.android.na_my_work.databinding.FragmentCurrencyBinding
import kodecamp.android.na_my_work.ui.adapter.CurrencyAdapter
import kodecamp.android.na_my_work.ui.data.DummyData

class CurrencyFragment : Fragment() {
    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!

    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencies = DummyData().currencies()
        currencyAdapter = CurrencyAdapter(currencies, requireContext())
        binding.currencyRecyclerView.adapter = currencyAdapter
        binding.currencyRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}