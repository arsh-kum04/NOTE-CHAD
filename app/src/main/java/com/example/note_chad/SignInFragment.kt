package com.example.note_chad

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.note_chad.databinding.FragmentSignInBinding
import com.example.note_chad.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Toast.makeText(requireContext(),"oncreateview is called",Toast.LENGTH_SHORT).show()
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val currentUser=authViewModel.getCurrentUser()
//        if(currentUser!=null){
//            Toast.makeText(requireContext(), "Welcome ,${currentUser.displayName} ( ˃ ᵕ ˂ )", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_fragment_sign_in_to_fragment_notes_r_v2)
//        }
        authViewModel.authState.observe(viewLifecycleOwner, Observer {
            result->
            result.onSuccess {

                findNavController().navigate(R.id.action_fragment_sign_in_to_fragment_notes_r_v2)
            }
            result.onFailure {
                exception ->  Toast.makeText(requireContext(),
                "Authentication failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
            })
        binding.signinBtn.setOnClickListener{
            signIn()
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("102524420048-o2rnu9mv0i8bfht7g3reqmvm4mj18rol.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = getClient(requireContext(), gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
               account?.idToken?.let { idToken->
                   authViewModel.signInWithGoogle(idToken)
               }
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        authViewModel.signOut()
        _binding = null
    }
}
