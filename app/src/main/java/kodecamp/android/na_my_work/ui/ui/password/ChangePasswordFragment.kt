package kodecamp.android.na_my_work.ui.ui.password

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentChangePasswordBinding
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource

class ChangePasswordFragment : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textFieldsSetup()
        clickListeners()

         viewModel.updateState.observe(viewLifecycleOwner) { content ->
             content.getContentIfNotHandled().let { response ->
                 when (response) {
                     is Resource.Error -> {
                         Snackbar.make(
                             binding.root,
                             getString(R.string.password_change_failed),
                             Snackbar.LENGTH_SHORT
                         ).show()
                     }

                     is Resource.Success -> {
                         showSuccessDialog()
                     }

                     is Resource.Loading -> {}
                     else -> {}
                 }
             }
         }
    }

    private fun showSuccessDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(getString(R.string.success))

        val alertDialog = dialog.create()
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE, getString(R.string.done)
        ) { _, _ ->
            findNavController().navigateUp()
        }

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        val radius = resources.getDimension(R.dimen.dialog_corner_radius)
        val shapeDrawable = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    radius, radius, radius, radius, radius, radius, radius, radius
                ), null, null
            )
        )
        shapeDrawable.paint.color = Color.WHITE
        alertDialog.window?.setBackgroundDrawable(shapeDrawable)

        alertDialog.show()
    }

    private fun clickListeners() {
        binding.saveButton.setOnClickListener {
            if (Object.user!!.password == binding.oldPasswordEditText.text.toString()) {
                Object.user!!.password = binding.confirmNewPasswordEditText.text.toString()
                viewModel.updateUserInformation(Object.user!!)
            } else{
                Snackbar.make(it, getString(R.string.password_incorrect), Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun textFieldsSetup() {
        val textFields = listOf(binding.newPasswordEditText, binding.confirmNewPasswordEditText)
        textFields.forEach { textInputEditText ->
            textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val hasCapitalLetter = s.toString().any { it.isUpperCase() }
                    val hasNumberOrSpecialCharacter =
                        s.toString().any { it.isDigit() || !it.isLetterOrDigit() }
                    val passwordsMatch =
                        binding.newPasswordEditText.text.toString() == binding.confirmNewPasswordEditText.text.toString()

                    if (hasCapitalLetter) {
                        binding.dot1.setImageResource(R.drawable.green_circle)
                    } else {
                        binding.dot1.setImageResource(R.drawable.red_circle)
                    }

                    if (hasNumberOrSpecialCharacter) {
                        binding.dot2.setImageResource(R.drawable.green_circle)
                    } else {
                        binding.dot2.setImageResource(R.drawable.red_circle)
                    }

                    binding.passwordError.visibility = if (binding.confirmNewPasswordEditText.text.toString()
                            .isNotBlank() && !passwordsMatch
                    ) {
                        View.VISIBLE
                    } else {
                        View.INVISIBLE
                    }

                    binding.saveButton.isEnabled =
                        hasCapitalLetter && hasNumberOrSpecialCharacter && s.toString().length >= 8 && passwordsMatch
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}