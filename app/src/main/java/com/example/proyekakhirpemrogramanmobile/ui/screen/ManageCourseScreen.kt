package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.archive.ScheduleStatus
import com.example.proyekakhirpemrogramanmobile.data.source.ClassStatus
import com.example.proyekakhirpemrogramanmobile.data.source.CourseInfo
import com.example.proyekakhirpemrogramanmobile.data.source.CourseModul
import com.example.proyekakhirpemrogramanmobile.data.source.CourseTask
import com.example.proyekakhirpemrogramanmobile.data.source.listClassStatus
import com.example.proyekakhirpemrogramanmobile.data.source.listInfoDetails
import com.example.proyekakhirpemrogramanmobile.data.source.listModulDetails
import com.example.proyekakhirpemrogramanmobile.data.source.listTaskDetails
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.Poppins

@Preview
@Composable
fun ManageCourseScreen() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = R.string.sidebar_home
    val scrollState = rememberScrollState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideBar(
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                selectedMenu = selectedMenu
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState
                )
            }
        ) { contentPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                //isi disini
                Title(title = "Kriptografi")
                ManageCourseStatus(modifier = Modifier.height(400.dp))
                ManageCourseTasks(modifier = Modifier.height(400.dp))
                ManageCourseModul(modifier = Modifier.height(400.dp))
                ManageCourseInfo(modifier = Modifier.height(400.dp))

            }
        }
    }
}
//Status Pertemuan
@Composable
fun ManageCourseStatus(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = "Pertemuan",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        if (listClassStatus.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )
                    //                Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Tidak ada jadwal Kelas",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(listClassStatus) { item ->
                    ManageCourseStatusItem(item)
                }
            }
        }
    }
}

@Composable
fun ManageCourseStatusItem(item : ClassStatus) {
    var editDialog by remember { mutableStateOf(false) }
    var cancelDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = when (item.status) {
                    ScheduleStatus.PRESENT -> colorResource(R.color.light_green)
                    ScheduleStatus.UNKNOWN -> colorResource(R.color.light_yellow)
                    ScheduleStatus.CANCELLED -> colorResource(R.color.light_red)
                },
                shape = RoundedCornerShape(16.dp)
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = item.meet,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(

                ){
                    IconButton(
                        onClick = {editDialog = true},
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_pencil),
                            contentDescription = "Edit Class Date",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                        )
                    }
                    if(editDialog){
                        AlertDialog(
                            onDismissRequest = { editDialog = false },
                            containerColor = colorResource(R.color.very_dark_blue),
                            title = {
                                    Text(
                                        text ="Ubah Tanggal Pertemuan",
                                        color = colorResource(R.color.white)
                                    )
                                    },
                            text = {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Ubah Pertemuan pada tanggal\n${item.time} ?",
                                        fontSize = 14.sp,
                                        color = colorResource(R.color.white),
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Start
                                    )
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        editDialog = false
//                                        item.status = ScheduleStatus.UNKNOWN //gatau ini aku cara ngubah warnanya kalau di edit
                                              },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.white),
                                        contentColor = colorResource(R.color.very_dark_blue),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor =colorResource(R.color.white)
                                    )
                                ) {
                                    Text(
                                        text = "Ubah",
                                        )
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { editDialog = false },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.very_dark_blue),
                                        contentColor = colorResource(R.color.white),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor =colorResource(R.color.white)
                                    )
                                ) {
                                    Text("Batal")
                                }
                            }
                        )
                    }
                    IconButton(
                        onClick = {cancelDialog = true}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.close_icon),
                            contentDescription = "Cancel Class Status",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                        )
                    }
                    if(cancelDialog){
                        AlertDialog(
                            onDismissRequest = { cancelDialog = false },
                            containerColor = colorResource(R.color.very_dark_blue),
                            title = {
                                Text(
                                    text ="Batalkan Pertemuan",
                                    color = colorResource(R.color.white)
                                )
                            },
                            text = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Batalkan Pertemuan pada tanggal\n${item.time} ?",
                                    fontSize = 14.sp,
                                    color = colorResource(R.color.white),
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start
                                )
                            },
                            confirmButton = {
                                Button(
                                    onClick = {cancelDialog=false},
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.white),
                                        contentColor = colorResource(R.color.very_dark_blue),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor =colorResource(R.color.white)
                                    )
                                ) {
                                    Text(
                                        text = "Konfirmasi",
                                    )
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { cancelDialog = false },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.very_dark_blue),
                                        contentColor = colorResource(R.color.white),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor =colorResource(R.color.white)
                                    )
                                ) {
                                    Text("Batal")
                                }
                            }
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Text(
                    text = when (item.status){
                        ScheduleStatus.PRESENT -> "Selesai"
                        ScheduleStatus.UNKNOWN -> "Diganti"
                        ScheduleStatus.CANCELLED -> "Batal"
                    }
                )
                Text(
                    text = item.time,
                    fontSize = 14.sp
                )
            }
        }
    }
}
//TUGAS
@Composable
fun ManageCourseTasks(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = "Tugas",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "+ Tambahkan Tugas"
            )
        }
        HorizontalDivider(
            color = colorResource(R.color.light_gray),
            thickness = 4.dp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (listTaskDetails.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Tidak ada Tugas Saat Ini",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } else{
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(listTaskDetails) { item ->
                    ManageCourseTasksItem(item)
                }
            }

        }
    }
}

@Composable
fun ManageCourseTasksItem(item: CourseTask) {
    var editDialog by remember { mutableStateOf(false) }
    var removeDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                ){
                    if (item.taskType){
                        Image(
                            painter = painterResource(R.drawable.tugas_pribadi),
                            contentDescription = null
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.tugas_kelompok),
                            contentDescription = null
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    if (item.status){
                        Text(
                            text = "Selesai"
                        )
                    } else{
                        Text(
                            text = "Berjalan"
                        )
                    }
                    Text (
                        text = item.time
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(
                    onClick = {editDialog = true},
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_pencil),
                        contentDescription = "Edit Class Date",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .size(15.dp)
                    )
                }
                if(editDialog){
                    AlertDialog(
                        onDismissRequest = { editDialog = false },
                        containerColor = colorResource(R.color.very_dark_blue),
                        title = {
                            Text(
                                text ="Ubah Informasi Tugas",
                                color = colorResource(R.color.white)
                            )
                        },
                        text = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Ubah Informasi Tugas\n${item.name} ?",
                                fontSize = 14.sp,
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    editDialog = false
//                                        item.status = ScheduleStatus.UNKNOWN //gatau ini aku cara ngubah warnanya kalau di edit
                                },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.white),
                                    contentColor = colorResource(R.color.very_dark_blue),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor =colorResource(R.color.white)
                                )
                            ) {
                                Text(
                                    text = "Ubah",
                                )
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { editDialog = false },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.very_dark_blue),
                                    contentColor = colorResource(R.color.white),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor =colorResource(R.color.white)
                                )
                            ) {
                                Text("Batal")
                            }
                        }
                    )
                }
                IconButton(
                    onClick = {removeDialog = true}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.close_icon),
                        contentDescription = "Cancel Class Status",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .size(15.dp)
                    )
                }
                if(removeDialog){
                    AlertDialog(
                        onDismissRequest = { removeDialog = false },
                        containerColor = colorResource(R.color.very_dark_blue),
                        title = {
                            Text(
                                text ="Hapus Tugas",
                                color = colorResource(R.color.white)
                            )
                        },
                        text = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Hapus Tugas\n${item.name} ?",
                                fontSize = 14.sp,
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {removeDialog=false},
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.white),
                                    contentColor = colorResource(R.color.very_dark_blue),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor =colorResource(R.color.white)
                                )
                            ) {
                                Text(
                                    text = "Konfirmasi",
                                )
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { removeDialog = false },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.very_dark_blue),
                                    contentColor = colorResource(R.color.white),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor =colorResource(R.color.white)
                                )
                            ) {
                                Text("Batal")
                            }
                        }
                    )
                }
            }
        }
    }
}
//MODUL
@Composable
fun ManageCourseModul(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = "Modul",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "+ Tambahkan Modul"
            )
        }
        HorizontalDivider(
            color = colorResource(R.color.light_gray),
            thickness = 4.dp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (listModulDetails.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Tidak ada Tugas Saat Ini",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } else{
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(listModulDetails) { item ->
                    ManageCourseModulsItem(item)
                }
            }
        }
    }
}

@Composable
fun ManageCourseModulsItem(item: CourseModul) {
    var removeDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white)
            )
            IconButton(
                onClick = {removeDialog = true}
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Cancel Class Status",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .size(15.dp)
                )
            }
            if(removeDialog){
                AlertDialog(
                    onDismissRequest = { removeDialog = false },
                    containerColor = colorResource(R.color.very_dark_blue),
                    title = {
                        Text(
                            text ="Hapus Modul?",
                            color = colorResource(R.color.white)
                        )
                    },
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Hapus Modul : \n${item.name}",
                            fontSize = 14.sp,
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.very_dark_blue),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor =colorResource(R.color.white)
                            )
                        ) {
                            Text(
                                text = "Konfirmasi",
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.very_dark_blue),
                                contentColor = colorResource(R.color.white),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor =colorResource(R.color.white)
                            )
                        ) {
                            Text("Batal")
                        }
                    }
                )
            }
        }
    }
}
//INFO
@Composable
fun ManageCourseInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = "Informasi",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "+ Tambahkan Info"
            )
        }
        HorizontalDivider(
            color = colorResource(R.color.light_gray),
            thickness = 4.dp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (listModulDetails.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Tidak ada Tugas Saat Ini",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } else{
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(listInfoDetails) { item ->
                    ManageCourseInfosItem(item)
                }
            }
        }
    }
}

@Composable
fun ManageCourseInfosItem(item : CourseInfo) {
    var removeDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.weight(1f),
                text = item.informasi,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white)
            )
            IconButton(
                onClick = {removeDialog = true}
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Cancel Class Status",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .size(15.dp)
                )
            }
            if(removeDialog){
                AlertDialog(
                    onDismissRequest = { removeDialog = false },
                    containerColor = colorResource(R.color.very_dark_blue),
                    title = {
                        Text(
                            text ="Hapus Informasi?",
                            color = colorResource(R.color.white)
                        )
                    },
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Hapus informasi : \n${item.informasi}",
                            fontSize = 14.sp,
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.very_dark_blue),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor =colorResource(R.color.white)
                            )
                        ) {
                            Text(
                                text = "Konfirmasi",
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.very_dark_blue),
                                contentColor = colorResource(R.color.white),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor =colorResource(R.color.white)
                            )
                        ) {
                            Text("Batal")
                        }
                    }
                )
            }
        }
    }
}
