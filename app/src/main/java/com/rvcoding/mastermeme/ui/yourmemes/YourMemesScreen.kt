package com.rvcoding.mastermeme.ui.yourmemes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rvcoding.mastermeme.R
import com.rvcoding.mastermeme.domain.Meme
import com.rvcoding.mastermeme.domain.navigation.Actions
import com.rvcoding.mastermeme.ui.component.TopBar
import com.rvcoding.mastermeme.ui.theme.ButtonPrimary
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
        modifier = Modifier.size(65.dp),
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
    TODO("Not yet implemented")
}

@Composable
fun EmptyState() {
    Image(
        modifier = Modifier.size(200.dp),
        imageVector = ImageVector.vectorResource(R.drawable.empty_list),
        contentDescription = "Empty list"
    )
}

@Preview(showBackground = true)
@Composable
fun YourMemesScreenPreview() {
    YourMemesScreen(
        state = emptyList(),
        onAction = { }
    )
}