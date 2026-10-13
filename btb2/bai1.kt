
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

data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)

@Composable
fun Bai1() {
    val danhSach = listOf(
        Student("Nguyễn Văn An", "an@sv.edu.vn", "0901 234 567"),
        Student("Trần Thị Bình", null, "0912 888 999"),
        Student("Lê Minh Chi", "chi@sv.edu.vn", null),
        Student("Phạm Quốc Dũng", null, null),
        Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222")
    )

    val xanh = Color(0xFF304878)
    val nen = Color(0xFFF3F2F7)
    val thieuEmail = danhSach.count { it.email == null }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(nen)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(xanh)
                .padding(20.dp)
        ) {
            Text(
                "Danh sách sinh viên",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        ) {
            for ((name, email, phone) in danhSach) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Text(
                            name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Text(
                            "Email: ${email ?: "Chưa cập nhật"}",
                            color = if (email == null) Color(0xFF956D6D)
                            else Color.DarkGray,
                            fontSize = 12.sp
                        )

                        Text(
                            "SĐT: ${phone ?: "Chưa cập nhật"}",
                            color = if (phone == null) Color(0xFF956D6D)
                            else Color.DarkGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFE2EAF5),
                        RoundedCornerShape(10.dp)
                    )
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text("Tổng: ", fontSize = 12.sp, color = xanh)
                    Text(
                        "${danhSach.size} sinh viên",
                        color = xanh,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                Row {
                    Text("Thiếu email: ", fontSize = 12.sp, color = xanh)
                    Text(
                        "$thieuEmail",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )




                }
            }
        }
    }
}
