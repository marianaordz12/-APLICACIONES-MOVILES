
package com.example.examenmar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// IMPORTACIONES GOOGLE MAPS
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PantallaPerfil()
            }
        }
    }
}

// PANTALLA PRINCIPAL
@Composable
fun PantallaPerfil() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9FD))
            .verticalScroll(rememberScrollState())
    ) {

        // 1. PORTADA
        Portada()

        // 2. FOTOGRAFÍA DE PERFIL
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .offset(y = (-45).dp)
        ) {
            FotoPerfil()
        }

        // 3. INFORMACIÓN PERSONAL
        InformacionPerfil()

        Spacer(modifier = Modifier.height(20.dp))

        // 4. DESCRIPCIÓN ABOUT
        SeccionAbout()

        // 5. ETIQUETAS HORIZONTALES
        SeccionEtiquetas()

        Spacer(modifier = Modifier.height(25.dp))

        // 6. MAPA DE UBICACIÓN
        MapaUbicacion()

        Spacer(modifier = Modifier.height(25.dp))
    }
}

// COMPONENTE PORTADA
@Composable
fun Portada() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFF6424A5)),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "PORTADA",
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// COMPONENTE FOTO DE PERFIL
@Composable
fun FotoPerfil() {

    Image(
        painter = painterResource(
            id = R.drawable.perfil
        ),
        contentDescription = "Fotografía de perfil",
        modifier = Modifier
            .size(110.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

// COMPONENTE INFORMACIÓN PERSONAL
@Composable
fun InformacionPerfil() {

    Column(
        modifier = Modifier.padding(
            start = 20.dp,
            top = 10.dp
        )
    ) {

        Text(
            text = "Mariana Ordaz Cano",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF202020)
        )

        Text(
            text = "moc2531126199@micorreo.upp.edu.mx",
            fontSize = 15.sp,
            color = Color.Gray
        )
    }
}

// COMPONENTE ABOUT
@Composable
fun SeccionAbout() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = "ABOUT",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6424A5)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Soy estudiante de Ingeniería en Tecnologías " +
                    "de la Información e Innovación Digital " +
                    "de la Universidad Politécnica de Pachuca. " +
                    "Me interesa el desarrollo de aplicaciones, " +
                    "la programación y la tecnología.",
            fontSize = 15.sp,
            color = Color.DarkGray,
            lineHeight = 23.sp
        )
    }
}

// COMPONENTE ETIQUETAS HORIZONTALES
@Composable
fun SeccionEtiquetas() {

    val etiquetas = listOf(
        "Kotlin",
        "Java",
        "Python",
        "Android",
        "HTML",
        "CSS",
        "JavaScript",
        "MySQL",
        "Git",
        "Firebase"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
    ) {

        Text(
            text = "Lenguajes de programación",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6424A5),
            modifier = Modifier.padding(
                start = 20.dp,
                bottom = 15.dp
            )
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {

            items(etiquetas) { etiqueta ->

                Text(
                    text = etiqueta,
                    modifier = Modifier
                        .background(
                            Color(0xFFEDE3F8),
                            CircleShape
                        )
                        .padding(
                            horizontal = 18.dp,
                            vertical = 12.dp
                        ),
                    color = Color(0xFF6424A5),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// COMPONENTE GOOGLE MAPS
@Composable
fun MapaUbicacion() {

    // Coordenadas de ubicación de la UPP
    val ubicacionUPP = LatLng(20.1017, -98.7145)
    val marcadorUPP = rememberMarkerState(
        position = ubicacionUPP
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            ubicacionUPP,
            16f
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = "MI UBICACIÓN",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6424A5)
        )

        Spacer(modifier = Modifier.height(12.dp))

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(15.dp)),
            cameraPositionState = cameraPositionState
        ) {

            Marker(
                state = marcadorUPP,
                title = "Universidad Politécnica de Pachuca",
                snippet = "UPP - Hidalgo"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Universidad Politécnica de Pachuca, Hidalgo.",
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

// VISTA PREVIA
@Preview(showBackground = true)
@Composable
fun PerfilPreview() {

    MaterialTheme {
        PantallaPerfil()
    }
}