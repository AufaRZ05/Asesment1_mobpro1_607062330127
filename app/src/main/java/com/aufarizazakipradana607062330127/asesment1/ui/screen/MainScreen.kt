package com.aufarizazakipradana607062330127.asesment1.ui.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aufarizazakipradana607062330127.asesment1.R
import com.aufarizazakipradana607062330127.asesment1.ui.theme.Asesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var namaMerek by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var stok by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var pilihanKategori by remember { mutableStateOf("Pilih kategori") }
    var pesanErrorNama by remember { mutableStateOf("") }
    var pesanErrorHarga by remember { mutableStateOf("") }
    var pesanErrorStok by remember { mutableStateOf("") }
    var simpanData by remember { mutableStateOf<List<String>>(emptyList()) }
    val kategori = listOf(R.string.lampu, R.string.kipas_angin, R.string.speaker)

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
                pesanErrorNama = if (namaMerek.isBlank()) "Nama merek tidak boleh kosong" else ""
                pesanErrorHarga = if (harga.isBlank()) "Harga tidak boleh kosong" else ""
                pesanErrorStok = if (stok.isBlank()) "Stok tidak boleh kosong" else ""

                if (namaMerek.isNotBlank() && harga.isNotBlank() && stok.isNotBlank() && pilihanKategori != "Pilih kategori") {
                    simpanData = simpanData + listOf(
                        "Nama merek: $namaMerek\nHarga: $harga\nStok: $stok\nKategori: $pilihanKategori"
                    )
                    namaMerek = ""
                    harga = ""
                    stok = ""
                    pilihanKategori = "Pilih kategori"

                    pesanErrorNama = ""
                    pesanErrorHarga = ""
                    pesanErrorStok = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
            contentPadding =  PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(stringResource(R.string.simpan))
        }

        // Menampilkan hasil simpan
        if (simpanData.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Data yang disimpan:", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            simpanData.forEach { data ->
                Text(text = data, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Asesment1Theme {
        MainScreen()
    }
}