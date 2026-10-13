package com.example.androidz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun xepLoai(d: Double) = if (d !in 0.0..10.0) "Không hợp lệ"
else if (d >= 8) "Giỏi"
else if (d >= 6.5) "Khá"
else if (d >= 5) "Trung bình"
else "Yếu"

// Badge dùng chung — fullWidth cho card trên, pill nhỏ cho list dưới
@Composable
fun Badge(loai: String, fullWidth: Boolean = false) {
    val bg = if (loai == "Giỏi") Color(0xFFE8F5E9)
    else if (loai == "Khá") Color(0xFFE3F2FD)
    else if (loai == "Trung bình") Color(0xFFFFF3E0)
    else if (loai == "Yếu") Color(0xFFFFEBEE)
    else Color(0xFFF5F5F5)

    val fg = if (loai == "Giỏi") Color(0xFF54815C)
    else if (loai == "Khá") Color(0xFF526E9D)
    else if (loai == "Trung bình") Color(0xFF9C8249)
    else if (loai == "Yếu") Color(0xFFB45E5E)
    else Color.Gray

    Box(
        modifier = Modifier
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier)
            .background(bg, RoundedCornerShape(if (fullWidth) 8.dp else 12.dp))
            .padding(
                horizontal = if (fullWidth) 0.dp else 12.dp,
                vertical = if (fullWidth) 10.dp else 4.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            loai,
            color = fg,
            fontWeight = FontWeight.Bold,
            fontSize = if (fullWidth) 15.sp else 11.sp
        )
    }
}

@Composable
fun Bai2() {
    val diem = 8.5
    val ds = listOf(9.0, 7.0, 5.5, 3.0, 11.0)
    val xanh = Color(0xFF304878)

    Column(Modifier.fillMaxSize().background(Color(0xFFF3F2F7))) {
        Row(Modifier.fillMaxWidth().background(xanh).padding(18.dp)) {
            Text("Xếp loại học lực", color = Color.White, fontSize = 20.sp)
        }

        Column(Modifier.verticalScroll(rememberScrollState()).padding(14.dp)) {
            // Card điểm trung bình
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Điểm trung bình", color = Color.Gray, fontSize = 12.sp)
                    Text("$diem", fontSize = 44.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Badge(xepLoai(diem), fullWidth = true)  // ← full ngang
                }
            }

            Spacer(Modifier.height(18.dp))
            Text("KIỂM TRA THÊM", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Spacer(Modifier.height(8.dp))

            // Card danh sách
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(Modifier.padding(horizontal = 14.dp)) {
                    ds.forEachIndexed { i, d ->
                        Row(
                            Modifier.fillMaxWidth().padding(vertical = 14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Điểm $d", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Badge(xepLoai(d))  // pill nhỏ như cũ
                        }
                        if (i < ds.size - 1) HorizontalDivider(color = Color(0xFFEEEEEE))
                    }
                }
            }
        }
    }
}