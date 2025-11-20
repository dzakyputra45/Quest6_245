package com.example.myarsitektur

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myarsitektur.model.DataJK
import com.example.myarsitektur.view.uicontroller.FormSiswa
import com.example.myarsitektur.view.uicontroller.TampilSiswa
import com.example.myarsitektur.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulir,
    Detail
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaApp(
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,
            modifier = Modifier.padding(isiRuang)
        ) {

            composable(route = Navigasi.Formulir.name) {

                val konteks = LocalContext.current

                FormSiswa(
                    pilihanJK = DataJK.jenisJK.map { id -> konteks.resources.getString(id) },
                    OnSubmitButtonClick = {
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name) {
                TampilSiswa(
                    statusUiSiswa = uiState.value,
                onBackBtnClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}


private fun cancelAndBackToFormulir(
    navController: NavHostController
){
    navController.popBackStack(Navigasi .Formulir.name, inclusive = false)
}