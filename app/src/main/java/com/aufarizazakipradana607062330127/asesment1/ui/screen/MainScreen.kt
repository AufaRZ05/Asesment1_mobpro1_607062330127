package com.aufarizazakipradana607062330127.asesment1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aufarizazakipradana607062330127.asesment1.R
import com.aufarizazakipradana607062330127.asesment1.navigation.Screen
import com.aufarizazakipradana607062330127.asesment1.ui.theme.Asesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = stringResource(R.string.about),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.about)) },
                            onClick = {
                                expanded = false
                                navController.navigate(Screen.About.route)
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var namaMerek by rememberSaveable { mutableStateOf("") }
    var harga by rememberSaveable { mutableStateOf("") }
    var stok by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var pilihanKategori by rememberSaveable { mutableStateOf("Pilih kategori") }
    var pesanErrorNama by rememberSaveable { mutableStateOf("") }
    var pesanErrorHarga by rememberSaveable { mutableStateOf("") }
    var pesanErrorStok by rememberSaveable { mutableStateOf("") }
    var simpanData by rememberSaveable { mutableStateOf<String?>(null) }

    val kategori = listOf(R.string.lampu, R.string.kipas_angin, R.string.speaker)
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.manajemen_barang_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        // Form Nama Merek
        OutlinedTextField(
            value = namaMerek,
            onValueChange = {
                namaMerek = it
                pesanErrorNama = if (it.isBlank()) "Nama merek tidak boleh kosong" else ""
            },
            label = { Text(text = stringResource(R.string.nama_merek)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (pesanErrorNama.isNotEmpty()) {
            Text(text = pesanErrorNama, color = Color.Red)
        }

        // Form Harga
        OutlinedTextField(
            value = harga,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    harga = it
                }
                pesanErrorHarga = if (harga.isBlank()) "Harga tidak boleh kosong" else ""
            },
            label = { Text(text = stringResource(R.string.harga)) },
            trailingIcon = { Text(text = "Rp") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (pesanErrorHarga.isNotEmpty()) {
            Text(text = pesanErrorHarga, color = Color.Red)
        }

        // Form Stok
        OutlinedTextField(
            value = stok,
            onValueChange = {
                if (it.all { char -> char.isDigit()}) {
                    stok = it
                }
                pesanErrorStok = if (it.isBlank()) "Stok tidak boleh kosong" else ""
            },
            label = { Text(text = stringResource(R.string.stok)) },
            trailingIcon = { Text(text = "Item") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (pesanErrorStok.isNotEmpty()) {
            Text(text = pesanErrorStok, color = Color.Red)
        }

        // Dropdown Menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = pilihanKategori,
                onValueChange = {},
                placeholder = { Text(text = stringResource(R.string.kategori)) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                kategori.forEach { kategoriResId ->
                    val kategoriText = stringResource(kategoriResId)
                    DropdownMenuItem(
                        text = { Text(kategoriText) },
                        onClick = {
                            pilihanKategori = kategoriText
                            expanded = false
                        }
                    )
                }
            }
        }

        // Tombol Simpan
        Button(
            onClick = {
                if (namaMerek.isNotBlank() && harga.isNotBlank() && stok.isNotBlank() && pilihanKategori != "Pilih kategori") {
                    simpanData = context.getString(R.string.bagikan_template, namaMerek, harga, stok, pilihanKategori)
                    namaMerek = ""
                    harga = ""
                    stok = ""
                    pilihanKategori = "Pilih kategori"
                }
            },
            modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
            contentPadding =  PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(stringResource(R.string.simpan))
        }

        // Menampilkan hasil simpan
        simpanData?.let { data ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Data yang disimpan:", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Text(text = data, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }


        // Tombol bagikan
        simpanData?.let { data ->
            Button(
                onClick = {
                    shareData(context, data)
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
        }
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.bagikan)))
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Asesment1Theme {
        MainScreen(rememberNavController())
    }
}