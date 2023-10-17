package kodecamp.android.na_my_work.ui.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentProfileBioDataBinding
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Object.isNamePatternCorrect
import kodecamp.android.na_my_work.ui.utils.Resource

class ProfileBioDataFragment : Fragment() {

    private lateinit var photoIntentLauncher: ActivityResultLauncher<Intent>
    private var _binding: FragmentProfileBioDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<HomeViewModel>()

    private var photoUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBioDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val uri = data!!.data
                    photoUri = uri
                    binding.profileImage.setImageURI(uri)
                }
            }

        initUserData()

        textFieldsSetup()
        clickListeners()

        viewModel.updateState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response){
                    is Resource.Success -> {
                        Object.user!!.profilePicture = photoUri
                        Snackbar.make(binding.root, response.data!!, Snackbar.LENGTH_SHORT).show()
                        nextFragment()
                    }

                    is Resource.Error -> {
                        Snackbar.make(binding.root, response.message!!, Snackbar.LENGTH_SHORT)
                            .show()
                    }

                    is Resource.Loading -> {}

                    else -> {}
                }
            }
        }
    }

    private fun initUserData() {
        binding.apply {
            Object.user!!.apply {
                profileImage.setImageURI(profilePicture)
                fullNameEditText.setText(this.fullName)
                emailAddressEditText.setText(email)
                phoneNumberEditText.setText(phoneNumber)
                dateOfBirthEditText.setText(dateOfBirth)
                countryEditText.setText(countryOfResidence)
                stateEditText.setText(state)
                aboutMeEditText.setText(bio)
            }

            saveButton.isEnabled =
                phoneNumberEditText.text.toString().isNotBlank() && dateOfBirthEditText.text.toString().isNotBlank() && countryEditText.text.toString().isNotBlank() && stateEditText.text.toString().isNotBlank()
        }
    }

    private fun clickListeners() {
        binding.apply {
            saveButton.setOnClickListener {
                Object.user!!.apply {
                    fullName = fullNameEditText.text.toString().trim()
                    phoneNumber = phoneNumberEditText.text.toString().trim()
                    dateOfBirth = dateOfBirthEditText.text.toString().trim()
                    countryOfResidence = countryEditText.text.toString().trim()
                    state = stateEditText.text.toString().trim()
                    bio = aboutMeEditText.text.toString().trim()
                }
                saveProfile(Object.user!!)
            }

            addPhoto.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                photoIntentLauncher.launch(intent)
            }
        }
    }

    private fun saveProfile(user: UserEntity) {
        viewModel.updateUserInformation(user)
    }

    private fun textFieldsSetup() {
        val textFields : List<TextInputEditText>

        binding.apply {
            textFields = listOf(
                fullNameEditText,
                phoneNumberEditText,
                dateOfBirthEditText,
                countryEditText,
                stateEditText
            )
        }

        textFields.forEach { textInputEditText ->
            textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.apply {
                        val fullName = fullNameEditText.text.toString().trim()
                        val phoneNumber = phoneNumberEditText.text.toString().trim()
                        val dateOfBirth = dateOfBirthEditText.text.toString().trim()
                        val country = countryEditText.text.toString().trim()
                        val state = stateEditText.text.toString().trim()

                        val isNamePatternCorrect = isNamePatternCorrect(fullName)

                        if (fullName.isBlank()) {
                            fullNameEditText.error = "field can't be empty"
                        } else if (!isNamePatternCorrect) {
                            fullNameEditText.error = "name can be A-Z and 0-9"
                        }

                        if (phoneNumber.isBlank()) {
                            phoneNumberContainer.error = "field can't be empty"
                        }

                        if (dateOfBirth.isBlank()) {
                            dateOfBirthEditText.error = "field can't be empty"
                        }

                        if (country.isBlank()) {
                            countryEditText.error = "field can't be empty"
                        }

                        if (state.isBlank()) {
                            stateEditText.error = "field can't be empty"
                        }

                        saveButton.isEnabled =
                            fullName.isNotBlank() && isNamePatternCorrect && phoneNumber.isNotBlank() && dateOfBirth.isNotBlank() && country.isNotBlank() && state.isNotBlank()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }


    }

    private fun nextFragment() {
        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewpager)
        viewPager.currentItem = viewPager.currentItem + 1
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}