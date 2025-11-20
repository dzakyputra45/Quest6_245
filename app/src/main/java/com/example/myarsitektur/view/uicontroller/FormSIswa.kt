package com.example.myarsitektur.view.uicontroller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myarsitektur.view.uicontroller.FormSiswa
import com.example.myarsitektur.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSiswa(
    pilihanJK: List<String>,
    OnSubmitButtonClick: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
){
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textGender by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name), color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500)
                )
            )
        }
    ) { isiRuang ->

        Column(
            modifier = Modifier.padding(isiRuang),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = textNama,
                onValueChange = { textNama = it },
                singleLine = true,
                label = { Text("Nama Lengkap") },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(250.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(20.dp)
                    .width(250.dp),
                thickness = 1.dp,
                color = Color.Red
            )

            OutlinedTextField(
                value = textAlamat,
                onValueChange = { textAlamat = it },
                singleLine = true,
                label = { Text("Alamat") },
                modifier = Modifier.width(250.dp)
            )

            Row {
                pilihanJK.forEach { item ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = textGender == item,
                                onClick = { textGender = item }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = textGender == item,
                            onClick = { textGender = item }
                        )
                        Text(item)
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(20.dp)
                    .width(250.dp),
                thickness = 1.dp,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { val data = mutableListOf(textNama, textAlamat, textGender)
                    OnSubmitButtonClick(data)
                },
                enabled = textNama.isNotEmpty() && textAlamat.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}
