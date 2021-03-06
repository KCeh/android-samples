package com.sample.mvvmtodo.ui.addedittask

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sample.mvvmtodo.R
import com.sample.mvvmtodo.databinding.FragmentAddEditTaskBinding
import com.sample.mvvmtodo.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditTaskFragment : Fragment(R.layout.fragment_add_edit_task) {

    private val viewModel : AddEditViewTaskModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditTaskBinding.bind(view)

        binding.apply {
            editTaskName.setText(viewModel.taskName)
            checkBoxImportant.isChecked = viewModel.taskImportance
            checkBoxImportant.jumpDrawablesToCurrentState()//skip animation
            textViewDateCreated.isVisible = viewModel.task != null
            textViewDateCreated.text = "Created: ${viewModel.task?.createdDateFormatted}"

            editTaskName.addTextChangedListener {
                viewModel.taskName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener{ _, isChecked->
                viewModel.taskImportance = isChecked
            }

            fabSaveTask.setOnClickListener {
                viewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditTaskEvent.collect { event ->
                when(event){
                    is AddEditViewTaskModel.AddEditTaskEvent.NavigateBackWithResult -> {
                        binding.editTaskName.clearFocus()
                        setFragmentResult(
                            "add_edit_request",
                            bundleOf("add_edit_result" to event.result)
                        )
                        findNavController().popBackStack()
                    }
                    is AddEditViewTaskModel.AddEditTaskEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                }.exhaustive //trick for compile time safety
            }
        }
    }
}