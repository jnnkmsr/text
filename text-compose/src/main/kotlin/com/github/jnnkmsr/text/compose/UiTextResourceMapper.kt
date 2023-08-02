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

import com.github.jnnkmsr.text.IdTextResource
import com.github.jnnkmsr.text.StringTextResource
import com.github.jnnkmsr.text.TextResource

/**
 * Maps `this` [UiTextResource] instance to a matching [TextResource].
 *
 * @see toUiTextResource
 */
public fun UiTextResource.toTextResource(): TextResource = when (this) {
    is UiStringTextResource -> StringTextResource(value)
    is UiIdTextResource -> IdTextResource(value)
}

/**
 * Maps `this` [TextResource] instance to a matching [UiTextResource].
 *
 * @see toUiTextResource
 */
public fun TextResource.toUiTextResource(): UiTextResource = when (this) {
    is StringTextResource -> UiStringTextResource(value)
    is IdTextResource -> UiIdTextResource(value)
}
