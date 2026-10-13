
package com.example.androidz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DiemSinhVien(
    val name: String,
    val score: Double?
)

@Composable
fun Bai3() {
    val danhSach = listOf(
        DiemSinhVien("Nguyễn Văn An", 9.0),
        DiemSinhVien("Trần Thị Bình", 7.2),
        DiemSinhVien("Lê Minh Chi", null),
        DiemSinhVien("Phạm Quốc Dũng", 4.5),
        DiemSinhVien("Võ Thanh Em", 5.8)
    )

    val diemDayDu = danhSach.mapNotNull { it.score }
    val diemTB = diemDayDu.average()
    val soGioi = diemDayDu.count { it >= 8 }
    val soThieu = danhSach.count { it.score == null }
    val caoNhat = diemDayDu.maxOrNull() ?: 0.0

    val xanh = Color(0xFF304878)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F2F7))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(xanh)
                .padding(18.dp)
        ) {
            Text(
                "Bảng điểm lớp",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(14.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = xanh
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Điểm trung bình lớp",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Text(
                        "%.2f".format(diemTB),
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )

                    HorizontalDivider(
                        color = Color.LightGray,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Giỏi", color = Color.LightGray, fontSize = 11.sp)
                            Text("$soGioi", color = Color.White)
                        }

                        Column {
                            Text("Chưa có điểm", color = Color.LightGray, fontSize = 11.sp)
                            Text("$soThieu", color = Color.White)
                        }

                        Column {
                            Text("Cao nhất", color = Color.LightGray, fontSize = 11.sp)
                            Text("%.1f".format(caoNhat), color = Color.White)
                        }
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                "DANH SÁCH · ${danhSach.size} SINH VIÊN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(Modifier.height(8.dp))

            for ((name, score) in danhSach) {
                val xepLoai = if (score == null) {
                    "Chưa có điểm"
                } else {
                    xepLoai(score)
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )

                            Text(
                                xepLoai,
                                color = when (xepLoai) {
                                    "Giỏi" -> Color(0xFF54815C)
                                    "Khá" -> Color(0xFF526E9D)
                                    "Yếu" -> Color(0xFFB45E5E)
                                    "Trung bình" -> Color(0xFF9C8249)
                                    else -> Color.Gray
                                },
                                fontSize = 12.sp
                            )
                        }

                        Text(
                            score?.let { "%.1f".format(it) } ?: "—",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
