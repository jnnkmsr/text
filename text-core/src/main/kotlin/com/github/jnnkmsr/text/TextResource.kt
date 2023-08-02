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

package com.github.jnnkmsr.text

import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope
import androidx.annotation.StringRes

/**
 * Creates and returns a [TextResource] instance wrapping the given [text].
 *
 * @param text The string content.
 */
public fun TextResource(text: String): TextResource = StringTextResource(text)

/**
 * Creates and returns a [TextResource] instance wrapping a string resource
 * with the given reference [id].
 *
 * @param id The reference to the string resource.
 */
public fun TextResource(id: Int): TextResource = IdTextResource(id)

/**
 * Wrapper for text to be shown in the UI, allowing to pass text either as a
 * string or as a string resource ID.
 */
public sealed interface TextResource

/** [TextResource] wrapper for string text. */
@RestrictTo(Scope.LIBRARY_GROUP)
@JvmInline
public value class StringTextResource(
    public val value: String,
) : TextResource

/** [TextResource] wrapper for string resources. */
@RestrictTo(Scope.LIBRARY_GROUP)
@JvmInline
public value class IdTextResource(
    @StringRes public val value: Int,
) : TextResource
