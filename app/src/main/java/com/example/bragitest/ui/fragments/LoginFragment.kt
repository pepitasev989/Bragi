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
import com.example.bragitest.databinding.FragmentLoginBinding
import com.example.bragitest.ui.viewmodels.CommandViewModel
import com.example.bragitest.ui.viewmodels.ConnectionStateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val connectionStateViewModel: ConnectionStateViewModel by viewModels()
    private val commandViewModel: CommandViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = connectionStateViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionStateViewModel.observeData()
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.buttonCheckConnection.setOnClickListener {
            if (connectionStateViewModel.connectionState == ConnectionState.CONNECTED) {
                findNavController().navigate(R.id.action_to_messageDialog)
            }
        }
        binding.buttonSendCommands.setOnClickListener {
            commandViewModel.observeCommandResults()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        connectionStateViewModel.clear()
        commandViewModel.clear()
    }
}