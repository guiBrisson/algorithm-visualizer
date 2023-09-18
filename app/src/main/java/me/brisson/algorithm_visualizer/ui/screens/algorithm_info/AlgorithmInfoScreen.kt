package me.brisson.algorithm_visualizer.ui.screens.algorithm_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.CodeBlockStyle
import com.halilibo.richtext.ui.RichTextStyle
import com.halilibo.richtext.ui.TableStyle
import com.halilibo.richtext.ui.material3.Material3RichText
import com.halilibo.richtext.ui.string.RichTextStringStyle
import me.brisson.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme

@Composable
fun AlgorithmInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: AlgorithmInfoViewModel = viewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.updateMdUiState(context)
    }

    AlgorithmInfoScreen(modifier = modifier, uiState = uiState, onBack = onBack)
}

@Composable
internal fun AlgorithmInfoScreen(
    modifier: Modifier = Modifier,
    uiState: AlgorithmInfoUiState,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(modifier = Modifier.offset(x = (-6).dp), onClick = onBack) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        uiState.markdownText?.let { markdownText ->
            val richTextStyle = RichTextStyle(
                stringStyle = RichTextStringStyle(
                    linkStyle = SpanStyle(
                        color = Color(0xff5598C3),
                        textDecoration = TextDecoration.Underline,
                    ),
                    codeStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        background = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                        fontSize = 16.sp,
                        letterSpacing = 1.2.sp
                    ),
                ),
                codeBlockStyle = CodeBlockStyle(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
                ),
                paragraphSpacing = 16.sp,
                tableStyle = TableStyle(
                    borderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.69f),
                ),
            )

            LazyColumn {
                item {
                    Material3RichText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .background(MaterialTheme.colorScheme.background),
                        style = richTextStyle,
                    ) {
                        Markdown(content = markdownText)
                        Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    }

                }
            }
        }

    }
}

@Preview
@Composable
private fun PreviewAlgorithmInfoScreen() {
    AlgorithmVisualizerTheme {
        val uiState = AlgorithmInfoUiState()

        AlgorithmInfoScreen(uiState = uiState, onBack = { })
    }
}