package com.lee_idle.layoutmodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lee_idle.layoutmodifier.ui.theme.LayoutModifierTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutModifierTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    /*
    Box(modifier = Modifier.size(120.dp, 80.dp)){
        ColorBox(modifier = Modifier
            .exampleLayout(90, 50)
            .background(Color.Blue))
    }
     */
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(120.dp, 80.dp)) {
        Column{
            ColorBox(Modifier.exampleLayout(0f).background(Color.Blue))
            ColorBox(Modifier.exampleLayout(0.25f).background(Color.Green))
            ColorBox(Modifier.exampleLayout(0.5f).background(Color.Yellow))
            ColorBox(Modifier.exampleLayout(0.25f).background(Color.Red))
            ColorBox(Modifier.exampleLayout(0f).background(Color.Magenta))
        }
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier))
}

/*
// measurable: 자식 요소가 배치될 정보
// constraints: 자식이 이용할 수 있는 최대/최소 폭과 높이
fun Modifier.exampleLayout(
    x: Int,
    y: Int,
) = layout { measurable, constraints ->
    // 자식의 측정값(크기, 위치)을 알아낸다.
    val placeable = measurable.measure(constraints)


    /// 커스텀 레이아웃에서는 모디파이어가 호출될 때마다 자식을 측정하는 규칙이 적용된다.
    /// 이를 '싱글 패스 측정'이라 부르며, 사용자 인터페이스 트리 계층을 신속하고 효율적으로
    /// 렌더링 하기 위해 곡 필요하다.
    /// placeable 값으로부터 높이와 폭 전달
    layout(placeable.width, placeable.height){
        // 자식 요소의 위치 지정
        placeable.placeRelative(x, y)
    }
}
*/

// 정렬선 이동하기(자식 요소의 시작 위치 정보(x, y)를 이동)
fun Modifier.exampleLayout(
    fraction:Float
) = layout{measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // 자신의 폭 비율을 세로 정렬 선 위치로 나타낸다
    // 음수로 바꿨으므로 정렬 선이 오른쪽으로 이동한다
    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height){
        placeable.placeRelative(x = x, y = 0)
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    LayoutModifierTheme {
        MainScreen()
    }
}