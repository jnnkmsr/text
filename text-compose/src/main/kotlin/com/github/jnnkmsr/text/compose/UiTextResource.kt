/*
 * Copyright 2023 Jannik Möser
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jnnkmsr.text.compose

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize
import com.github.jnnkmsr.text.TextResource

/**
 * Creates and returns a [UiTextResource] instance wrapping the given [text].
 *
 * @param text The string content.
 */
public fun UiTextResource(text: String): UiTextResource = UiStringTextResource(text)

/**
 * Creates and returns a [UiTextResource] instance wrapping a string resource
 * with the given reference [id].
 *
 * @param id The reference to the string resource.
 */
public fun UiTextResource(@StringRes id: Int): UiTextResource = UiIdTextResource(id)

/**
 * Wrapper for text to be shown in the UI, allowing to pass text either as a
 * string or as a string resource ID.
 *
 * This is a modification of [TextResource] tailored for Compose. Use
 * [toUiTextResource] and [toTextResource] to from/to [TextResource] instances.
 *
 * @see TextResource
 * @see toUiTextResource
 * @see toTextResource
 */
@Immutable
public sealed interface UiTextResource : Parcelable {

    /**
     * Converts `this` [UiTextResource] into a [String] that can be shown in the
     * UI.
     *
     * @param args Format arguments for string resources.
     */
    @Composable
    @ReadOnlyComposable
    public operator fun invoke(vararg args: Any): String

    /**
     * Converts `this` [UiTextResource] into a [String] that can be shown in the
     * UI.
     *
     * @param context [Context] providing access to the string resources.
     * @param args Format arguments for string resources.
     */
    public operator fun invoke(context: Context, vararg args: Any): String
}

/** [UiTextResource] wrapper for string text. */
@Parcelize
@JvmInline
internal value class UiStringTextResource(
    internal val value: String,
) : UiTextResource {

    @Composable
    @ReadOnlyComposable
    override fun invoke(vararg args: Any): String = value

    override fun invoke(context: Context, vararg args: Any): String = value
}

/** [UiTextResource] wrapper for string resources. */
@Parcelize
@JvmInline
internal value class UiIdTextResource internal constructor(
    @StringRes internal val value: Int,
) : UiTextResource {

    @Composable
    @ReadOnlyComposable
    override fun invoke(vararg args: Any): String = stringResource(value, *args)

    override fun invoke(context: Context, vararg args: Any): String =
        context.resources.getString(value, *args)
}
