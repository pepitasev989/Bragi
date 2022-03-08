package com.example.bragitest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bragitest.R
import com.example.bragitest.data.model.ConnectionState
import com.example.bragitest.databinding.FragmentSignUpBinding
import com.example.bragitest.ui.viewmodels.ConnectionStateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: ConnectionStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeData()
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_forgotPasswordFragment)
        }
        binding.buttonCheckConnection.setOnClickListener {
            if (viewModel.connectionState == ConnectionState.CONNECTED) {
                findNavController().navigate(R.id.action_to_messageDialog)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clear()
    }
}