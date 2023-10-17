package kodecamp.android.na_my_work.ui.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentWorkExperienceBinding
import kodecamp.android.na_my_work.ui.model.WorkExperience
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource

class WorkExperienceFragment : Fragment() {
    private var _binding: FragmentWorkExperienceBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<HomeViewModel>()

    private var allFieldsEmpty = true
    private var allFieldsNotEmpty = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkExperienceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        textChangedListeners()
        clickListeners()

        viewModel.updateState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response) {
                    is Resource.Success -> {
                        nextFragment()
                    }

                    is Resource.Error -> {
                        Snackbar.make(binding.root, response.message!!, Snackbar.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {}

                    else -> {}
                }
            }
        }
    }

    private fun clickListeners() {
        binding.apply {
            saveButton.setOnClickListener {
                if (allFieldsNotEmpty) {
                    val experience = WorkExperience(
                        companyNameEditText.text.toString().trim(),
                        positionMenu.text.toString().trim(),
                        workDescription = workDescriptionEditText.text.toString().trim()
                    )
                    Object.user!!.experience.add(experience)
                    viewModel.updateUserInformation(Object.user!!)
                }
            }
        }
    }

    private fun textChangedListeners() {
        val textInputEditTexts = listOf(
            binding.companyNameEditText, binding.workDescriptionEditText, binding.positionMenu
        )

        textInputEditTexts.forEach {
            it.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    allFieldsEmpty =
                        textInputEditTexts.all { editText -> editText.text.isNullOrBlank() }
                    allFieldsNotEmpty =
                        textInputEditTexts.all { editText -> !editText.text.isNullOrBlank() }

                    if (allFieldsEmpty) {
                        textInputEditTexts.forEach { editText ->
                            editText.error = null
                        }
                    } else {
                        textInputEditTexts.filter { editText ->
                            editText.text.isNullOrBlank()
                        }.forEach { editText ->
                            if (editText.text.isNullOrBlank()) {
                                editText.error = "This field cannot be empty."
                            }
                        }
                    }

                    binding.saveButton.isEnabled = allFieldsEmpty || allFieldsNotEmpty
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }
    }

    private fun init() {
        binding.apply {
            if (Object.user!!.experience.isNotEmpty()) {
                Object.user!!.experience[0].apply {
                    companyNameEditText.setText(companyName)
                    positionMenu.setText(position)
                    workDescriptionEditText.setText(workDescription)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun nextFragment() {
        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewpager)
        viewPager.currentItem = viewPager.currentItem + 1
    }
}