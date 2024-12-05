package com.rvcoding.mastermeme.ui.yourmemes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rvcoding.mastermeme.R
import com.rvcoding.mastermeme.domain.Meme
import com.rvcoding.mastermeme.domain.navigation.Actions
import com.rvcoding.mastermeme.ui.component.TopBar
import com.rvcoding.mastermeme.ui.theme.BackgroundContainer
import com.rvcoding.mastermeme.ui.theme.ButtonPrimary
import com.rvcoding.mastermeme.ui.theme.Drawables
import com.rvcoding.mastermeme.ui.theme.Secondary
import com.rvcoding.mastermeme.ui.theme.Tertiary
import org.koin.androidx.compose.koinViewModel


@Composable
fun YourMemesScreenRoot(
    vm: YourMemesViewModel = koinViewModel(),
) {
    val state by vm.memes.collectAsStateWithLifecycle()

    YourMemesScreen(
        state = state,
        onAction = vm::onAction
    )
}

@Composable
fun YourMemesScreen(
    state: List<Meme>,
    onAction: (Actions.YourMemes) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(TopBar.Title(R.string.your_memes_title))
            if (state.isEmpty()) {
                EmptyState()
            } else {
                MemeList(memes = state)
            }
        }
        FAButton(onAction)
    }
}

@Composable
fun FAButton(
    onAction: (Actions.YourMemes) -> Unit
) {
    FilledIconButton(
        onClick = { onAction.invoke(Actions.YourMemes.OpenChoseTemplate) },
        modifier = Modifier
            .padding(end = 16.dp, bottom = 32.dp)
            .size(65.dp),
        shape = RoundedCornerShape(12.dp),
        colors = IconButtonColors(
            containerColor = Secondary,
            contentColor = Secondary,
            disabledContainerColor = Tertiary,
            disabledContentColor = Tertiary
        )
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Filled.Add,
            contentDescription = "Add new meme",
            tint = ButtonPrimary
        )
    }
}

@Composable
fun MemeList(memes: List<Meme>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),

            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            )
        ) {
            items(
                count = memes.size
            ) { index ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(176.dp),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(memes[index].id)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Meme ${memes[index].title}",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                modifier = Modifier.size(200.dp),
                imageVector = ImageVector.vectorResource(R.drawable.empty_list),
                contentDescription = "Empty list"
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.your_memes_empty_list),
                color = Tertiary,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YourMemesScreenEmptyPreview() {
    YourMemesScreen(
        state = emptyList(),
        onAction = { }
    )
}

@Preview(showBackground = true)
@Composable
fun YourMemesScreenNonEmptyPreview() {
    YourMemesScreen(
        state = Drawables.memeMap.entries.map { Meme(it.key, it.value) },
        onAction = { }
    )
}