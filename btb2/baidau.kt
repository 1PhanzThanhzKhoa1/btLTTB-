
package com.example.androidz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaiDau() {
    var so1 by remember { mutableStateOf("") }
    var so2 by remember { mutableStateOf("") }
    var ketQua by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(20.dp)
    ) {
        Spacer(Modifier.height(100.dp))

        Text(
            "Thực hành 03",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 14.sp
        )

        Spacer(Modifier.height(58.dp))

        OutlinedTextField(
            so1, { so1 = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(Modifier.height(18.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val phepTinh = listOf("+", "-", "*", "/")
            val mau = listOf(
                Color.Red,
                Color(0xFFE2B34F),
                Color(0xFF6036D9),
                Color.Black
            )

            for (i in phepTinh.indices) {
                Button(
                    onClick = {
                        val a = so1.toDoubleOrNull()
                        val b = so2.toDoubleOrNull()

                        ketQua = if (a == null || b == null) {
                            "Vui lòng nhập hai số"
                        } else {
                            when (phepTinh[i]) {
                                "+" -> (a + b).toString()
                                "-" -> (a - b).toString()
                                "*" -> (a * b).toString()
                                "/" -> if (b == 0.0)
                                    "Không thể chia cho 0"
                                else (a / b).toString()
                                else -> ""
                            }
                        }
                    },
                    modifier = Modifier.size(44.dp),
                    shape = RoundedCornerShape(7.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mau[i]
                    )
                ) {
                    Text(phepTinh[i])
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        OutlinedTextField(
            so2, { so2 = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )

        Spacer(Modifier.height(10.dp))

        Text("Kết quả: $ketQua", fontSize = 14.sp)
    }
}
